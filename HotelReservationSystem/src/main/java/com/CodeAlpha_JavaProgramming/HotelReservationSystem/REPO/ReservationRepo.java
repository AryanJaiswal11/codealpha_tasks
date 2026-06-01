package com.CodeAlpha_JavaProgramming.HotelReservationSystem.REPO;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepo extends JpaRepository<Reservation, Integer> {
    Reservation findByExternalTxnId(String externalTxnId);
}
