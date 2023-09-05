package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HiddenUserTaskDto {
    private Long idUser;
    private Long idTask;
}