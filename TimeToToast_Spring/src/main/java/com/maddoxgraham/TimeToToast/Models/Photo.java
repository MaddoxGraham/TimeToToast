package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Photo {
    @Id
    private String idPhoto;

    @ManyToOne
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "idGuest", nullable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;
}