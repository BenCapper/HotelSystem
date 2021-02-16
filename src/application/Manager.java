package application;

public class Manager extends User{
    private String managerIdNum;

    public Manager (String ppsNum, String managerIdNum, String name, String password, String phoneNumber, String department){
        super(ppsNum, name, password, phoneNumber, department);
        this.managerIdNum = managerIdNum;
    }

    public String getManagerIdNum() {
        return managerIdNum;
    }

    public void setManagerIdNum(String managerIdNum) {
        this.managerIdNum = managerIdNum;
    }

    @Override
    public String toString() {
        return super.toString() + "Manager Id Number: " + managerIdNum + "\n" +
                "<-------------------------------------------------------------------------------------------------------------->" + "\n" +"\n";
    }
}