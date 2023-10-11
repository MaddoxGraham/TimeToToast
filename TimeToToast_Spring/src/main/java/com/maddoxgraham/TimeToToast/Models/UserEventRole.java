package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"person","event"})
@Entity
public class UserEventRole implements Serializable {

    @EmbeddedId
    private UserEventKey userEventKey;

    @MapsId("idPerson")
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @MapsId("idEvent")
    @ManyToOne
    @JoinColumn(name = "idEvent")
    private Event event;

    @Column(name = "role", nullable = false)
    private String role;
}