package ticketing_system.train;
import ticketing_system.locomotive.Locomotive;
import ticketing_system.stop.Stop;
import ticketing_system.car.Car;
import ticketing_system.route.Route;
import java.util.ArrayList;

public class Train {
    private int id;
    private Locomotive locomotive;
    private Route route;
    private ArrayList<Car> cars;

    public Train(int id, Route route, Locomotive locomotive, ArrayList<Car> cars){
        this.id = id;
        this.route = route;
        this.locomotive = locomotive;
        this.cars = cars;
    }

    

    public int getId() {
        return id;
    }



    public Locomotive getLocomotive() {
        return locomotive;
    }



    public Route getRoute() {
        return route;
    }



    public ArrayList<Car> getCars() {
        return cars;
    }

    public String getInfo(){
       return String.format("TRAIN:\nID: %s\nName: %s\n",id, route.getName(), id, route.getName());
    }
}
