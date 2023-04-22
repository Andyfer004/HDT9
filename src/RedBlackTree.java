import java.util.*;

public class RedBlackTree<K extends Comparable<K>, V> implements Map<K, V> {
    private Node<K, V> root;
    private int size;
    private final Comparator<? super K> comparator;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private static class Node<K extends Comparable<K>, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        boolean color;
        int size;

        public Node(K key, V value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackTree() {
        this.comparator = null;
    }

    public RedBlackTree(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
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
        Node<K, V> x = root;
        while (x != null) {
            int cmp = compare(key, x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("Cannot insert null key.");
        }
        Node<K, V> newNode = new Node<>(key, value, RED, 1);
        root = put(root, newNode);
        root.color = BLACK;
        return newNode.value;
    }

    private Node<K, V> put(Node<K, V> h, Node<K, V> newNode) {
        if (h == null) {
            size++;
            return newNode;
        }
        int cmp = compare(newNode.key, h.key);
        if (cmp < 0) {
            h.left = put(h.left, newNode);
        } else if (cmp > 0) {
            h.right = put(h.right, newNode);
        } else {
            h.value = newNode.value;
        }

        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    @Override
    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        if (!containsKey(key)) {
            return null;
        }
        V oldValue = get(key);
        root = remove(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
        size--;
        return oldValue;
    }

    /**
     * Recursive helper method to remove a key-value pair from the tree.
     *
     * @param node the root of the subtree to search for the key
     * @param key  the key to remove
     * @return the new root of the subtree after the removal
     */
    private Node remove(Node node, Object key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }
            if (key.equals(node.key) && (node.right == null)) {
                return null;
            }
            if (!isRed(node.right) && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }
            if (key.equals(node.key)) {
                Node successor = getMinNode(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = deleteMin(node.right);
            } else {
                node.right = remove(node.right, key);
            }
        }
        return balance(node);
    }

    /**
     * Helper method to get the node with the minimum key in a subtree.
     *
     * @param node the root of the subtree
     * @return the node with the minimum key in the subtree
     */
    private Node getMinNode(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * Helper method to delete the node with the minimum key in a subtree.
     *
     * @param node the root of the subtree
     * @return the new root of the subtree after the removal
     */
    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    /**
     * Helper method to balance a node and its children in a red-black tree.
     *
     * @param node the root of the subtree to balance
     * @return the new root of the subtree after balancing
     */
    private Node balance(Node node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * Helper method to move a red node to the left child of its parent.
     *
     * @param node the node to move
     * @return the new root of the subtree after the move
     */
    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (node.right != null && isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (node.left != null && isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private Node balance(Node node) {
        if (isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private boolean isRed(Node node) {
        return (node != null && node.color == RED);
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.color = node.color;
        node.color = RED;
        return newRoot;
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.color = node.color;
        node.color = RED;
        return newRoot;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        if (root != null) {
            addEntries(root, set);
        }
        return set;
    }

    private void addEntries(Node node, Set<Map.Entry<K, V>> set) {
        if (node.left != null) {
            addEntries(node.left, set);
        }
        set.add(new AbstractMap.SimpleEntry<>(node.key, node.value));
        if (node.right != null) {
            addEntries(node.right, set);
        }
    }

    @Override
    public int size() {
        return size;
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
    public V put(K key, V value) {
        if (key == null) {
            return null;
        }
        root = put(root, key, value);
        root.color = BLACK;
        return null;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }
}