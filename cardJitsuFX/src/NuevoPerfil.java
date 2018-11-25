import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NuevoPerfil {

    public void regresarInicioDeSesion(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InicioDeSesion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Inicio de Sesion");
            stage.setScene(new Scene(root,450,450));
            InicioDeSesion controllerInicioDeSesion = loader.getController();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
