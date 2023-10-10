package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@ToString(exclude = { "event", "contributions" })
@Entity
public class Gift implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idGift;

    private String name;
    @Column(name = "url", columnDefinition = "TEXT")
    private String url;
    private String photo;
    private Long wanted;
    private Double price;

    private String categorie;
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name="idEvent", nullable=false)
    private Event event;

    @OneToMany(mappedBy = "gift")
    private Set<GiftContribution> contributions;

}
