package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createAccountController implements Initializable {
    protected RestaurantReserveModel users;
    String role, department;
    @FXML
    private TextField ppsNum, idNum, name, password, phoneNum;
    @FXML
    private ComboBox comboRole, comboDepartment;
    @FXML
    private TextArea feedback;
    @FXML
    private Button login;

    public createAccountController() {
    }

    public void handleCreateAccountButton(ActionEvent e) {
        int errorCount = 0;
        String role = (String)comboRole.getValue();
        if(role.isEmpty() || role.matches("Select Role")){
            feedback.appendText("Please Select a Role \n");
            errorCount++;
        }
        String department = (String)comboDepartment.getValue();
        if(department.isEmpty() || department.matches("Select Department")){
            feedback.appendText("Please Select a Department \n");
            errorCount++;
        }
        String ppsNum = this.ppsNum.getText();
        if(ppsNum.length() <= 0){
            feedback.appendText("Invalid PPS Number \n");
            errorCount++;
        }
        String idNum = this.idNum.getText();
        if((idNum.length() <= 0)){
            feedback.appendText("Invalid ID Number \n");
            errorCount++;
        }
        String name = this.name.getText();
        if(name.length() <= 0){
            feedback.appendText("Invalid Name \n");
            errorCount++;
        }
        String password = this.password.getText();
        if(password.length() < 5){
            feedback.appendText("Invalid Password \n");
            errorCount++;
        }
        String phoneNum = this.phoneNum.getText();
        if(phoneNum.length() < 9){
            feedback.appendText("Invalid Phone Number \n");
            errorCount++;
        }
        if(role.matches("Manager") && errorCount == 0){
            users.addManager(ppsNum,idNum,name,password,phoneNum,department);
            feedback.setText("Manager Account Created \n Please Save Your Entry");

        }
        else if(role.matches("Employee") && errorCount == 0){
            users.addEmployee(ppsNum,idNum,name,password,phoneNum,department);
            feedback.setText("Employee Account Created \n Please Save Your Entry");
        }
    }

    public void handleLoginButton(ActionEvent e) {
        Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Restaurant Reservation System Login");
        primaryStage.setScene(new Scene(root, 700, 400));
        root.getStylesheets().add(getClass().getResource("PurpleTheme.css").toExternalForm());
        primaryStage.show();
    }

    public void handleSaveButton(ActionEvent e) throws Exception {
        int errorCount = 0;
        String ppsNum = this.ppsNum.getText();
        if(ppsNum.length() <= 0){
            errorCount++;
        }
        String idNum = this.idNum.getText();
        if((idNum.length() <= 0)){
            errorCount++;
        }
        String name = this.name.getText();
        if(name.length() <= 0){
            errorCount++;
        }
        String password = this.password.getText();
        if(password.length() < 5){
            errorCount++;
        }
        String phoneNum = this.phoneNum.getText();
        if(phoneNum.length() < 9){
            errorCount++;
        }
        String role = (String)comboRole.getValue();
        if(role.isEmpty() || role.matches("Select Role")){
            errorCount++;
        }
        String department = (String)comboDepartment.getValue();
        if(department.isEmpty() || department.matches("Select Department")){
            errorCount++;
        }
        if(errorCount == 0) {
            users.saveUsers();
            feedback.setText("User Saved");
        }
        else {
            feedback.setText("The invalid user entered has not been saved\nPlease enter valid User details");
        }
    }

    ObservableList<String> roles = FXCollections.observableArrayList("Select Role","Manager", "Employee");

    ObservableList<String> departments = FXCollections.observableArrayList("Select Department","Restaurant", "Bar");

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle){
        users = new RestaurantReserveModel();

        role = "Select Role";

        department = "Select Department";

        comboRole.setItems(roles);

        comboDepartment.setItems(departments);

        comboRole.setValue("Select Role");

        comboDepartment.setValue("Select Department");

    }
}
