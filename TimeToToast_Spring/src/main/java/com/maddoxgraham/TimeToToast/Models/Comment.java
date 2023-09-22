package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@ToString(exclude = {"photo", "event", "person", "parentComment", "replies"})
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idComment;

    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person person;

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
