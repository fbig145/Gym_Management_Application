package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ListView;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

public class appointmentController {
    @FXML
    private Button cancelButton;
    @FXML
    private ListView trainerListView;
    @FXML
    private TextField trainerNameTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private Label trainerLabelSuccess;
    @FXML
    private Label programLabelSuccess;
    @FXML
    private ListView programListView;
    @FXML
    private TextField programNameTextField;


    public void initialize(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getValues = "select first_name, last_name, experience, description from trainers";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getValues);
            ObservableList<String> items = FXCollections.observableArrayList();
            String trainerFirstName;
            String trainerLastName;
            String experience;
            String description;
            String fullString;

            while(queryResult.next()){
                trainerFirstName = queryResult.getString("first_name");
                trainerLastName = queryResult.getString("last_name");
                experience = queryResult.getString("experience");
                description = queryResult.getString("description");
                fullString = trainerFirstName + " " + trainerLastName + "       " + experience + "      " + description;
                items.add(fullString);
               // trainerListView.setItems(items);
            }
            trainerListView.setItems(items);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        String getValuesProgram = "select name, length, happening_date, hour, description from training_programs";

        try{
            Statement statement2 = connectDB.createStatement();
            ResultSet queryResult2 = statement2.executeQuery(getValuesProgram);
            ObservableList<String> items2 = FXCollections.observableArrayList();
            String name;
            String length;
            String happening_date;
            String hour;
            String description;
            String fullString;

            while(queryResult2.next()){
                name = queryResult2.getString("name");
                length = queryResult2.getString("length");
                happening_date = queryResult2.getString("happening_date");
                hour = queryResult2.getString("hour");
                description = queryResult2.getString("description");
                fullString = name + "      " + length + "     " + happening_date + "     " + hour + "    " + description;
                items2.add(fullString);
                // trainerListView.setItems(items);
            }
            programListView.setItems(items2);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

     //   ObservableList<String> items = FXCollections.observableArrayList (
       //         "Single", "Double", "Suite", "Family App", "ceva", "ceva", "ceva");
       // trainerListView.setItems(items);
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void makeAppTrainerButtonOnAction(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getTrainerId = "select id_trainer from trainers where first_name = '" + trainerNameTextField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getTrainerId);
            String trainerID = null;
            while(queryResult.next()){
                trainerID = queryResult.getString("id_trainer");
            }
            String insertString = "insert into trainers_appointments(id_trainer, id_customer, appointment_date) values ('" + trainerID + "','8','" + dateTextField.getText() +"')";
            int queryResult2 = statement.executeUpdate(insertString);
            trainerLabelSuccess.setText("Appointment made successfully");

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }

    }

    public void makeAppProgramButtonOnAction(ActionEvent event){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String getProgramId = "select id_program from training_programs where name = '" + programNameTextField.getText() + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(getProgramId);
            String programID = null;
            while(queryResult.next()){
                programID = queryResult.getString("id_program");
            }
            String insertString = "insert into program_appointments(id_program, id_customer) values ('" + programID + "','8')";
            int queryResult2 = statement.executeUpdate(insertString);
            programLabelSuccess.setText("Appointment made successfully");

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void trainerReviewButtonOnAction(ActionEvent event){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("trainerReview.fxml"));
            Stage reviewStage = new Stage();
            reviewStage.initStyle(StageStyle.UNDECORATED);
            reviewStage.setScene(new Scene(root, 520, 500));
            reviewStage.show();

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void gymReviewButtonOnAction(ActionEvent event){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("gymReview.fxml"));
            Stage reviewStage = new Stage();
            reviewStage.initStyle(StageStyle.UNDECORATED);
            reviewStage.setScene(new Scene(root, 520, 500));
            reviewStage.show();

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

}
