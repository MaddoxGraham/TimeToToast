package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.EventDto;
import com.maddoxgraham.TimeToToast.Models.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public static EventDto toDto(Event event) {
        if (event == null) {
            return null;
        }
        EventDto dto = new EventDto();
        dto.setIdEvent(event.getIdEvent());
        dto.setTitle(event.getTitle());
        dto.setDescription(event.getDescription());
        dto.setCategorie(event.getCategorie());
        dto.setAdresse(event.getAdresse());
        dto.setStartTime(event.getStartTime());
        dto.setCp(event.getCp());
        dto.setVille(event.getVille());
        dto.setCreatedAt(event.getCreatedAt());
        dto.setEventDate(event.getEventDate());
        return dto;
    }

    public static Event toEntity(EventDto dto) {
        if (dto == null) {
            return null;
        }
        Event event = new Event();
        event.setIdEvent(dto.getIdEvent());
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setCategorie(dto.getCategorie());
        event.setAdresse(dto.getAdresse());
        event.setStartTime(dto.getStartTime());
        event.setCp(dto.getCp());
        event.setVille(dto.getVille());
        event.setCreatedAt(dto.getCreatedAt());
        event.setEventDate(dto.getEventDate());
        return event;
    }
}
