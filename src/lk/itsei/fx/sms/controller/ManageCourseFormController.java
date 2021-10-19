package lk.itsei.fx.sms.controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import lk.itsei.fx.sms.business.ManageCourseBusiness;
import lk.itsei.fx.sms.business.ManageStudentBusiness;
import lk.itsei.fx.sms.db.DBConnection;
import lk.itsei.fx.sms.dto.CourseDTO;
import lk.itsei.fx.sms.entity.Course;
import lk.itsei.fx.sms.main.AppInitializer;
import lk.itsei.fx.sms.view.util.CourseTM;
import lk.itsei.fx.sms.view.util.StudentTM;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageCourseFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgHome;

    @FXML
    private Button btnAddNew;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtCoursePrice;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnViewBlockCourse;

    @FXML
    private Button btnViewPendingCourse;

    @FXML
    private Button btnBlock;

    @FXML
    private Button btnPending;

    @FXML
    private TableView<CourseTM> tblCourse;

    @FXML
    private ComboBox txtCmb;

    String  selectedItem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblCourse.getColumns ().get (0).setCellValueFactory (new PropertyValueFactory<> ("cid"));
        tblCourse.getColumns ().get (1).setCellValueFactory (new PropertyValueFactory<> ("duration"));
        tblCourse.getColumns ().get (2).setCellValueFactory (new PropertyValueFactory<> ("name"));
        tblCourse.getColumns ().get (3).setCellValueFactory (new PropertyValueFactory<> ("price"));
        tblCourse.getColumns ().get (4).setCellValueFactory (new PropertyValueFactory<> ("status"));

        btnSave.setDisable (true);
        btnDelete.setDisable (true);

        ArrayList<CourseDTO> courseDB = null;
        try {
            courseDB= (ArrayList <CourseDTO>) ManageCourseBusiness.getCourse ();
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("error is " + e);
        }
        ObservableList<CourseDTO> courseDTOS = FXCollections.observableArrayList (courseDB);
        ObservableList<CourseTM> courseTM = FXCollections.observableArrayList ();
        for (CourseDTO courseDTO : courseDTOS) {
            courseTM.add (new CourseTM (
                    courseDTO.getCid (),
                    courseDTO.getDuration (),
                    courseDTO.getName (),
                    courseDTO.getPrice (),
                    courseDTO.getStatus ()
            ));
        }
        tblCourse.setItems (courseTM);

        tblCourse.getSelectionModel ().selectedItemProperty ().addListener (new ChangeListener <CourseTM> () {
            @Override
            public void changed(ObservableValue<? extends CourseTM> observable, CourseTM oldValue, CourseTM selectedCourse) {
                if (selectedCourse == null) {
                    return;
                }
                txtCourseId.setText (selectedCourse.getCid ());
                txtCourseDuration.setText (selectedCourse.getDuration ());
                txtCourseName.setText (selectedCourse.getName ());
                txtCoursePrice.setText (selectedCourse.getPrice () + "");


                txtCourseId.setEditable (false);

                btnSave.setDisable (false);
                btnDelete.setDisable (false);
            }
        });
        txtCmb.getItems ().add ("Pending");
        txtCmb.getItems ().add ("Block");
        txtCmb.setEditable (true);

    }

    @FXML
    void btnAddNew_OnAction(ActionEvent event) {
        reset ();

    }

    @FXML
    void btnDelete_OnAction(ActionEvent event) throws Exception {

        String selectIndex = tblCourse.getSelectionModel ().getSelectedItem ().getCid ();
        Optional<ButtonType> buttonType = new Alert (Alert.AlertType.CONFIRMATION, "Are you sure to delete?", ButtonType.OK).showAndWait ();
        if (buttonType.get().getText ().equals ("OK")) {
            ManageCourseBusiness.deleteCourse (selectIndex);
        }
        reset ();

    }

    @FXML
    void btnSave_OnAction(ActionEvent event) throws Exception {

        if (txtCourseId.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Course Id is required", ButtonType.OK).showAndWait ();
            txtCourseId.requestFocus ();
            return;
        }else if (txtCourseDuration.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Course duration is required", ButtonType.OK).showAndWait ();
            txtCourseDuration.requestFocus ();
            return;
        } else if (txtCourseName.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Course name is required", ButtonType.OK).showAndWait ();
            txtCourseName.requestFocus ();
            return;
        }else if (txtCoursePrice.getText ().trim ().isEmpty ()) {
            new Alert (Alert.AlertType.ERROR, "Course price is required", ButtonType.OK).showAndWait ();
            txtCoursePrice.requestFocus ();
            return;
        }else if (!isDouble (txtCoursePrice.getText ()) || Double.parseDouble (txtCoursePrice.getText ()) < 0) {
            new Alert (Alert.AlertType.ERROR, "Invalid course price", ButtonType.OK).showAndWait ();
            return;
        }
        if (tblCourse.getSelectionModel ().isEmpty ()) {
            ObservableList<CourseTM> courses = tblCourse.getItems ();
            System.out.println (courses.toString ());
            for (CourseTM courseTM : courses) {
                if (courseTM.getCid ().equals (txtCourseId.getText ())) {
                    new Alert (Alert.AlertType.ERROR, "Duplicate course id", ButtonType.OK).showAndWait ();
                    txtCourseId.requestFocus ();
                    return;
                }
            }
            CourseTM courseTM = new CourseTM (txtCourseId.getText (), txtCourseDuration.getText (), txtCourseName.getText (), Double.parseDouble (txtCoursePrice.getText ()), (String) txtCmb.getSelectionModel ().getSelectedItem ());
            tblCourse.getItems ().add (courseTM);
            CourseDTO courseDTO = new CourseDTO (txtCourseId.getText (), txtCourseDuration.getText (), txtCourseName.getText (), Double.parseDouble (txtCoursePrice.getText ()), (String) txtCmb.getSelectionModel ().getSelectedItem ());
            ManageCourseBusiness.createCourse (courseDTO);

            new Alert (Alert.AlertType.CONFIRMATION, "Course has been saved successfully", ButtonType.OK).showAndWait ();
            tblCourse.scrollTo (courseTM);
        } else {
            CourseTM selectedCourse = tblCourse.getSelectionModel ().getSelectedItem ();
            selectedCourse.setDuration (txtCourseDuration.getText ());
            selectedCourse.setName (txtCourseName.getText ());
            selectedCourse.setPrice (Double.parseDouble (txtCoursePrice.getText ()));
            selectedCourse.setStatus ((String) txtCmb.getSelectionModel ().getSelectedItem ());

            String selectedCourseID = tblCourse.getSelectionModel ().getSelectedItem ().getCid ();
            ManageCourseBusiness.updateCourse (
                    selectedCourseID,
                    new CourseDTO (txtCourseId.getText (), txtCourseDuration.getText (), txtCourseName.getText (),
                   Double.parseDouble (txtCoursePrice.getText ()), (String) txtCmb.getSelectionModel ().getSelectedItem ()
                    ));

            new Alert (Alert.AlertType.CONFIRMATION, "CourseDTO has been updated successfully", ButtonType.OK).showAndWait ();
        }
        reset ();
    }

    @FXML
    void btnViewBlockCourse_OnAction(ActionEvent event) throws Exception {

        ArrayList<CourseDTO> blockCourseDB = null;
        try {
            blockCourseDB = (ArrayList <CourseDTO>) ManageCourseBusiness.getBlockCourse ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        ObservableList<CourseDTO> courseDTOS = FXCollections.observableArrayList (blockCourseDB);
        ObservableList<CourseTM> courseTM = FXCollections.observableArrayList ();
        for (CourseDTO courseDTO : courseDTOS) {
            courseTM.add (new CourseTM (
                    courseDTO.getCid (),
                    courseDTO.getDuration (),
                    courseDTO.getName (),
                    courseDTO.getPrice (),
                    courseDTO.getStatus ()
            ));
        }
        tblCourse.setItems (courseTM);
    }

    @FXML
    void btnViewPendingCourse_OnAction(ActionEvent event) {

        ArrayList<CourseDTO> pendingCourseDB = null;
        try {
            pendingCourseDB = (ArrayList <CourseDTO>) ManageCourseBusiness.getPendingCourse ();
        } catch (Exception e) {
            e.printStackTrace ();
        }
        ObservableList<CourseDTO> courseDTOS = FXCollections.observableArrayList (pendingCourseDB);
        ObservableList<CourseTM> courseTM = FXCollections.observableArrayList ();
        for (CourseDTO courseDTO : courseDTOS) {
            courseTM.add (new CourseTM (
                    courseDTO.getCid (),
                    courseDTO.getDuration (),
                    courseDTO.getName (),
                    courseDTO.getPrice (),
                    courseDTO.getStatus ()
            ));
        }
        tblCourse.setItems (courseTM);

    }

    @FXML
    void navigateToHome(MouseEvent event) throws Exception {
        AppInitializer.navigateToHome (root, (Stage) this.root.getScene ().getWindow ());

    }


    public void reset() {
        txtCourseId.clear ();
        txtCourseDuration.clear ();
        txtCourseName.clear ();
        txtCoursePrice.clear ();
        tblCourse.getSelectionModel ().clearSelection ();
        txtCourseId.setEditable (true);
        btnSave.setDisable (false);
        btnDelete.setDisable (false);
    }

    private boolean isDouble(String number) {
        try {
            Double.parseDouble (number);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}
