import Clases.Carta;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Batalla {
    @FXML
    private Label j1;
    @FXML
    private Label j2;
    @FXML
    private Label ganador;

    public void continuarActionHandler (Event e){
        ((Node) e.getSource()).getScene().getWindow().hide();
        ArrayList <Carta> c = new ArrayList();
        try {
            Main.setCartasEnJuego(c);
            Main.getModoDeJuego().pasoRonda();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try{
            String p ="";
            if (Main.getModoDeJuego().juez(Main.getJugador(0),Main.getJugador(1))==true){
                p  = "Ha ganado "+Main.getJugador(1).getUsuario();
                Main.getJugador(1).haGanado();
                Main.getJugador(0).haPerdido();
                JOptionPane.showMessageDialog(null, p);
                String p0 = "";
                if (Main.getJugador(0).hayRecompensa()!=0){
                    int a = Main.getJugador(0).hayRecompensa();
                    if (a == 1){
                        p0 = Main.getJugador(0).getUsuario()+" ha gando una carta!";
                        JOptionPane.showMessageDialog(null, p0);
                    }else if (a==2){
                        p0 = Main.getJugador(0).getUsuario()+" ha ascendido de cinturón!";
                        JOptionPane.showMessageDialog(null, p0);
                    }
                }


                if (Main.getJugador(1).hayRecompensa()!=0){
                    int a = Main.getJugador(1).hayRecompensa();
                    if (a == 1){
                        p0 = Main.getJugador(1).getUsuario()+" ha gando una carta!";
                        JOptionPane.showMessageDialog(null, p0);
                    }else if (a==2){
                        p0 = Main.getJugador(1).getUsuario()+" ha ascendido de cinturón!";
                        JOptionPane.showMessageDialog(null, p0);
                    }
                }
                // TODO Aqui se hace un update de datos en la BD
                String queryJ1 = "UPDATE USUARIOS SET DECK = '"+Main.getJugador(0).getDeck().deckSerializer()+"', PERFIL = '"+Main.getJugador(0).perfilSerializer()+"' WHERE USUARIO = '"+Main.getJugador(0).getUsuario()+"'";
                String queryJ2 = "UPDATE USUARIOS SET DECK = '"+Main.getJugador(1).getDeck().deckSerializer()+"', PERFIL = '"+Main.getJugador(1).perfilSerializer()+"' WHERE USUARIO = '"+Main.getJugador(1).getUsuario()+"'";
                Connection conn = null;
                Statement stmt = null;
                try{
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
                    conn.setAutoCommit(false);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(queryJ1);
                    stmt.executeUpdate(queryJ2);
                    conn.commit();
                    conn.close();
                    stmt.close();
                }catch(Exception e0){
                    e0.printStackTrace();
                }

                System.exit(0);
            }else{
                p = "Ha ganado "+Main.getJugador(0).getUsuario();
                Main.getJugador(0).haGanado();
                Main.getJugador(1).haPerdido();
                JOptionPane.showMessageDialog(null, p);
                String p0 = "";
                if (Main.getJugador(0).hayRecompensa()!=0){
                    int a = Main.getJugador(0).hayRecompensa();
                    if (a == 1){
                        p0 = Main.getJugador(0).getUsuario()+" ha gando una carta!";
                        JOptionPane.showMessageDialog(null, p0);
                    }else if (a==2){
                        p0 = Main.getJugador(0).getUsuario()+" ha ascendido de cinturón!";
                        JOptionPane.showMessageDialog(null, p0);
                    }
                }


                if (Main.getJugador(1).hayRecompensa()!=0){
                    int a = Main.getJugador(1).hayRecompensa();
                    if (a == 1){
                        p0 = Main.getJugador(1).getUsuario()+" ha gando una carta!";
                        JOptionPane.showMessageDialog(null, p0);
                    }else if (a==2){
                        p0 = Main.getJugador(1).getUsuario()+" ha ascendido de cinturón!";
                        JOptionPane.showMessageDialog(null, p0);
                    }
                }
                // TODO Aqui se hace un update de datos en la BD
                String queryJ1 = "UPDATE USUARIOS SET DECK = '"+Main.getJugador(0).getDeck().deckSerializer()+"', PERFIL = '"+Main.getJugador(0).perfilSerializer()+"' WHERE USUARIO = '"+Main.getJugador(0).getUsuario()+"'";
                String queryJ2 = "UPDATE USUARIOS SET DECK = '"+Main.getJugador(1).getDeck().deckSerializer()+"', PERFIL = '"+Main.getJugador(1).perfilSerializer()+"' WHERE USUARIO = '"+Main.getJugador(1).getUsuario()+"'";
                Connection conn = null;
                Statement stmt = null;
                try{
                    Class.forName("org.postgresql.Driver");
                    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
                    conn.setAutoCommit(false);
                    stmt = conn.createStatement();
                    stmt.executeUpdate(queryJ1);
                    stmt.executeUpdate(queryJ2);
                    conn.commit();
                    conn.close();
                    stmt.close();
                }catch(Exception e0){
                    e0.printStackTrace();
                }
                System.exit(0);
            }
        }catch(Exception e0){
            String modo = Main.getModoDeJuego().getNombre();
            if (modo == "modo2"){
                if(Main.getModoDeJuego().getContador() == 5){
                    JOptionPane.showMessageDialog(null, "Hay un empate!");
                    System.exit(0);
                }
            }
        }
    }
    public void initialize(){
        j1.setText(Main.getCartaEnJuego(0).toString());
        j2.setText(Main.getCartaEnJuego(1).toString());
        String g = "";
        try {
            Boolean ganador = Main.getModoDeJuego().encuentro(Main.getCartaEnJuego(0), Main.getCartaEnJuego(1));
            if (ganador == true) {
                g = "El ganador es: "+Main.getJugador(0).getUsuario();
                Main.getJugador(0).add(Main.getCartaEnJuego(0));
                //Main.getJugador(0).agregarCartaGanada(Main.getCartaEnJuego(0));
            }else{
                g= "El ganador es: "+Main.getJugador(1).getUsuario();
                Main.getJugador(1).add(Main.getCartaEnJuego(1));
                //Main.getJugador(1).agregarCartaGanada(Main.getCartaEnJuego(1));
            }
        }catch(Exception e){
            g = "Hay un empate";
        }
        ganador.setText(g);
    }
}
