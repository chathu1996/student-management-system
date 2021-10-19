package lk.itsei.fx.sms.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgStudent;

    @FXML
    private ImageView imgCourse;

    @FXML
    private ImageView imgPayment;

    @FXML
    private ImageView imgRegistration;

    @FXML
    private ImageView imgSearchStudent;

    @FXML
    private ImageView imgLogOut;

    @FXML
    private Label lblMenu;

    @FXML
    private Label lblDescription;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();

    }

    @FXML
    void btnLogOut_OnMouseClick(MouseEvent event) throws IOException {
        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        alert.setTitle ("LogOut");
        alert.setHeaderText ("You're about to logout");
        alert.setContentText ("Do you want to logout?");

        if (alert.showAndWait ().get () == ButtonType.OK){
            Stage stage = (Stage) root.getScene ().getWindow ();
            stage.close ();
        }


    }

    @FXML
    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource () instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource ();

            Parent root = null;

            switch (icon.getId ()) {
                case "imgStudent":
                    root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/ManageStudentForm.fxml"));
                    break;
                case "imgCourse":
                    root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/ManageCourseForm.fxml"));
                    break;
                case "imgPayment":
                    root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/ManagePayment.fxml"));
                    break;
                case "imgRegistration":
                    root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/RegistrationDetails.fxml"));
                    break;
                case "imgSearchStudent":
                    root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/SearchStudentForm.fxml"));
                    break;
            }

            if (root != null) {
                Scene subScene = new Scene (root);
                Stage primaryStage = (Stage) this.root.getScene ().getWindow ();
                primaryStage.setScene (subScene);
                primaryStage.centerOnScreen ();

                TranslateTransition ttl = new TranslateTransition (Duration.millis (350), subScene.getRoot ());
                ttl.setFromX (-subScene.getWidth ());
                ttl.setToX (0);
                ttl.play ();
            }
        }
    }

    @FXML
    void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource () instanceof ImageView ) {
            ImageView icon = (ImageView) event.getSource ();

            switch (icon.getId ()) {
                case "imgStudent":
                    lblMenu.setText ("Manage Student");
                    lblDescription.setText ("Click to add, edit, delete, search or view student");
                    break;
                case "imgCourse":
                    lblMenu.setText ("Manage Course");
                    lblDescription.setText ("Click to add, edit, delete, search or view course");
                    break;
                case "imgPayment":
                    lblMenu.setText ("Manage Payment");
                    lblDescription.setText ("Click to payment");
                    break;
                case "imgRegistration":
                    lblMenu.setText ("Registration");
                    lblDescription.setText ("Click to register student in courses");
                    break;
                case "imgSearchStudent":
                    lblMenu.setText ("Search Student");
                    lblDescription.setText ("Click to search students");
                    break;
            }
        }

    }

    @FXML
    void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof javafx.scene.image.ImageView){
            javafx.scene.image.ImageView icon = (javafx.scene.image.ImageView) event.getSource();
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }

    }



}
