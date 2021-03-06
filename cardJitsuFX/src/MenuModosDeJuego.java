import Clases.Arena;
import Clases.ModoDeJuego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuModosDeJuego {

    @FXML
    private Button regresar;

    public void regresarPantallaInicial(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pantalla inicial");
            stage.setScene(new Scene(root,500,450));
            PantallaInicial controllerPantallaInicial= loader.getController();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void modo1Button (ActionEvent event){
        Main.setModoDeJuego(new ModoDeJuego("modo1"));
        Main.setArena(new Arena(Main.getJugadores(),Main.getModoDeJuego()));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuTurnoJugador.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Turno Jugadores");
            stage.setScene(new Scene(root,600,450));
            MenuTurnoJugador controllerMenuTurnoJugador = loader.getController();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void modo2Button (ActionEvent event){
        Main.setModoDeJuego(new ModoDeJuego("modo2"));
        Main.setArena(new Arena(Main.getJugadores(),Main.getModoDeJuego()));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuTurnoJugador.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Turno Jugadores");
            stage.setScene(new Scene(root,600,450));
            MenuTurnoJugador controllerMenuTurnoJugador = loader.getController();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
