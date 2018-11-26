package Clases;

public class Cinturon {
    private int color;
    public Cinturon(int c){
        this.color = c;
    }

    public int getColor() {
        return color;
    }

    public void asenso() {
        color = color + 1;
    }

    public String toString(){
        String r = "";
        switch(color) {
            case 1:
                r = "blanco";
                break;
            case 2:
                r = "Amarillo";
                break;
            case 3:
                r = "Naranja";
                break;
            case 4:
                r = "Verde";
                break;
            case 5:
                r = "Azul";
                break;
            case 6:
                r = "Rojo";
                break;
            case 7:
                r = "Morado";
                break;
            case 9:
                r = "Marron";
                break;
            case 10:
                r = "Negro";
                break;
            case 11:
                r = "Ninja";
                break;
        }
        return r ;
    }
}
