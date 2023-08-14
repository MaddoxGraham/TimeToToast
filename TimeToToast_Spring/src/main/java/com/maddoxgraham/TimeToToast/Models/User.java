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
@ToString(exclude = {"photos","contributions","comments","assignedTasks"})
@Builder
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idUser;

    private String Name;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<UserEventRole> userEventRoles;

    @OneToMany(mappedBy = "user")
    private Set<Photo> photos;

    @OneToMany(mappedBy = "user")
    private Set<GiftContribution> contributions;

    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "assigneeUser")
    private Set<Task> assignedTasks;

}
