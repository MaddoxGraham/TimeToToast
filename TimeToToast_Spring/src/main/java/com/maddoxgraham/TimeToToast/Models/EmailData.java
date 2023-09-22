package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmailData {

    private String[] to;
    private Person person;
    private Event event;
}
