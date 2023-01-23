package ticketing_system.passenger;

public class Child extends Passenger{

    public Child(String name, String surname, int age, String gender, String iin, String phone) {
        super(name, surname, age, gender, iin, phone);
        this.type = "Child";
        this.discount = 50;
    }

    
}
