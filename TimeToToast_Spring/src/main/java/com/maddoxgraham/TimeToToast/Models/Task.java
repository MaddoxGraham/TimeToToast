package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"event", "creator", "assigneeUser", "assigneeGuest"})
@Entity
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idTask;

    @ManyToOne
    @JoinColumn(name="idEvent", nullable=false)
    private Event event;

    @ManyToOne
    @JoinColumn(name="idCreator", nullable=false)
    private User creator;

    @ManyToOne
    @JoinColumn(name="idAssigneeUser")
    private User assigneeUser;

    @ManyToOne
    @JoinColumn(name="idAssigneeGuest")
    private Guest assigneeGuest;
}
