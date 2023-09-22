package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@ToString(exclude = {"gift", "person"})
@Entity
public class GiftContribution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idGiftContribution;

    @Column(nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name="idGift", nullable=false)
    private Gift gift;

    @ManyToOne
    @JoinColumn(name="idPerson")
    private Person person;

}