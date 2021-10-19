package lk.itsei.fx.sms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewStudentFormController {

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblMainNav;

    @FXML
    private TextField txtRegistrationId;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtStudentName;

    @FXML
    private DatePicker txtRegDate;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TextField txtCoursePrice;

    @FXML
    private TextField txtCourseDuration;

    @FXML
    private TableView<?> tblRegistrationDetail;

    @FXML
    private Label lblCourseName;

    @FXML
    void navigateToMain(MouseEvent event) {

    }
}
