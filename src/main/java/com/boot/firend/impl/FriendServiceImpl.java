package com.boot.firend.impl;

import com.boot.firend.FriendService;
import com.boot.firend.model.Friend;
import com.boot.firend.model.FriendId;
import com.boot.firend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public Collection<Friend> getFriends(Integer user_id) {
        return friendRepository.getFriends(user_id);
    }

    @Override
    public Optional<Friend> getFriendById(FriendId friendId) {
        return Optional.ofNullable(friendRepository.findOne(friendId));
    }

    @Override
    public Friend addFriend(Friend friend) {
        if(friendRepository.findOne(friend.getFriend())!=null){
            return null;
        }
        return friendRepository.save(friend);
    }

    @Override
    public Collection<Friend> getAuthorizedFriends(Integer owner_id) {
        return friendRepository.getAuthorizedFriends(owner_id);
    }

    @Override
    public Friend addAuthorizedFriend(Friend authorizedFriend) {
        if(friendRepository.exists(authorizedFriend.getFriend())){
            friendRepository.save(authorizedFriend);
        }
        return null;
    }
}
