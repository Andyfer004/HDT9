import java.util.Comparator;
import java.util.Set;
import java.util.Map;
import java.util.Collection;
import java.util.Iterator;
import java.util.*;

public class SplayTree<K, V> implements Map<K, V> {
    private Comparator<K> comparator;
    private Node<K, V> root;

    public SplayTree() {
        this(null);
    }

    public SplayTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> splay(Node<K, V> root, K key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }
        if (compare(key, root.key) < 0) {
            if (root.left == null) {
                return root;
            }
            if (compare(key, root.left.key) < 0) {
                root.left.left = splay(root.left.left, key);
                root = rotateRight(root);
            } else if (compare(key, root.left.key) > 0) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null) {
                    root.left = rotateLeft(root.left);
                }
            }
            return root.left == null ? root : rotateRight(root);
        } else {
            if (root.right == null) {
                return root;
            }
            if (compare(key, root.right.key) > 0) {
                root.right.right = splay(root.right.right, key);
                root = rotateLeft(root);
            } else if (compare(key, root.right.key) < 0) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null) {
                    root.right = rotateRight(root.right);
                }
            }
            return root.right == null ? root : rotateLeft(root);
        }
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        return leftChild;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        return rightChild;
    }

    private int compare(K a, K b) {
        if (comparator != null) {
            return comparator.compare(a, b);
        } else {
            return ((Comparable<K>) a).compareTo(b);
        }
    }

    @Override
    public int size() {
        return entrySet().size();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> entry : entrySet()) {
            if (entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (root == null) {
            return null;
        }
        root = splay(root, (K) key);
        if (root.key.equals(key)) {
            return root.value;
        } else {
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException();

    }

    @Override
    public Set<K> keySet() {
        // implementa este método
        return null;
    }

    @Override
    public Collection<V> values() {
        // implementa este método
        return null;
    }

}










