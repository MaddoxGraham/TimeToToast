package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"person","comment"})
@Entity
public class UserComment {

    @EmbeddedId
    private UserCommentKey userCommentKey;

    @MapsId("idPerson")
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @MapsId("idComment")
    @ManyToOne
    @JoinColumn(name = "idComment")
    private Comment comment;

}
