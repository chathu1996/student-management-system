package lk.itsei.fx.sms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.itsei.fx.sms.db.DBConnection;
import lk.itsei.fx.sms.validate.Validation;

import java.awt.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class SignUpWindowController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignUp;

    Connection connection = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;

    public SignUpWindowController() throws Exception {
        connection = DBConnection.getConnection ();
    }

    @FXML
    void btnSignUp_OnAction(ActionEvent event) {
        if (txtName.getText ().trim ().isEmpty ()){
            new Alert (Alert.AlertType.ERROR, "Enter a name", ButtonType.OK).showAndWait ();
            txtName.requestFocus ();
            return;
        } else if (!(Validation.isNameValid (txtName.getText ()))) {
            new Alert (Alert.AlertType.ERROR, "Enter a Valid Name", ButtonType.OK).showAndWait ();
            txtName.requestFocus ();
            return;
        } else if (txtEmail.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Enter a Email", ButtonType.OK).showAndWait ();
            txtEmail.requestFocus ();
            return;
        } else if (!(Validation.isValidEmail (txtEmail.getText ()))) {
            new Alert (Alert.AlertType.ERROR, "Enter a Valid Email", ButtonType.OK).showAndWait ();
            txtEmail.requestFocus ();
            return;
        } else if (txtPassword.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Enter a Password", ButtonType.OK).showAndWait ();
            txtPassword.requestFocus ();
            return;
//        } else if (!(Validation.isValidPassword (txtPassword.getText ()))) {
//            new Alert (Alert.AlertType.ERROR, "Enter a Valid Password", ButtonType.OK).showAndWait ();
//            txtPassword.requestFocus ();
//            return;
        }

        String name = txtName.getText ();
        String email = txtEmail.getText ();
        String password = txtPassword.getText ();

        try {

            pstm = connection.prepareStatement ("INSERT into admin(email, password, name) values (?,?,?)");
            pstm.setString (1, email);
            pstm.setString (2, password);
            pstm.setString (3, name);

            System.out.println (pstm);
            pstm.executeUpdate ();

            showAlert (Alert.AlertType.CONFIRMATION, "Registration Successful", "Welcome " + name);


        } catch (Exception e) {
            e.printStackTrace ();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert (alertType);
        alert.setTitle (title);
        alert.setHeaderText (null);
        alert.setContentText (message);
        alert.show ();
    }
}
