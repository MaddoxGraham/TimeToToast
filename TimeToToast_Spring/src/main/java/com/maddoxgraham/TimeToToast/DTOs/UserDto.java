package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long idUser;
    private String firstName;
    private String lastName;

    private String adresse;
    private String cp;
    private String ville;
    private Date birthday;
    private String avatar;
    private String email;
    private String phone;

    private String login;
    private String token;
    private String refreshToken;
    private String role;

}
