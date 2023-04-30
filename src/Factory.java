import structure5.*;
import java.util.HashMap;
/**
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * ALGORITMOS Y ESTRUCTURA DE DATOS
 * @author Andy Fuentes
 * @version 2.0
 */

public class Factory<K extends Comparable<K>, V> {
    /**
     * Devuelve la implementación seleccionada
     * @param option seleccion de la implementacion
     * @return IMaping implementacion seleccionada
     */
    public Map<K, V> getMap(int option) {
        switch(option) {
            case 1:
                System.out.println("Se seleccionó HashMap");
                return (Map<K, V>) new HashMap<K, V>();
            case 2:
                System.out.println("Se seleccionó Red Black Tree");
                return new RedBlackTree<K, V>();
            case 3:
                System.out.println("Se seleccionó Splay Tree");
                return (Map<K, V>) new SplayTree<K, V>();
            default:
                System.out.println("Opción inválida");
                return null;
        }
    }
}
