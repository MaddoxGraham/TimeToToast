package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude =  {"guests", "userEventRoles", "photos"})
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idEvent;

    @OneToMany(mappedBy = "idEvent")
    private Set<UserEventRole> userEventRoles;

    @OneToMany(mappedBy = "event")
    private Set<Guest> guests;

    @OneToMany(mappedBy = "event")
    private Set<Photo> photos;

}