package ticketing_system.locomotive;

public class Locomotive {
    private int id;
    private String name;
    private int numOfKupe;
    private int numOfPlatskart;
    private int numOfLux;
    
    public Locomotive(int id, String name, int numOfKupe, int numOfPlatskart, int numOfLux) {
        this.id = id;
        this.name = name;
        this.numOfKupe = numOfKupe;
        this.numOfPlatskart = numOfPlatskart;
        this.numOfLux = numOfLux;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumOfKupe() {
        return numOfKupe;
    }

    public int getNumOfPlatskart() {
        return numOfPlatskart;
    }

    public int getNumOfLux() {
        return numOfLux;
    }

    
}
