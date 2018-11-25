import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuModosDeJuego {

    @FXML
    private Button regresar;

    public void regresarPantallaInicial(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pantalla inicial");
            stage.setScene(new Scene(root,450,450));
            PantallaInicial controllerPantallaInicial= loader.getController();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void abrirMenuTurnoJugadores(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuTurnoJugador.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Turno Jugadores");
            stage.setScene(new Scene(root,450,450));
            MenuTurnoJugador controllerMenuTurnoJugador = loader.getController();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
