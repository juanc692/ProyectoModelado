package pack.proyectomodelado.front;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class menuControlador {
    public ImageView logo;
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize(){
        Image img = new Image(getClass().getResource("/images/logo.jpg").toExternalForm());

        logo.setImage(img);

    }
}
