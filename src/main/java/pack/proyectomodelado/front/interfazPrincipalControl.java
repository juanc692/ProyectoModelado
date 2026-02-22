package pack.proyectomodelado.front;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pack.proyectomodelado.entidades.evento;

import java.util.ArrayList;
import java.util.Optional;

public class interfazPrincipalControl {

    @FXML
    private Button btnCrearEvento;
    @FXML
    private AnchorPane anchorFormulario;
    @FXML
    private AnchorPane anchorPrincipal;
    @FXML
    private ComboBox comboCiudad;
    @FXML
    private TextField fieldNombre;
    @FXML
    private ComboBox comboCategoria;
    @FXML
    private TextField fieldEntrada;
    @FXML
    private Button btnCrear;


    private ArrayList <evento> listaEventos = new ArrayList<>();

    @FXML
    public void initialize(){
        btnCrearEvento.setOnAction(e->{
            mostrarFormulario();
        });
        btnCrear.setOnAction(e->{
            anadirEvento();
        });

    }

    private void mostrarFormulario(){
        anchorPrincipal.setVisible(false);
        anchorPrincipal.setDisable(true);

        anchorFormulario.setVisible(true);
        anchorFormulario.setDisable(false);
    }

    private void ocultarFormulario(){
        anchorPrincipal.setVisible(true);
        anchorPrincipal.setDisable(false);

        anchorFormulario.setVisible(false);
        anchorFormulario.setDisable(true);
    }

    private void anadirEvento(){
        if (comboCiudad != null || comboCategoria != null || fieldNombre.getText().isEmpty() || Float.valueOf(fieldEntrada.getText()) <= 0 ){
            evento nuevo = new evento(comboCiudad.getValue().toString(),fieldNombre.getText(),comboCategoria.getValue().toString(),Float.valueOf(fieldEntrada.getText()));
            System.out.println(nuevo.getPrecioEntrada()+" exito");
            listaEventos.add(nuevo);
            ocultarFormulario();
        }
    }

}
