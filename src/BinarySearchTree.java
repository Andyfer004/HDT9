public abstract class BinarySearchTree<K extends Comparable<K>, V> implements Map<K, V> {
    protected Node<K, V> root;
    protected int size;

    protected static class Node<K, V> {
        protected K key;
        protected V value;
        protected Node<K, V> left;
        protected Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Implementación de los métodos de Map<K, V> omitidos por brevedad
    // ...
}
