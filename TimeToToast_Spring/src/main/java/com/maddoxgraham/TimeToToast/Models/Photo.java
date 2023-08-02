package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"comments"})
@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPhoto;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGuest", nullable = true)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "photo")
    private Set<Comment> comments;
}