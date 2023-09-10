package com.maddoxgraham.TimeToToast.DTOs;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDataDto {
    private String[] to;
    private String subject;
    private String body;

}
