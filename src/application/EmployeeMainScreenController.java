package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeMainScreenController implements Initializable {
    private RestaurantReserveModel reservations, users;
    String timeSlot, timeSlot2;
    @FXML
    private TextField resNum, tableNum, customerName, comments, cardNumber, tab2ResNum, tab2TableNum, tab2CustomerName, tab2Comments, tab2CardNumber,
            tab3EmployeeId, tab3EmployeeName, tab3EmployeePhoneNum;
    @FXML
    private TextArea feedback, tab2Feedback, tab3Feedback;
    @FXML
    private DatePicker dateOfReservation, tab2DateOfReservation;
    @FXML
    private ComboBox comboTabTimeSlot;
    @FXML
    private ComboBox comboTab2TimeSlot;
    @FXML
    private PasswordField tab3EmployeePassword, tab3EmployeePasswordConfirm;


    public EmployeeMainScreenController() {
    }


    /* Reads in a reservation based on user input ID Number */
    public void handleSearchResNumberButton(ActionEvent e) throws Exception {
        String resNum = tab2ResNum.getText();
        Reservation reservationDetails = reservations.searchReservationById(resNum);
        if (reservationDetails == null) {
            tab2Feedback.setText("Invalid Reservation Number");
        } else {
            tab2TableNum.setText(reservationDetails.getTableNum());
            tab2DateOfReservation.setValue(reservationDetails.getDateOfReservation());
            comboTab2TimeSlot.setValue(reservationDetails.getTimeSlotAllocated());
            tab2CustomerName.setText(reservationDetails.getCustomerName());
            tab2Comments.setText(reservationDetails.getComments());
            tab2CardNumber.setText(reservationDetails.getCardNumber());
            tab2Feedback.setText("Reservation Details Read In Successfully");

        }

    }

    /* Reads in Employee details based on employeeIdNum */
    public void handleSearchEmployeeNumberButton(ActionEvent e) throws Exception{
        String empNum = tab3EmployeeId.getText();
        Employee employeeDetails = (Employee)users.searchEmployeeById(empNum);
        if(employeeDetails == null){
            tab3Feedback.setText("Invalid Employee Number");
        }
        else {
            tab3EmployeeName.setText(employeeDetails.getName());
            tab3EmployeePassword.setText(employeeDetails.getPassword());
            tab3EmployeePhoneNum.setText(employeeDetails.getPhoneNumber());
            tab3Feedback.setText("Reservation Details Read In Successfully");

        }
    }

    /* Creates an instance of a reservation  */
    public void handleResButton(ActionEvent e) throws Exception {
        int errorCount = 0;

        String resNum = this.resNum.getText();
        for (int i = 0; i < reservations.numberOfReservations(); i++) {


            if (resNum.isEmpty()) {
                errorCount++;
                feedback.setText("Enter a Reservation Number");
            } else if (resNum.matches(reservations.getReservationDetails(i).getResNum())) {
                errorCount++;
                feedback.setText("This Reservation Number is in Use");
            }
        }
        String tableNum = this.tableNum.getText();
        if (tableNum.isEmpty()) {
            errorCount++;
            feedback.setText("Enter a Table Number \n");
        }
        String customerName = this.customerName.getText();
        if (customerName.isEmpty()) {
            errorCount++;
            feedback.appendText("Enter a Customer Name \n");
        }

        String comments = this.comments.getText();
        if (comments.isEmpty()) {
            errorCount++;
            feedback.appendText("Enter a Comment \n");
        }
        String cardNum = this.cardNumber.getText();
        if (cardNum.isEmpty()) {
            errorCount++;
            feedback.appendText("Enter Credit Card Number \n");
        }
        String timeSlotAllocated = this.comboTabTimeSlot.getValue().toString();
        if (this.comboTabTimeSlot.getValue().toString().matches("Select Time Slot")) {
            errorCount++;
            feedback.appendText("Select Time Slot \n");
        }

        LocalDate dateOfRes = this.dateOfReservation.getValue();
        if (this.dateOfReservation.getValue() == null) {
            errorCount++;
            feedback.appendText("Select a Date");
        }

        String count = String.valueOf(errorCount);
        if (errorCount <= 0) {
            for (int i = 0; i < reservations.numberOfReservations(); i++) {
                if (this.reservations.getReservationDetails(i).getDateOfReservation().toString().matches(reservations.getReservationDetails(i).getDateOfReservation().toString())
                        && timeSlotAllocated.matches(reservations.getReservationDetails(i).getTimeSlotAllocated()) && tableNum.matches(reservations.getReservationDetails(i).getTableNum())) {
                    errorCount++;
                    feedback.setText("This Date / Time / Table Combination is Reserved");
                }
            }
            if (errorCount == 0) {
                reservations.addReservation(resNum, tableNum, cardNum, dateOfRes, timeSlotAllocated, customerName, comments);
                feedback.setText("Reservation for " + timeSlotAllocated + " " + dateOfRes + " created successfully" + "\n");
                reservations.saveReservations();
                feedback.appendText("Reservation Saved");
            }
        }
    }

    /* Updates Card Number attribute of reservation (second tab)  */
        public void handleTab2UpdateButton(ActionEvent e) throws Exception {
            int errorCount = 0;
            if (tab2ResNum.getText().isEmpty()) {
                tab2Feedback.setText("Search for a Reservation to Update with the Reservation ID Number");
                errorCount++;
            } else if (tab2TableNum.getText().isEmpty()) {
                tab2Feedback.setText("Enter a Table Number");
                errorCount++;
            } else if (tab2DateOfReservation.getValue().toString().isEmpty()) {
                tab2Feedback.setText("Select a Date");
                errorCount++;
            } else if (comboTab2TimeSlot.getValue().toString().isEmpty()) {
                tab2Feedback.setText("Select a Time");
                errorCount++;
            } else if (customerName.getText().isEmpty()) {
                tab2Feedback.setText("Enter a Customer Name");
                errorCount++;
            } else if (comments.getText().isEmpty()) {
                tab2Feedback.setText("Enter Reservation Comments");
                errorCount++;
            } else if (cardNumber.getText().isEmpty()) {
                tab2Feedback.setText("Enter a Credit Card Number");
                errorCount++;
            }
            if (errorCount <= 0) {
                for (int i = 0; i < reservations.numberOfReservations(); i++) {
                    if (reservations.getReservationDetails(i).getTableNum().matches(tab2TableNum.getText()) &&
                            reservations.getReservationDetails(i).getTimeSlotAllocated().matches(comboTab2TimeSlot.getValue().toString()) &&
                            reservations.getReservationDetails(i).getDateOfReservation().isEqual(tab2DateOfReservation.getValue())) {
                        tab2Feedback.setText("This Date / Time / Table Combination is Reserved");
                    } else {

                        String id = tab2ResNum.getText();
                        String newTableValue = tab2TableNum.getText();
                        LocalDate newDateValue = tab2DateOfReservation.getValue();
                        String newTimeValue = comboTab2TimeSlot.getValue().toString();
                        String newCustomerName = tab2CustomerName.getText();
                        String newCommentsValue = tab2Comments.getText();
                        String newCardValue = tab2CardNumber.getText();
                        reservations.searchReservationById(id).setTableNum(newTableValue);
                        tab2Feedback.setText("Reservation Table Number Updated to: " + newTableValue + "\n");
                        reservations.searchReservationById(id).setDateOfReservation(newDateValue);
                        tab2Feedback.appendText("Reservation Date Updated to: " + newDateValue + "\n");
                        reservations.searchReservationById(id).setTimeSlotAllocated(newTimeValue);
                        tab2Feedback.appendText("Reservation Time Updated to: " + newTimeValue + "\n");
                        reservations.searchReservationById(id).setCustomerName(newCustomerName);
                        tab2Feedback.appendText("Customer Name Updated to: " + newCustomerName + "\n");
                        reservations.searchReservationById(id).setComments(newCommentsValue);
                        tab2Feedback.appendText("Reservation Comments Updated to: " + newCommentsValue + "\n");
                        reservations.searchReservationById(id).setCardNumber(newCardValue);
                        tab2Feedback.appendText("Credit Card Number Updated to: " + newCardValue + "\n");
                        reservations.saveReservations();
                    }
                }
            }
        }

    /* Updates Phone Number attribute of reservation (Third tab)  */
    public void handleTab3UpdatePhoneButton(ActionEvent e) throws Exception{
        int errorCount = 0;
        String id = tab3EmployeeId.getText();
        if(id.isEmpty()){
            tab3Feedback.setText("Cannot Update Employee Number" + "\n");
            errorCount++;
        }
        String newNameValue = tab3EmployeeName.getText();
        if(newNameValue.isEmpty()){
            tab3Feedback.appendText("Enter Employee Name" + "\n");
            errorCount++;
        }
        String newPasswordValue = tab3EmployeePassword.getText();
        if(newPasswordValue.isEmpty()){
            tab3Feedback.appendText("Enter Employee Password" + "\n");
            errorCount++;
        }
        String newPhoneValue = tab3EmployeePhoneNum.getText();
        if(newPhoneValue.isEmpty()){
            tab3Feedback.appendText("Enter Employee Phone Number" + "\n");
            errorCount++;
        }
        for (int i = 0; i < users.numberOfUsers(); i++) {

            if (!tab3EmployeePasswordConfirm.getText().isEmpty() && !tab3EmployeePassword.getText().matches(users.getUser(i).getPassword())) {
                tab3Feedback.appendText("Old Password Must be Entered to Set a New Password" + "\n");
                errorCount++;
            }
            else if(!id.matches(((Manager)users.getUserDetails(i)).getManagerIdNum()) || !id.matches(((Employee)users.getUserDetails(i)).getEmployeeIdNum())){
                tab3Feedback.setText("Enter a Valid ID Number");
                errorCount++;
            }
        }
        if(errorCount == 0) {
            users.searchEmployeeById(id).setName(newNameValue);
            tab3Feedback.appendText("Employee Name Updated to: " + newNameValue + "\n");
            users.searchEmployeeById(id).setPassword(newPasswordValue);
            tab3Feedback.appendText("Employee Password Updated to: " + newPasswordValue + "\n");
            users.searchEmployeeById(id).setPhoneNumber(newPhoneValue);
            tab3Feedback.appendText("Employee Phone Number Updated to: " + newPhoneValue + "\n");
            users.saveUsers();

        }
    }


    public void handleLoadReservationButton(ActionEvent e) throws Exception{
        reservations.loadReservations();
        if(reservations.numberOfReservations() <= 0){
            tab2Feedback.setText("There are no Reservations Booked");
        }
        else {
            feedback.setText("Reservations Loaded");
            tab2Feedback.setText("Reservations Loaded");
        }
    }

    public void handleTab3LoadButton(ActionEvent e) throws Exception{
        users.loadUsers();
        feedback.setText("Employee's Loaded");
    }





    public void handleLogOutButton(ActionEvent e) throws Exception{
        Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Restaurant Reservation System");
        primaryStage.setScene(new Scene(root, 700, 400));
        root.getStylesheets().add(getClass().getResource("PurpleTheme.css").toExternalForm());
        primaryStage.show();
    }


    ObservableList<String> times = FXCollections.observableArrayList("Select Time Slot","5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM");
    ObservableList<String> times2 = FXCollections.observableArrayList("Select Time Slot","5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM");
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        reservations = new RestaurantReserveModel();
        users = new RestaurantReserveModel();
        timeSlot = "Select Time Slot";
        timeSlot2 = "Select Time Slot";
        comboTabTimeSlot.setItems(times);
        comboTab2TimeSlot.setItems(times2);
        comboTabTimeSlot.setValue("Select Time Slot");
        comboTab2TimeSlot.setValue("Select Time Slot");

    }
}