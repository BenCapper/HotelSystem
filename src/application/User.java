package application;

public abstract class User {
    private String ppsNum, name, password, phoneNumber, department;

    public User(){
    }

    public User (String ppsNum, String name, String password, String phoneNumber, String department){
        this.ppsNum = ppsNum;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.department = department;
    }

    public String getPpsNum() {
        return ppsNum;
    }

    public void setPpsNum(String ppsNum) {
        this.ppsNum = ppsNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return
                "<----------------------------------------------------------------------------------------------------------->" + "\n" +
                "Name: " + name + " || " +
                        "PPS Number: " + ppsNum + " || " +
                "Password: " + password + " || \n" +
                "PhoneNumber: " + phoneNumber + " || " +
                "Department: " + department + " || ";
    }
}
