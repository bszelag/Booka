package com.boot.friend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend implements Serializable{

    @EmbeddedId
    private FriendId friendId;

    private Boolean friend1Allow;
    private Boolean friend2Allow;

    private Boolean friendshipConfirmed;

}
