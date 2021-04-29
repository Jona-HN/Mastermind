package uabc.computacion.mastermind;

import java.util.Arrays;
import java.util.Objects;

/**
 * Modelo de una canica utilizada en un juego de Mastermind
 * 
 * @author Jonathan Herrera
 * @version 29/04/2021
 */
public class Canica 
{
    private String color;
    private int orden;
    // Colores validos: amarillo, azul, blanco, naranja, negro, rojo, verde
    private static final String[] COLORES_VALIDOS = {"AM", "AZ", "BL", "NA", "NE", "RO", "VE"};
    private static final String[] NOMBRES_COLORES_VALIDOS = {"Amarillo", "Azul", "Blanco", "Naranja", "Negro", "Rojo", "Verde"};
    
    /**
     * Constructor para una canica
     * @param color: que debe tener (dentro de los colores válidos)
     * @param orden: posición de la canica dentro de la combinación
     */
    public Canica(String color, int orden)
    {
        this.color = color;
        this.orden = orden;
    }
    
    // Getter para el color
    public String getColor()
    {
        return color;
    }
    
    /**
     * Genera un hash code de acuerdo al color y 
     * posición de la canica
     * @return hash code
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(color) + Objects.hash(orden);
    }
    
    /**
     * Compara una canica con un objeto (idealmente, otra canica)
     * @param obj
     * @return si las canicas son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Canica other = (Canica) obj;
        if (this.orden != other.orden) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return true;
    }
    
    /**
     * Regresa el número de colores válidos
     * @return número de colores válidos disponibles
     */
    public static int getNumeroColoresDisponibles()
    {
        return COLORES_VALIDOS.length;
    }
    
    /**
     * Verifica si un color dado se encuentra dentro de
     * los colores permitidos
     * @param colorIngresado: un color dado
     * @return si el color dado es válido
     */
    public static boolean verificarColor(String colorIngresado)
    {
        return Arrays.asList(COLORES_VALIDOS).contains(colorIngresado);
    }
    
    /**
     * Regresa un color (dentro de los permitidos), de acuerdo
     * al índice del mismo
     * @param numero: índice del color
     * @return un color
     */
    public static String escogerColor(int numero)
    {
        return COLORES_VALIDOS[numero];
    }
    
    /**
     * Imprime por consola los colores permitidos
     */
    public static void mostrarColoresValidos()
    {
        System.out.println("Colores válidos: ");
        String identificadorColor, nombreColor;
        
        for(int i = 0; i < COLORES_VALIDOS.length; i++)
        {
            identificadorColor = COLORES_VALIDOS[i];
            nombreColor = NOMBRES_COLORES_VALIDOS[i];
            
            System.out.println("\t" + identificadorColor + " = " + nombreColor);
        }
    }
}
