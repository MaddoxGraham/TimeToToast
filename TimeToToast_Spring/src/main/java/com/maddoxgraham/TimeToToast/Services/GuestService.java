package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Config.UserAuthProvider;
import com.maddoxgraham.TimeToToast.DTOs.GuestDto;
import com.maddoxgraham.TimeToToast.DTOs.UserDto;
import com.maddoxgraham.TimeToToast.Exception.AppException;
import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Mappers.GuestMapper;
import com.maddoxgraham.TimeToToast.Models.Guest;
import com.maddoxgraham.TimeToToast.Models.User;
import com.maddoxgraham.TimeToToast.Repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;


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
    public  List<Guest> findGuestByEvent(Long idEvent){
        return  guestRepository.findAllByEvent_IdEvent(idEvent);
    }

    public void deleteGuest(Long idGuest){
        //guestRepository.deleteGuestByIdGuest(idGuest);
        guestRepository.deleteById(idGuest);
    }

    public GuestDto verifyGuest(String email) {
        Optional<Guest> guestOpt = guestRepository.findGuestByEmail(email);
        if(guestOpt.isPresent()){
            Guest guest = guestOpt.get();
            GuestDto guestDto = GuestMapper.toDto(guest);
            return guestDto;
        }
        return null;
    }

    public GuestDto findByEmail(String email) {
        Guest guest = guestRepository.findGuestByEmail(email)
                .orElseThrow(() -> new AppException("Unknown guest", HttpStatus.NOT_FOUND));
        return GuestMapper.toDto(guest);
    }

    public GuestDto addDetailsToGuest(Long igGuest, String firstName, String lastName) {
        Optional<Guest> guestOpt = guestRepository.findGuestByIdGuest(igGuest);
        if(guestOpt.isPresent()){
            Guest guest = guestOpt.get();
            guest.setFirstName(firstName);
            guest.setLastName(lastName);
            guestRepository.save(guest);
            GuestDto guestDto = GuestMapper.toDto(guest);
            return guestDto;
        }
        return null;
    }
}