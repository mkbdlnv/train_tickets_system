package ticketing_system.ticket;
import ticketing_system.train.Train;
import ticketing_system.passenger.Passenger;
public class Ticket {
    private Train train;
    private double price;
    private Passenger passenger;
    private int carNumber;

    public Ticket(Train train, double price, Passenger passenger, int carNumber){
        this.train = train;
        this.price = price;
        this.passenger = passenger;
        this.carNumber = carNumber;
    }

    public Train getTrain() {
        return train;
    }

    public double getPrice() {
        return price;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public String getInfo(){
        return String.format("Price: %s\nCar number: %s\n", price, carNumber);
    }

}   
