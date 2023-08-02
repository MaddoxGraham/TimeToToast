package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.TimeToToastApplication;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Guest;
import com.maddoxgraham.TimeToToast.Repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public Guest addGuest(Guest guest){
        return guestRepository.save(guest);
    }

    public List<Guest> findAllGuests(){
        return guestRepository.findAll();
    }

    public Guest updateGuest(Guest guest){
        return guestRepository.save(guest);
    }

    public Guest findGuestByIdGuest(Long idGuest){
        return guestRepository.findGuestByIdGuest(idGuest).orElseThrow(() -> new UserNotFoundException("User n° " + idGuest + " was not found"));
    }

    public void deleteGuest(Long idGuest){
        guestRepository.deleteGuestByIdGuest(idGuest);
    }
}