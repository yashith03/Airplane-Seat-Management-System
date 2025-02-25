import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
public class Ticket {                // Representing  tickets information
    private String Row;
    private int Seat_no;
    private int Price;
    private Person person;           //Person Associate with the Ticket

    public Ticket(String Row, int Seat_no, int Price, Person person) {  //constructor
        this.Row = Row;
        this.Price = Price;
        this.Seat_no = Seat_no;
        this.person = person;
    }

    public String getRow() {
        return Row;
    }

    public int getSeat_no() {
        return Seat_no;
    }

    public int getPrice() {
        return Price;
    }

    public Person getPerson() {
        return person;
    }

    public void setRow(String Row) {
        this.Row = Row;
    }

    public void setSeat_no(int Seat_no) {
        this.Seat_no = Seat_no;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void save() {                   //Method to save Ticket info. to a file
        String Full_Name = person.getName() + " " + person.getSurname();
        String File_Name = "seat " + Row + String.valueOf(Seat_no) + ".txt";
        try (FileWriter writer = new FileWriter(File_Name)) {
            writer.write("Seat : " + Row + Seat_no + "\n");
            writer.write("Price : £" + Price + "\n");
            writer.write("Full Name : " + Full_Name + "\n");
            writer.write("Email : " + person.getEmail() + "\n");
            System.out.println();
            System.out.println("Ticket info. saved to file: " + File_Name);
        } catch (IOException e) {
            System.out.println("Ticket saving unsuccessfull.");
        }
    }
}





