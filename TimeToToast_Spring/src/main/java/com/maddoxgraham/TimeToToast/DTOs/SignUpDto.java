package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class SignUpDto {

        private String firstName;
        private String lastName;
        private String adresse;
        private String cp;
        private String ville;
        private String email;
        private String phone;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate birthday;
        private String login;
        private char[] password;

}
