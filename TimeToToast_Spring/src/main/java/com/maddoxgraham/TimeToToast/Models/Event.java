package com.maddoxgraham.TimeToToast.Models;

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
@ToString(exclude =  {"guests", "userEventRoles", "photos", "gifts","comments","tasks"})
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idEvent;
    private String Title;
    private String Description;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;


    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate EventDate;
    private String startTime;
    private String Categorie;
    private String Adresse;
    private String Ville;
    private String cp;

    private boolean taskModuleActive;
    private boolean GiftModuleActive;
    private boolean PhotoModuleActive;

    @OneToMany(mappedBy = "event")
    private Set<UserEventRole> userEventRoles;

    @OneToMany(mappedBy = "event")
    private Set<Guest> guests;

    @OneToMany(mappedBy = "event")
    private Set<Photo> photos;

    @OneToMany(mappedBy="event")
    private Set<Gift> gifts;

    @OneToMany(mappedBy = "event")
    private Set<Comment> comments;

    @OneToMany(mappedBy = "event")
    private Set<Task> tasks;

}