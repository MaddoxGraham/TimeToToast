package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = { "event", "contributions" })
@Entity
public class Gift implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idGift;

    @ManyToOne
    @JoinColumn(name="idEvent", nullable=false)
    private Event event;

    @OneToMany(mappedBy = "gift")
    private Set<GiftContribution> contributions;

}
