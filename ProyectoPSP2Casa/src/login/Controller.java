package login;

import Programa.Entrada;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Connection con;

    @FXML
    TextField txtUser, txtPass;
    @FXML
    Button btnLogin, btnSalir, btnRegistro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        btnSalir.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                System.exit(0);
            }
        });

        btnRegistro.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {

                String passhash = shaHash(txtPass.getText(), "gaspamola");
                try {
                    con.createStatement().executeUpdate(String.format("INSERT INTO login (`user`,`pass`) VALUES ('%s' ,'%s')",txtUser.getText(), passhash));
                    Alert dialogoWarning = new Alert(Alert.AlertType.INFORMATION);
                    dialogoWarning.setTitle("Warning");
                    dialogoWarning.setHeaderText("Registro satisfactorio");
                    dialogoWarning.showAndWait();
                    con.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String passhash = shaHash(txtPass.getText(), "gaspamola");
                try {
                    ResultSet rs = con.prepareStatement(String.format("SELECT * FROM login WHERE user = '%s' AND pass = '%s'", txtUser.getText(), passhash)).executeQuery();
                    int contador = 0;

                    while(rs.next()){
                        contador++;
                    }
                    if(contador > 0){
                       Thread t = new Thread(new Runnable() {
                           @Override
                           public void run() {
                               try {
                                   Entrada entrada = new Entrada();
                               } catch (IOException e) {
                                   e.printStackTrace();
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                           }
                       });
                       t.start();

                    }else{
                        Alert dialogoWarning = new Alert(Alert.AlertType.ERROR);
                        dialogoWarning.setTitle("Error");
                        dialogoWarning.setHeaderText("Login incorrecto");
                        dialogoWarning.showAndWait();
                    }
                    con.close();
                    Stage s = (Stage) txtUser.getScene().getWindow();
                    s.hide();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static String shaHash(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
