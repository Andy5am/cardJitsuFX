package Clases;
import java.util.ArrayList;
public class CartaEspecial extends Carta {
    private int numero;
    private String color;
    private String elemento;

    public CartaEspecial(int num, String color, String elemento){
        this.numero= num;
        this. color=color;
        this.elemento=elemento;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    @Override
    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getElemento() {
        return elemento;
    }

    @Override
    public void setElemento(String elemento) {
        this.elemento = elemento;
    }
}
