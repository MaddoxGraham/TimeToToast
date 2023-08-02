package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
public class UserEventKey implements Serializable {
    private Long idUser;
    private Long idEvent;

}
