package application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;



public class RestaurantReserveModel {

    private ArrayList<User> users;
    private ArrayList<SeatingTable> tables;
    private ArrayList<Reservation> reservations;

    public RestaurantReserveModel() {

        users = new ArrayList<User>();
        tables = new ArrayList<SeatingTable>();
        reservations = new ArrayList<Reservation>();

    }

    public void addEmployee(String ppsNum, String employeeIdNum, String name, String password, String phoneNumber, String department) {

        User user = new Employee(ppsNum, employeeIdNum, name, password, phoneNumber, department);

        users.add(user);
    }

    public void addManager(String ppsNum, String managerIdNum, String name, String password, String phoneNumber, String department) {

        User manager = new Manager(ppsNum, managerIdNum, name, password, phoneNumber, department);

        users.add(manager);
    }

    public void addReservation(String resNum, String tableNumber, String cardNumber, LocalDate dateOfReservation ,String timeSlotAllocated, String customerName, String comments) {

        Reservation reservation = new Reservation(resNum, tableNumber, cardNumber, dateOfReservation, timeSlotAllocated, customerName, comments);

        reservations.add(reservation);
    }

    public void addTable(String number, String capacity, String department, String availability) {

        SeatingTable table = new SeatingTable(number, capacity, department, availability);

        tables.add(table);
    }


    public void saveUsers() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream

                (new FileWriter("users.xml"));

        out.writeObject(users);

        out.close();

    }

    public void saveTables() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream

                (new FileWriter("tables.xml"));

        out.writeObject(tables);

        out.close();

    }

    public void saveReservations() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream

                (new FileWriter("reservations.xml"));

        out.writeObject(reservations);

        out.close();

    }


    public void loadUsers() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream

                (new FileReader("users.xml"));

        users = (ArrayList<User>) is.readObject();

        is.close();

    }

    public void loadTables() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream

                (new FileReader("tables.xml"));

        tables = (ArrayList<SeatingTable>) is.readObject();

        is.close();

    }

    public void loadReservations() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream

                (new FileReader("reservations.xml"));

        reservations = (ArrayList<Reservation>) is.readObject();

        is.close();

    }

    public String getTitle() {

        return ("Restaurant Reservation System");    //TODO    change this to whatever you wish to call your site

    }


    public String listAllUsers() {
        if (users.size() == 0) {
            return "No Users are currently registered";
        } else {
            String list = "";
            for (int i = 0; i < users.size(); i++) {
                list = list + i + ": " + users.get(i);
            }
            return list;
        }
    }

    public String listAllReservations() {
        if (reservations.size() == 0) {
            return "No Reservations are currently registered";
        } else {
            String list = "";
            for (int i = 0; i < reservations.size(); i++) {
                list = list + i + ": " + reservations.get(i);
            }
            return list;
        }
    }

    public String listAllTables() {
        if (tables.size() == 0) {
            return "No Tables are currently registered";
        } else {
            String list = "";
            for (int i = 0; i < tables.size(); i++) {
                list = list + i + ": " + tables.get(i);
            }
            return list;
        }
    }

    public int numberOfUsers(){
        return users.size();
    }

    public int numberOfTables(){
        return tables.size();
    }

    public int numberOfReservations(){
        return reservations.size();
    }

    public User getUser(int userIndex) {
        if (userIndex >= 0 && userIndex <= this.users.size()) {
            User userObject = this.users.get(userIndex);
            return userObject;
        } else {
            return null;
        }
    }

    public SeatingTable getTable(int index) {
        if (index >= 0 && index <= this.tables.size()) {
            SeatingTable tableObject = this.tables.get(index);
            return tableObject;
        } else {
            return null;
        }
    }

    public User getUserDetails(int index) {
        if (index >= 0 && index <= users.size()) {
            User userDetails = users.get(index);
            return userDetails;
        } else {
            return null;
        }
    }

    public Reservation getReservationDetails(int index) {
        if (index >= 0 && index <= reservations.size()) {
            Reservation resDetails = reservations.get(index);
            return resDetails;
        } else {
            return null;
        }
    }


    public User searchEmployeeById(String id) {
        User employeeDetails;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Employee && ((Employee) users.get(i)).getEmployeeIdNum().matches(id)) {
                employeeDetails = users.get(i);
                return employeeDetails;
            }
        }
        return null;
    }

    public SeatingTable searchTableById(String id) {
        SeatingTable tableDetails;
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getNumber().matches(id)) {
                tableDetails = tables.get(i);
                return tableDetails;
            }
        }
        return null;
    }

    public User searchManagerById(String id) {
        User managerDetails;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i) instanceof Manager && ((Manager) users.get(i)).getManagerIdNum().matches(id)) {
                managerDetails = users.get(i);
                return managerDetails;
            }
        }
        return null;
    }

    public Reservation searchReservationById(String id) {
        Reservation resDetails;
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getResNum().matches(id)) {
                resDetails = reservations.get(i);
                return resDetails;
            }
        }
        return null;
    }

    public Reservation searchReservationByDateAndId(LocalDate date, String resNum) {
        Reservation resDetails;
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getDateOfReservation().toString().matches(date.toString()) && reservations.get(i).getResNum().matches(resNum)) {
                resDetails = reservations.get(i);
                return resDetails;
            }
        }
        return null;
    }

    public boolean removeReservation(int index) {
        if (this.reservations.size() < index) {
            return false;
        } else {
            this.reservations.remove(index);
            return true;
        }
    }

    public boolean removeUser(int index) {
        if (this.users.size() < index) {
            return false;
        } else {
            this.users.remove(index);
            return true;
        }
    }

    public boolean removeTable(int index) {
        if (this.tables.size() < index) {
            return false;
        } else {
            this.tables.remove(index);
            return true;
        }
    }

    }



