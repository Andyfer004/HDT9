/**
 * UNIVERSIDAD DEL VALLE DE GUATEMALA
 * ALGORITMOS Y ESTRUCTURA DE DATOS
 * @author Andy Fuentes
 * @version 2.0
 */
public interface Map<K, V> {
    int size();
    boolean isEmpty();

    V get(K key);
    V put(K key);
    V remove(K key);
    Iterable<K> keySet();
    Iterable<V> values();
    Iterable<Entry<K, V>> entrySet();

    interface Entry<K, V> {
        K getKey();
        V getValue();
        void setValue(V value);
    }
}
