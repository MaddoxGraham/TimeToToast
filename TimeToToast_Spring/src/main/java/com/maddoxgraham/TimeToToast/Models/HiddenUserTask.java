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

    @EmbeddedId
    private HiddenUserTaskKey hiddenUserTaskKey;

    @MapsId("idUser")
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @MapsId("idTask")
    @ManyToOne
    @JoinColumn(name = "idTask")
    private Task task;


}
