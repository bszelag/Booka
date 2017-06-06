package com.boot.friend;

import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface FriendService {

    Collection<Friend> getFriends(Integer user_id);

    Collection<Friend> getFriendsAllowed(Integer user_id);

    Optional<Friend> getFriendById(FriendId friendId);

    Optional<Friend> getIfFriends(Integer userId1, Integer userId2);

    Friend addFriend(Friend friend) throws IllegalArgumentException;

    Friend modify(Friend authorizedViewer) throws IllegalArgumentException;
}
