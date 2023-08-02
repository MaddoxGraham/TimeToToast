package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class UserEventKey implements Serializable {
    private Long idUser;
    private Long idEvent;
}
