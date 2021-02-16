package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController implements Initializable {
    private RestaurantReserveModel users;
    @FXML
    private TextField ppsNum, password;
    @FXML
    private TextArea userMessage;

    public loginController(){
    }

    /* Transitions scene to the EmployeeMainScreen  */
    public void loginButton(ActionEvent e) throws Exception{
        Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("EmployeeMainScreen.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Employee Control Panel");
        primaryStage.setScene(new Scene(root, 700, 400));
        root.getStylesheets().add(getClass().getResource("PurpleTheme.css").toExternalForm());
        primaryStage.show();
    }

    /* Allows the user to login. Contains data verification.  */
    public void handleLoginButton(ActionEvent e) throws IOException {
        String ppsNum = this.ppsNum.getText();
        String password = this.password.getText();
        for (int i = 0; i < this.users.numberOfUsers(); i++) {
        if (this.users.numberOfUsers() >= 0) {
                if (ppsNum.matches(this.users.getUser(i).getPpsNum()) && password.matches(this.users.getUser(i).getPassword()) && this.users.getUser(i) instanceof Manager) {
                    Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("ManagerMainScreen.fxml"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    primaryStage.setTitle("Manager Control Panel");
                    primaryStage.setScene(new Scene(root, 700, 400));
                    root.getStylesheets().add(getClass().getResource("PurpleTheme.css").toExternalForm());
                    primaryStage.show();
                    }
                else if (ppsNum.matches(this.users.getUser(i).getPpsNum()) && password.matches(this.users.getUser(i).getPassword()) && this.users.getUser(i) instanceof Employee) {
                    Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("EmployeeMainScreen.fxml"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    primaryStage.setTitle("Employee Control Panel");
                    primaryStage.setScene(new Scene(root, 700, 400));
                    root.getStylesheets().add(getClass().getResource("PurpleTheme.css").toExternalForm());
                    primaryStage.show();
                }
                    else {
                        userMessage.setText("Incorrect PPS Number or Password" + "\n" + "Please Try Again");
                        this.ppsNum.clear();
                        this.password.clear();
                    }
                }
                    else {
                    userMessage.setText("No User's are Currently Registered");
                }
        }
    }
    /* Transitions scene to the CreateAccountScreen  */
    public void handleCreateAccountButton(ActionEvent e) {
        Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CreateAccountScreen.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Restaurant Reservation System");
        primaryStage.setScene(new Scene(root, 700, 400));
        root.getStylesheets().add(getClass().getResource("PurpleTheme.css").toExternalForm());
        primaryStage.show();
    }
    /* Loads User List  */
    public void handleLoadButton(ActionEvent e) throws Exception {
        users.loadUsers();
        userMessage.setText("Staff Records Loaded");
    }


        @Override
        public void initialize(URL location, ResourceBundle resourceBundle){
            users = new RestaurantReserveModel();
        }
    }

