package ticketing_system.passenger;

public class Disabled extends Passenger{

    public Disabled(String name, String surname, int age, String gender, String iin, String phone) {
        super(name, surname, age, gender, iin, phone);
        this.type = "Disabled";
        this.discount = 30;
    }
    
}
