package com.maddoxgraham.TimeToToast.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDataDto {
    private String[] to;
    private Long idPerson;
    private Long idEvent;

}
