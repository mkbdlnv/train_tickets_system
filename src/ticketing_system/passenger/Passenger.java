package ticketing_system.passenger;

public class Passenger {
    private String name;
    private String surname;
    private int age;
    private String gender;
    private String iin;
    private String phone;
    protected double discount;
    protected String type;

    public Passenger(String name, String surname, int age, String gender, String iin, String phone){
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
        this.iin = iin;
        this.phone = phone;
    }

    

    public String getName() {
        return name;
    }



    public String getSurname() {
        return surname;
    }



    public int getAge() {
        return age;
    }



    public String getGender() {
        return gender;
    }



    public String getIin() {
        return iin;
    }



    public String getPhone() {
        return phone;
    }



    public double getDiscount() {
        return discount;
    }



    public String getType() {
        return type;
    }



    public String getInfo(){
        return String.format("PASSENGER:\nFull name: %s %s\nAge: %s\nGenger: %s\nIIN: %s\nPhone: %s",name, surname, age, gender, iin, phone);
    }
}
