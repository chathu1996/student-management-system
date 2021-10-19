package lk.itsei.fx.sms.main;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppInitializer extends Application {

    public static void navigateToHome(Node node, Stage mainStage) throws Exception {

        Parent root = FXMLLoader.load (AppInitializer.class.getResource ("/lk/itsei/fx/sms/view/MainForm.fxml"));
        Scene mainScene = new Scene (root);
        mainStage.setScene (mainScene);

        TranslateTransition ttl = new TranslateTransition (Duration.millis (300), root.lookup ("AnchorPane"));
        ttl.setToX (0);
        ttl.setFromX (-mainScene.getWidth ());
        ttl.play ();

        mainStage.centerOnScreen ();
    }

    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load (this.getClass ().getResource ("/lk/itsei/fx/sms/view/MainForm.fxml"));

        Scene mainScene = new Scene (root);

        primaryStage.setTitle ("ITSEI FX SMS");
        primaryStage.setScene (mainScene);
        primaryStage.setResizable (false);

        primaryStage.show ();

    }
}
