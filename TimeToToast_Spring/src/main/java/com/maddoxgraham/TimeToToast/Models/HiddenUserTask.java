package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"person","task"})
@Entity
public class HiddenUserTask implements Serializable {

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
