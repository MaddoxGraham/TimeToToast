package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {
    Long id;
    String token;
    Long idEvent;
    String role;
    String email;
    String firstName;
    String lastName;
    Boolean isPresent;
}
