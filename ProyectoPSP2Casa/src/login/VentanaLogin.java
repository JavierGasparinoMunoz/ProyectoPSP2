package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VentanaLogin extends Application {
    @Override
    public void start(Stage s) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./layout_login.fxml"));
        s.setScene(new Scene(root));
        s.show();
    }
}

