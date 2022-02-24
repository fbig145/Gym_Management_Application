package sample;

import javafx.application.Platform;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextArea;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class trainerReviewController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    @FXML
    private TextArea reviewTextArea;
    @FXML
    private Label successLabel;

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void submitButtonOnAction(ActionEvent event){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String text = "insert into trainers_review(review, id_customer) values ('" + reviewTextArea.getText() +"','8'" +")";
        try{
            Statement statement = connectDB.createStatement();
            int queryResult = statement.executeUpdate(text);
            successLabel.setText("Thank you! Your review was submitted successfully");
            reviewTextArea.clear();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
