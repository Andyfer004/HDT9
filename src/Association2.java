/**
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * ALGORITMOS Y ESTRUCTURA DE DATOS
 * @author Andy Fuentes
 * @version 2.0
 * @source Clase Association
 *
 * @param <K> Tipo de dato a almacenar como llave
 * @param <V> Tipo de dato a almacenar como valor
 */

public class Association2<K, V> {
    private K key;
    private V value;

    public Association2(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean equals(Object other) {
        if (other instanceof Association2) {
            Association2<K, V> otherAssoc = (Association2<K, V>) other;
            return key.equals(otherAssoc.getKey()) && value.equals(otherAssoc.getValue());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return key.hashCode() + value.hashCode();
    }

    public String toString() {
        return "(" + key.toString() + ", " + value.toString() + ")";
    }
}

