package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"comments"})
@Data
@Entity
public class Photo {

    private boolean photosModuleActive;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idPhoto;

    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "photo")
    private Set<Comment> comments;
}