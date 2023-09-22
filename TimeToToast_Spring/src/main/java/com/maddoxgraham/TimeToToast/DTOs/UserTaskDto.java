package com.maddoxgraham.TimeToToast.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskDto {
    private Long hiddenUserKey;
    private Long idGuest;
    private Long idUser;
    private Long idTask;
}