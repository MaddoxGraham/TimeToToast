package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {

    private Long idEvent;
    private String Title;
    private String Description;
    private String Categorie;
    private String adresse;
    private String startTime;
    private String cp;
    private String ville;@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate EventDate;

}
