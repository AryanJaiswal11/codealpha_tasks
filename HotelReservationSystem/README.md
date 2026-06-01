# Hotel Reservation System

A console-based Hotel Reservation System built using Java, Spring Boot, Spring Data JPA, and Microsoft SQL Server.

## Features

### Room Management

* View all rooms
* View available rooms
* View rooms by category
* Room categories:

  * Standard
  * Deluxe
  * Suite

### Reservation Management

* Create reservations
* Cancel reservations
* View reservation details
* Automatic room allocation based on room type and availability

### Payment Simulation

* Calculates booking amount based on:

  * Room type
  * Duration of stay
* Generates unique transaction IDs for reservations

### Validation

* Check-in date must be today or a future date
* Check-out date must be after check-in date
* Room availability validation
* Date format validation

## Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* Microsoft SQL Server
* Lombok
* Jackson (JSON Processing)
* Maven

## Project Structure

```text
src
├── main
│   ├── java
│   │   ├── CONFIGS
│   │   ├── DTO
│   │   ├── ENUMS
│   │   ├── EXCEPTIONS
│   │   ├── MAIN
│   │   ├── MODELS
│   │   ├── REPO
│   │   └── SERVICE
│   └── resources
│       ├── application.properties
│       └── rooms.json
```

## Database Schema

### Room

```java
RoomId
RoomType
Availability
TimesBooked
```

### Reservation

```java
externalTxnId
name
phone
roomId
type
amount
status
checkInDate
checkOutDate
```

## Room Pricing

Configured through application.properties

```properties
room.type.standard.price=700
room.type.deluxe.price=1200
room.type.suite.price=3000
```

## Console Menu

```text
1. Enter Guest Details
2. View All Rooms
3. View Available Rooms
4. View Rooms By Type
5. Reserve Room
6. Cancel Reservation
7. View Reservation Details
8. Exit
```

## Reservation Flow

```text
User selects room type
        ↓
System finds available room
        ↓
User enters check-in/check-out dates
        ↓
Amount is calculated
        ↓
Transaction ID generated
        ↓
Reservation stored
        ↓
Room marked as booked
```

## Cancellation Flow

```text
Enter Reservation ID
        ↓
Reservation found
        ↓
Status updated
        ↓
Room released
        ↓
Room marked available
```

## Sample Reservation

```text
Name: Aryan
Phone: 9876543210
Room Type: DELUXE
Check-In: 2026-06-01
Check-Out: 2026-06-04
Amount: ₹3600
Status: PENDING
Transaction ID: Generated Automatically
```

## Future Improvements

* Authentication and Authorization
* Multiple Guests per Reservation
* Online Payment Gateway Integration
* Room Service Management
* Reservation History
* Reporting and Analytics
* REST API Support
* Kafka-based Notification System

## Author

Aryan Jaiswal

Built as a Java & Spring Boot learning project to practice:

* Object-Oriented Programming
* Spring Boot
* JPA/Hibernate
* Database Design
* Console Application Development
* Exception Handling
* DTO Design Patterns
