package uabc.computacion.mastermind;

import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

/**
 * Clase que permite almacenar combinaciones de canicas,
 * así como realizar operaciones con las mismas
 * (crear una combinación aleatoria, comparar dos combinaciones, etc)
 * 
 * @author Jonathan Herrera
 * @version 29/04/2021
 */
public class Combinacion 
{
    // ArrayList de canicas
    private ArrayList<Canica> combinacion;
    
    /**
     * Constructor para una combinación dada
     * @param canicas: ArrayList con canicas dadas en orden
     */
    public Combinacion(ArrayList<Canica> canicas)
    {
        combinacion = (ArrayList<Canica>) canicas.clone();
    }
    
    /**
     * Constructor pra una combinación vacía
     */
    public Combinacion()
    {
        combinacion = new ArrayList<>();
    }
    
    /**
     * Imprime por pantalla los colores
     * de las canicas de una combinación
     */
    public void mostrar()
    {
        System.out.println(toString());
    }
    
    /**
     * Genera una cadena con los colores
     * de las canicas de una combinación
     * @return String con los colores de las canicas
     */
    @Override
    public String toString()
    {
        String resultado = "";
        
        for(Canica canica : combinacion)
        {
            resultado += canica.getColor() + "\t";
        }
        return resultado;
    }
    
    /**
     * Genera un hash code por medio del ArrayList
     * de la combinación
     * @return hash code de la combinación
     */
    @Override
    public int hashCode()
    {
        return combinacion.hashCode();
    }
    
    /**
     * Compara una combinación con un objeto (idealmente, otra combinación)
     * (dado color y posición de las canicas)
     * @param obj
     * @return si las combinaciones son iguales
     */
    @Override
    public boolean equals(Object obj) 
    {
        ArrayList<Canica> canicasDiferentes;
        Combinacion other = (Combinacion) obj;
        
        if (!(obj instanceof Combinacion)) 
        {
            return false;
        }
        
        canicasDiferentes = (ArrayList<Canica>) combinacion.clone();
        canicasDiferentes.removeAll(other.combinacion);
        
        return canicasDiferentes.isEmpty();
    }
    
    /**
     * Regresa los aciertos del jugador,
     * en otras palabras, las canicas que están correctas en posición
     * y aquellas que no, pero sí en color
     * @param combinacionGanadora: combinación a descifrar (se usa para combpararla
     * con la del jugador)
     * @param combinacionJugador: combinación dada por el jugador
     * @return aciertos del jugador
     */
    public static String mostrarCorrectas(Combinacion combinacionGanadora, Combinacion combinacionJugador)
    {
        ArrayList<Canica> canicasCorrectasEnPosicion;
        ArrayList<Canica> canicasCorrectasEnColor = new ArrayList<>();
        ArrayList<Canica> canicasJugador = (ArrayList<Canica>) combinacionJugador.combinacion.clone();
        ArrayList<Canica> canicasGanadoras = (ArrayList<Canica>) combinacionGanadora.combinacion.clone();
        ArrayList<Canica> canicasRestantesJugador;
        ArrayList<Canica> canicasRestantesGanadoras = (ArrayList<Canica>) combinacionGanadora.combinacion.clone();
        String resultado = "";
        
        // Se quedan únicamente los objetos iguales, en este caso, las canicas
        // que coinciden tanto en color como en posición.
        // La igualdad se verifica mediante los métodos hashCode y equals
        canicasCorrectasEnPosicion = (ArrayList<Canica>) canicasJugador.clone();
        canicasCorrectasEnPosicion.retainAll(canicasGanadoras);
        
        // Se quedan únicamente las canicas que falta por determinar si están 
        // correctas en color. Para esto, se eliminan las que están correctas
        // en posición.
        canicasRestantesJugador = (ArrayList<Canica>) canicasJugador.clone();
        canicasRestantesJugador.removeAll(canicasCorrectasEnPosicion);
        canicasRestantesGanadoras.removeAll(canicasCorrectasEnPosicion);
        
        Iterator<Canica> it1 = canicasRestantesJugador.iterator();
        Iterator<Canica> it2;
        Canica canica1;
        Canica canica2;
        
        // Búsqueda en la coincidencia del color de las canicas incluidas
        // tanto en la combinación del jugador como en la que se debe descifrar
        while(it1.hasNext())
        {
            canica1 = it1.next();
            it2 = canicasRestantesGanadoras.iterator();
            
            while(it2.hasNext())
            {
                canica2 = it2.next();
                
                if(canica1.getColor().equals(canica2.getColor()))
                {
                    canicasCorrectasEnColor.add(canica1);
                    it2.remove();
                    break;
                }
            }
            
            it1.remove();
        }
        
        // Generación de resultados
        for(int i = 0; i < canicasCorrectasEnPosicion.size(); i++)
        {
            resultado += ". ";
        }
        
        for(int i = 0; i < canicasCorrectasEnColor.size(); i++)
        {
            resultado += "o ";
        }
        
        return resultado;
    }
    
    /**
     * Genera una combinación aleatoria
     * @param longitudCombinacion: número de canicas que debe tener la combinación
     * @return una combinación aleatoria
     */    
    public static Combinacion crearAleatoria(int longitudCombinacion)
    {
        ArrayList<Canica> canicas = new ArrayList<>(longitudCombinacion);
        Random r = new Random();
        Combinacion combinacionAleatoria;
        String color;
        
        for(int i = 0; i < longitudCombinacion; i++)
        {
            color = Canica.escogerColor(r.nextInt(Canica.getNumeroColoresDisponibles()));
            canicas.add(new Canica(color, canicas.size() + 1));
        }
        
        combinacionAleatoria = new Combinacion(canicas);
        
        return combinacionAleatoria;
    }
}
