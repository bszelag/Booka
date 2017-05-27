package com.boot.friend;

import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface FriendService {

    Collection<Friend> getFriends(Integer user_id);

    Optional<Friend> getFriendById(FriendId friendId);

    Friend addFriend(Friend friend);

    Collection<Friend> getAuthorizedFriends(Integer owner_id);

    Friend modify(Friend authorizedViewer);
}
