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


public class changePasswordController {

    public String getUsernameForCheck;

    @FXML
    private Button cancelButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private PasswordField changePasswordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private Label changePasswordLabel;


    public void myFunction3(String text){
        getUsernameForCheck= text;
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        // Platform.exit();
    }

    public void changePasswordButtonOnAction(ActionEvent event){
       // System.out.println(getUsernameForCheck);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String foundID = null;

        String aquiredID = "select id_customer from customers where username = '" + getUsernameForCheck + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(aquiredID);

            while(queryResult.next()){
                foundID = queryResult.getString("id_customer");
            }
           // System.out.println(foundID);

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        if(changePasswordTextField.getText().equals(confirmPasswordTextField.getText())){

            String updatePassword = "update customers set password = '" + changePasswordTextField.getText() + "' where id_customer = '" + foundID +"'";
            try{
                Statement statement3 = connectDB.createStatement();
                int queryResult2 = statement3.executeUpdate(updatePassword);
                changePasswordLabel.setText("Password updated successfully");

            }catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

        }else{
            changePasswordLabel.setText("Password doesn't match. Please try again");
        }
    }

}
