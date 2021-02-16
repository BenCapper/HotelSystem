package application;

import java.io.IOException;

import javafx.beans.property.BooleanProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ManagerMainScreenController implements Initializable {
    protected RestaurantReserveModel reservations, users, tables;
    String timeSlot, timeSlot2, timeslot3, roles, tab3Dept, tab4Dept, tab4Avail;
    @FXML
    private TextField resNum, tableNum, customerName, comments, cardNumber, tab2ResNum, tab2TableNum, tab2CustomerName, tab2Comments, tab2CardNumber,
                      tab3EmployeeId, tab3EmployeeName, tab3EmployeePhoneNum, tab3EmployeePps, tab4TableNum, tab4TableCapacity, tab5ResNum, tab5TableNum;
    @FXML
    private TextArea feedback, tab2Feedback, tab3Feedback, tab4Feedback, tab5Feedback;
    @FXML
    private DatePicker dateOfReservation, tab2DateOfReservation, tab5DateOfReservation;
    @FXML
    private ComboBox comboTimeBooked;
    @FXML
    private ComboBox comboTab3EmployeeRole;
    @FXML
    private ComboBox comboTab2TimeBooked;
    @FXML
    private ComboBox comboTab3EmployeeDepartment;
    @FXML
    private ComboBox comboTab4TableDepartment;
    @FXML
    private ComboBox comboTab4TableAvailability;
    @FXML
    private ComboBox comboTab5TimeSlot;
    @FXML
    private PasswordField tab3EmployeePassword, tab3EmployeePasswordConfirm;


    public ManagerMainScreenController() {
    }

    /*<--------------------------------------------------------------<UPDATE BUTTONS>--------------------------------------------------------->*/

    /*<--------------------------------------------------------------<DELETE BUTTONS>--------------------------------------------------------->*/

    /* Updates Table Number attribute of a Reservation (Second tab)  */
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
        } else if (comboTab2TimeBooked.getValue().toString().isEmpty()) {
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
                        reservations.getReservationDetails(i).getTimeSlotAllocated().matches(comboTab2TimeBooked.getValue().toString()) &&
                        reservations.getReservationDetails(i).getDateOfReservation().isEqual(tab2DateOfReservation.getValue())) {
                    tab2Feedback.setText("This Date / Time / Table Combination is Reserved");
                } else {

                    String id = tab2ResNum.getText();
                    String newTableValue = tab2TableNum.getText();
                    LocalDate newDateValue = tab2DateOfReservation.getValue();
                    String newTimeValue = comboTab2TimeBooked.getValue().toString();
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

    /* Updates Capacity attribute of a table (Fourth tab)  */
    public void handleTab4UpdateButton(ActionEvent e) throws Exception {
        int errorCount = 0;
        if (tab4TableNum.getText().isEmpty()) {
            errorCount++;
            tab4Feedback.setText("Search for a Table to Update with the Table Number \n");
        } else if (comboTab4TableDepartment.getValue().toString().isEmpty()) {
            errorCount++;
            tab4Feedback.appendText("Select a Department \n");
        } else if (comboTab4TableAvailability.getValue().toString().isEmpty()) {
            errorCount++;
            tab4Feedback.appendText("Select Availability \n");
        } else if (tab4TableCapacity.getText().isEmpty()) {
            errorCount++;
            tab4Feedback.appendText("Enter Capacity \n");
        }
        for (int i = 0; i < tables.numberOfTables(); i++) {
            if (tables.getTable(i).getNumber().matches(tab4TableNum.getText()) && errorCount == 0) {

                String id = tab4TableNum.getText();
                String newDeptValue = comboTab4TableDepartment.getValue().toString();
                String newCapacityValue = tab4TableCapacity.getText();
                String newAvailValue = comboTab4TableAvailability.getValue().toString();
                tables.searchTableById(id).setDepartment(newDeptValue);
                tab4Feedback.setText("Table Department Updated to: " + newDeptValue + "\n");
                tables.searchTableById(id).setCapacity(newCapacityValue);
                tab4Feedback.appendText("Table Capacity Updated to: " + newCapacityValue + "\n");
                tables.searchTableById(id).setAvailability(newAvailValue);
                tab4Feedback.appendText("Table Availability Updated to: " + newAvailValue + "\n");
                tables.saveTables();
                tab4Feedback.appendText("Table Saved" + "\n");
            }
        }
    }

    /* Updates Name attribute of an Employee (Third tab)  */
    public void handleTab3UpdateButton(ActionEvent e) throws Exception {
        if (tab3EmployeeId.getText().isEmpty()) {
            tab3Feedback.setText("Search for an Employee to update with the Employee ID Number");
        } else if (tab3EmployeePps.getText().isEmpty()) {
            tab3Feedback.setText("Enter the Employee PPS Number");
        } else if (tab3EmployeeName.getText().isEmpty()) {
            tab3Feedback.setText("Enter the Employee Name");
        } else if (tab3EmployeePassword.getText().isEmpty()) {
            tab3Feedback.setText("Enter the Employee Password");
        } else if (tab3EmployeePhoneNum.getText().isEmpty()) {
            tab3Feedback.setText("Enter the Employee Phone Number");
        } else if (comboTab3EmployeeDepartment.getValue().toString().isEmpty()) {
            tab3Feedback.setText("Select a Department");
        }
        for (int i = 0; i < users.numberOfUsers(); i++) {
            if (tab3EmployeePps.getText().matches(users.getUser(i).getPpsNum())) {
                tab3Feedback.setText("This PPS Number is Already in Use");
            } else if (!tab3EmployeePasswordConfirm.getText().isEmpty() && !tab3EmployeePassword.getText().matches(users.getUser(i).getPassword())) {
                tab3Feedback.setText("Old Password Must be Entered to Set a New Password");
            } else {
                String pps = tab3EmployeePps.getText();
                String id = tab3EmployeeId.getText();
                String name = tab3EmployeeName.getText();
                String newPass = tab3EmployeePasswordConfirm.getText();
                String phoneNum = tab3EmployeePhoneNum.getText();
                String dept = comboTab3EmployeeDepartment.getValue().toString();
                users.searchEmployeeById(id).setPpsNum(pps);
                tab3Feedback.setText("Employee PPS Number Updated to: " + pps + "\n");
                users.searchEmployeeById(id).setName(name);
                tab3Feedback.setText("Employee Name Updated to: " + name +"\n");
                users.searchEmployeeById(id).setPassword(newPass);
                tab3Feedback.setText("Employee Password Updated to: " + newPass +"\n");
                users.searchEmployeeById(id).setPhoneNumber(phoneNum);
                tab3Feedback.setText("Employee Phone Number Updated to: " + phoneNum +"\n");
                users.searchEmployeeById(id).setDepartment(dept);
                tab3Feedback.setText("Employee Department Updated to: " + dept +"\n");
                users.saveUsers();
                tab3Feedback.setText("Employee Saved");
            }
        }
    }
    /*<--------------------------------------------------------------<END OF UPDATE>--------------------------------------------------------->*/

    /*<--------------------------------------------------------------<DELETE BUTTONS>--------------------------------------------------------->*/

    /* Deletes an instance of a Reservation (Second tab)  */
    public void handleTab2DeleteButton(ActionEvent e) throws Exception {
        int errorCount = 0;
        String id = tab2ResNum.getText();
        if (tab2ResNum.getText().isEmpty()) {
            tab2Feedback.setText("Enter The ID Number of the Staff Member to be Deleted");
            errorCount++;
        }

        if (reservations.numberOfReservations() > 0) {
            for (int i = 0; i < reservations.numberOfReservations(); i++) {
                if (!tab2ResNum.getText().matches(reservations.getReservationDetails(i).getResNum())) {
                    errorCount++;
                    tab2Feedback.setText("This Reservation ID Does Not Match a Reservation");
                }
                if (id.matches(reservations.getReservationDetails(i).getResNum()) && errorCount == 0) {
                    reservations.removeReservation(i);
                    tab2Feedback.setText("Reservation Deleted");
                    tab2ResNum.clear();
                    tab2TableNum.clear();
                    tab2DateOfReservation.setValue(LocalDate.now());
                    comboTab2TimeBooked.setValue("Select Time Slot");
                    tab2CustomerName.clear();
                    tab2Comments.clear();
                    tab2CardNumber.clear();
                } else if (tab2ResNum.getText().isEmpty()) {
                    tab2Feedback.setText("Enter the Reservation Number of the Reservation to be Deleted");
                }
            }
        }
        else{
            tab2Feedback.setText("No Reservations Have Been Made");
        }
    }


    /* Allows a manager to delete a table  */
    public void handleTab4DeleteButton(ActionEvent e) throws Exception{
        int errorCount = 0;
        String id = tab4TableNum.getText();
        if(tables.numberOfTables() > 0) {
            for (int i = 0; i < tables.numberOfTables(); i++) {
                if(tab4TableNum.getText().isEmpty()){
                    errorCount++;
                    tab4Feedback.setText("Enter the Table Number of the Table to be Deleted");
                }
                else if(!tab4TableNum.getText().matches(tables.getTable(i).getNumber())){
                    tab4Feedback.setText("This Table Number Does not Match any Tables on Record");
                }
                if (tables.searchTableById(id).getNumber().matches(tables.getTable(i).getNumber()) && errorCount == 0) {
                    tables.removeTable(i);
                    tab4Feedback.setText("Table Record Deleted");
                    tab4TableNum.clear();
                    tab4TableCapacity.clear();
                    comboTab4TableAvailability.setValue("Select Availability");
                    comboTab4TableDepartment.setValue("Select Department");
                }
            }
        }
        else{
            tab4Feedback.setText("No Tables Records on File");
        }
    }


    /* Deletes an instance of an Employee (Third tab)  */
    public void handleTab3DeleteButton(ActionEvent e) throws Exception{
        String id = tab3EmployeeId.getText();
        if(users.numberOfUsers() > 0) {
            for (int i = 0; i < users.numberOfUsers(); i++) {
                if (users.getUser(i) instanceof Employee && id.matches(((Employee) users.getUser(i)).getEmployeeIdNum())) {
                    users.removeUser(i);
                    tab3Feedback.setText("Employee Deleted");
                    tab3EmployeeId.clear();
                    tab3EmployeePhoneNum.clear();
                    tab3EmployeePassword.clear();
                    tab3EmployeePps.clear();
                    tab3EmployeeName.clear();
                    comboTab3EmployeeDepartment.setValue("Select Department");
                    comboTab3EmployeeRole.setValue("Select Role");
                } else if(users.getUser(i) instanceof Manager && id.matches(((Manager) users.getUser(i)).getManagerIdNum())){
                    users.removeUser(i);
                    tab3Feedback.setText("Manager Deleted");
                    tab3EmployeeId.clear();
                    tab3EmployeePhoneNum.clear();
                    tab3EmployeePassword.clear();
                    tab3EmployeePps.clear();
                    tab3EmployeeName.clear();
                    comboTab3EmployeeDepartment.setValue("Select Department");
                    comboTab3EmployeeRole.setValue("Select Role");
                }
                else if (tab3EmployeeId.getText().isEmpty()) {
                    tab3Feedback.setText("Enter the Employee ID Number of the Employee to be Deleted");
                }
            }
        }
        else{
            tab3Feedback.setText("No Employee's are Currently Registered");
        }
    }
    /* Clear all Fields tab 5 */
    public void handleClearButton(){
        tab5Feedback.clear();
        tab5ResNum.clear();
        tab5DateOfReservation.getEditor().clear();
        comboTab5TimeSlot.getEditor().clear();
    }

    /*<---------------------------------------------------------------<END OF DELETE>------------------------------------------------------------------------>*/

    /*<--------------------------------------------------------------<SEARCH BUTTONS>--------------------------------------------------------->*/

    /* Reads in table object details based on table num entered  */
    public void handleSearchTableResourceButton(ActionEvent e) throws Exception{
        if(tab4TableNum.getText().isEmpty()){
            tab4Feedback.setText("Search for a Table by Entering the Table Number");
        }
        String tableNum = tab4TableNum.getText();
        SeatingTable tableDetails = tables.searchTableById(tableNum);
        if(tableDetails == null){
            tab4Feedback.setText("Invalid Table Number");
        }
        else {
            comboTab4TableDepartment.setValue(tableDetails.getDepartment());
            tab4TableCapacity.setText(tableDetails.getCapacity());
            comboTab4TableAvailability.setValue(tableDetails.isAvailability());
            tab4Feedback.setText("Table Details Read In Successfully");
            }
        }

    /* Reads in Reservation details based on user entered reservation Number (Second Tab)  */
    public void handleSearchResNumberButton(ActionEvent e) throws Exception {
        int errorCount = 0;
        String id = tab2ResNum.getText();
        LocalDate date = tab2DateOfReservation.getValue();
        Reservation reserve = reservations.searchReservationByDateAndId(date, id);

        if(reserve == null){
            errorCount++;
            tab2Feedback.setText("Invalid Reservation Number / Date");
        }
        for (int i = 0; i < reservations.numberOfReservations(); i++) {
            if (errorCount == 0 && id.matches(reservations.getReservationDetails(i).getResNum()) && date.isEqual(reservations.getReservationDetails(i).getDateOfReservation())) {
                tab2TableNum.setText(reservations.getReservationDetails(i).getTableNum());
                tab2DateOfReservation.setValue(reservations.getReservationDetails(i).getDateOfReservation());
                comboTab2TimeBooked.setValue(reservations.getReservationDetails(i).getTimeSlotAllocated());
                tab2CustomerName.setText(reservations.getReservationDetails(i).getCustomerName());
                tab2Comments.setText(reservations.getReservationDetails(i).getComments());
                tab2CardNumber.setText(reservations.getReservationDetails(i).getCardNumber());
                tab2Feedback.setText("Reservation Details Read In Successfully");

            }
            else{
                errorCount++;
            }
        }
    }


    /* Reads in Employee details based on user entered employeeIdNumber (Third Tab)  */
    public void handleSearchEmployeeNumberButton(ActionEvent e) throws Exception {
        if (tab3EmployeeId.getText().isEmpty()) {
            tab3Feedback.setText("Search for an Employee to update with the Employee ID Number");
        } else {
            String empNum = tab3EmployeeId.getText();
            User employeeDetails = users.searchEmployeeById(empNum);
            User managerDetails = users.searchManagerById(empNum);
            if (employeeDetails == null && managerDetails == null) {
                tab3Feedback.setText("Invalid Employee Number");
            } else {
                for (int i = 0; i < users.numberOfUsers(); i++) {
                    if (users.getUser(i) instanceof Manager && empNum.matches(((Manager) users.getUser(i)).getManagerIdNum())) {

                        tab3EmployeePps.setText(managerDetails.getPpsNum());
                        tab3EmployeeName.setText(managerDetails.getName());
                        tab3EmployeePassword.setText(managerDetails.getPassword());
                        tab3EmployeePhoneNum.setText(managerDetails.getPhoneNumber());
                        comboTab3EmployeeDepartment.setValue(managerDetails.getDepartment());
                        tab3Feedback.setText("Manager Details Read In Successfully");
                    }
                    else if(users.getUser(i) instanceof Employee && empNum.matches(((Employee) users.getUser(i)).getEmployeeIdNum())){
                        tab3EmployeePps.setText(employeeDetails.getPpsNum());
                        tab3EmployeeName.setText(employeeDetails.getName());
                        tab3EmployeePassword.setText(employeeDetails.getPassword());
                        tab3EmployeePhoneNum.setText(employeeDetails.getPhoneNumber());
                        comboTab3EmployeeDepartment.setValue(employeeDetails.getDepartment());
                        tab3Feedback.setText("Employee Details Read In Successfully");
                    }
                }
            }
        }
    }

    /* Search for Reservation based on user input ResNum and Date. Reads info into TextFields and prints object toString to TextArea */
    public void handleTab5SearchButton(ActionEvent e) throws Exception {
        int error = 0;
        int errorCount = 0;
        String id = tab5ResNum.getText();
        LocalDate date = tab5DateOfReservation.getValue();
        if (id.isEmpty()) {
            tab5Feedback.appendText("Enter Reservation Number \n");
            errorCount++;
        }
        if (tab5DateOfReservation.getValue() == null) {
            tab5Feedback.appendText("Enter a Date \n");
            errorCount++;
        }
        for (int i = 0; i < reservations.numberOfReservations(); i++) {
            if(id.length() > 0 && !date.isEqual(reservations.getReservationDetails(i).getDateOfReservation())) {
                error++;
                errorCount++;
            }
        }
        if (error == reservations.numberOfReservations()){
            tab5Feedback.appendText("There are no Reservations with these Parameters \n");
        }
        for (int i = 0; i < reservations.numberOfReservations(); i++) {
            if (errorCount == 0 && id.matches(reservations.getReservationDetails(i).getResNum()) && date.isEqual(reservations.getReservationDetails(i).getDateOfReservation())) {
                tab5Feedback.clear();
                tab5Feedback.setText(reservations.getReservationDetails(i).toString());
                comboTab5TimeSlot.setValue(reservations.getReservationDetails(i).getTimeSlotAllocated());
                tab5TableNum.setText(reservations.getReservationDetails(i).getTableNum());
            }
        }
    }
    /*<--------------------------------------------------------------<END OF SEARCH>--------------------------------------------------------->*/

    /*<--------------------------------------------------------------<LOAD BUTTONS>--------------------------------------------------------->*/

    /* Loads tables from an xml file  */
    public void handleLoadTableButton(ActionEvent e) throws Exception {
        tables.loadTables();
        if (tables.numberOfTables() <= 0) {
            tab4Feedback.setText("There are no Tables Registered");
        } else {
            tab4Feedback.setText("Table Records Loaded");
        }
    }

    /* Loads Reservations */
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

    /* Loads Employees */
    public void handleTab3LoadButton(ActionEvent e) throws Exception{
        users.loadUsers();
        if(users.numberOfUsers() <= 0){
            tab3Feedback.setText("There are no registered Employees");
        }
        else {

            tab3Feedback.setText("Employee's Loaded");
        }
    }

    /* Loads all ArrayLists for tab 5 listing */
    public void handleTab5LoadAllButton(ActionEvent e) throws Exception{
        reservations.loadReservations();
        users.loadUsers();
        tables.loadTables();
        if(reservations.numberOfReservations() == 0){
            tab5Feedback.setText("There are no Reservation Records to Load");
        }
        else if(users.numberOfUsers() == 0){
            tab5Feedback.setText("There are no Employee Records to Load");
        }
        else if(tables.numberOfTables() == 0) {
            tab5Feedback.setText("There are no Table Records to Load");
        }
    }

    /*<--------------------------------------------------------------<END OF LOAD>--------------------------------------------------------->*/

    /*<--------------------------------------------------------------<CREATE BUTTONS>--------------------------------------------------------->*/

    /* Creates a SeatingTable object (Fourth tab)  */
    public void handleTab4CreateTableButton(ActionEvent e) throws Exception{
        String num = tab4TableNum.getText();
        int errorCount = 0;
        if(tab4TableNum.getText().isEmpty()){
            tab4Feedback.appendText("Enter a Table Number \n");
            errorCount++;
        }
        String capacity = tab4TableCapacity.getText();
        if(tab4TableCapacity.getText().isEmpty()){
            tab4Feedback.appendText("Enter the Table's Capacity \n");
            errorCount++;
        }
        String dept = comboTab4TableDepartment.getValue().toString();
        if(comboTab4TableDepartment.getValue().toString().isEmpty()){
            tab4Feedback.appendText("Select a Department \n");
            errorCount++;
        }
        String avail = comboTab4TableAvailability.getValue().toString();
        if(comboTab4TableAvailability.getValue().toString().isEmpty()){
            tab4Feedback.appendText("Select Availability \n");
            errorCount++;
        }
        if(errorCount == 0) {
            tables.addTable(num, capacity, dept, avail);
            tab4Feedback.setText("Table Added \n");
            tables.saveTables();
            tab4Feedback.appendText("Table Saved");

        }
    }






    /* Creates an instance of an Employee (Third tab)  */
    public void handleTab3CreateEmployeeButton(ActionEvent e) throws Exception{
        String pps = tab3EmployeePps.getText();
        int errorCount = 0;
        if(tab3EmployeePps.getText().isEmpty()){
            tab3Feedback.setText("Enter a PPS Number \n");
            errorCount++;
        }
        String id = tab3EmployeeId.getText();
        if(tab3EmployeeId.getText().isEmpty()){
            tab3Feedback.appendText("Enter an ID Number \n");
            errorCount++;
        }
        String name = tab3EmployeeName.getText();
        if(tab3EmployeeName.getText().isEmpty()){
            tab3Feedback.appendText("Enter an Employee Name \n");
            errorCount++;
        }
        String password = tab3EmployeePassword.getText();
        if(tab3EmployeePassword.getText().isEmpty()){
            tab3Feedback.appendText("Enter a Password \n");
            errorCount++;
        }
        String phone = tab3EmployeePhoneNum.getText();
        if(tab3EmployeePhoneNum.getText().isEmpty()){
            tab3Feedback.appendText("Enter an Employee Phone Number \n");
            errorCount++;
        }
        String dept = comboTab3EmployeeDepartment.getValue().toString();
        if(comboTab3EmployeeDepartment.getValue().toString().isBlank()){
            tab3Feedback.appendText("Select a Department \n");
            errorCount++;
        }
        String role = comboTab3EmployeeRole.getValue().toString();
        if(comboTab3EmployeeRole.getValue().toString().isBlank() || comboTab3EmployeeRole.getValue().toString().matches("Select Role")){
            tab3Feedback.appendText("Select a Role \n");
            errorCount++;
        }
        if(errorCount == 0 && role.matches("Employee")) {
            users.addEmployee(pps, id, name, password, phone, dept);
            tab3Feedback.setText("Employee Added \n");
            users.saveUsers();
            tab3Feedback.appendText("Employee Saved \n");
        }
        else if(errorCount == 0 && role.matches("Manager")){
            users.addManager(pps,id,name,password,phone,dept);
            tab3Feedback.setText("Manager Added \n");
            users.saveUsers();
            tab3Feedback.appendText("Manager Saved \n");
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
        String timeSlotAllocated = this.comboTimeBooked.getValue().toString();
        if (this.comboTimeBooked.getValue().toString().matches("Select Time Slot")) {
            errorCount++;
            feedback.appendText("Select Time Slot \n");
        }

        LocalDate dateOfRes = this.dateOfReservation.getValue();
        if (this.dateOfReservation.getValue() == null) {
            errorCount++;
            feedback.appendText("Select a Date");
        }

        String count = String.valueOf(errorCount);
        if (errorCount == 0) {
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

            /* Makes the selected Reservation weekly for 3 months*/
    public void handleMakeWeeklyButton(ActionEvent e) throws Exception {
        int countDays = 0;
        if (!tab5ResNum.getText().isEmpty() && !comboTab5TimeSlot.getValue().toString().isEmpty() && !tab5TableNum.getText().isEmpty()) {
            for (int i = 0; i < reservations.numberOfReservations(); i++) {
                if (reservations.getReservationDetails(i).getResNum().matches(tab5ResNum.getText()) && reservations.getReservationDetails(i).getTableNum().matches(tab5TableNum.getText())
                        && reservations.getReservationDetails(i).getTimeSlotAllocated().matches(comboTab5TimeSlot.getValue().toString())) {
                    Reservation newReserve = reservations.getReservationDetails(i);
                    tab5Feedback.appendText(newReserve.toString());
                    countDays+=7;
                    reservations.addReservation(newReserve.getResNum(), newReserve.getTableNum(), newReserve.getCardNumber(), newReserve.getDateOfReservation().plusDays(7),newReserve.getTimeSlotAllocated(),
                            newReserve.getCustomerName(), newReserve.getCardNumber());

                    if(countDays >= 120){
                        reservations.saveReservations();
                        return;
                    }
                    else if(reservations.getReservationDetails(i).getDateOfReservation().isAfter(newReserve.getDateOfReservation().plusYears(1))){
                        return;
                    }
                }

            }

        }
        else{
            tab5Feedback.setText("Search for a Reservation using the Reservation Number and Date before selecting this option");
        }
    }


    /* Makes the selected Reservation monthly for 1 Year */
    public void handleMakeMonthlyButton(ActionEvent e) throws Exception {
        int countDays = 0;
        if (!tab5ResNum.getText().isEmpty() && !comboTab5TimeSlot.getValue().toString().isEmpty() && !tab5TableNum.getText().isEmpty()) {
            for (int i = 0; i < reservations.numberOfReservations(); i++) {
                if (reservations.getReservationDetails(i).getResNum().matches(tab5ResNum.getText()) && reservations.getReservationDetails(i).getTableNum().matches(tab5TableNum.getText())
                        && reservations.getReservationDetails(i).getTimeSlotAllocated().matches(comboTab5TimeSlot.getValue().toString())) {
                    Reservation newReserve = reservations.getReservationDetails(i);
                    tab5Feedback.appendText(newReserve.toString());
                    countDays+=1;
                    reservations.addReservation(newReserve.getResNum(), newReserve.getTableNum(), newReserve.getCardNumber(), newReserve.getDateOfReservation().plusMonths(1),newReserve.getTimeSlotAllocated(),
                            newReserve.getCustomerName(), newReserve.getCardNumber());

                    if(countDays > 12){
                        reservations.saveReservations();
                        return;
                    }
                    else if(reservations.getReservationDetails(i).getDateOfReservation().isAfter(newReserve.getDateOfReservation().plusYears(1))){
                        return;
                    }
                }

            }

        }       else{
            tab5Feedback.setText("Search for a Reservation using the Reservation Number and Date before selecting this option");
        }
    }

    /*<--------------------------------------------------------------<END OF CREATE>--------------------------------------------------------->*/

    /*<--------------------------------------------------------------<LIST BUTTONS>--------------------------------------------------------->*/

    /* Lists Reservations */
    public void handleListReservationsButton(){
        if (reservations.numberOfReservations() > 0){
           tab5Feedback.setText(reservations.listAllReservations());
        }
        else{
            tab5Feedback.setText("There are no Reservations Loaded");
        }
    }



    /* Lists Employees */
    public void handleListEmployeesButton(){
        if (users.numberOfUsers() > 0){
            tab5Feedback.setText(users.listAllUsers());
        }
        else{
            tab5Feedback.setText("There are no Employees Loaded");
        }
    }

    /* Lists Table Records */
    public void handleListTablesButton(){
        if (tables.numberOfTables() > 0){
            tab5Feedback.setText(tables.listAllTables());
        }
        else{
            tab5Feedback.setText("There are no Tables Loaded");
        }
    }

    /*<--------------------------------------------------------------<END OF LIST>--------------------------------------------------------->*/



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
    ObservableList<String> departments = FXCollections.observableArrayList("Select Department","Restaurant", "Bar");
    ObservableList<String> availability = FXCollections.observableArrayList("Select Availability","Available", "Reserved");
    ObservableList<String> role = FXCollections.observableArrayList("Select Role","Manager", "Employee");

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        reservations = new RestaurantReserveModel();
        users = new RestaurantReserveModel();
        tables = new RestaurantReserveModel();
        roles = "Select Role";
        timeSlot = "Select Time Slot";
        timeSlot2 = "Select Time Slot";
        timeslot3 = "Select Time Slot";
        tab3Dept = "Select Department";
        tab4Dept = "Select Department";
        tab4Avail = "Select Availability";
        comboTimeBooked.setItems(times);
        comboTab2TimeBooked.setItems(times2);
        comboTab3EmployeeRole.setItems(role);
        comboTab3EmployeeDepartment.setItems(departments);
        comboTab4TableDepartment.setItems(departments);
        comboTab4TableAvailability.setItems(availability);
        comboTab5TimeSlot.setItems(times);
        comboTab3EmployeeRole.setValue("Select Role");
        comboTimeBooked.setValue("Select Time Slot");
        comboTab2TimeBooked.setValue("Select Time Slot");
        comboTab3EmployeeDepartment.setValue("Select Department");
        comboTab4TableDepartment.setValue("Select Department");
        comboTab4TableAvailability.setValue("Select Availability");
        comboTab5TimeSlot.setValue("Select Time Slot");


    }
}
