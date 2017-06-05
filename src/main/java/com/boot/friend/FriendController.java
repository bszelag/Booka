package com.boot.friend;


import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import com.boot.security.AuthorizationService;
import com.boot.security.utility.Session;
import com.boot.user.UserService;
import com.boot.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/v1/friends")
public class FriendController {

    @Autowired
    public FriendService friendService;

    @Autowired
    public UserService userService;

    @Autowired
    public AuthorizationService authorizationService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Friend> getFriends(@CookieValue(Session.COOKIE_NAME) String sessionToken){
        return friendService.getFriends(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map( u -> u.getId()).orElse(0));
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public ResponseEntity<Friend> addFriend(@CookieValue(Session.COOKIE_NAME) String sessionToken, @PathVariable Integer userId) {
        Optional<User> user1 = userService.getById(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map(User::getId).orElse(0));
        Optional<User> user2 = userService.getById(userId);
        if (user1.isPresent() && user2.isPresent() && !user1.get().equals(user2.get())) {
            FriendId newFriendId;
            if (user1.get().getId() > user2.get().getId())
                newFriendId = new FriendId(user2.get(), user1.get());
            else
                newFriendId = new FriendId(user1.get(), user2.get());
            Friend friend = new Friend(newFriendId, false, false, false);
            try {
                return new ResponseEntity<>(friendService.addFriend(friend), HttpStatus.OK);
            }
            catch (IllegalArgumentException e) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @RequestMapping(value = "changeAuthorizedState/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Friend> changeAuthorizedState(@CookieValue(Session.COOKIE_NAME) String sessionToken, @PathVariable Integer userId)
            throws InstantiationException, IllegalAccessException {
        Optional<User> user = userService.getById(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map( u -> u.getId()).orElse(0));
        Optional<User> friendUser = userService.getById(userId);
        if (user.isPresent() && friendUser.isPresent()){
            FriendId friendId;
            if (user.get().getId() > friendUser.get().getId())
                friendId = new FriendId(friendUser.get(),user.get());
            else
                friendId = new FriendId(user.get(),friendUser.get());
            Optional<Friend> originalFriend = friendService.getFriendById(friendId);
            if (originalFriend.isPresent()) {
                if(originalFriend.get().getFriendshipConfirmed()) {
                    if (friendId.getFriend1().equals(user.get())) {
                        originalFriend.get().setFriend1Allow(!originalFriend.get().getFriend1Allow());
                    } else
                        originalFriend.get().setFriend2Allow(!originalFriend.get().getFriend2Allow());
                    return new ResponseEntity<>(friendService.modify(originalFriend.get()), HttpStatus.OK);
                }else
                    return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "confirmFriendship", method = RequestMethod.POST)
    public ResponseEntity<Friend> addAuthorizedFriend(@RequestBody FriendId friendId)
            throws InstantiationException, IllegalAccessException {
        if (friendId.getFriend1() != null && friendId.getFriend2() != null){
            if (friendId.getFriend1().getId() > friendId.getFriend2().getId()){
                Integer temp = friendId.getFriend1().getId();
                friendId.getFriend1().setId(friendId.getFriend2().getId());
                friendId.getFriend2().setId(temp);
            }
            Optional<Friend> originalFriend = friendService.getFriendById(friendId);
            if (originalFriend.isPresent()) {
                originalFriend.get().setFriendshipConfirmed(true);
                return new ResponseEntity<>(friendService.modify(originalFriend.get()), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
