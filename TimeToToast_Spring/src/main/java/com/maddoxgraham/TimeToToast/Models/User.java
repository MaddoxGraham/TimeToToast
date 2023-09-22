package com.maddoxgraham.TimeToToast.Models;

import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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

    private String firstName;
    private String lastName;

    private String adresse;
    private String cp;
    private String ville;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String avatar;
    private String email;
    private String phone;

    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    private String token;

    @Column(name = "refreshToken")
    private String refreshToken;

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
