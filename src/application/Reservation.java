package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Reservation {
    private String resNum, tableNum,cardNumber, customerName, comments, timeSlotAllocated;
    private LocalDate dateOfReservation;

    public Reservation(String resNum, String tableNum, String cardNumber,LocalDate dateOfReservation, String timeSlotAllocated, String customerName, String comments){
        this.resNum = resNum;
        this.tableNum = tableNum;
        this.cardNumber = cardNumber;
        this.dateOfReservation = dateOfReservation;
        this.timeSlotAllocated = timeSlotAllocated;
        this.customerName = customerName;
        this.comments = comments;
    }

    public String getResNum() {
        return resNum;
    }

    public void setResNum(String resNum) {
        this.resNum = resNum;
    }

    public String getTableNum() {
        return tableNum;
    }

    public void setTableNum(String tableNum) {
        this.tableNum = tableNum;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getDateOfReservation() {
        return this.dateOfReservation;
    }

    public void setDateOfReservation(LocalDate dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public String getTimeSlotAllocated() {
        return timeSlotAllocated;
    }

    public void setTimeSlotAllocated(String timeSlotAllocated) {
        this.timeSlotAllocated = timeSlotAllocated;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "<----------------------------------------------------------------------------------------------------------->" + "\n" +
                "Reservation Number: " + resNum + " || " +
                "Table Number: " + tableNum + " || " +
                "Time Booked: " + timeSlotAllocated + " || " +
                "Date Of Reservation: " + dateOfReservation + " || \n" +
                "Customer Name: " + customerName + " || " +
                "Credit Card Number: " + cardNumber + " || " +
                "Comments: " + comments + " || " + "\n" +
                "<-------------------------------------------------------------------------------------------------------------->" + "\n" +"\n";
    }
}
