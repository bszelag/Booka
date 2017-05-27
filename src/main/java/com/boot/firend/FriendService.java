package com.boot.firend;

import com.boot.firend.model.Friend;
import com.boot.firend.model.FriendId;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface FriendService {

    public Collection<Friend> getFriends(Integer user_id);

    public Friend getFriend(FriendId friendId);

    public Friend addFriend(Friend contact);

    public Collection<Friend> getAuthorizedFriends(Integer owner_id);

    public Friend addAuthorizedFriend(Friend authorizedViewer);
}
