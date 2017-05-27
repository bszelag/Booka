package com.boot.firend;


import com.boot.firend.model.Friend;
import com.boot.firend.model.FriendId;
import com.boot.user.UserService;
import com.boot.user.model.User;
import com.boot.utilities.mergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "api/v1/friendss")
public class FriendController {

    @Autowired
    public FriendService friendService;

    @Autowired
    public UserService userService;


    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Collection<Friend> getFriends(@PathVariable Integer id){
        return friendService.getFriends(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Friend> addContact(@RequestBody Friend friend){
        if (friend.getFriend().getFriend1() != null && friend.getFriend().getFriend2() != null){
            Optional<User> user1 = userService.getById(friend.getFriend().getFriend1().getId());
            Optional<User> user2 =  userService.getById(friend.getFriend().getFriend2().getId());
            if (user1.isPresent() && user2.isPresent() && user1.get() != user2.get() ) {
                FriendId friendId = friend.getFriend();
                friendId.setFriend1(user1.get());
                friendId.setFriend2(user2.get());
                friend.setFriend(friendId);
                if (user1.get().getId() > user2.get().getId()){
                    User user = friend.getFriend().getFriend1();
                    friendId.setFriend1(friend.getFriend().getFriend2());
                    friendId.setFriend2(user);
                    friend.setFriend(friendId);
                }
                return new ResponseEntity<>(friendService.addFriend(friend), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "authorized-viewers/{id}", method = RequestMethod.GET)
    public Collection<Friend> getAuthorizedFriends(@PathVariable Integer id){
        return friendService.getAuthorizedFriends(id);
    }

    @RequestMapping(value = "authorized-viewers", method = RequestMethod.POST)
    public ResponseEntity<Friend> addAuthorizedFriend(@RequestBody Friend authorizedFriend)
            throws InstantiationException, IllegalAccessException {
        if (authorizedFriend.getFriend().getFriend1() != null && authorizedFriend.getFriend().getFriend2() != null
                && (authorizedFriend.getFriend1Allow() != null || authorizedFriend.getFriend2Allow() != null
                    || authorizedFriend.getFriendConfirm() != null)){
            Optional<Friend> originalFriend = friendService.getFriend(authorizedFriend.getFriend());
            if (originalFriend.isPresent()) {
                Friend finalfriend = mergeTool.mergeObjects(authorizedFriend,originalFriend.get());
                return new ResponseEntity<>(friendService.addAuthorizedFriend(finalfriend), HttpStatus.OK);
            } else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
