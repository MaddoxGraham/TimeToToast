package com.maddoxgraham.TimeToToast.DTOs;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhotoDto {
    private Long idPhoto;
    private String name;
    private String source;
    private LocalDate createdAt;
    private Long event;
    private Long person;
}
