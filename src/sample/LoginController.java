package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import java.net.URL;

public class LoginController implements Initializable {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        File brandingFile = new File("Images/city gym app logo3.jpg");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);

        File lockFile = new File("Images/lock img.jpg");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }

    public void loginButtonOnAction(ActionEvent event){
        if(usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false){
            validateLogin();
        }else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void registerButtonOnAction(ActionEvent event)
    {
        createAccountForm();
    }

    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "select count(1) from customers where username = '" + usernameTextField.getText() +"'and password ='" + passwordTextField.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    loginMessageLabel.setText("Congratulations");
                    createCustomerFrontPage();
                }else if(usernameTextField.getText().equals("admin") && passwordTextField.getText().equals("admin")){
                    //loginMessageLabel.setText("Invalid login. Please try again");
                    createAdminPage();
                }else{
                    loginMessageLabel.setText("Invalid login. Please try again");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(){
        try{

            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage registerStage = new Stage();
            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root, 520, 605));
            registerStage.show();

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void createCustomerFrontPage(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customerFrontPage.fxml"));
            //Parent root = FXMLLoader.load(getClass().getResource("customerFrontPage.fxml"));
            Parent root = (Parent) loader.load();
            customerFrontPageController secController = loader.getController();
            secController.myFunction(usernameTextField.getText(), passwordTextField.getText());

            Stage customerFrontStage = new Stage();
            customerFrontStage.initStyle(StageStyle.UNDECORATED);
            customerFrontStage.setScene(new Scene(root, 520, 680));
            customerFrontStage.show();

        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }

    public void createAdminPage(){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
            Stage adminStage = new Stage();
            adminStage.initStyle(StageStyle.UNDECORATED);
            adminStage.setScene(new Scene(root, 520, 350));
            adminStage.show();


        }catch(Exception e){
            e.getCause();
            e.printStackTrace();
        }
    }


}
