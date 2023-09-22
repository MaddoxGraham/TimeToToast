package com.maddoxgraham.TimeToToast.Models;

import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@ToString(exclude = {"photos","contributions","comments","assignedTasks"})
@Builder
@Entity
public class User extends Person {
    private String adresse;
    private String cp;
    private String ville;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private String avatar;
    private String phone;
    private String login;
    private String password;
    private String refreshToken;

}
