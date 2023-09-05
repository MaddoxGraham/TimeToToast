package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class HiddenUserTaskKey {
    private Long idUser;
    private Long idTask;
}
