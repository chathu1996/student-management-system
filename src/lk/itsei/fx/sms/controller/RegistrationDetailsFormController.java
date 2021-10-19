package lk.itsei.fx.sms.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.itsei.fx.sms.business.ManageCourseBusiness;
import lk.itsei.fx.sms.business.ManageStudentBusiness;
import lk.itsei.fx.sms.business.RegistrationDetailsBusiness;
import lk.itsei.fx.sms.dto.CourseDTO;
import lk.itsei.fx.sms.dto.RegistrationDetailsDTO;
import lk.itsei.fx.sms.dto.StudentDTO;
import lk.itsei.fx.sms.view.util.RegistrationDetailsTM;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrationDetailsFormController implements Initializable {



    @FXML
    private AnchorPane root;

    @FXML
    private Label lblMainNav;

    @FXML
    private Button btnRegisterNewStudent;

    @FXML
    private TextField txtRegisterId;

    @FXML
    private TextField txtStudentId;

    @FXML
    private DatePicker txtRegDate;

    @FXML
    private TextField txtStudentName;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TextField txtCoursePrice;

    @FXML
    private TextField txtCourseStatus;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView<RegistrationDetailsTM> tblRegistrationDetails;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        tblRegistrationDetails.getColumns ().get (0).setCellValueFactory (new PropertyValueFactory<> ("rid"));
        tblRegistrationDetails.getColumns ().get (1).setCellValueFactory (new PropertyValueFactory<> ("sid"));
        tblRegistrationDetails.getColumns ().get (2).setCellValueFactory (new PropertyValueFactory<> ("studentName"));
        tblRegistrationDetails.getColumns ().get (3).setCellValueFactory (new PropertyValueFactory<> ("cid"));
        tblRegistrationDetails.getColumns ().get (4).setCellValueFactory (new PropertyValueFactory<> ("name"));
        tblRegistrationDetails.getColumns ().get (5).setCellValueFactory (new PropertyValueFactory<> ("status"));
        tblRegistrationDetails.getColumns ().get (6).setCellValueFactory (new PropertyValueFactory<> ("price"));
        tblRegistrationDetails.getColumns ().get (7).setCellValueFactory (new PropertyValueFactory<> ("CouseDuration"));


        ArrayList<RegistrationDetailsDTO> registrationDB = null;
        try {
            registrationDB = (ArrayList <RegistrationDetailsDTO>) RegistrationDetailsBusiness.getRegistrationDetails ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        ObservableList<RegistrationDetailsDTO> registrationDetailsDTOS = FXCollections.observableArrayList (registrationDB);
        ObservableList<RegistrationDetailsTM> tblItms = FXCollections.observableArrayList ();
        for (RegistrationDetailsDTO registrationDetailsDTO : registrationDetailsDTOS) {
            tblItms.add (new RegistrationDetailsTM (
                    registrationDetailsDTO.getRid (),
                    registrationDetailsDTO.getSid (),
                    registrationDetailsDTO.getStudentName (),
                    registrationDetailsDTO.getCid (),
                    registrationDetailsDTO.getName (),
                    registrationDetailsDTO.getStatus (),
                    registrationDetailsDTO.getPrice (),
                    registrationDetailsDTO.getCourseDuration ()
            ));
        }
        tblRegistrationDetails.setItems (tblItms);

        tblRegistrationDetails.getSelectionModel ().selectedItemProperty ().addListener (new ChangeListener <RegistrationDetailsTM> () {
            @Override
            public void changed(ObservableValue<? extends RegistrationDetailsTM> observable, RegistrationDetailsTM oldValue, RegistrationDetailsTM selectedRegistration) {

                if (selectedRegistration == null) {
                    return;
                }

                txtRegisterId.setText (selectedRegistration.getRid ());
                txtStudentId.setText (selectedRegistration.getSid ());
                txtStudentName.setText (selectedRegistration.getStudentName ());
                txtCourseId.setText (selectedRegistration.getCid ());
                txtCourseName.setText (selectedRegistration.getName ());
                txtCourseStatus.setText (selectedRegistration.getStatus ());
                txtCoursePrice.setText ( String.valueOf (selectedRegistration.getPrice ()));
                txtCourseDuration.setText (selectedRegistration.getCouseDuration ());


                txtRegisterId.setEditable (false);

                btnSave.setDisable (false);
                btnDelete.setDisable (false);
            }
        });

        Platform.runLater (() -> {
            txtRegisterId.requestFocus ();
        });

    }


    @FXML
    void btnDelete_onAction(ActionEvent event) throws Exception {
        Alert conFirmMsg = new Alert (Alert.AlertType.CONFIRMATION, "Are You Sure to Delete?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = conFirmMsg.showAndWait ();

        if (buttonType.get () == ButtonType.YES) {
            String selectedReg = tblRegistrationDetails.getSelectionModel ().getSelectedItem ().getRid ();

            tblRegistrationDetails.getItems ().remove (tblRegistrationDetails.getSelectionModel ().getSelectedItem ());
            RegistrationDetailsBusiness.deleteRegistration (selectedReg);
            reset ();
        }

    }

    @FXML
    void btnRegisterNewStudent_onAction(ActionEvent event) {
        reset();

    }

    @FXML
    void btnSave_onAction(ActionEvent event) throws Exception {

        if (txtRegisterId.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Register Id is Empty", ButtonType.OK).showAndWait ();
            txtRegisterId.requestFocus ();
            return;
        } else if (txtStudentId.getText ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Can't Register Student without Student Id", ButtonType.OK).showAndWait ();
            txtStudentId.requestFocus ();
            return;
        } else if (txtCourseId.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Can't Register Student without Course Id", ButtonType.OK).showAndWait ();
            txtCourseId.requestFocus ();
            return;
        }

        if (tblRegistrationDetails.getSelectionModel ().isEmpty ()) {

            ObservableList<RegistrationDetailsTM> registrationDetailsTMS = tblRegistrationDetails.getItems ();
            for (RegistrationDetailsTM registrationDetailsTM : registrationDetailsTMS) {
                if (registrationDetailsTM.getRid ().equals (txtRegisterId.getText ())) {
                    new Alert (Alert.AlertType.ERROR, "Duplicate Registration Id", ButtonType.OK).showAndWait ();
                    txtCourseId.requestFocus ();
                    return;
                }
            }
            RegistrationDetailsTM registrationDetailsTM = new RegistrationDetailsTM (
                    txtRegisterId.getText (),
                    txtStudentId.getText (),
                    txtStudentName.getText (),
                    txtCourseId.getText (),
                    txtCourseName.getText (),
                    txtCourseStatus.getText (),
                    Double.parseDouble (txtCoursePrice.getText ()),
                    txtCourseDuration.getText ()
            );
            tblRegistrationDetails.getItems ().add (registrationDetailsTM);
            RegistrationDetailsDTO registrationDetailsDTO = new RegistrationDetailsDTO (
                    txtRegisterId.getText (),
                    txtStudentId.getText (),
                    txtStudentName.getText (),
                    txtCourseId.getText (),
                    txtCourseName.getText (),
                    txtCourseStatus.getText (),
                    Double.parseDouble (txtCoursePrice.getText ()),
                    txtCourseDuration.getText ()
            );
            RegistrationDetailsBusiness.createRegistration (registrationDetailsDTO);
            new Alert (Alert.AlertType.CONFIRMATION, "Student has been Registered for the course", ButtonType.OK).showAndWait ();
            tblRegistrationDetails.scrollTo (registrationDetailsTM);
        }
    }

    @FXML
    void navigateToMain(MouseEvent event) throws IOException {
        Label lblMainNav = (Label) event.getSource ();
        Stage primaryStage = (Stage) lblMainNav.getScene ().getWindow ();

        Parent root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/MainForm.fxml"));
        Scene mainScene = new Scene (root);
        primaryStage.setScene (mainScene);
        primaryStage.centerOnScreen ();

    }

    @FXML
    void txtCourseId_onAction(ActionEvent event) throws Exception {
        CourseDTO courseDTO = validateCourseId ();

        if (courseDTO != null) {
            txtCourseName.setText (courseDTO.getName ());
            txtCourseDuration.setText (courseDTO.getDuration ());
            txtCoursePrice.setText (courseDTO.getPrice () + "");
            txtCourseStatus.setText (courseDTO.getStatus ());
        }
    }

    @FXML
    void txtStudentId_OnAction(ActionEvent event) throws Exception {
        String studentId = txtStudentId.getText ();

        StudentDTO studentDTO = ManageStudentBusiness.findStudent (studentId);
        if (studentDTO == null) {
            new Alert (Alert.AlertType.ERROR, "Invalid Student Id", ButtonType.OK).showAndWait ();
            txtStudentId.clear ();
            txtStudentId.requestFocus ();
            txtStudentId.selectAll ();
        } else {
            txtStudentName.setText (studentDTO.getStudentNAme ());
            txtCourseId.requestFocus ();
        }
    }

    private CourseDTO validateCourseId() throws Exception {
        String courseId = txtCourseId.getText ();

        CourseDTO courseDTO = ManageCourseBusiness.findCourse (courseId);
        if (courseDTO == null) {
            new Alert (Alert.AlertType.ERROR, "Invalid Course Id", ButtonType.OK).showAndWait ();
            txtCourseName.clear ();
            txtCourseDuration.clear ();
            txtCoursePrice.clear ();
            txtCourseStatus.clear ();
            txtCourseId.requestFocus ();
            txtCourseId.selectAll ();
        }
        return courseDTO;
    }

    private RegistrationDetailsTM isCourseExist(String courseId) {
        ObservableList<RegistrationDetailsTM> registrations = tblRegistrationDetails.getItems ();
        for (RegistrationDetailsTM items : registrations) {
            if (items.getCid ().equals (courseId)) {
                return items;
            }
        }
        return null;
    }


    public void reset() {
        txtRegisterId.clear ();
        txtStudentId.clear ();
        txtStudentName.clear ();
        txtCourseId.clear ();
        txtCourseName.clear ();
        txtCourseStatus.clear ();
        txtCoursePrice.clear ();
        txtCourseDuration.clear ();
        txtRegisterId.requestFocus ();
        txtRegisterId.setEditable (true);
        tblRegistrationDetails.getSelectionModel ().clearSelection ();

    }

}
