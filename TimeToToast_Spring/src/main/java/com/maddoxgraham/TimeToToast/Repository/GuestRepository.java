package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    //void deleteGuestByIdGuest(Long idGuest);
    Optional<Guest> findGuestByIdGuest(Long idGuest);

    Optional<Guest> findGuestByEmail(String email);

}

