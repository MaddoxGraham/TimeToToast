package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class UserEventRole implements Serializable {

    @EmbeddedId
    private UserEventKey userEventKey;

    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, updatable = false, insertable = false)
    private User idUser;

    @MapsId("idEvent")
    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false, updatable = false, insertable = false)
    private Event idEvent;

    @Column(name = "role", nullable = false)
    private String role;
}