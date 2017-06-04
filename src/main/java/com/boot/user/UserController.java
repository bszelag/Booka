package com.boot.user;

import com.boot.security.AuthorizationService;
import com.boot.security.utility.Credentials;
import com.boot.security.utility.Session;
import com.boot.user.model.User;
import com.boot.utilities.mergeTool;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
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

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/v1/users") ///api/v1/ required till nginx
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    public AuthorizationService authorizationService;

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
                User finalUser = mergeTool.mergeObjects(user, originalUser.get());
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
        try {
            return ResponseEntity.ok(userService.add(user)); }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);}
    }

    @RequestMapping(value = "hash", method =  RequestMethod.POST)
    public void hashAll() {
        userService.hashAll();
    }

    @RequestMapping(value = "search/{query}", method = RequestMethod.GET)
    public Collection<User> searchQuery(@PathVariable Optional<String> query) {
        return userService.searchQuery(query.orElse(""));
    }
}
