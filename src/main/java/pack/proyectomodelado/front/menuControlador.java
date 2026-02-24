package pack.proyectomodelado.front;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pack.proyectomodelado.Navegador;
import javafx.scene.control.TextInputDialog;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class menuControlador {
    public ImageView logo;
    public Button iniciarSesion;
    public TextField usuario;
    public PasswordField contrase;
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
            if(!usuario.getText().isEmpty() && !contrase.getText().isEmpty())
            {
                if(confirmCode())
                {
                    Navegador.setStageSize(1280,740);
                    Navegador.load("interfazPrincipal");
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Llena ambos campos");
                alert.showAndWait();
            }
        });
    }

    private int genCode()
    {
        int code = (int) (Math.random() * 100000);
        code = 123456;
        return code;
    }

    private boolean confirmCode(){
        int code = genCode();
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Confirmacion");
        input.setHeaderText("Se ah enviado un codigo al correo, porfavor digitelo");
        input.setContentText("contenido");

        Optional<String> res;
        int result = 0;
        do {
             res = input.showAndWait();
            try {
                result = Integer.parseInt(res.get());
            } catch (NoSuchElementException e) {
                if (res.isPresent()) {
                    Alert err = new Alert(Alert.AlertType.ERROR);
                    err.setContentText("El campo esta vacio");
                    err.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert err = new Alert(Alert.AlertType.ERROR);
                err.setContentText("Error, el campo no puede estar vacio y no puede tener letras");
                err.showAndWait();
                continue;
            }
            if(res.isPresent() && result != code)
            {
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("El codigo no coincide");
             alert.showAndWait();
            }else {
                AtomicBoolean cancel = new AtomicBoolean(false);
                final Button cancelCheck = (Button) input.getDialogPane().lookupButton(ButtonType.CANCEL);
                cancelCheck.addEventFilter(ActionEvent.ACTION, event ->
                        cancel.set(true)
                );
                if(cancel.get())
                {
                    break;
                }
            }
        }while (res.isPresent() && result != code);

        return (result == code);
    }
}
