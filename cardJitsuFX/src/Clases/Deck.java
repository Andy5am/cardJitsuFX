package Clases;
import java.util.ArrayList;
import com.google.gson.*;
public class Deck {
    private ArrayList<Carta> cartas;
    private ArrayList<Carta> cartasVisibles;
    //Este constructor es solo de prueba
    public Deck (){
        this.cartas = new ArrayList<>();
        cartas.add(new Carta(5,"Amarillo","Agua"));
        cartas.add(new Carta(6,"Verde","Fuego"));
        cartas.add(new Carta(3,"Azul","Nieve"));
        cartas.add(new Carta(9,"Azul", "Fuego"));
        cartas.add(new Carta(7,"Rojo","Agua"));
        cartasVisibles=cartas;
    }

    public Deck(ArrayList<Carta> c){
        this.cartas = c;
        //System.out.println(cartas.size());
        this.cartasVisibles = cartas;
        /**for (int i = 1; (i< cartas.size()) && (i<5); i++){
         this.cartasVisibles.add(cartas.get(i));
         }**/
    }
    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    public void setCartas(ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }
    public ArrayList<Carta> getCartasVisibles() {
        return cartasVisibles;
    }
    public void agregarCarta(Carta c){
        cartas.add(c);
    }

    public void usarCarta(Carta c){
        for (int i = 0; i <cartasVisibles.size();i++){
            if (cartasVisibles.get(i).getNumero()==(c.getNumero())){
                if (cartasVisibles.get(i).getColor().equals(c.getColor())){
                    if(cartasVisibles.get(i).getElemento().equals(c.getElemento())){
                        //Cuando se usa una carta, esta sale de las cartas visibles y otra la suplanta
                        cartasVisibles.remove(i);
                        int numAleatorio = (int) (Math.random() * (cartas.size()-1)) ;
                        //int numAleatorio=(int)Math.floor(Math.random()*((-1)-(cartas.size()+1))+(cartas.size()));
                        cartasVisibles.add(cartas.get(numAleatorio));
                    }
                }
            }
        }
    }
    public void setCartasVisibles(ArrayList<Carta> cartasVisibles) {
        this.cartasVisibles = cartasVisibles;
    }

    public String deckSerializer(){
        String json = "";
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        json = gson.toJson(this.cartas);
        return json;
    }

}
