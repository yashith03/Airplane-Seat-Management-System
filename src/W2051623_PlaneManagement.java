import java.util.InputMismatchException;
import java.util.Scanner;
public class W2051623_PlaneManagement {
    private static int[][] Seat_List;     //Array to store seat status
    private static Ticket[] tickets;
    private static int ticket_count;     // Number of purchased tickets
    public static int Price;             //seat Price
    private static int i;
    static String Row, Name, Surname, Email;
    static int Seat_no;                   //seat numbers
    static int Total_Amount;             //total sales by tickets

    public static void main(String[] args) {
        System.out.println();
        System.out.println("-Welcome to the Plane Management application-");
        System.out.println();
        Seat_List = new int[4][];
        Seat_List[0] = new int[14];
        Seat_List[1] = new int[12];
        Seat_List[2] = new int[12];
        Seat_List[3] = new int[14];

        tickets = new Ticket[42];
        ticket_count = 0;

        Scanner input = new Scanner(System.in);
        int option = -1;
        while (option != 0) {
            System.out.println("****************************************************");
            System.out.println("*                  MENU OPTIONS                    *");
            System.out.println("****************************************************");
            System.out.println("    1) Buy a Seat");
            System.out.println("    2) Cancel a seat");
            System.out.println("    3) Find First available seat");
            System.out.println("    4) Show seating plan");
            System.out.println("    5) Print tickets information and total sales");
            System.out.println("    6) search ticket");
            System.out.println("    0) Quit");
            System.out.println("****************************************************");
            System.out.print("Please select an Option: ");

            try {
                option = input.nextInt();
            } catch (InputMismatchException ex) {
                input.nextLine();
                System.out.println("-Invalid choice.Integer Required-");
                System.out.println("   ");
                continue;
            }
            switch (option) {                     //Asking users choice
                case 1:
                    Buy_Seat();
                    break;
                case 2:
                    Cancel_Seat();
                    break;
                case 3:
                    First_Available_Seat();
                    break;
                case 4:
                    Seating_plan();
                    break;
                case 5:
                    Print_Ticket_info();
                    break;
                case 6:
                    Search_Ticket();
                    break;
                case 0:
                    System.out.print("Exiting the program....");
                    try {
                        Thread.sleep(2000);          //after 2000milliseconds
                    } catch (InterruptedException sleep) {
                        sleep.printStackTrace();
                    }
                    System.out.println("\r" + " ".repeat(50));//overwriting the msg "Exiting the program" with 50 spaces
                    System.out.println("--Program Terminated!--");
                    break;

                default:
                    System.out.println();
                    System.out.println("Invalid choice,Please try again.");
                    System.out.println("       ");
            }
        }
    }
    private static void Buy_Seat() {       //Method to Buy a Seat
        Scanner input = new Scanner(System.in);
        Row_SeatNum_Validation();                     //Calling the method to get Row  and seat no.

        int Row_Number = Row.charAt(0) - 'A';
        if (Row_Number < 0 || Seat_no < 1 || Row_Number >= Seat_List.length || Seat_no > Seat_List[Row_Number].length) {
            System.out.println();
            System.out.println("Invalid row or seat number. Please try again.");
            System.out.println();
            return;
        }
        if (Seat_List[Row_Number][Seat_no - 1] == 1) {
            System.out.println();
            System.out.println("Seat is already sold.Please try another one.");
            System.out.println();
            return;
        }
        Seat_List[Row_Number][Seat_no - 1] = 1;  // Mark seat as sold
        if (1 <= Seat_no && Seat_no <= 5) {
            Price = 200;
        } else if (Seat_no >= 6 && Seat_no <= 9) {
            Price = 150;
        } else {
            Price = 180;
        }
        while (true) {
            System.out.print("Enter your first Name: ");
            try {
                Name = input.next();
                if (!Name.matches("[a-zA-Z]+")){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid Name.Try again.");
                System.out.println();
            }
        }
        while (true) {
            System.out.print("Enter your Surname: ");
            try {
                Surname = input.next();
                if (!Surname.matches("[a-zA-Z]+")){
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.println("Invalid Surname. Try again.");
                System.out.println();
            }
        }
        System.out.print("Enter your Email address: ");
        Email = input.next();

        Person person = new Person(Name, Surname, Email);
        Ticket ticket = new Ticket(Row, Seat_no, Price, person);
        tickets[ticket_count]=ticket;
        ticket_count = ticket_count+1;                //Counting  the No. of tickets
        Total_Amount=Total_Amount+Price;              //Counting the Total Sales

        System.out.println();
        System.out.println("Seat [" + Row + Seat_no + "] purchased successfully! .");
        System.out.println("Price: " + Price + "£");
        ticket.save();
        System.out.println();
    }
    private static void Cancel_Seat() {                   //Method to Cancel a seat
        Row_SeatNum_Validation();                        //Calling the method to get Row  and seat no.

        int Row_Number = Row.charAt(0) - 'A';
        if (Row_Number < 0 || Seat_no < 1 || Row_Number >= Seat_List.length || Seat_no > Seat_List[Row_Number].length) {
            System.out.println();
            System.out.println("Invalid Row or seat number.Please try again.");
            System.out.println();
            return;
        }
        if (Seat_List[Row_Number][Seat_no - 1] == 0) {
            System.out.println();
            System.out.println("Seat is already available.Please try again");
            System.out.println();
            return;
        }
        Seat_List[Row_Number][Seat_no - 1] = 0;              // Mark seat as available
        for (int i = 0; i < ticket_count; i = i + 1) {
            if (tickets[i]!= null &&tickets[i].getRow().equals(Row) && tickets[i].getSeat_no() == Seat_no) {
                tickets[i] = null;
                break;
            }
        }
        if (1 <= Seat_no && Seat_no <= 5) {
            Price = 200;
        } else if (Seat_no >= 6 && Seat_no <= 9) {
            Price = 150;
        } else {
            Price = 180;
        }
        Person person = new Person(Name, Surname, Email);
        Ticket ticket = new Ticket(Row, Seat_no, Price, person);
        tickets[ticket_count-1]=ticket;
        ticket_count = ticket_count-1;              //Counting the No. of tickets
        Total_Amount=Total_Amount-Price;            //Counting the Total Sales

        System.out.println();
        System.out.println("Seat [" + Row + Seat_no + "] cancelled successfully!");
        System.out.println();
    }

    private static void First_Available_Seat() {             //Method to find the first available seat
        for (int i = 0; i < Seat_List.length; i = i + 1) {
            for (int j = 0; j < Seat_List[i].length; j = j + 1) {
                if (Seat_List[i][j] == 0) {
                    System.out.println();
                    System.out.println("The first available seat is: " + (char) ('A' + i) + (j + 1));
                    System.out.println();
                    return;
                }
            }
        }

    }

    private static void Seating_plan() {                    //Method to get the seating plan
        System.out.println("Seating Plan: ");
        System.out.println("   1  2  3  4  5  6  7  8  9 10 11 12 13 14 ");
        for (i = 0; i < Seat_List.length; i = i + 1) {
            System.out.print((char) ('A' + i) + "  ");

            for (int j = 0; j < Seat_List[i].length; j = j + 1) {
                if (Seat_List[i][j] == 0) {
                    System.out.print("0  ");
                } else {
                    System.out.print("X  ");
                }
            }
            System.out.println();
        }
    }
    private static void Search_Ticket() {                         //Method to search for a seat
        Row_SeatNum_Validation();                                  //Calling the method to get Row and seat Number

        int Row_Number = Row.charAt(0) - 'A';
        if (Row_Number < 0 || Row_Number >= Seat_List.length || Seat_no > Seat_List[Row_Number].length) {
            System.out.println();
            System.out.println("Invalid selection.Please try again.");
            System.out.println();
            return;
        }
        boolean seat_found = false;
        for (i = 0; i < ticket_count; i = i + 1) {
            Ticket ticket = tickets[i];

            if (ticket.getRow().equals(Row) && ticket.getSeat_no() == Seat_no) {
                System.out.println();
                System.out.println("Seat[" + Row + Seat_no + "] is already booked");
                System.out.println("Row: " + ticket.getRow());
                System.out.println("Seat: " + ticket.getSeat_no());
                System.out.println("Price: £" + ticket.getPrice());
                System.out.println("Person Information:");
                System.out.println("Name: " + ticket.getPerson().getName());
                System.out.println("Surname: " + ticket.getPerson().getSurname());
                System.out.println("Email: " + ticket.getPerson().getEmail());
                System.out.println();
                return;
            }
        }
        if (!seat_found) {
            System.out.println(" ");
            System.out.println("Seat [" + Row + Seat_no + "] is available.");
            System.out.println(" ");
        }
    }
     private static void Print_Ticket_info() {                //Method to Print the Ticket and Ticket info.

         if(ticket_count ==0) {
             System.out.println();
             System.out.println("No tickets have been sold");
             System.out.println();
         }
         else{
             for (int i = 0; i < ticket_count; i = i + 1) {
                     Ticket ticket = tickets[i];
                     if(ticket !=null && Total_Amount!=0) {
                         System.out.println("------------------------");
                         System.out.println("Ticket " + (i + 1) + ":");
                         System.out.println("Row : " + ticket.getRow());
                         System.out.println("Seat : " + ticket.getSeat_no());
                         System.out.println("Price : £" + ticket.getPrice());
                         System.out.println("Person's Information : ");
                         System.out.println("Name : " + ticket.getPerson().getName());
                         System.out.println("Surname : " + ticket.getPerson().getSurname());
                         System.out.println("Email : " + ticket.getPerson().getEmail());
                         System.out.println();

                     }
                 }
                 System.out.println("Total sales: " + Total_Amount + " £");
                 System.out.println("ticket count: " + ticket_count);
                 System.out.println();

         }
     }
    public static void Row_SeatNum_Validation(){                         //Method to get Row and Seat number

        Scanner input = new Scanner(System.in);
        while (true){
            System.out.print("Enter the Row Letter[A-D]: ");
            Row = input.nextLine().toUpperCase();
            if (Row.matches("[A-D]")){
                break;
            } else {
                System.out.println();
                System.out.println("Invalid choice .A Letter required.");
                System.out.println();
            }
        }
        while(true){
            System.out.print("Enter the Seat Number: ");
            try {
                Seat_no = input.nextInt();
                break;
            }catch(InputMismatchException e) {
                input.next();
                System.out.println();
                System.out.println("Invalid Choice .Integer Required.");
                System.out.println("  ");
            }}
    }}













