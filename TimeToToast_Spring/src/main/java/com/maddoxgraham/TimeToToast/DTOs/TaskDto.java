package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private long description;
    private long urgence;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTask;
    private long event;
    private long creator;
    private long assigneeUser;
    private long assigneeGuest;

}
