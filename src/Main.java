import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, String> table = new MyHashTable<>(200);
        Random rand = new Random();

        for (int i = 0; i < 10000; i++) {
            int id = rand.nextInt(1000000); // Случайный ID
            String name = "Key_" + rand.nextInt(10000);
            MyTestingClass key = new MyTestingClass(name, id);
            table.put(key, "Value_" + i);
        }
        table.printBucketSizes();



        BST<Integer, String> bst = new BST<>();

        bst.put(10, "Ten");
        bst.put(20, "Twenty");
        bst.put(5, "Five");
        bst.put(15, "Fifteen");
        bst.put(30, "Thirty");
        bst.put(25, "Twenty Five");

        System.out.println("Get 15: " + bst.get(15));
        System.out.println("Get 30: " + bst.get(30));
        System.out.println("Get 100: " + bst.get(100));

        bst.delete(15);
        System.out.println("Get 15: " + bst.get(15));

        bst.delete(10);
        System.out.println("Get 10: " + bst.get(10));

        for (BST.Entry<Integer, String> entry : bst) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}


