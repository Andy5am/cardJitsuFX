import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MenuTurnoJugador {

    //conexion con grafico
    @FXML
    private Label turnoLabel;
    @FXML
    private Label cartasJ1;
    @FXML
    private Label cartasJ2;

    public void regresarMenuModosDeJuego(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuModosDeJuego.fxml"));
            Parent root =loader.load();
            Stage stage = new Stage();
            stage.setTitle("Menu Modos de Juego");
            stage.setScene(new Scene(root,450,450));
            MenuModosDeJuego controllerMenuModosDeJuego = loader.getController();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
