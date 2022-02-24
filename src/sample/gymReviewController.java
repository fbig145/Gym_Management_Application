package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;

public class gymReviewController {
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

        String text = "insert into gym_reviews(review, id_customer) values ('" + reviewTextArea.getText() +"','8'" +")";
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
