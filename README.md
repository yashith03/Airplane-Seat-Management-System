# Airplane Seat Management System

## Overview

This Java program manages seat bookings for a plane, allowing users to buy, cancel, search for seats, and view seating plans. The system records ticket information and calculates total sales.

## Features

- **Seat Management:**
  - Buy and cancel seats.
  - Find the first available seat.
  - Display a seating plan.
- **Ticket Handling:**
  - Store ticket details including passenger name, seat number, and price.
  - Save ticket information to a file.
  - Search for booked tickets.
- **Sales Calculation:**
  - Keep track of total sales revenue from ticket purchases.

## Installation & Usage

### Prerequisites

- Java installed on your system.

### Running the Program

1. Compile the program:
   ```
     javac Airplane-Seat-Management-System.java
   ```
2. Run the program:
   ```
   java Airplane-Seat-Management-System
   ```
3. Follow the menu options:
   - **1)** Buy a Seat
   - **2)** Cancel a Seat
   - **3)** Find First Available Seat
   - **4)** Show Seating Plan
   - **5)** Print Tickets & Total Sales
   - **6)** Search for a Ticket
   - **0)** Quit the program

## Example Usage

### Buying a Seat:

```
Enter the Row Letter[A-D]: A
Enter the Seat Number: 5
Enter your first Name: John
Enter your Surname: Doe
Enter your Email address: john.doe@example.com
Seat [A5] purchased successfully!
Price: 200£
Ticket info saved to file: seat A5.txt
```

### Searching for a Ticket:

```
Enter the Row Letter[A-D]: A
Enter the Seat Number: 5
Seat [A5] is already booked
Row: A
Seat: 5
Price: £200
Person Information:
Name: John
Surname: Doe
Email: john.doe@example.com
```

## File Structure

- `Person.java` – Stores passenger details.
- `Ticket.java` – Handles ticket information and file storage.
- `W2051623_PlaneManagement.java` – Main program logic.

## License

This project is open-source and free to use.

