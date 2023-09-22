package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto extends PersonDto {
    Long id;
    String token;
    Long idEvent;
    Boolean isPresent;
}
