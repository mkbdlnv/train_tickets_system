package ticketing_system.car;

public class Car {
    private int id;
    private int sits;
    private int carNumber;
    private double price;
    protected String carClass;
    public Car(int id, int sits, int carNumber, double price){
        this.sits = sits;
        this.id = id;
        this.carNumber = carNumber;
        this.price = price;
    }
    
    public int getId() {
        return id;
    }
    public int getSits() {
        return sits;
    }
    public int getCarNumber() {
        return carNumber;
    }
    public double getPrice() {
        return price;
    }
    
    public String getCarClass(){
        return this.carClass;
    }

    public void setSitsNumber(int sits){
        this.sits = sits;
    }
}
