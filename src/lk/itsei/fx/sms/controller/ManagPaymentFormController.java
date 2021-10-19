package lk.itsei.fx.sms.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.itsei.fx.sms.main.AppInitializer;

public class ManagPaymentFormController {


    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgHome;

    @FXML
    private Button btnAddNewPayment;

    @FXML
    private TextField txtPaymentAmout;

    @FXML
    private TextField txtCvc;

    @FXML
    private TextField txtCardNumber;

    @FXML
    private Button btnPay;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtCourseName;

    @FXML
    private TableView<?> tblPaymentDetail;

    @FXML
    private TextField txtExpireDate;

    @FXML
    void btnAddNewPayment_OnAction(ActionEvent event) {

    }

    @FXML
    void btnPay_OnAction(ActionEvent event) {

    }

    @FXML
    void navigateToHome(MouseEvent event) throws Exception {
        AppInitializer.navigateToHome (root, (Stage) this.root.getScene ().getWindow ());
    }
}
