package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"photos","contributions","comments","assignedTasks"})
@Entity
public class Guest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idGuest;

   private Long token;

    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "guest")
    private Set<Photo> photos;

    @OneToMany(mappedBy = "guest")
    private Set<GiftContribution> contributions;

    @OneToMany(mappedBy = "guest")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "assigneeGuest")
    private Set<Task> assignedTasks;

}
