package com.boot.user;

import com.boot.security.AuthorizationService;
import com.boot.security.VerificationTokenService;
import com.boot.security.model.VerificationToken;
import com.boot.security.utility.Credentials;
import com.boot.security.utility.Session;
import com.boot.user.model.User;
import com.boot.utilities.MergeTool;
import com.boot.utilities.email.EmailHtmlSender;
import com.boot.utilities.email.EmailStatus;
import lombok.extern.java.Log;
import lombok.val;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import org.thymeleaf.context.Context;
import static org.springframework.http.ResponseEntity.ok;

@Log
@RestController
@RequestMapping(value = "/api/v1/users") ///api/v1/ required till nginx
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    public AuthorizationService authorizationService;

    @Autowired
    public VerificationTokenService verificationTokenService;

    @Autowired
    public EmailHtmlSender emailHtmlSender;

    @Autowired
    private Environment env;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User>  getById(@PathVariable Integer id){
        return userService.getById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "sign_in", method = RequestMethod.POST)
    public void signIn(@RequestBody Credentials credentials, HttpServletResponse response) {
        val token = authorizationService.authenticate(credentials).
                map(Session::getToken).
                map(UUID::toString);

        if (token.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);

            val sessionCookie = new Cookie(Session.COOKIE_NAME, token.get());
            sessionCookie.setPath("/");
            sessionCookie.setMaxAge((int) Session.VALIDITY_DURATION.getSeconds());

            response.addCookie(sessionCookie);
        } else
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @RequestMapping(value = "sign_out", method = RequestMethod.POST)
    public void signOut(@CookieValue(name = Session.COOKIE_NAME) String sessionToken, HttpServletResponse response) {
        if(authorizationService.invalidateSession(UUID.fromString(sessionToken))) {
            val sessionCookie = new Cookie(Session.COOKIE_NAME, sessionToken);
            sessionCookie.setPath("/");
            sessionCookie.setMaxAge(0);
            response.addCookie(sessionCookie);
        }
    }

    @RequestMapping(value = "session", method = RequestMethod.GET)
    public ResponseEntity<User> getBySessionToken(@CookieValue(Session.COOKIE_NAME) String sessionToken) {
        return authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).
                map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> modify(@RequestBody User user)
            throws InstantiationException, IllegalAccessException {
        if(user.getId() != null) {
            Optional<User> originalUser = userService.getById(user.getId());
            if (originalUser.isPresent()) {
                User finalUser = MergeTool.mergeObjects(user, originalUser.get());
                try {
                    userService.modify(finalUser);
                    return new ResponseEntity<>(HttpStatus.OK); }
                catch (IllegalArgumentException e) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id, HttpServletResponse response) {
        try {
            userService.delete(id);
            val sessionCookie = new Cookie(Session.COOKIE_NAME, "");
            sessionCookie.setMaxAge(0);
            response.addCookie(sessionCookie);
            return new ResponseEntity<>(HttpStatus.OK);}
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "sign_up", method = RequestMethod.POST)
    public ResponseEntity<User> add(@RequestBody User user){

        user.setIsConfirmed(false);
        try {
            user = userService.add(user);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);}


        VerificationToken token = verificationTokenService.create(user);
        Context context = new Context();
        String link = env.getProperty("server.address");
        link += ":" + env.getProperty("server.port");
        link += "/api/v1/users/confirm/" + token.getToken();
        context.setVariable("link", link);

        EmailStatus emailStatus = emailHtmlSender.send(user.getEmail(), "Confirm your booka account", "confirmation_email", context);

        log.info(emailStatus.getStatus());
        if (emailStatus.isError()) {
            log.warning(emailStatus.getErrorMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "hash", method =  RequestMethod.POST)
    public void hashAll() {
        userService.hashAll();
    }

    @RequestMapping(value = "search/{query}", method = RequestMethod.GET)
    public Collection<User> searchQuery(@PathVariable Optional<String> query) {
        return userService.searchQuery(query.orElse(""));
    }

    @RequestMapping(value = "confirm/{token}", method = RequestMethod.GET)
    public ResponseEntity<?> confirmUser(@PathVariable String token) {

        log.info("Request recieved");
        Optional<VerificationToken> verificationToken = verificationTokenService.getByToken(token);
        if (verificationToken.isPresent())
        {

            User user = verificationToken.get().getUser();
            user.setIsConfirmed(true);
            verificationTokenService.delete(verificationToken.get().getId());
            try {
                userService.modify(user);
                return new ResponseEntity<>(HttpStatus.OK); }
            catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND); }

        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
