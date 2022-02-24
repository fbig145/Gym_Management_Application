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

public class customerFrontPageController {

    @FXML
    private Button cancelButton;
    @FXML
    private Button seeCredentialsButton;
    @FXML
    private Label usernameLabelText;
    @FXML
    private Label firstnameLabelText;
    @FXML
    private Label lastnameLabelText;
    @FXML
    private Label emailLabelText;
    @FXML
    private Label birthdayLabelText;
    @FXML
    private Label cityLabelText;
    @FXML
    private Label addressLabelText;
    @FXML
    private Label membershipLabelText;
    @FXML
    private Button updateCredentialsButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private Button makeAppointmentButton;

    public String initialLabel;
    public String initialLabelForPass;



    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
        // Platform.exit();
    }

    public void setSeeCredentialsButtonOnAction(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select first_name, last_name, email, birth_date, city, address, membershipEND from customers where username = '" + initialLabel + "'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                /*
                String firstName = queryResult.getString("first_name");
                String lastName = queryResult.getString("last_name");
                String email = queryResult.getString("email");
                String birthDate = queryResult.getString("birth_date");
                String address = queryResult.getString("address");
                String membershipEnd = queryResult.getString("membershipEND");
                */
                firstnameLabelText.setText(queryResult.getString("first_name"));
                lastnameLabelText.setText(queryResult.getString("last_name"));
                emailLabelText.setText(queryResult.getString("email"));
                birthdayLabelText.setText(queryResult.getString("birth_date"));
                cityLabelText.setText(queryResult.getString("city"));
                addressLabelText.setText(queryResult.getString("address"));
                membershipLabelText.setText(queryResult.getString("membershipEND"));
                usernameLabelText.setText(initialLabel);
            }

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void myFunction(String text, String text2){
       // usernameLabelText.setText(text);
        initialLabel = text;
        initialLabelForPass = text2;
    }

    public void updateCredentials(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("updateCredentials.fxml"));
            Parent root = (Parent) loader.load();
            updateCredentialsController secController = loader.getController();
            secController.myFunction2(initialLabelForPass);

            Stage updateStage = new Stage();
            updateStage.initStyle(StageStyle.UNDECORATED);
            updateStage.setScene(new Scene(root, 520, 600));
            updateStage.show();
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void updateCredentialsButtonOnAction(ActionEvent event) {
        updateCredentials();
    }

    public void changePasswordOnAction(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("changePassword.fxml"));
            Parent root = (Parent) loader.load();
            changePasswordController secController = loader.getController();
            secController.myFunction3(usernameLabelText.getText());

            Stage updateStage = new Stage();
            updateStage.initStyle(StageStyle.UNDECORATED);
            updateStage.setScene(new Scene(root, 520, 400));
            updateStage.show();
        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }

    }

    public void makeAppointmentButtonOnAction(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("appointment.fxml"));
            Parent root = (Parent) loader.load();
            appointmentController secController = loader.getController();
            secController.initialize();

            Stage updateStage = new Stage();
            updateStage.initStyle(StageStyle.UNDECORATED);
            updateStage.setScene(new Scene(root, 800, 520));
            updateStage.show();

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }

    }

}
