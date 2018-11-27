import Clases.Carta;
import Clases.Deck;
import Clases.Perfil;
import Clases.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class NuevoPerfil {
    @FXML
    private TextField userTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField emailTextField;


    public void regresarInicioDeSesion(ActionEvent event){
        ((Node) event.getSource()).getScene().getWindow().hide();
        try{

            ((Node)event.getSource()).getScene().getWindow().hide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void crearNuevoUsuario (ActionEvent event){
        String user = userTextField.getText();
        String pass = passwordTextField.getText();
        String email = emailTextField.getText();

        ArrayList<String> elementos = new ArrayList();
        elementos.add("Fuego");
        elementos.add("Agua");
        elementos.add("Nieve");

        ArrayList<String> colores = new ArrayList();
        colores.add("Rojo");
        colores.add("Amarillo");
        colores.add("Azul");
        colores.add("Verde");
        colores.add("Morado");
        colores.add("Naranja");
        ArrayList<Carta> lista = new ArrayList();
        for (int i = 0;i<5; i++){
            int el = (int) (Math.random() * elementos.size());
            int col = (int) (Math.random() * colores.size());
            int num = (int) (Math.random() * 10) + 1;
            lista.add(new Carta(num,colores.get(col),elementos.get(el)));
        }
        Deck deck = new Deck(lista);

        Usuario j = new Usuario(user,pass,email,deck,new Perfil());
        Main.agregarJugador(j);
        try{
            String jsonDeck= deck.deckSerializer();
            String jsonPerfil= j.perfilSerializer();
            Connection c = null;
            Statement stmt = null;
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String query = "INSERT INTO USUARIOS (USUARIO, CONTRASENA, EMAIL, DECK, PERFIL) VALUES ('"+user+"','"+pass+"','"+email+"','"+jsonDeck+"','"+jsonPerfil+"')";
            stmt.executeUpdate(query);
            c.commit();
            stmt.close();

            c.close();
            ((Node) event.getSource()).getScene().getWindow().hide();
        }catch(Exception e0){
            e0.printStackTrace();
        }

    }
}
