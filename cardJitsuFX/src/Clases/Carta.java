package Clases;

public class Carta {
    private int numero;
    private String color;
    private String elemento;


    public Carta (){}
    public Carta(Integer num, String color, String elemento){
        this.numero=(int)(Math.random()*10+1);
        this. color=color;
        this.elemento=elemento;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getElemento() {
        return elemento;
    }
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
    public String toString(){
        String r = "Numero: "+numero+"\nElemento: "+elemento+"\nColor: "+color;
        return r ;
    }
}
