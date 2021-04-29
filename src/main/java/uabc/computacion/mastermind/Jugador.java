package uabc.computacion.mastermind;

import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Implementación del jugador y sus acciones
 * 
 * @author Jonathan Herrera
 * @version 29/04/2021
 */
public class Jugador 
{
    // Registro (cronológico) de entradas conformadas por combinación y aciertos
    private LinkedHashMap<Combinacion, String> registroCombinaciones;
    // Historial (no cronológico) de las combinaciones dadas
    private HashSet<Combinacion> historialCombinaciones;
    
    /**
     * Constructor de la clase
     */
    public Jugador()
    {
        registroCombinaciones = new LinkedHashMap<>();
        historialCombinaciones = new HashSet<>();
    }
    
    /**
     * Ingreso de una combinación
     * @param longitudCombinacion: cantidad de colores a ingresar
     * @return un ArrayList de canicas
     */
    public ArrayList<Canica> pedirCombinacion(int longitudCombinacion)
    {
        Scanner sc = new Scanner(System.in);
        String entrada, colores[];
        boolean colorInvalido = false, cantidadInvalida;
        ArrayList<Canica> canicas = new ArrayList<>();
        
        do
        {
            do
            {
                System.out.println("Ingrese una combinación de " + longitudCombinacion + " colores:");
                entrada = sc.nextLine();
                colores = entrada.split(" ");
                cantidadInvalida = colores.length != longitudCombinacion;
                
                if(cantidadInvalida)
                {
                    System.out.println("No se permiten colores vacíos");
                }
                
            } while(cantidadInvalida);
            
            for(String color : colores)
            {
                colorInvalido = !Canica.verificarColor(color);
                
                if(colorInvalido)
                {
                    System.out.println("Alguno de los colores ingresados no es válido");
                    break;
                }
            }
            
        } while(colorInvalido);
        
        for(String color : colores)
        {
            canicas.add(new Canica(color, canicas.size() + 1));
        }
        
        return canicas;
    }
    
    /**
     * Intenta añadir una combinación al historial de combinaciones
     * @param combinacion: dada por el jugador
     * @return si la combinación se pudo añadir, si no, significa que está repetida
     */
    public boolean verificarCombinacionRepetida(Combinacion combinacion)
    {
        return historialCombinaciones.add(combinacion);
    }
    
    /**
     * Crea una entrada para un par de objetos conformado por combinación y aciertos
     * @param combinacionGanadora: la combinación a descifrar
     * @param combinacionJugador: la combinación dada por el jugador
     */
    public void registrarCombinacion(Combinacion combinacionGanadora, Combinacion combinacionJugador)
    {
        registroCombinaciones.put(combinacionJugador, 
                Combinacion.mostrarCorrectas(combinacionGanadora, combinacionJugador));
    }
    
    /**
     * Imprime por consola las combinaciones anteriores dadas
     */
    public void mostrarHistorial()
    {
        System.out.println("----------------------------------------");
        for(Combinacion combinacion : registroCombinaciones.keySet())
        {
            System.out.println(combinacion.toString() + " " + registroCombinaciones.get(combinacion));
        }
    }
}
