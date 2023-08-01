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
@Table(name = "UserEventRole")
public class UserEventRole implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false, updatable = false, referencedColumnName = "idUser")
    private User idUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false, updatable = false, referencedColumnName = "idEvent")
    private Event idEvent;

    @Column(name = "role", nullable = false)
    private String role;
}