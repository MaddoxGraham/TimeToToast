package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = {"gift", "user", "guest"})
@Entity
public class GiftContribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private String idContribution;

    @Column(nullable = false)
    private String amount;

    @ManyToOne
    @JoinColumn(name="idGift", nullable=false)
    private Gift gift;

    @ManyToOne
    @JoinColumn(name="idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name="idGuest")
    private Guest guest;
}