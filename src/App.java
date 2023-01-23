import ticketing_system.car.*;
import ticketing_system.locomotive.*;
import ticketing_system.passenger.Adult;
import ticketing_system.passenger.Child;
import ticketing_system.passenger.Disabled;
import ticketing_system.passenger.Passenger;
import ticketing_system.train.*;
import ticketing_system.stop.*;
import ticketing_system.ticket.Ticket;
import ticketing_system.db.Database;
import ticketing_system.route.Route;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Time;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.Array;
import java.util.Scanner;

public class App {
    static void print(String str){
        System.out.print(str);
    }

    static void print_line(){
        System.out.println("_____________________________________");
    }
    public static void main(String[] args) throws Exception {
        Database db= new Database();
        ArrayList<Route> routes = new ArrayList<>();
        ArrayList<Locomotive> locomotives = new ArrayList<>();
        ArrayList<Train> trains = new ArrayList<>();
        ArrayList<Ticket> tickets = new ArrayList<>();

        //retrieve data about routes from DB
        ResultSet data = db.getData("routes");
        while(data.next()){
            ArrayList<Stop> stops = new ArrayList<>();
            Database db1 = new Database();
            Array arr = data.getArray("stops");
            Integer[] stopsId = null;
            stopsId = (Integer[])arr.getArray();
            
            for(int stopId : stopsId){
                ResultSet data1 = db1.getData("stops WHERE stop_id="+stopId);
                while(data1.next()){
                    String stopName = data1.getString("stop_name");
                    Time departureTime = data1.getTime("departure_time");
                    Time arriveTime = data1.getTime("arrive_time");
                    stops.add(new Stop(stopId, stopName, departureTime, arriveTime));
                }
            }
            int routeId = data.getInt("route_id");
            String routeName = stops.get(0).getName()+"-"+stops.get(stops.size()-1).getName();
            routes.add(new Route(routeId, routeName, stops));
            db1.close();
        }

        //retrieve data about locomotives
        data = db.getData("locomotives");
        while(data.next()){
            int locomotiveId = data.getInt("locomotive_id");
            String locomotiveName = data.getString("locomotive_name");
            int numOfKupe = data.getInt("numofkupe");
            int numOfPlatskart = data.getInt("numofplatskart");
            int numOfLux = data.getInt("numoflux");
            locomotives.add(new Locomotive(locomotiveId, locomotiveName, numOfKupe, numOfPlatskart, numOfLux));
        }

        //retrieve data about trains
        data = db.getData("trains");
        while(data.next()){
            int trainId = data.getInt("train_id");
            int routeId = data.getInt("route_id");
            int locomotiveId = data.getInt("locomotive_id");
            Route route = null;
            Locomotive locomotive = null;
            for(Route r:routes){
                if(r.getId()==routeId){
                    route = r;
                    break;
                }
            }
            for(Locomotive l:locomotives){
                if(l.getId()==locomotiveId){
                    locomotive = l;
                    break;
                }
            }

            Database db1 = new Database();
            ResultSet data1 = db1.getData("cars WHERE car_class='platskart'");
            ArrayList<Car> cars = new ArrayList<>();
            int number = 1;
            while(data1.next()){
                for(int i=0;i<locomotive.getNumOfPlatskart();i++){
                    int carId = data1.getInt("car_id");
                    int sits = data1.getInt("sits");
                    double price = data1.getDouble("price");
                    cars.add(new Platskart(carId, sits, number, price));
                    number++;
                }
            }
            

            data1 = db1.getData("cars WHERE car_class='kupe'");
            while(data1.next()){
                for(int i=0;i<locomotive.getNumOfKupe();i++){
                
                    int carId = data1.getInt("car_id");
                    int sits = data1.getInt("sits");
                    double price = data1.getDouble("price");
                    cars.add(new Kupe(carId, sits, number, price));
                    number++;
                }
            }
            

            data1 = db1.getData("cars WHERE car_class='lux'");
            while(data1.next()){
                for(int i=0;i<locomotive.getNumOfLux();i++){
                    int carId = data1.getInt("car_id");
                    int sits = data1.getInt("sits");
                    double price = data1.getDouble("price");
                    cars.add(new Lux(carId, sits, number, price));
                    number++;
                }

            }
            
            trains.add(new Train(trainId, route, locomotive, cars));
            db1.close();
        }


        //prompt menu
        Scanner in = new Scanner(System.in);
        boolean run = true;
        while(run){
            print("Welcome to train ticketing system! Select an option to do:\n"+
            "1. View schedule\n2. Buy ticket\n3. Close program\n");
            String prompt = in.nextLine();
            switch(prompt){
                case "1":
                    showSchedule(trains);
                    break;
                case "2":
                    tickets.add(buyTicket(trains));
                    break;
                case "3":
                    run = false;
                    break;
                default:
                    print("Please choose suggested option (1, 2 or 3)\n");
            }
        }
        
        db.close();
    }


    //prompt functions
    static void showSchedule(ArrayList<Train> trains){
        print("ID\t\t\t\tName\t\t\t\t\tDeparture\t\t\t\tArrive\n");
        for(Train train:trains){
            int id = train.getId();
            String name = train.getRoute().getName();
            Time departure = train.getRoute().getStops().get(0).getDepartureTime();
            int size = train.getRoute().getStops().size();
            Time arrive = train.getRoute().getStops().get(size-1).getArriveTime();
            print(id+"\t\t\t\t"+name+"\t\t\t\t"+departure+"\t\t\t\t"+arrive+"\n");
        }
       
    }

    static Ticket buyTicket(ArrayList<Train> trains){
        print("Input ID of the train: ");
        Scanner in = new Scanner(System.in);
        int trainId = in.nextInt();
        in.nextLine();
        print("Input name: ");
        String name = in.nextLine();
        print("Input surname: ");
        String surname = in.nextLine();
        print("Input age: ");
        int age = in.nextInt();
        in.nextLine();
        print("Input gender (male/female): ");
        String gender = in.nextLine();
        print("Input iin: ");
        String iin = in.nextLine();
        print("Input phone: ");
        String phone = in.nextLine();
        print("Input '+' if passenger has disabilty, otherwise '-': ");
        String disablity = in.nextLine();

        Passenger passenger;
        if(disablity.equals("+")){
            passenger = new Disabled(name, surname, age, gender, iin, phone);
        }
        else if(age<15){
            passenger = new Child(name, surname, age, gender, iin, phone);
        }
        else{
            passenger = new Adult(name, surname, age, gender, iin, phone);
        }

        Train train = null;
        for(Train t:trains){
            if(t.getId()==trainId){
                train = t;
                break;
            }
        }
        
        print("Input type of car (platskart/kupe/lux): ");
        String carClass = in.nextLine();
        
        for(Car car:train.getCars()){
            if(car.getCarClass().equals(carClass)){
                print("Car number: "+car.getCarNumber()+"\tNumber of sits: "+car.getSits()+"\n");
            }
        }
        print("Input number of car: ");
        int carNumber = in.nextInt();
        
        double price = train.getCars().get(carNumber-1).getPrice() - (train.getCars().get(carNumber-1).getPrice()/100)*passenger.getDiscount();
        Ticket ticket = new Ticket(train, price, passenger, carNumber);
        train.getCars().get(carNumber-1).setSitsNumber(train.getCars().get(carNumber-1).getSits()-1);
        printTicket(ticket);
        return ticket;
    }

    static void printTicket(Ticket ticket){
        print_line();
        print(ticket.getPassenger().getInfo()+"\n");
        print_line();
        print(ticket.getTrain().getInfo()+"\n");
        print_line();
        print(ticket.getInfo());
        print_line();
    }
}
