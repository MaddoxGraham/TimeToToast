package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewTaskDto {
    private Long idTask;
    private Long[] assignee;
    private String description;
    private LocalDate dueDate;
    private Long[] invisibleTo;
    private Long urgency;
    private Long event;
    private Long creator;
}
