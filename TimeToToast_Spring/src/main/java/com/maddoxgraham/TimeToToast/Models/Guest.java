package com.maddoxgraham.TimeToToast.Models;

import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@ToString(exclude = {"photos","contributions","comments","assignedTasks"})
@Entity
public class Guest extends Person {
    private Boolean isPresent;

    @ManyToOne
    @JoinColumn(name = "idEvent", nullable = false)
    private Event event;

}
