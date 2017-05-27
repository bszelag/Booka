package com.boot.friend.impl;

import com.boot.friend.FriendService;
import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import com.boot.friend.repository.FriendRepository;
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
        if(friendRepository.exists(friend.getFriendId())){
            return null;
        }
        return friendRepository.save(friend);
    }

    @Override
    public Collection<Friend> getAuthorizedFriends(Integer owner_id) {
        return friendRepository.getAuthorizedFriends(owner_id);
    }

    @Override
    public Friend modify(Friend authorizedFriend) {
        if(friendRepository.exists(authorizedFriend.getFriendId())){
            friendRepository.save(authorizedFriend);
        }
        return null;
    }
}
