package Clases;
import java.util.ArrayList;
public class ModoDeJuego {
    private String nombre;
    private int contador = 0;
    public ModoDeJuego(String m){
        this.nombre = m;
    }
    public String getNombre() {
        return nombre;
    }
    public void pasoRonda(){
        contador = contador + 1;
    }
    public int getContador (){
        return contador;
    }

    //El metodo encuentro determinará el ganador. Si retorna TRUE, gana el jugador 1, de lo contrario, gana jugador 2. Retorna null si es empate
    public Boolean encuentro (Carta j1, Carta j2){
        Boolean resultado = null;

        if(j1.getElemento().equals("Agua")){
            if (j2.getElemento().equals("Hielo")){
                resultado = false;
            }else if(j2.getElemento().equals("Fuego")){
                resultado = true;
            }
        }else if(j1.getElemento().equals("Hielo")){
            if (j2.getElemento().equals("Fuego")){
                resultado = false;
            }else if (j2.getElemento().equals("Agua")){
                resultado = true;
            }
        }else if(j1.getElemento().equals("Fuego")){
            if (j2.getElemento().equals("Agua")){
                resultado = false;
            }else if (j2.getElemento().equals("Hielo")){
                resultado = true;
            }
        }else if (j1.getElemento().equals(j2.getElemento())){
            //resultado = null;
            if (j1.getNumero()> j2.getNumero()){
                resultado = true;
            }else if (j1.getNumero()< j2.getNumero()){
                resultado = false;
            }else{
                resultado = null;
            }
        }
        return resultado;
    }

    // El metodo sigue evalua si cada jugador ha alcanzado el maximo para ganar la partida en MODO1
    public Boolean sigue (int [][] matriz){
        boolean eureka = false;
        //Primero sumas en horizontales
        for (int i = 0; i < 3 ; i++){
            int suma = 0;
            for(int j = 0; j < 6 ; j++){
                suma = suma + matriz[i][j];
            }
            if (suma == 3){
                eureka = true;
            }
            System.out.println("La suma en la fila "+i+"es: "+suma);
        }

        if (eureka == false) {
            for(int i = 0;i<6;i++){
                if (matriz [0][i]==1){
                    for (int j = 0;j<i;j++){
                        if (matriz[1][j]==1){
                            for (int l = 0; l < j ; l ++){
                                if(matriz[2][l]==1){
                                    eureka = true;
                                }
                            }
                            for (int n = j+1; n<i;n++){
                                if(matriz[2][n]==1){
                                    eureka = true;
                                }
                            }
                            for (int t = i+1; t<6;t++){
                                if(matriz[2][t]==1){
                                    eureka = true;
                                }
                            }
                        }
                    }
                    for (int k = i+1; k<6;k++){
                        if (matriz[1][k]==1){
                            for (int m = 0; m < i; m ++){
                                if (matriz[2][m]==1){
                                    eureka = true;
                                }
                            }
                            for(int o = i + 1; o<k;o++){
                                if (matriz[2][o]==1){
                                    eureka = true;
                                }
                            }
                            for(int y = k + 1; y<6;y++){
                                if (matriz[2][y]==1){
                                    eureka = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return eureka;
    }

    //Este metodo determina si hay ganador (y quien es) dependiendo de modo de juego. True si j1 ha ganado, false si j2 ha ganado, null si nadie ha ganado
    public Boolean juez(Usuario j1, Usuario j2){
        Boolean resultado = null;
        if (this.nombre.equals("modo1")){
            if (sigue(j1.matriz)){
                resultado = true;
            }
            else if (sigue(j2.matriz)){
                resultado = false;
            }
        }else if (this.nombre.equals("modo2")){

            if (contador==5){
                int suma1 = j1.sumaFilas();
                int suma2 = j2.sumaFilas();
                if (suma1 < suma2){
                    resultado = true;
                }else if (suma2 < suma1){
                    resultado = false;
                }else if (suma1 == suma2){
                    resultado = null;
                }
            }else{
                resultado = null;
            }
        }
        return resultado;
    }

    /*
        public boolean sigue (ArrayList<Carta> lista){
            Boolean seguir = true;
            if (lista.size()>=3){
                int rojo = 0;
                int azul = 0;
                int amarillo = 0;
                int morado = 0;
                int naranja = 0;
                int verde = 0;
                int agua = 0;
                int fuego = 0;
                int nieve = 0;
                for (int i = 0;i < lista.size();i++){
                    switch (lista.get(i).getElemento()){
                        case "Agua":
                            agua = agua + 1;
                            break;
                        case "Fuego":
                            fuego = fuego + 1;
                            break;
                        case "Nieve":
                            nieve = nieve + 1;
                    }
                    switch(lista.get(i).getColor()){
                        case "Amarillo":
                            amarillo = amarillo + 1;
                            break;
                        case "Rojo":
                            rojo = rojo + 1;
                            break;
                        case "Azul":
                            azul = azul + 1;
                            break;
                        case "Morado":
                            morado = morado + 1;
                            break;
                        case "Naranja":
                            naranja = naranja + 1;
                            break;
                        case "Verde":
                            verde = verde + 1;
                            break;
                    }
                }
            }else{
                seguir = true;
            }
            return seguir;
        }*/
    public String toString(Boolean a){
        String resultado = "";
        if (a == true){
            resultado = "Jugador 1";
        }else{
            resultado = "Jugador 2";
        }
        return resultado;
    }
}
