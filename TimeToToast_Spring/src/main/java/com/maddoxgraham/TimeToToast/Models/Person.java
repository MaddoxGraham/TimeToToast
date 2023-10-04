package com.maddoxgraham.TimeToToast.Models;


import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Entity
@ToString(exclude = {"photos", "contributions", "comments", "assignedTasks"})
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPerson;

    private String firstName;
    private String lastName;
    private String email;
    private String token;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "person")
    private Set<UserEventRole> userEventRoles;

    @OneToMany(mappedBy = "person")
    private Set<Photo> photos;

    @OneToMany(mappedBy = "person")
    private Set<GiftContribution> contributions;

    @OneToMany(mappedBy = "person")
    private Set<Comment> comments;


    // User field
    private String adresse;
    private String cp;
    private String ville;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @Column(columnDefinition = "LONGTEXT")
    private String avatar;
    private String phone;
    private String login;
    private String password;
    @Column(name = "refreshToken")
    private String refreshToken;



    // Guest field
    private Boolean isPresent;

}
