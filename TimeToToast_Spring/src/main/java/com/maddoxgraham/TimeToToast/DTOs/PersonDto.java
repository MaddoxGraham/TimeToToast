package com.maddoxgraham.TimeToToast.DTOs;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
