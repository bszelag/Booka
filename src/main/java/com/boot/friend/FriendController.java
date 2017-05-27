package com.boot.friend;


import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import com.boot.security.AuthorizationService;
import com.boot.security.utility.Session;
import com.boot.user.UserService;
import com.boot.user.model.User;
import com.boot.utilities.mergeTool;
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

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Collection<Friend> getFriends(@CookieValue(Session.COOKIE_NAME) String sessionToken){
        return friendService.getFriends(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map( u -> u.getId()).orElse(0));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Friend> addFriend(@RequestBody FriendId friendId){
        if (friendId.getFriend1() != null && friendId.getFriend2() != null){
            Optional<User> user1 = userService.getById(friendId.getFriend1().getId());
            Optional<User> user2 =  userService.getById(friendId.getFriend2().getId());
            if (user1.isPresent() && user2.isPresent() && !user1.get().equals(user2.get() )) {
                FriendId newFriendId = new FriendId(user1.get(),user2.get());
                if (user1.get().getId() > user2.get().getId()){
                    User user = newFriendId.getFriend1();
                    newFriendId.setFriend1(newFriendId.getFriend2());
                    newFriendId.setFriend2(user);
                }
                Friend friend = new Friend(newFriendId,false,false,false);
                return new ResponseEntity<>(friendService.addFriend(friend), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "changeAuthorizedState/{userId}", method = RequestMethod.POST)
    public ResponseEntity<Friend> changeAuthorizedState(@CookieValue(Session.COOKIE_NAME) String sessionToken, @PathVariable Integer userId)
            throws InstantiationException, IllegalAccessException {
        Optional<User> user = userService.getById(authorizationService.getSession(UUID.fromString(sessionToken)).
                map(Session::getUser).map( u -> u.getId()).orElse(0));
        Optional<User> friendUser = userService.getById(userId);
        if (user.isPresent() && friendUser.isPresent()){
            FriendId friendId = new FriendId(user.get(),friendUser.get());
            if (user.get().getId() > friendUser.get().getId()){
                User tempUser = friendId.getFriend1();
                friendId.setFriend1(friendId.getFriend2());
                friendId.setFriend2(tempUser);
            }
            Optional<Friend> originalFriend = friendService.getFriendById(friendId);
            if (originalFriend.isPresent()) {
                if (friendId.getFriend1() == user.get()){
                    originalFriend.get().setFriend1Allow(true);
                }else
                    originalFriend.get().setFriend2Allow(true);
                return new ResponseEntity<>(friendService.modify(originalFriend.get()), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "confirmFriendship/", method = RequestMethod.POST)
    public ResponseEntity<Friend> addAuthorizedFriend(@RequestBody FriendId friendId)
            throws InstantiationException, IllegalAccessException {
        if (friendId.getFriend1() != null && friendId.getFriend2() != null){
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
