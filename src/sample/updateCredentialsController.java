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

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class updateCredentialsController {

    @FXML
    private Button cancelButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField birthdateTextField;
    @FXML
    private TextField cityTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private Label successLabel;

    public String secondLabel;

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        // Platform.exit();
    }

    public void updateButtonOnAction(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String foundID = null;

        String aquiredID = "select id_customer from customers where password = '" + secondLabel + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(aquiredID);

            while(queryResult.next()){
                foundID = queryResult.getString("id_customer");
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        String updateCredentials = "update customers set first_name = '" + firstnameTextField.getText() +
                "', last_name = '" + lastnameTextField.getText() + "', email = '" + emailTextField.getText() +
                "', city = '" + cityTextField.getText() + "', address = '" + addressTextField.getText() + "', username = '" +
                usernameTextField.getText() + "' where id_customer = '" + foundID +"'" ;
        try{
            Statement statement2 = connectDB.createStatement();
            int queryResult2 = statement2.executeUpdate(updateCredentials);
            successLabel.setText("Credentials updated successfully");

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }



    }
    public void myFunction2(String text){
        secondLabel = text;
    }




}
