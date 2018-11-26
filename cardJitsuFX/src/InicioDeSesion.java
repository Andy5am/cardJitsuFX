import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioDeSesion {

    //conexion con grafico
    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;

    public void abrirPantallaInicial(ActionEvent event){
        Parent root;
        try {
            String usuario = userField.getText();
            String contraserna = passwordField.getText();
            //Carga ventana
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
            root =loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pantalla inicial");
            stage.setScene(new Scene(root,450,450));
            //Conexion con controller de nueva lista
            PantallaInicial controllerPantallaInicial = loader.getController();

            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void abrirNuevoPerfil(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NuevoPerfil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Nuevo Perfil");
            stage.setScene(new Scene(root,450,450));
            NuevoPerfil controllerNuevoPerfil = loader.getController();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
