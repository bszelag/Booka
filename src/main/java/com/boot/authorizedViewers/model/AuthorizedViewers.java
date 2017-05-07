package com.boot.authorizedViewers.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizedViewers implements Serializable{

    @EmbeddedId
    private AuthorizedViewersId authorizedViewer;

}
