package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //@Override
   // public void start(Stage primaryStage) throws Exception{
       // Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
      //  primaryStage.setTitle("Restaurant Reservation System Login");
      //  primaryStage.setScene(new Scene(root, 500, 400));
     //   scene.getStylesheets().add(getClass().getResource("greyTheme.css").toExternalForm());
       // primaryStage.show();
   // }

    @Override

    public void start(Stage primaryStage) {

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



    public static void main(String[] args) {
        launch(args);
    }
}


