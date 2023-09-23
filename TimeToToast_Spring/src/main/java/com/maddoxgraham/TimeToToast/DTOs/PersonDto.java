package com.maddoxgraham.TimeToToast.DTOs;

import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDto {

    private Long idPerson;
    private String firstName;
    private String lastName;
    private String email;
    private String token;
    private String role;
    // User field
    private String adresse;
    private String cp;
    private String ville;
    private LocalDate birthday;
    private String avatar;
    private String phone;
    private String login;
    private String password;
    private String refreshToken;
    // Guest field
    private Boolean isPresent;
    private Long eventId;
}
