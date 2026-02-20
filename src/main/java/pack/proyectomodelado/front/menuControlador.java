package pack.proyectomodelado.front;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pack.proyectomodelado.Navegador;

public class menuControlador {
    public ImageView logo;
    public Button iniciarSesion;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(){
        Image img = new Image(getClass().getResource("/images/logo.jpg").toExternalForm());
        logo.setImage(img);


        iniciarSesion.setOnAction(actionEvent -> {
            Navegador.setStageSize(1280,720);
            Navegador.load("interfazPrincipal");
        });
    }
}
