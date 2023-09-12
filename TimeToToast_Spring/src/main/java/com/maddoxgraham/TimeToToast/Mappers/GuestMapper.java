package com.maddoxgraham.TimeToToast.Mappers;

import com.maddoxgraham.TimeToToast.DTOs.GuestDto;
import com.maddoxgraham.TimeToToast.Models.Enums.Role;
import com.maddoxgraham.TimeToToast.Models.Guest;

public class GuestMapper {

    public static GuestDto toDto(Guest guest) {
        if (guest == null) {
            return null;
        }

        GuestDto dto = new GuestDto();
        dto.setIdGuest(guest.getIdGuest());
        dto.setToken(guest.getToken());
        dto.setEmail(guest.getEmail());
        dto.setFirstName(guest.getFirstName());
        dto.setLastName(guest.getLastName());
        dto.setIsPresent(guest.getIsPresent());
        dto.setRole(guest.getRole() != null ? guest.getRole().toString() : null);
        dto.setIdEvent(guest.getEvent() != null ? guest.getEvent().getIdEvent() : null);

        return dto;
    }

    public static Guest toModel(GuestDto dto) {
        if (dto == null) {
            return null;
        }

        Guest guest = new Guest();
        guest.setIdGuest(dto.getIdGuest());
        guest.setToken(dto.getToken());
        guest.setEmail(dto.getEmail());
        guest.setFirstName(dto.getFirstName());
        guest.setLastName(dto.getLastName());
        guest.setIsPresent(dto.getIsPresent());
        guest.setRole(dto.getRole() != null ? Role.valueOf(dto.getRole()) : null);
        // Note: You'll need to populate the Event object, perhaps via another mapper or service
        // guest.setEvent(...);

        return guest;
    }
}
