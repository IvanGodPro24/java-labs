public class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    boolean color; // true = RED, false = BLACK

    public Node(int data) {
        this.data = data;
        this.color = true; // новий вузол завжди червоний
    }
}
