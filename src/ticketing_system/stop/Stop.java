package ticketing_system.stop;
import java.sql.Time;



public class Stop {
    private int id;
    private String name;
    private Time departureTime;
    private Time arriveTime;

    public Stop(int id, String stopName, Time departureTime, Time arriveTime) {
        this.id = id;
        this.name = stopName;
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Time getDepartureTime() {
        return departureTime;
    }

    public Time getArriveTime() {
        return arriveTime;
    }

    
    
}
