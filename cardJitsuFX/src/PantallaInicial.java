import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PantallaInicial {

    public void abrirMenuModosDeJuego(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuModosDeJuego.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Modos de juego");
            stage.setScene(new Scene(root,600,450));
            MenuModosDeJuego controllerMenuModosDeJuego = loader.getController();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void abrirMenuPerfil(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuPerfil.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Informacion perfiles");
            stage.setScene(new Scene(root,450,450));
            MenuPerfil controllerMenuPerfil = loader.getController();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
