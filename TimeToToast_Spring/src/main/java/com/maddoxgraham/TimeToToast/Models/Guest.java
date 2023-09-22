//package com.maddoxgraham.TimeToToast.Models;
//
//import com.maddoxgraham.TimeToToast.Models.Enums.Role;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.io.Serializable;
//import java.util.Set;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@Data
//@ToString(exclude = {"photos","contributions","comments","assignedTasks"})
//@Entity
//public class Guest implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(nullable = false, updatable = false)
//    private Long idGuest;
//
//    private String email;
//    private String lastName;
//    private String firstName;
//
//    private Role role;
//
//    private String token;
//    private Boolean isPresent;
//
//    @ManyToOne
//    @JoinColumn(name = "idEvent", nullable = false)
//    private Event event;
//
//    @OneToMany(mappedBy = "guest")
//    private Set<Photo> photos;
//
//    @OneToMany(mappedBy = "guest")
//    private Set<GiftContribution> contributions;
//
//    @OneToMany(mappedBy = "guest")
//    private Set<Comment> comments;
//
//    @OneToMany(mappedBy = "assigneeGuest")
//    private Set<Task> assignedTasks;
//
//}
