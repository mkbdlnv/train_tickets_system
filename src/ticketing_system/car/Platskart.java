package ticketing_system.car;

public class Platskart extends Car{
    
    public Platskart(int id, int sits, int carNumber, Double price) {
        super(id, sits, carNumber, price);
        //TODO Auto-generated constructor stub
        this.carClass = "platskart";
    }
    
}
