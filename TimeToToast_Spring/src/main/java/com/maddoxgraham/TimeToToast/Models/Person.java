//package com.maddoxgraham.TimeToToast.Models;
//
//
//import com.maddoxgraham.TimeToToast.Models.Enums.Role;
//import jakarta.persistence.*;
//import lombok.*;
//import java.io.Serializable;
//import java.util.Set;
//
//@MappedSuperclass
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@Data
//@ToString(exclude = {"photos", "contributions", "comments", "assignedTasks"})
//public class Person implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, updatable = false)
//    private Long id;
//
//    private String firstName;
//    private String lastName;
//    private String email;
//    private String token;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//    @OneToMany(mappedBy = "person")
//    private Set<UserEventRole> userEventRoles;
//
//    @OneToMany(mappedBy = "person")
//    private Set<Photo> photos;
//
//    @OneToMany(mappedBy = "person")
//    private Set<GiftContribution> contributions;
//
//    @OneToMany(mappedBy = "person")
//    private Set<Comment> comments;
//
//    @OneToMany(mappedBy = "assignee")
//    private Set<Task> assignedTasks;
//}
