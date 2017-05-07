package com.boot.user;

import com.boot.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1//users") ///api/v1/ required till nginx
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<User>  getById(@PathVariable String id){
        return userService.getById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public void modify(@PathVariable String id, @RequestBody User user){
        userService.modify(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id){
        userService.delete(id);
    }

    @RequestMapping(value = "sign_in", method = RequestMethod.POST)
    public void add(@PathVariable String id, @RequestBody User user){
        userService.add(user);
    }
}
