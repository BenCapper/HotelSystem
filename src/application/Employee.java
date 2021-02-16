package application;

public class Employee extends User{
    private String employeeIdNum;

    public Employee (String ppsNum, String employeeIdNum, String name, String password, String phoneNumber, String department){
        super(ppsNum, name, password, phoneNumber, department);
        this.employeeIdNum = employeeIdNum;
    }

    public String getEmployeeIdNum() {
        return employeeIdNum;
    }

    public void setEmployeeIdNum(String employeeIdNum) {
        this.employeeIdNum = employeeIdNum;
    }

    @Override
    public String toString() {
        return super.toString() + "Employee Id Number:   " + employeeIdNum + "\n" +
                "<-------------------------------------------------------------------------------------------------------------->" + "\n" +"\n";
    }
}
