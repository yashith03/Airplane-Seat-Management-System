public class Person {    //Representing persons info.
    private String Name;
    private String Surname;
    private String Email;

    public Person(String Name, String Surname,String Email){  //Constructors
       this.Name=Name;
       this.Surname=Surname;
       this.Email=Email;

    }
    public String getName(){
        return Name;
    }public String getSurname() {
        return Surname;
    }public String getEmail(){
        return Email;
    }

    public void setName(String Name){
        this.Name=Name;
    }
    public void setSurname(String Surname){
        this.Surname=Surname;
    }
    public void setEmail(String Email){
        this.Email=Email;
    }
}


