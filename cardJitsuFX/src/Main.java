import Clases.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main extends Application {

    private static ArrayList<Usuario> jugadores = new ArrayList();
    private static int contador = 1;
    private static Arena arena;
    private static ModoDeJuego modo;
    private static ArrayList<Carta> cartasEnJuego = new ArrayList();

    public static void aumentarContador(){
        contador++;
    }
    public static int getContador(){
        return contador;
    }
    public static void setContador(int i){
        contador = i;
    }
    public static void setCartasEnJuego(ArrayList<Carta> c){
        cartasEnJuego = c;
    }
    public static void agregarCartaEnJueo(Carta c){
        cartasEnJuego.add(c);
    }
    public static Carta getCartaEnJuego(int a){
        return cartasEnJuego.get(a);
    }
    public static ArrayList<Carta> getCartasEnJuego () {
        return cartasEnJuego;
    }
    public static ModoDeJuego getModoDeJuego(){
        return modo;
    }
    public static ArrayList<Usuario> getJugadores(){
        return jugadores;
    }
    public static Arena getArena(){
        return arena;
    }
    public static void setArena (Arena a){
        arena = a;
    }
    public static void setModoDeJuego( ModoDeJuego m){
        modo = m;
    }
    public static void agregarJugador(Usuario j){
        jugadores.add(j);
    }
    public static Usuario getJugador (int i){
        return jugadores.get(i);
    }
    public static int getNumJugadores(){
        return jugadores.size();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("InicioDeSesion.fxml"));
        primaryStage.setTitle("Inicio de Sesion");
        //primaryStage.
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
        /**String json = "{'nombre':'Marco', 'apellido':'Fuentes', 'edad': 19 }";
         Gson gson = new Gson();
         Algo empleado = gson.fromJson(json, Algo.class);
         System.out.println(empleado.toString());**/
        //Primero se hace la conexion con la base de datos para que se puedan obtener los datos.
        Connection c = null;
        Statement stmt = null;

        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
            c.setAutoCommit(false);
            ResultSet rs = null;
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            //String query = "SELECT USUARIO, CONTRASENA FROM USUARIOS WHERE "
            //Se define una lista que contiene todos los jugadores de la base de datos
            //Y otra que unicamente contiene los jugadres que jugaran esta partida
            //ArrayList<Usuario> usuarios = new ArrayList();

            //Estos usuarios sirvIERONen de prueba.
            //usuarios.add(new Usuario("Marco","Cinco","hola@gmail.com"));
            //usuarios.add(new Usuario("Chus","Seis","adios@gmail.com"));
            Scanner input = new Scanner(System.in);
            Scanner input1= new Scanner(System.in);
            Scanner input2 = new Scanner(System.in);
            for (int a = 0;a <2;a++){
                //Se hace el inicio de sesion para ambos usuarios, verificando usuarios y contraseñas
                Boolean datos = false;
                while (datos==false){
                    System.out.println("Ingrese su usuario "+(a+1)+":");
                    String usuario = input.nextLine();
                    System.out.println("Intrese su contraseña "+(a+1)+": ");
                    String contrasena = input1.nextLine();
                    String query = "SELECT USUARIO, CONTRASENA, EMAIL, DECK, PERFIL FROM USUARIOS WHERE Usuario = '"+usuario+"' AND  Contrasena = '"+contrasena+"'";
                    try{
                        rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            datos = true;
                            String user = "";
                            String password = "";
                            String email = "";
                            String deck = "";
                            String perfil = "";
                            Perfil temporal;
                            Deck deckt;
                            Gson gson = new Gson();
                            //Este ciclo solo se implementara una vez. Puesto que el resultSet solo contiene un Elemento
                            try {
                                user = rs.getString("usuario");
                                password = rs.getString("contrasena");
                                email = rs.getString("email");
                                deck = rs.getString("deck");
                                String perfil0 = rs.getString("perfil");
                                perfil = perfil0.replace("\"", "'");
                                Type tipoListaCartas = new TypeToken<ArrayList<Carta>>() {
                                }.getType();
                                ArrayList<Carta> temp = gson.fromJson(deck, tipoListaCartas);
                                deckt = new Deck(temp);
                                temporal = gson.fromJson(perfil, Perfil.class);
                                jugadores.add(new Usuario(user, password, email, deckt, temporal));
                            } catch (Exception e) {
                                //System.out.println("JAJA SALU2");
                                e.printStackTrace();
                            }
                        }else{
                            System.out.println("Hay datos incorrectos");
                        }

                    }catch(Exception e){
                        System.out.println("No se pudo ejecutar la instruccion del query");
                        e.printStackTrace();
                    }
                }

            }

            System.out.println(jugadores.size());
            for(int i=0;i<jugadores.size();i++){
                System.out.println(jugadores.get(i).getUsuario()+"\n"+jugadores.get(i).getPerfil().toString()+"\n");
            }
            //TODO AQUI SE CIERRA LA CONEXION A BD
            rs.close();
            stmt.close();
            c.close();
            if (jugadores.size()!=2){
                System.out.println("Hay datos incorrectos!");


            }else{
                System.out.println("Bienvenidos ambos");
                //               PRUEBAS DE LOS SERIALIZERS DE LOS OBJETOS
                /**System.out.println("JSON de deck de J1: "+jugadores.get(0).getDeck().deckSerializer());
                System.out.println("JSON de perfil de J1: "+jugadores.get(0).perfilSerializer());
                System.out.println("JSON de deck de J2; "+jugadores.get(1).getDeck().deckSerializer());
                System.out.println("JSON de perfil de J2: "+jugadores.get(1).perfilSerializer());**/

                Scanner sc = new Scanner (System.in);
                Boolean hayModo = true;
                String modoDeJuego = "";
                while (hayModo){
                    System.out.println("¿Qué modo de juego quieren jugar? 1/2");
                    String m = sc.nextLine().toString();

                    if (m.equals("1")){
                        modoDeJuego = "modo1";
                        hayModo = false;
                    }else if (m.equals("2")){
                        modoDeJuego = "modo2";
                        hayModo = false;
                    }else{
                        System.out.println("Ese valor es incorrecto...");
                    }
                }

                //Aqui comienza la lógica del juego
                /*modo = new ModoDeJuego(modoDeJuego);
                arena = new Arena(jugadores, modo);*/

                Boolean sigue = true;
                while (sigue){
                    ArrayList<Carta> cartasEnJuego = new ArrayList<>();
                    for (int a  = 0; a<2;a++){
                        System.out.println("Jugador "+(a+1));
                        System.out.println(jugadores.get(a).toString());
                        System.out.print("Ingrese el numero de su elección de carta: ");
                        try{
                            int respuesta = input2.nextInt()-1;
                            cartasEnJuego.add(jugadores.get(a).getDeck().getCartasVisibles().get(respuesta));
                            jugadores.get(a).getDeck().usarCarta(cartasEnJuego.get(a));
                        }catch(Exception e){
                            System.out.println("Ese no es u dato valido...");
                        }
                    }
                    Boolean juez = arena.getModoDeJuego().encuentro(cartasEnJuego.get(0),cartasEnJuego.get(1));
                    //TODO el metodo encuentro debe considerar el elemento de las cartas - DONE
                    System.out.println("El ganador es: "+ juez);
                    //En esta parte se agregan las cartas ganadas por los jugadores a sus respectivas matrices con el método add()
                    try{
                        if (juez){
                            System.out.println("Carta 1: "+cartasEnJuego.get(0).getNumero());
                            System.out.println("Carta 2: "+cartasEnJuego.get(1).getNumero());
                            jugadores.get(0).cartasGanadasPartida.add(cartasEnJuego.get(0));
                            jugadores.get(0).add(cartasEnJuego.get(0));
                        }else{
                            jugadores.get(1).cartasGanadasPartida.add(cartasEnJuego.get(1));
                            jugadores.get(1).add(cartasEnJuego.get(1));
                        }
                    }catch(Exception e0) {
                        System.out.println("Empate");
                    }

                    //Aqui se verifica si ya hay un ganador
                    if (arena.getModoDeJuego().juez(jugadores.get(0),jugadores.get(1)) == null){
                        sigue = true;
                    }else {
                        sigue = false;
                        if (arena.getModoDeJuego().juez(jugadores.get(0),jugadores.get(1)) == true){
                            System.out.println("Jugador 1 ha ganado");
                            jugadores.get(0).haGanado();
                            jugadores.get(1).haPerdido();
                        }else{
                            System.out.println("Jugador 2 ha ganado");
                            jugadores.get(0).haPerdido();
                            jugadores.get(1).haGanado();
                        }
                        int j1 = jugadores.get(0).hayRecompensa();
                        int j2 = jugadores.get(1).hayRecompensa();

                        if(j1==1){
                            System.out.println("El jugador 1 ha ganado un nuevo cinturon!");
                        }else if (j1 == 2){
                            System.out.println("El jugador 1 ha ganado una carta!");
                        }
                        if (j2==1){
                            System.out.println("El jugador 2 ha ganado un nuevo cinturon!");
                        }else if (j2== 2){
                            System.out.println("El jugador 2 ha ganado una carta!");
                        }
                        // TODO Aqui se hace un update de datos en la BD
                        String queryJ1 = "UPDATE USUARIOS SET DECK = '"+jugadores.get(0).getDeck().deckSerializer()+"', PERFIL = '"+jugadores.get(0).perfilSerializer()+"' WHERE USUARIO = '"+jugadores.get(0).getUsuario()+"'";
                        String queryJ2 = "UPDATE USUARIOS SET DECK = '"+jugadores.get(1).getDeck().deckSerializer()+"', PERFIL = '"+jugadores.get(1).perfilSerializer()+"' WHERE USUARIO = '"+jugadores.get(1).getUsuario()+"'";
                        Connection conn = null;
                        Statement stmc = null;
                        try{
                            Class.forName("org.postgresql.Driver");
                            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","");
                            c.setAutoCommit(false);
                            stmt = c.createStatement();
                            stmt.executeUpdate(queryJ1);
                            stmt.executeUpdate(queryJ2);
                            conn.commit();
                            conn.close();
                            stmt.close();
                        }catch(Exception e0){
                            e0.printStackTrace();
                        }

                    }
                    modo.pasoRonda();
                }
            }
        }catch (Exception e){
            System.out.println("No se pudo conectar con la BD");
            e.printStackTrace();
        }
    }
}
