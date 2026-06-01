package com.CodeAlpha_JavaProgramming.HotelReservationSystem.SERVICE;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.DTO.ReservationDTO;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Status;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.Reservation;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Availability;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.RoomModel;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.REPO.ReservationRepo;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.REPO.RoomRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class HotelService {
    @Autowired
    RoomRepo  roomRepo;
    @Autowired
    ReservationRepo reservationRepo;

    public void ShowAllRooms(){
        System.out.println("Viewing Rooms:");
        roomRepo.findAll().forEach(room -> {
            System.out.println(
                    "Room ID: " + room.getRoomId()
                            + " | Type: " + room.getRoomType()
                            + " | Times Booked: " + room.getTimesBooked()
            );
        });
    }
    public void ShowAvilableRooms(){
        System.out.println("Viewing Avilable Rooms:");
        roomRepo.findByAvailability(Availability.AVAILABLE).forEach(room -> {
            System.out.println(
                    "Room ID: " + room.getRoomId()
                            + " | Type: " + room.getRoomType()
                            + " | Times Booked: " + room.getTimesBooked()
            );
        });
    }
    public void ShowAllRoomsByType(Type type) {
        System.out.println("Viewing Avilable " + type + " Rooms:");
        roomRepo.findByRoomType(type).forEach(room -> {
            System.out.println(
                    "Room ID: " + room.getRoomId()
                            + " | Type: " + room.getRoomType()
                            + " | Times Booked: " + room.getTimesBooked()
            );
        });
    }
    public void ShowRoom(Integer id) {
        System.out.println("Viewing Room: ");
        RoomModel room= roomRepo.findByRoomId(id);
            System.out.println(
                    "Room ID: " + room.getRoomId()
                            + " | Type: " + room.getRoomType()
                            + " | Times Booked: " + room.getTimesBooked()
            );

    }

        @Value("${Room.Type.STANDARD.Price}")
        private Integer standardPrice;

        @Value("${Room.Type.DELUXE.Price}")
        private Integer deluxePrice;

        @Value("${Room.Type.SUITE.Price}")
        private Integer suitePrice;

    private Integer getPriceByType(Type type) {

        return switch (type) {

            case STANDARD -> standardPrice;

            case DELUXE -> deluxePrice;

            case SUITE -> suitePrice;
        };
    }

    @Transactional
    public void reserveRoom(ReservationDTO dto) throws Exception {
        if (!dto.getCheckOutDate()
                .isAfter(dto.getCheckInDate())) {
            throw new IllegalArgumentException(
                    "Check-out date must be after check-in date"
            );
        }
        if (dto.getCheckInDate()
                .isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Check-in date must be today or a future date."
            );
        }
        RoomModel room =
                roomRepo.findFirstByRoomTypeAndAvailability(
                        dto.getRoomType(),
                        Availability.AVAILABLE
                );

        if(room == null) {
            throw new RuntimeException("No room available");
        }
        Long days =
                ChronoUnit.DAYS.between(
                        dto.getCheckInDate(),
                        dto.getCheckOutDate()
                );

        Long amount = days * getPriceByType(dto.getRoomType());

        Reservation reservation =
                Reservation.builder()
                        .name(dto.getName())
                        .Phone(dto.getPhoneNumber())
                        .roomId(room.getRoomId())
                        .type(dto.getRoomType())
                        .amount(amount)
                        .externalTxnId(UUID.randomUUID().toString())
                        .status(Status.ACTIVE)
                        .build();

        reservationRepo.save(reservation);

        room.setAvailability(Availability.BOOKED);
        room.setTimesBooked(room.getTimesBooked() + 1);
        roomRepo.save(room);
        System.out.println(reservation.getExternalTxnId());
    }

    public void ShowReservation(String reservationId) {
        System.out.println("Viewing Reservation: " + this.reservationRepo.findByExternalTxnId(reservationId));
    }

    @Transactional
    public void CancelReservation(String reservationId) throws Exception {
        Reservation reservation = this.reservationRepo.findByExternalTxnId(reservationId);
        if(reservation == null) {
            throw new Exception("Reservation not found");
        }
        if(reservation.getCheckInDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot cancel reservation now");
        }
        RoomModel room = this.roomRepo.findByRoomId(reservation.getRoomId());
        this.reservationRepo.delete(reservation);
        room.setAvailability(Availability.AVAILABLE);
        room.setTimesBooked(room.getTimesBooked() - 1);
        this.roomRepo.save(room);
    }
}
