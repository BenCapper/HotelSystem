package application;

public class SeatingTable {
    private String number,department, capacity, availability;

    public SeatingTable(String number,String capacity, String department, String availability){
        this.number = number;
        this.capacity = capacity;
        this.department = department;
        this.availability = availability;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String isAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "<----------------------------------------------------------------------------------------------------------->" + "\n" +
                "Table Number: " + number + " || " +
                "Department: " + department + " || " +
                "Capacity: " + capacity + " || " +
                "Availability: " + availability  + "\n" +
                "<-------------------------------------------------------------------------------------------------------------->" + "\n" + "\n";

    }
}
