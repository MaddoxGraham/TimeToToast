package com.maddoxgraham.TimeToToast.DTOs;

import java.util.Date;

public record SignUpDto (
        String firstName,
        String lastName,
        String adresse,
        String cp,
        String ville,
        String email,
        String phone,


        String login,
        char[] password) {

}
