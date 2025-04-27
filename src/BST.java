import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size = 0;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public static class Entry<K, V> {
        private final K key;
        private final V val;

        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    private class SimpleStack<T> {
        private class StackNode {
            T val;
            StackNode next;

            StackNode(T val, StackNode next) {
                this.val = val;
                this.next = next;
            }
        }

        private StackNode top = null;

        public void push(T val) {
            top = new StackNode(val, top);
        }

        public T pop() {
            if (top == null) {
                return null;
            }
            T val = top.val;
            top = top.next;
            return val;
        }

        public boolean isEmpty() {
            if (top == null) {
                return true;
            } else {
                return false;
            }
        }

    }

    public void put(K key, V value) {
        if (root == null) {
            root = new Node(key, value);
            size++;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                current.val = value;
                return;
            }
        }

        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left = new Node(key, value);
        } else {
            parent.right = new Node(key, value);
        }

        size++;
    }

    public V get(K key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0){
                current = current.left;
            }
            else if (cmp > 0) {
                current = current.right;
            }
            else {
                return current.val;
            }
        }
        return null;
    }

    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !key.equals(current.key)) {
            parent = current;
            if (key.compareTo(current.key) < 0){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }

        if (current == null){
            return;
        }


        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            else if (parent.left == current){
                parent.left = null;
            }
            else{
                parent.right = null;
            }
        }

        else if (current.left == null || current.right == null) {
            Node child;
            if (current.left != null) {
                child = current.left;
            } else {
                child = current.right;
            }

            if (current == root){
                root = child;
            }
            else if (parent.left == current){
                parent.left = child;
            }
            else{
                parent.right = child;
            }
        }

        else {
            Node successorParent = current;
            Node successor = current.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.key = successor.key;
            current.val = successor.val;
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }

        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<Entry<K, V>> {
        private final SimpleStack<Node> stack = new SimpleStack<>();
        private Node current = root;

        public InOrderIterator() {
            pushLeft(current);
        }

        private void pushLeft(Node node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        public boolean hasNext() {
            if(stack.isEmpty()){
                return false;
            }
            return true;
        }

        public Entry<K, V> next() {
            Node node = stack.pop();
            Entry<K, V> entry = new Entry<>(node.key, node.val);
            if (node.right != null) {
                pushLeft(node.right);
            }
            return entry;
        }
    }
}
