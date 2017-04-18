package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/friends")
public class FriendsController {

    @RequestMapping(method = RequestMethod.GET)
    public String books() {
        return "friends/friends";
    }
}
