package com.CodeAlpha_JavaProgramming.HotelReservationSystem.REPO;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Availability;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.RoomModel;
import org.hibernate.annotations.processing.SQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepo extends JpaRepository<RoomModel, Integer> {

    List<RoomModel> findByAvailability(Availability availability);
    List<RoomModel> findByRoomType(Type type);
    RoomModel findByRoomId(Integer roomId);
    RoomModel findFirstByRoomTypeAndAvailability(Type type, Availability availability);
}

