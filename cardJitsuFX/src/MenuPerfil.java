import Clases.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MenuPerfil {
    @FXML
    private Label LabelPerfil1;
    @FXML
    private Label LabelPerfil2;
    @FXML
    private Label LabelCinturon1;
    @FXML
    private Label LabelCinturon2;
    @FXML
    private Label LabelVictorias1;
    @FXML
    private Label LabelVictorias2;
    @FXML
    private Label LabelDerrotas1;
    @FXML
    private Label LabelDerrotas2;
    @FXML
    private Label LabelXp1;
    @FXML
    private Label LabelXp2;

    public void initialize(){
        Usuario usuario1 = Main.getJugador(0);
        Usuario usuario2 = Main.getJugador(1);
        LabelPerfil1.setText(usuario1.getUsuario());
        LabelPerfil2.setText(usuario2.getUsuario());
        LabelCinturon1.setText(usuario1.getCinturon());
        LabelCinturon2.setText(usuario2.getCinturon());
        LabelVictorias1.setText(usuario1.getVictorias());
        LabelVictorias2.setText(usuario2.getVictorias());
        LabelDerrotas1.setText(usuario1.getDerrotas());
        LabelDerrotas2.setText(usuario2.getDerrotas());
        LabelXp1.setText(usuario1.getXp());
        LabelXp2.setText(usuario2.getXp());
    }
    public void regresarPantallaInicial(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Pantalla inicial");
            stage.setScene(new Scene(root, 450 ,450));
            PantallaInicial controllerPantallaInicial = loader.getController();
            ((Node)event.getSource()).getScene().getWindow().hide();
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
