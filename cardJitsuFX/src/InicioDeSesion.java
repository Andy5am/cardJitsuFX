import Clases.Carta;
import Clases.Deck;
import Clases.Perfil;
import Clases.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

public class InicioDeSesion {

    //conexion con grafico
    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label usuario;

    public void abrirPantallaInicial(ActionEvent event){
        Parent root;
        try {
            try{
                Connection c = null;
                Statement stmt = null;
                Class.forName("org.postgresql.Driver");
                c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
                c.setAutoCommit(false);
                ResultSet rs = null;
                //System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String usuario = userField.getText();
                String contrasena = passwordField.getText();
                String query = "SELECT USUARIO, CONTRASENA, EMAIL, DECK, PERFIL FROM USUARIOS WHERE Usuario = '"+usuario+"' AND  Contrasena = '"+contrasena+"'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    String email = "";
                    String deck = "";
                    String perfil = "";
                    Perfil temporal;
                    Deck deckt;
                    Gson gson = new Gson();
                    /**user = rs.getString("usuario");
                    password = rs.getString("contrasena");**/
                    email = rs.getString("email");
                    deck = rs.getString("deck");
                    String perfil0 = rs.getString("perfil");
                    perfil = perfil0.replace("\"", "'");
                    Type tipoListaCartas = new TypeToken<ArrayList<Carta>>() {
                    }.getType();
                    ArrayList<Carta> temp = gson.fromJson(deck, tipoListaCartas);
                    deckt = new Deck(temp);
                    temporal = gson.fromJson(perfil, Perfil.class);
                    Usuario p = new  Usuario(usuario,contrasena,email,deckt,temporal);
                    Main.agregarJugador(p);
                    JOptionPane.showMessageDialog(null, "Bienvenido "+Main.getJugador(Main.getNumJugadores()-1).getUsuario());
                    userField.setText("");
                    passwordField.setText("");
                    /**if (Main.getNumJugadores() == 1){
                        if (Main.getJugador(0).equals(p)){
                            JOptionPane.showMessageDialog(null, "Hay datos repetidos");
                            userField.setText("");
                            passwordField.setText("");
                        }else{
                            Main.agregarJugador(p);
                            JOptionPane.showMessageDialog(null, "Bienvenido "+Main.getJugador(Main.getNumJugadores()-1).getUsuario());
                        }
                    }**/
                    this.usuario.setText("Usuario: "+Main.getNumJugadores()+1);
                }else{
                    JOptionPane.showMessageDialog(null, "Hay datos incorrectos");
                    userField.setText("");
                    passwordField.setText("");
                }


            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos. \nInténtalo de nuevo mas tarde");
                e.printStackTrace();
            }
            if (Main.getNumJugadores() == 2){
                //Carga ventana
                FXMLLoader loader = new FXMLLoader(getClass().getResource("PantallaInicial.fxml"));
                root =loader.load();
                Stage stage = new Stage();
                stage.setTitle("Pantalla inicial");
                stage.setScene(new Scene(root,450,450));
                //Conexion con controller de nueva lista
                PantallaInicial controllerPantallaInicial = loader.getController();

                stage.show();
            }else{
            }
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
