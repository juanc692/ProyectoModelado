package pack.proyectomodelado;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Navegador {
    private static StackPane mainContainer;

    public static void setMainContainer(StackPane container) {
        mainContainer = container;
    }

    public static void load(String fxml) {
        try {
            Parent view = FXMLLoader.load(
                    Navegador.class.getResource("/pack/proyectomodelado/" + fxml + ".fxml")
            );
            mainContainer.getChildren().setAll(view);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setStageSize(int width, int height) {
        Stage stage = (Stage) mainContainer.getScene().getWindow();
        stage.setWidth(width);
        stage.setHeight(height);
        stage.centerOnScreen();
    }
}
