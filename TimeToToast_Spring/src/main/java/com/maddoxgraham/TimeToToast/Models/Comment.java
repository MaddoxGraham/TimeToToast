package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"photo", "event", "user", "guest", "parentComment", "replies"})
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idComment;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name="idGuest")
    private Guest guest;

    @ManyToOne
    @JoinColumn(name="idEvent")
    private Event event;

    @ManyToOne
    @JoinColumn(name="idPhoto")
    private Photo photo;

    @ManyToOne
    @JoinColumn(name="idParentComment")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment")
    private Set<Comment> replies;

}
