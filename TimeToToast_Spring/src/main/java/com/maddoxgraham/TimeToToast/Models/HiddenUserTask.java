package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"user","event"})
@Entity
public class HiddenUserTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long hiddenUserTaskKey;

    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;


    @MapsId("idTask")
    @ManyToOne
    @JoinColumn(name = "idTask")
    private Task task;


}
