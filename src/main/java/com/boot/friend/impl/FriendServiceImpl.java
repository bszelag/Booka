package com.boot.friend.impl;

import com.boot.friend.FriendService;
import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import com.boot.friend.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public Friend addFriend(Friend friend) throws IllegalArgumentException{
        try {
            return friendRepository.save(friend);
        }
        catch (DataAccessException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Friend modify(Friend authorizedFriend) throws IllegalArgumentException{
        if (friendRepository.exists(authorizedFriend.getFriendId())) {
            try {
                return friendRepository.save(authorizedFriend);
            }
            catch (DataAccessException e) {
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Friend does not exist");
    }
}
