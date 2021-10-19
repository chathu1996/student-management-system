package lk.itsei.fx.sms.controller;

import com.sun.xml.internal.ws.developer.MemberSubmissionAddressing;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.itsei.fx.sms.db.DBConnection;
import lk.itsei.fx.sms.validate.Validation;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class LoginFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private Button btnOnLogin;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnSignUp;

    Stage dialogStage = new Stage();
    Scene scene;

    Connection connection = null;
    PreparedStatement pstm = null;
    ResultSet rst = null;

    public LoginFormController() throws Exception {
        connection = DBConnection.getConnection ();
    }

    @FXML
    void buttonOnLogin(ActionEvent event) {
        if (txtEmail.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Email must be Entered", ButtonType.OK).showAndWait ();
            txtEmail.requestFocus ();
            return;
        } else if (!((Validation.isValidEmail (txtEmail.getText ())))) {
            new Alert (Alert.AlertType.ERROR, "Enter correct email eg:-info@example.com", ButtonType.OK).showAndWait ();
            txtEmail.requestFocus ();
            return;
        } else if (txtPassword.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Password must be Entered", ButtonType.OK).showAndWait ();
            txtPassword.requestFocus ();
            return;
        } else if ((Validation.isValidPassword (txtPassword.getText ()))) {
            new Alert (Alert.AlertType.ERROR, "The Password you Entered is incorrect.Please try again", ButtonType.OK).showAndWait ();
            txtPassword.requestFocus ();
            return;
        }

        String email = txtEmail.getText ();
        String password = txtPassword.getText ();

        String sql = "SELECT * FROM admin WHERE email = ? and password = ?";
        try {
            pstm = connection.prepareStatement (sql);
            pstm.setString (1, email);
            pstm.setString (2, password);
            rst = pstm.executeQuery ();
            if ( !rst.next ()) {
                infoBox ("Enter Correct Email and Password", "Failed", null);
            } else {
                infoBox ("Login Successful", "Success", null);
                Node source = (Node) event.getSource ();
                dialogStage = (Stage) source.getScene ().getWindow ();
                dialogStage.close ();
                scene = new Scene (FXMLLoader.load (getClass ().getResource ("/lk/itsei/fx/sms/view/MainForm.fxml")));
                dialogStage.setScene (scene);
                dialogStage.show ();
            }

        } catch (Exception e) {
            e.printStackTrace ();
        }

    }

    @FXML
    void navigatTo(MouseEvent event) {

    }

    public static void infoBox(String infoMessage,String titleBar, String headerMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void openNiwWindow_OnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/Signupwindow.fxml"));

        Scene scene = new Scene(root);
        Stage primaryStage = new Stage ();
        primaryStage.setTitle ("Sign up");
        primaryStage.setScene (scene);
        primaryStage.setResizable (false);
        primaryStage.initModality (Modality.NONE);
        primaryStage.show ();

    }
}
