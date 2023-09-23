package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEventsDto {
    private Long idPerson;
    private String role;
    private EventDto events[];
    private PersonDto persons[];
}
