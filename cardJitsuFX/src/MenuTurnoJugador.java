import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuTurnoJugador {
    //private int contador ;
    //conexion con grafico
    @FXML
    private Label turnoLabel;
    @FXML
    private Label cartasJ1;
    @FXML
    private Label cartasJ2;
    @FXML
    private Label cartaJ1A;
    @FXML
    private Label cartaJ1F;
    @FXML
    private Label cartaJ1N;
    @FXML
    private Label cartaJ2A;
    @FXML
    private Label cartaJ2F;
    @FXML
    private Label cartaJ2N;
    @FXML
    private Label carta1;
    @FXML
    private Label carta2;
    @FXML
    private Label carta3;
    @FXML
    private Label carta4;
    @FXML
    private Label carta5;
    @FXML
    private Label j1_nombre;
    @FXML
    private Label j2_nombre;

    @FXML
    private Button carta1Button;
    @FXML
    private Button carta2Button;
    @FXML
    private Button carta3Button;
    @FXML
    private Button carta4Button;
    @FXML
    private Button carta5Button;

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
    public void initialize(){
        refresh();
        j1_nombre.setText(Main.getJugador(0).getUsuario());
        j2_nombre.setText(Main.getJugador(1).getUsuario());
    }

    public void refresh(){
        int c = Main.getContador();
        String j = "";
        if (c%2==1){
            j = "Turno de "+Main.getJugador(0).getUsuario();
        }else{
            j = "Turno de "+Main.getJugador(1).getUsuario();
        }
        turnoLabel.setText(j);
        if (c % 2 == 1){
            carta1Button.setText(Main.getJugador(0).getDeck().getCartasVisibles().get(0).toString());
            carta2Button.setText(Main.getJugador(0).getDeck().getCartasVisibles().get(1).toString());
            carta3Button.setText(Main.getJugador(0).getDeck().getCartasVisibles().get(2).toString());
            carta4Button.setText(Main.getJugador(0).getDeck().getCartasVisibles().get(3).toString());
            carta5Button.setText(Main.getJugador(0).getDeck().getCartasVisibles().get(4).toString());
        }else{
            carta1Button.setText(Main.getJugador(1).getDeck().getCartasVisibles().get(0).toString());
            carta2Button.setText(Main.getJugador(1).getDeck().getCartasVisibles().get(1).toString());
            carta3Button.setText(Main.getJugador(1).getDeck().getCartasVisibles().get(2).toString());
            carta4Button.setText(Main.getJugador(1).getDeck().getCartasVisibles().get(3).toString());
            carta5Button.setText(Main.getJugador(1).getDeck().getCartasVisibles().get(4).toString());
        }
        String n1 = "";
        String f1 = "";
        String a1 = "";
        for (int i = 0; i < Main.getJugador(0).getCartasGanadasPartida().size();i++){
            if (Main.getJugador(0).getCartasGanadasPartida().get(i).getElemento().equals("Nieve")){
                n1 = n1 +Main.getJugador(0).getCartasGanadasPartida().get(i).getColor()+ "\n";
            }else if (Main.getJugador(0).getCartasGanadasPartida().get(i).getElemento().equals("Fuego")){
                f1 = f1 +Main.getJugador(0).getCartasGanadasPartida().get(i).getColor()+ "\n";
            }else if (Main.getJugador(0).getCartasGanadasPartida().get(i).getElemento().equals("Agua")){
                a1 = a1 +Main.getJugador(0).getCartasGanadasPartida().get(i).getColor()+ "\n";
            }
        }
        String n2 = "";
        String a2 = "";
        String f2 = "";
        for (int i = 0; i < Main.getJugador(1).getCartasGanadasPartida().size();i++){
            if (Main.getJugador(1).getCartasGanadasPartida().get(i).getElemento().equals("Nieve")){
                n2 = n2 +Main.getJugador(1).getCartasGanadasPartida().get(i).getColor()+ "\n";
            }else if (Main.getJugador(1).getCartasGanadasPartida().get(i).getElemento().equals("Fuego")){
                f2 = f2 +Main.getJugador(1).getCartasGanadasPartida().get(i).getColor()+ "\n";
            }else if (Main.getJugador(1).getCartasGanadasPartida().get(i).getElemento().equals("Agua")){
                a2 = a2 +Main.getJugador(1).getCartasGanadasPartida().get(i).getColor()+ "\n";
            }
        }
        cartaJ1F.setText(f1);
        cartaJ1A.setText(a1);
        cartaJ1N.setText(n1);

        cartaJ2A.setText(a2);
        cartaJ2N.setText(n2);
        cartaJ2F.setText(f2);

    }

    public void carta1ButtonHandler(ActionEvent event){
        int c = Main.getContador();
        if (c%2 == 1){
            Main.agregarCartaEnJueo(Main.getJugador(0).getDeck().getCartasVisibles().get(0));
            Main.getJugador(0).getDeck().usarCarta(Main.getCartaEnJuego(0));
        }else{
            try{
                Main.agregarCartaEnJueo(Main.getJugador(1).getDeck().getCartasVisibles().get(0));
                Main.getJugador(1).getDeck().usarCarta(Main.getCartaEnJuego(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Batalla.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Batalla JAJA SALU2");
                stage.setScene(new Scene(root,450,450));
                Batalla batalla = loader.getController();
                stage.show();
            }catch(Exception e){e.printStackTrace();}
        }
        Main.aumentarContador();
        refresh();
    }
    public void carta2ButtonHandler(ActionEvent event){
        int c = Main.getContador();
        if (c%2 == 1){
            Main.agregarCartaEnJueo(Main.getJugador(0).getDeck().getCartasVisibles().get(1));
            Main.getJugador(0).getDeck().usarCarta(Main.getCartaEnJuego(0));
        }else{
            try{
                Main.agregarCartaEnJueo(Main.getJugador(1).getDeck().getCartasVisibles().get(1));
                Main.getJugador(1).getDeck().usarCarta(Main.getCartaEnJuego(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Batalla.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Batalla JAJA SALU2");
                stage.setScene(new Scene(root,450,450));
                Batalla batalla = loader.getController();
                stage.show();
            }catch(Exception e){e.printStackTrace();}
        }

        Main.aumentarContador();
        refresh();
    }

    public void carta3ButtonHandler(ActionEvent event){
        int c = Main.getContador();
        if (c%2 == 1){
            Main.agregarCartaEnJueo(Main.getJugador(0).getDeck().getCartasVisibles().get(2));
            Main.getJugador(0).getDeck().usarCarta(Main.getCartaEnJuego(0));
        }else{
            try{
                Main.agregarCartaEnJueo(Main.getJugador(1).getDeck().getCartasVisibles().get(2));
                Main.getJugador(1).getDeck().usarCarta(Main.getCartaEnJuego(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Batalla.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Batalla JAJA SALU2");
                stage.setScene(new Scene(root,450,450));
                Batalla batalla = loader.getController();
                stage.show();
            }catch(Exception e){e.printStackTrace();}
        }
        Main.aumentarContador();
        refresh();

    }
    public void carta4ButtonHandler(ActionEvent event){
        int c = Main.getContador();
        if (c%2 == 1){
            Main.agregarCartaEnJueo(Main.getJugador(0).getDeck().getCartasVisibles().get(3));
            Main.getJugador(0).getDeck().usarCarta(Main.getCartaEnJuego(0));
        }else{
            try{
                Main.agregarCartaEnJueo(Main.getJugador(1).getDeck().getCartasVisibles().get(3));
                Main.getJugador(1).getDeck().usarCarta(Main.getCartaEnJuego(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Batalla.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Batalla JAJA SALU2");
                stage.setScene(new Scene(root,450,450));
                Batalla batalla = loader.getController();
                stage.show();
            }catch(Exception e){e.printStackTrace();}
        }
        Main.aumentarContador();
        refresh();

    }
    public void carta5ButtonHandler(ActionEvent event){
        int c = Main.getContador();
        if (c%2 == 1){
            Main.agregarCartaEnJueo(Main.getJugador(0).getDeck().getCartasVisibles().get(4));
            Main.getJugador(0).getDeck().usarCarta(Main.getCartaEnJuego(0));
        }else{
            try{
                Main.agregarCartaEnJueo(Main.getJugador(1).getDeck().getCartasVisibles().get(4));
                Main.getJugador(1).getDeck().usarCarta(Main.getCartaEnJuego(1));
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Batalla.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Batalla JAJA SALU2");
                stage.setScene(new Scene(root,450,450));
                Batalla batalla = loader.getController();
                stage.show();
            }catch(Exception e){e.printStackTrace();}
        }
        Main.aumentarContador();
        refresh();

    }
}