package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserEventRoleDto {
    private Long idPerson;
    private Long idEvent;
    private String role;
}
