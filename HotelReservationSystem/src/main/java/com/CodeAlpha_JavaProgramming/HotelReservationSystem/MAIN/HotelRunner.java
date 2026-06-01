package com.CodeAlpha_JavaProgramming.HotelReservationSystem.MAIN;

import com.CodeAlpha_JavaProgramming.HotelReservationSystem.DTO.ReservationDTO;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.MODELS.Reservation;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.ENUMS.Type;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.REPO.RoomRepo;
import com.CodeAlpha_JavaProgramming.HotelReservationSystem.SERVICE.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

@Component
public class HotelRunner implements CommandLineRunner {
    @Autowired
    RoomRepo roomRepo;
    @Autowired
    HotelService hotelService;

    @Override
    public void run(String... args) throws Exception {

        Scanner input = new Scanner(System.in);

        System.out.println("Hotel System Started");

        while (true) {

            System.out.println("""
             
                    Enter Choice:
                    1 : View Room by id
                    2 : View rooms
                    3 : View Available Rooms
                    4 : View Rooms by Type
                    5 : Room Reservation
                    6 : Cancel Booking
                    7 : View Reservation Details
                    8 : Exit
                    """);

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter Room ID: ");
                    Integer roomId = input.nextInt();
                    this.hotelService.ShowRoom(roomId);
                    input.nextLine();
                    break;

                case 2:
                    this.hotelService.ShowAllRooms();
                    break;

                case 3:
                    this.hotelService.ShowAvilableRooms();
                    break;

                case 4:
                    System.out.println("Entering Room Type: Standard, Deluxe, Suite");
                    String choice2 = input.next().toUpperCase();
                    input.nextLine();
                    Type type = null;
                    try{
                        type = Type.valueOf(choice2);
                        System.out.println("Enum: "+ type);
                    }catch(IllegalArgumentException e){
                        e.printStackTrace();
                    }
                    this.hotelService.ShowAllRoomsByType(type);
                    break;

                case 5:

                    System.out.println("Enter Name:");
                    String name = input.nextLine();

                    System.out.println("Enter Phone:");
                    String phone = input.next();

                    System.out.println("Enter Room Type:");
                    Type roomType =
                            Type.valueOf(
                                    input.next().toUpperCase()
                            );
                    LocalDate checkIn = null;
                    LocalDate checkOut = null;

                    try {
                        System.out.println("Enter Check-In Date (yyyy-mm-dd):");
                        checkIn = LocalDate.parse(input.next());

                        System.out.println("Enter Check-Out Date (yyyy-mm-dd):");
                        checkOut = LocalDate.parse(input.next());

                    } catch (DateTimeParseException e) {
                        e.printStackTrace();
                        break; // or continue;
                    }
                    ReservationDTO dto =
                            ReservationDTO.builder()
                                    .name(name)
                                    .phoneNumber(phone)
                                    .roomType(roomType)
                                    .checkInDate(checkIn)
                                    .checkOutDate(checkOut)
                                    .build();

                    try {
                        hotelService.reserveRoom(dto);
                    }
                    catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case 6:
                    System.out.println("Enter reservation ID:");
                    String cancelreservation = input.nextLine();
                    try {
                        this.hotelService.CancelReservation(cancelreservation);
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("Enter reservation ID:");
                    String reservationId = input.nextLine();
                    this.hotelService.ShowReservation(reservationId);
                    break;

                case 8:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}