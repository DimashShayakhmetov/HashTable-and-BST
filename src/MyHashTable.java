public class MyHashTable<K, V> {

    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;


    public MyHashTable() {
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }

    public void put(K key, V value) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[bucketIndex];
        chainArray[bucketIndex] = newNode;
        size++;
    }

    public V get(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;//previous node when we will traverse

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) {
                    prev.next = head.next;
                }
                else {
                    chainArray[bucketIndex] = head.next;//than it is the first element in the bucket
                }

                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)){
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    public void printBucketSizes() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println("Bucket " + i + ": " + count + " entries.");
        }

        int total = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            total += count;
            if (count < min) {
                min = count;
            }
            if (count > max){
                max = count;
            }
        }
        System.out.println("Total elements: " + total);
        System.out.println("Min bucket size: " + min);
        System.out.println("Max bucket size: " + max);
        System.out.println("Average bucket size: " + (total / M));
        System.out.println("Difference between MIN and MAX bucket size: " + (max - min));
    }
}
