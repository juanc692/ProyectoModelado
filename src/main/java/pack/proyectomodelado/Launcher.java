package pack.proyectomodelado;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("ventana.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Parchador");
        Image img = new Image(getClass().getResource("/images/logo.jpg").toExternalForm());
        stage.getIcons().add(img);

        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setResizable(false);
        stage.show();
    }
}
