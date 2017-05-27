package com.boot.firend.repository;


import com.boot.firend.model.Friend;
import com.boot.firend.model.FriendId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface FriendRepository extends CrudRepository<Friend,FriendId>{

    @Query("Select f" +
            " FROM Friend f" +
            " JOIN f.friend fi" +
            " JOIN fi.friend1 u1" +
            " JOIN fi.friend2 u2" +
            " WHERE (u1.id = :user_id" +
            " OR u2.id = :user_id)" +
            " AND f.friendConfirm = true ")
    Collection<Friend> getFriends(@Param("user_id") Integer user_id);

    @Query("Select f" +
            " FROM Friend f" +
            " JOIN f.friend fi" +
            " JOIN fi.friend1 u1" +
            " JOIN fi.friend2 u2" +
            " WHERE (u1.id = :user_id" +
            " AND f.friend2Allow = true )" +
            " OR (u2.id = :user_id" +
            " AND f.friend1Allow = true )" +
            " AND f.friendConfirm = true ")
    Collection<Friend> getAuthorizedFriends (@Param("user_id") Integer user_id);
}
