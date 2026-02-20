package pack.proyectomodelado;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

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
}
