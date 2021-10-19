package lk.itsei.fx.sms.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.itsei.fx.sms.main.AppInitializer;

public class SearchStudentFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView imgHome;

    @FXML
    private TableView<?> tblRegistered;

    @FXML
    void navigateToHome(MouseEvent event) throws Exception {
        AppInitializer.navigateToHome (root, (Stage) this.root.getScene ().getWindow ());

    }
}
