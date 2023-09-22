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
public class UserCommentKey {
    private Long idPerson;
    private Long idComment;
}
