package Clases;
import java.util.ArrayList;

public class Arena {
    private ArrayList<Usuario> usuarios;
    private ModoDeJuego modoDeJuego;
    //TODO Hay que pensar como llevar la cuenta de las cartas que van ganando los jugadores
    //TODO es decir, como sabemos cuando alguien gane definitivamente la partida - DONE
    public Arena (ArrayList <Usuario> jugadores, ModoDeJuego m){
        this.usuarios = jugadores;
        this.modoDeJuego = m;
    }
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public void anadirUsuario(Usuario jugador){
        usuarios.add(jugador);
    }
    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    public ModoDeJuego getModoDeJuego() {
        return modoDeJuego;
    }
    public void cambiarModo(ModoDeJuego m){
        this.setModoDeJuego(m);
    }
    public void setModoDeJuego(ModoDeJuego modoDeJuego) {
        this.modoDeJuego = modoDeJuego;
    }
}
