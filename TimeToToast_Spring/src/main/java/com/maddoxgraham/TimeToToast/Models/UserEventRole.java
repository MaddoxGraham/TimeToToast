package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"user","event"})
@Entity
public class UserEventRole implements Serializable {

    @EmbeddedId
    private UserEventKey userEventKey;

    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @MapsId("idEvent")
    @ManyToOne
    @JoinColumn(name = "idEvent")
    private Event event;

    @Column(name = "role", nullable = false)
    private String role;
}