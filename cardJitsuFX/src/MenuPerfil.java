import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MenuPerfil {

    public void regresarPantallaInicial(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pantalla inicial");
            stage.setScene(new Scene(root, 450 ,450));
            PantallaInicial controllerPantallaInicial = loader.getController();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
