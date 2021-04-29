package uabc.computacion.mastermind;

import java.util.Scanner;

/**
 * Clase Main para el proyecto Mastermind
 * 
 * @author Jonathan Herrera
 * @version 29/04/2021
 */
public class Mastermind {
    public static void main(String[] args) 
    {
        // Instancias y variables necesarias para el juego
        Combinacion combinacionGanadora, combinacionJugador;
        Jugador jugador = new Jugador();
        Scanner sc = new Scanner(System.in);
        // Longitud del código a adivinar, debe ser un número positivo
        final int LONGITUD_COMBINACION = 4;
        boolean jugadorHaAdivinado = false,
                combinacionRepetida,
                oportunidadesInvalidas;
        String oportunidadesPropuestas;
        int oportunidades = 0, intento = 0, puntaje = 100;
        
        // Impresión de instrucciones
        System.out.println("\t\t\t*** Mastermind ***");
        System.out.println(". = color correcto en posición correcta");
        System.out.println("o = color correcto en posición incorrecta");
        Canica.mostrarColoresValidos();
        
        // Ingreso del número de oportunidades
        do
        {
            System.out.println("¿Cuántas oportunidades tiene el jugador para adivinar? (1 - 15): ");
            oportunidadesPropuestas = sc.nextLine();
            oportunidadesInvalidas = !oportunidadesPropuestas.matches("[0-9]+");
            
            if(oportunidadesInvalidas)
            {
                System.out.println("No se ha ingresado una cantidad válida");
            }
            else
            {
                oportunidades = Integer.parseInt(oportunidadesPropuestas);
                oportunidadesInvalidas = oportunidades < 1 || oportunidades > 15 || 
                                         oportunidades != (int)oportunidades;
            }
        } while(oportunidadesInvalidas);
        
        // Inicio del juego        
        combinacionGanadora = Combinacion.crearAleatoria(LONGITUD_COMBINACION);      
        
        while(!jugadorHaAdivinado && intento < oportunidades)
        {
            intento++;
            
            do
            {
                // Se crea una combinación
                combinacionJugador = new Combinacion(jugador.pedirCombinacion(LONGITUD_COMBINACION));
                combinacionRepetida = !jugador.verificarCombinacionRepetida(combinacionJugador);
                
                if(combinacionRepetida)
                {
                    System.out.println("Combinación repetida");
                }
            } while(combinacionRepetida);
            
            // Si la combinación no está repetida, se registra y 
            // se muestran las combinaciones anteriores, además se comprueba
            // si el jugador ha adivinado la combinación
            jugador.registrarCombinacion(combinacionGanadora, combinacionJugador);
            jugador.mostrarHistorial();
            jugadorHaAdivinado = combinacionJugador.equals(combinacionGanadora);
            
            if(jugadorHaAdivinado)
            {
                System.out.println("¡Felicidades! Adivinaste la combinación al intento no. " + intento);
                System.out.println("Puntaje obtenido = " + puntaje + " puntos");
            }
            else
            {
                // Si el jugador llegó a su límite de oportunidades,
                // se muestra la combinación a descifrar y se acaba el juego
                if(intento == oportunidades)
                {
                    System.out.println("Se te han acabado las oportunidades, la combinación era: ");
                    combinacionGanadora.mostrar();
                }
                // Por cada intento usado, al puntaje se le resta una proporción dada
                puntaje -= 100 / oportunidades;
            }
        }
    }
}
