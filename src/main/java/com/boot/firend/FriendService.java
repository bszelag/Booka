package com.boot.firend;

import com.boot.firend.model.Friend;
import com.boot.firend.model.FriendId;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface FriendService {

    public Collection<Friend> getFriends(Integer user_id);

    public Optional<Friend> getFriendById(FriendId friendId);

    public Friend addFriend(Friend friend);

    public Collection<Friend> getAuthorizedFriends(Integer owner_id);

    public Friend addAuthorizedFriend(Friend authorizedViewer);
}
