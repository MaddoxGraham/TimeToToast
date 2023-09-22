package com.maddoxgraham.TimeToToast.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@ToString(exclude = {"event", "creator", "assignee"})
@Entity
public class Task implements Serializable {

    private String description;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateTask;
    private Long urgence;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long idTask;

    @ManyToOne
    @JoinColumn(name="idEvent", nullable=false)
    private Event event;

    @ManyToOne
    @JoinColumn(name="idCreator", nullable=false)
    private Person creator;

    @ManyToOne
    @JoinColumn(name="assignee")
    private Person assignee;
}
