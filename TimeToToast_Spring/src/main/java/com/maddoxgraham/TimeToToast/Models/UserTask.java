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
public class UserTask implements Serializable {

    @EmbeddedId
    private UserTaskKey userTaskKey;

    private Boolean isInvisible;

    @MapsId("idPerson")
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @MapsId("idTask")
    @ManyToOne
    @JoinColumn(name = "idTask")
    private Task task;
}
