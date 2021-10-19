package lk.itsei.fx.sms.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.itsei.fx.sms.business.ManageStudentBusiness;
import lk.itsei.fx.sms.dto.StudentDTO;
import lk.itsei.fx.sms.main.AppInitializer;
import lk.itsei.fx.sms.validate.Validation;
import lk.itsei.fx.sms.view.util.StudentTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManageStudentFormController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgHome;

    @FXML
    private Button btnAddNewStudents;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStdenName;

    @FXML
    private TextField txtStudentTeleNum;

    @FXML
    private TextField txtStudentEmail;

    @FXML
    private TextField txtStudentAdd;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private TableView <StudentTM> tblStudents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblStudents.getColumns ().get (0).setCellValueFactory (new PropertyValueFactory<> ("sid"));
        tblStudents.getColumns ().get (1).setCellValueFactory (new PropertyValueFactory<> ("name"));
        tblStudents.getColumns ().get (2).setCellValueFactory (new PropertyValueFactory<> ("teleNumber"));
        tblStudents.getColumns ().get (3).setCellValueFactory (new PropertyValueFactory<> ("email"));
        tblStudents.getColumns ().get (4).setCellValueFactory (new PropertyValueFactory<> ("address"));

        btnSave.setDisable (true);
        btnDelete.setDisable (true);

        ArrayList<StudentDTO> studentDB = null;
        try {
            studentDB = (ArrayList <StudentDTO>) ManageStudentBusiness.getStudents ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        ObservableList<StudentDTO> studentDTOS = FXCollections.observableArrayList (studentDB);
        ObservableList<StudentTM> tblItems = FXCollections.observableArrayList ();
        for (StudentDTO studentDTO : studentDTOS) {
            tblItems.add ( new StudentTM (
                    studentDTO.getSid (),
                    studentDTO.getStudentNAme (),
                    studentDTO.getStdentTelNum (),
                    studentDTO.getStudentEmail (),
                    studentDTO.getStudentAddress ()
            ));
        }
        tblStudents.setItems (tblItems);

        tblStudents.getSelectionModel ().selectedItemProperty ().addListener (new ChangeListener <StudentTM> () {
            @Override
            public void changed(ObservableValue<? extends StudentTM> observable, StudentTM oldValue, StudentTM selectedStudent) {
               if (selectedStudent == null) {
                   return;
               }
               txtStudentId.setText (selectedStudent.getSid ());
               txtStdenName.setText (selectedStudent.getName ());
               txtStudentTeleNum.setText (selectedStudent.getTeleNumber ());
               txtStudentEmail.setText (selectedStudent.getEmail ());
               txtStudentAdd.setText (selectedStudent.getAddress ());

               txtStudentId.setEditable (false);

               btnSave.setDisable (false);
               btnDelete.setDisable (false);
            }
        });

    }

    @FXML
    void btnAddNew_OnAction(ActionEvent event) {
        reset ();
    }

    @FXML
    void btnDelete_OnAction(ActionEvent event) throws Exception {

        String selectIndex = tblStudents.getSelectionModel ().getSelectedItem ().getSid ();
        Optional<ButtonType> buttonType = new Alert (Alert.AlertType.CONFIRMATION, "Are you sure to delete?", ButtonType.OK).showAndWait ();
        if (buttonType.get().getText ().equals ("OK")) {
            ManageStudentBusiness.deleteStudent (selectIndex);
            reset ();
        }

    }

    public static void main(String[] args) {

    }

    @FXML
    void btnSave_OnAction(ActionEvent event) throws Exception {

        if (txtStudentId.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Student ID is required", ButtonType.OK).showAndWait ();
            txtStudentId.requestFocus ();
            return;
        }else if (txtStdenName.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Student Name is required", ButtonType.OK).showAndWait ();
            txtStdenName.requestFocus ();
            return;
        } else if (!(Validation.isNameValid (txtStdenName.getText ()))) {
            new Alert (Alert.AlertType.ERROR, "Student Name Should be propper Name", ButtonType.OK).showAndWait ();
            txtStdenName.getText ();
            return;
        } else if (txtStudentTeleNum.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Student Telephone Number is required", ButtonType.OK).showAndWait ();
            txtStudentTeleNum.requestFocus ();
            return;
        } else if (!(Validation.isNumberValid (txtStudentTeleNum.getText ()))) {
            new Alert (Alert.AlertType.ERROR, "Tele Number Format is - xxxxxxxxxx", ButtonType.OK).showAndWait ();
            txtStudentTeleNum.requestFocus ();
            return;
        }
        else if (txtStudentAdd.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Student Address is required", ButtonType.OK).showAndWait ();
            txtStudentAdd.requestFocus ();
            return;
        }else if (txtStudentEmail.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Student Email is required", ButtonType.OK).showAndWait ();
            txtStudentEmail.requestFocus ();
            return;
        } else if (!(Validation.isValidEmail (txtStudentEmail.getText ()))) {
            new Alert (Alert.AlertType.ERROR, "Enter Correct Email eg:- info@example.com", ButtonType.OK).showAndWait ();
            txtStudentEmail.requestFocus ();
            return;
        }
        if (tblStudents.getSelectionModel ().isEmpty ()) {
            ObservableList<StudentTM> items = tblStudents.getItems ();
            for (StudentTM studentTM : items) {
                if (studentTM.getSid ().equals (txtStudentId.getText ())) {
                    new Alert (Alert.AlertType.ERROR, "Duplicate Student ID", ButtonType.OK).showAndWait ();
                    txtStudentId.requestFocus ();
                    return;
                }
            }

            StudentTM studentTM = new StudentTM (txtStudentId.getText (), txtStdenName.getText (), txtStudentTeleNum.getText (), txtStudentEmail.getText (), txtStudentAdd.getText ());
            tblStudents.getItems ().add (studentTM);
            StudentDTO student = new StudentDTO (
                    txtStudentId.getText (),
                    txtStdenName.getText (),
                    txtStudentTeleNum.getText (),
                    txtStudentEmail.getText (),
                    txtStudentAdd.getText ()
            );
            ManageStudentBusiness.createStudent (student);
            new Alert (Alert.AlertType.CONFIRMATION, "Student has been saved successully", ButtonType.OK).showAndWait ();
        } else {
            StudentTM selectedStudent = tblStudents.getSelectionModel ().getSelectedItem ();
            selectedStudent.setName (txtStdenName.getText ());
            selectedStudent.setTeleNumber (txtStudentTeleNum.getText ());
            selectedStudent.setEmail (txtStudentEmail.getText ());
            selectedStudent.setAddress (txtStudentAdd.getText ()
            );
            int selectedRow = tblStudents.getSelectionModel ().getSelectedIndex ();
            ManageStudentBusiness.updateStudent (selectedRow, new StudentDTO (
                    txtStudentId.getText (),
                    txtStdenName.getText (),
                    txtStudentTeleNum.getText (),
                    txtStudentEmail.getText (),
                    txtStudentAdd.getText ()
            ));
            new Alert (Alert.AlertType.CONFIRMATION, "Student has been updated",  ButtonType.OK).showAndWait ();
        }


    }

    @FXML
    void navigateToHome(MouseEvent event) throws Exception {
        AppInitializer.navigateToHome (root, (Stage) this.root.getScene ().getWindow ());
    }


    public void reset() {
        txtStudentId.clear ();
        txtStdenName.clear ();
        txtStudentTeleNum.clear ();
        txtStudentEmail.clear ();
        txtStudentAdd.clear ();
        tblStudents.getSelectionModel ().clearSelection ();
        txtStudentId.setEditable (true);
        btnSave.setDisable (false);
        btnDelete.setDisable (false);
    }


}
