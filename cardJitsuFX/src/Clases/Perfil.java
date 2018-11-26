package Clases;

import java.util.ArrayList;
import com.google.gson.*;
public class Perfil {
    private int victorias;
    private int derrotas;
    private int xp;
    private ArrayList<Cinturon> cinturones;

    public Perfil (){
        this.victorias = 0;
        this.derrotas = 0;
        this.xp = 0;
        this.cinturones = new ArrayList();
    }


    public int getVictorias() {
        return victorias;
    }
    public void partidaGanada(){
        victorias = victorias + 1;
    }

    public int getDerrotas() {
        return derrotas;
    }
    public void partidaPerdida(){
        derrotas = derrotas + 1;
    }

    public int getXp() {
        return xp;
    }
    public void sumarXp(int a){
        xp = xp + a;
    }

    public Cinturon getCinturon() {
        return cinturones.get(cinturones.size()-1);
    }
    public void agregarCinturon(Cinturon c){
        cinturones.add(c);
    }
    public void agregarCinturon(){
        int a = cinturones.get(cinturones.size()-1).getColor();
        cinturones.add(new Cinturon(a+1));
    }

    public String toString(){
        String r = "Victorias: "+victorias+"\nDerrotas: "+derrotas+"\nXP: "+xp;
        return r;
    }

}
