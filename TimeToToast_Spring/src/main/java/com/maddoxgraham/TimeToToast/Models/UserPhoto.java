package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(exclude = {"person","photo"})
@Entity
public class UserPhoto {

    @EmbeddedId
    private UserPhotoKey userPhotoKey;

    @MapsId("idPerson")
    @ManyToOne
    @JoinColumn(name = "idPerson")
    private Person person;

    @MapsId("idPhoto")
    @ManyToOne
    @JoinColumn(name = "idPhoto")
    private Photo photo;

}
