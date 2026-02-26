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

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class menuControlador {
    public ImageView logo;
    public Button iniciarSesion;
    public TextField usuario;
    public PasswordField contrase;

    public void initialize() {
        Image img = new Image(getClass().getResource("/images/logo.jpg").toExternalForm());
        logo.setImage(img);

        iniciarSesion.setOnAction(actionEvent -> {
            if (!usuario.getText().isEmpty() && !contrase.getText().isEmpty()) {
                if (usuario.getText().contains("@")) {

                    int codigo = enviarCodigoSeguridad(usuario.getText());


                    if (verificarCodigo(codigo)) {
                        Navegador.setStageSize(1280, 740);
                        Navegador.load("interfazPrincipal");
                    }
                } else {
                    mostrarAlerta("error", "Debe ser un correo válido");
                }
            } else {
                mostrarAlerta("error", "llena los campos");
            }
        });
    }

    /*
    private int genCode()
    {
        final String emmiter = "picosalavarrietajuandavid50@gmail.com";
        final String psswrd = "";


        String email = usuario.getText();
        int code = (int) (Math.random() * 10000000);
        String header = "Codigo de acceso a PARCHADOS";
        String message = "Hola! Te ah llegado el codigo para acceder a tu cuenta, el codigo es:\n"+code+"\nSi no digitaste tu correo para esto..\n pues no se, es una prueba de java";

        Properties props = new Properties();
        props.put("mail.smtp.auth",true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){
                        return new PasswordAuthentication(emmiter,psswrd);
                    }
                });
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(emmiter));
            msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            msg.setSubject(header);
            msg.setText(message);
            Transport.send(msg);
            System.out.println("Correo enviado");
        }catch (MessagingException e)
        {
            e.printStackTrace();
        }

        System.out.println(code);
        System.out.println("Se envio al correo "+usuario.getText());
        //code = 1234567;
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
    */

    private int enviarCodigoSeguridad(String destinatario) {
        // 1. Generar el código una sola vez
        int code = (int) (Math.random() * 9000000) + 1000000;

        // Configuración de red (Gmail)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("picosalavarrietajuandavid50@gmail.com", "shgc txlj svky qrgr");
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("picosalavarrietajuandavid50@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            msg.setSubject("Código de acceso a PARCHADOS");
            String message = "Hola! Te ah llegado el codigo para acceder a tu cuenta, el codigo es:\n"+code+"\nSi no digitaste tu correo para esto..\n pues no se, es una prueba de java";
            msg.setText(message);

            Transport.send(msg);

            System.out.println("Correo enviado a: " + destinatario);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return code;
    }

    private boolean verificarCodigo(int code) {
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Confirmación");
        input.setHeaderText("Se ha enviado un código al correo, por favor digítelo");
        input.setContentText("Código:");

        Optional<String> res;
        int result = 0;

        do {
            res = input.showAndWait();

            if (!res.isPresent()) return false; // El usuario cerró o canceló

            try {
                result = Integer.parseInt(res.get());
            } catch (NumberFormatException e) {
                mostrarAlerta("Error","Error: El campo no puede estar vacío y no puede tener letras");
                continue;
            }

            if (result != code) {
                mostrarAlerta("Error","El código no coincide");
            }

        } while (result != code); // El bucle sigue hasta que coincida o cancelen

        return true;
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
