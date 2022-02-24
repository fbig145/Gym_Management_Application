package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class adminController {
    @FXML
    private Button cancelButton;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private Label successLabel;

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void changeDateOnAction(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String customerID = null;

        String customer_id = "select id_customer from customers where username = '" + usernameTextField.getText() + "'";
       // String updateDate = "update customers set membershipEND = '" + dateTextField.getText() + "' where id_customer ='" + customerID + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(customer_id);
            while(queryResult.next()){
                customerID = queryResult.getString("id_customer");
            }
            String updateDate = "update customers set membershipEND = '" + dateTextField.getText() + "' where id_customer ='" + customerID + "'";
            int queryResult2 = statement.executeUpdate(updateDate);
            successLabel.setText("Membership date was changed successfully");

        }catch (Exception e){
            e.getCause();
            e.printStackTrace();
        }


    }

}
