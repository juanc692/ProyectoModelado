package pack.proyectomodelado.front;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import pack.proyectomodelado.Navegador;

import java.io.IOException;


public class principalControlador {
    public StackPane content;

    public void loadView(String fxml) throws IOException {
        Parent view = FXMLLoader.load(
                getClass().getResource("/pack/proyectomodelado/" + fxml + ".fxml")
        );

        content.getChildren().setAll(view);
    }

    @FXML
    public void initialize() {
        Navegador.setMainContainer(content);
        Navegador.load("menu");
    }
}





