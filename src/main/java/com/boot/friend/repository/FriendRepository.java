package com.boot.friend.repository;


import com.boot.friend.model.Friend;
import com.boot.friend.model.FriendId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FriendRepository extends CrudRepository<Friend,FriendId>{

    @Query("Select f" +
            " FROM Friend f" +
            " JOIN f.friendId.friend1 u1" +
            " JOIN f.friendId.friend2 u2" +
            " WHERE (u1.id = :user_id" +
            " OR u2.id = :user_id)" +
            " AND f.friendshipConfirmed = true ")
    Collection<Friend> getFriends(@Param("user_id") Integer user_id);

    @Query("Select f" +
            " FROM Friend f" +
            " JOIN f.friendId.friend1 u1" +
            " JOIN f.friendId.friend2 u2" +
            " WHERE u1.id = :user1_id" +
            " AND u2.id = :user2_id" +
            " AND f.friendshipConfirmed = true ")
    Friend getIfFriends(@Param("user1_id") Integer user1_id, @Param("user2_id") Integer user2_id);
}
