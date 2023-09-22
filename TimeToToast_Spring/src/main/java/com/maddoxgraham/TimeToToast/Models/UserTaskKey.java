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
public class UserTaskKey {

   private Long idPerson;
   private Long idTask;

}
