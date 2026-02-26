package pack.proyectomodelado.front;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import pack.proyectomodelado.entidades.evento;
import pack.proyectomodelado.entidades.usuario;

import java.util.ArrayList;
import java.util.Optional;

public class interfazPrincipalControl {

    public ComboBox comboUbicacion;
    public Button btnCancelar;
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
    @FXML
    private GridPane gridEventos;
    @FXML
    private Button btnBuscar;
    @FXML TextField fieldBuscar;
    @FXML ComboBox comboFiltroEvento;
    @FXML Button btnEliminarFiltros;


    private ArrayList <evento> listaEventos = new ArrayList<>();
    private int columna = 0;
    private int fila = 0;
    private int contadorFila=3;

    private usuario perfilUsuario = new usuario("Bogota");

    @FXML
    public void initialize(){
        ColumnConstraints col = new ColumnConstraints();
        col.setHgrow(Priority.ALWAYS);
        col.setFillWidth(true);

        comboFiltroEvento.setPromptText("Tipo Evento");
        comboUbicacion.setPromptText("Ubicación");

        gridEventos.getColumnConstraints().add(col);

        btnCrearEvento.setOnAction(e->{
            mostrarFormulario();
        });
        btnCrear.setOnAction(e->{
            anadirEvento();
            mostrarEventos();
        });
        btnCancelar.setOnAction(e->{
            ocultarFormulario();
            mostrarEventos();
        });
        btnBuscar.setOnAction(e->{
            filtrosEventos(fieldBuscar.getText());
        });
        comboFiltroEvento.setOnAction(e->{
            filtroCategoria();
        });
        comboUbicacion.setOnAction(e->{
            filtroCiudad();
        });

        btnEliminarFiltros.setOnAction(e->{

            comboFiltroEvento.setValue(null);
            comboUbicacion.setValue(null);

            mostrarEventos();
        });

    }

    private void filtroCategoria(){
        limpiarEventosGridPane();
        if(!comboFiltroEvento.getItems().isEmpty()) {
            for (evento e : listaEventos) {
                if (e.getCategoria().equals(comboFiltroEvento.getValue().toString())) {
                    modificarGridPane(e.getCiudad(), e.getNombre(), e.getCategoria(), e.getPrecioEntrada());
                }
            }
        }
    }

    private void filtroCiudad()
    {
        limpiarEventosGridPane();
        if(!comboUbicacion.getItems().isEmpty())
        {
            for(evento e : listaEventos)
            {
                if(e.getCiudad().equals(comboUbicacion.getValue().toString()))
                {
                    modificarGridPane(e.getCiudad(),e.getNombre(),e.getCategoria(),e.getPrecioEntrada());
                }
            }
        }

    }

    private void filtrosEventos(String nombreBusqueda){
        limpiarEventosGridPane();

        if (!nombreBusqueda.isEmpty()){
            if (comboFiltroEvento.getValue() !=null){
                for (evento e : listaEventos){
                    if (e.getCategoria().equals(comboFiltroEvento.getValue().toString()) && e.getNombre().equals(nombreBusqueda)){
                        modificarGridPane(e.getCiudad(),e.getNombre(),e.getCategoria(),e.getPrecioEntrada());
                    }
                }
            }else {
                for (evento e : listaEventos){
                    if (e.getNombre().equals(nombreBusqueda)){
                        modificarGridPane(e.getCiudad(),e.getNombre(),e.getCategoria(),e.getPrecioEntrada());
                    }
                }
            }
        }

    }

    private  void modificarGridPane(String ciudad, String nombre, String categoria, float precios){
        VBox vEvento = new VBox();

        vEvento.setMaxWidth(Double.MAX_VALUE);
        GridPane.setHgrow(vEvento, Priority.ALWAYS);

        Label labelNombre = new Label(nombre);
        labelNombre.setMinHeight(37.0);
        labelNombre.setMinWidth(105.0);

        Label labelCiudad = new Label("Ciudad: "+ciudad);
        labelCiudad.setMinHeight(37.0);
        labelCiudad.setMinWidth(105.0);

        Label labelCategoria = new Label("Categoria: "+categoria);
        labelCategoria.setMinHeight(37.0);
        labelCategoria.setMinWidth(105.0);

        Label labelPrecio = new Label("$"+precios);
        labelPrecio.setMinHeight(37.0);
        labelPrecio.setMinWidth(105.0);

        Button btnPagar = new Button("Pagar");
        vEvento.getChildren().addAll(
                labelNombre,labelCiudad,labelCategoria,labelPrecio,btnPagar
        );
        gridEventos.add(vEvento,columna,fila);
        columna++;
        //System.out.println("fila"+fila);
        //System.out.println("columna"+columna);
        if (columna >= contadorFila){
            fila++;
            columna=0;
            contadorFila = contadorFila + 3;
        }
    }

    private void anadirEvento(){
        try {
            if (comboCiudad != null || comboCategoria != null || fieldNombre.getText().isEmpty() || Float.valueOf(fieldEntrada.getText()) <= 0 ){
                evento nuevo = new evento(comboCiudad.getValue().toString(),fieldNombre.getText(),comboCategoria.getValue().toString(),Float.valueOf(fieldEntrada.getText()));
                //System.out.println(nuevo.getPrecioEntrada()+" exito");
                listaEventos.add(nuevo);
                ocultarFormulario();
            }
        } catch (Exception e) {
            System.out.println("fallo");
        }

    }

    //region metodos auxiliares
    private void mostrarEventos(){
        limpiarEventosGridPane();
        for (evento e: listaEventos){
            modificarGridPane(e.getCiudad(),e.getNombre(),e.getCategoria(),e.getPrecioEntrada());
        }
    }

    private void limpiarEventosGridPane(){
        gridEventos.getChildren().clear();
        columna=0;
        contadorFila=3;
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

        mostrarEventos();
    }
    //endregion


}
