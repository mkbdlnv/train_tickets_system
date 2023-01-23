package ticketing_system.passenger;

public class Adult extends Passenger{

    public Adult(String name, String surname, int age, String gender, String iin, String phone) {
        super(name, surname, age, gender, iin, phone);
        //TODO Auto-generated constructor stub
        this.type = "Adult";
    }
    
}
