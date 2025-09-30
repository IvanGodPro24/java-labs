public class RedBlackTree {
    private Node root;
    private final Node NIL;

    public RedBlackTree() {
        NIL = new Node(0);
        NIL.color = false; // чорний
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    // Поворот вліво
    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Поворот вправо
    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Балансування після вставки
    private void fixInsert(Node k) {
        Node u;
        while (k.parent != null && k.parent.color) { // поки батько червоний
            if (k.parent == k.parent.parent.left) {
                u = k.parent.parent.right; // дядько
                if (u.color) {
                    // випадок 1: дядько червоний
                    k.parent.color = false;
                    u.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        // випадок 2: k - правий син
                        k = k.parent;
                        rotateLeft(k);
                    }
                    // випадок 3: k - лівий син
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rotateRight(k.parent.parent);
                }
            } else {
                u = k.parent.parent.left; // дзеркально
                if (u.color) {
                    k.parent.color = false;
                    u.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rotateRight(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rotateLeft(k.parent.parent);
                }
            }
        }
        root.color = false; // корінь завжди чорний
    }

    // Додавання вузла
    public void insert(int key) {
        Node node = new Node(key);
        node.parent = null;
        node.left = NIL;
        node.right = NIL;

        Node y = null;
        Node x = this.root;

        while (x != NIL) {
            y = x;
            if (node.data < x.data) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data < y.data) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = false; // корінь чорний
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    // Обхід in-order
    public void inorder() {
        inorderHelper(this.root);
        System.out.println();
    }

    private void inorderHelper(Node node) {
        if (node != NIL) {
            inorderHelper(node.left);
            System.out.print(node.data + (node.color ? "(R) " : "(B) "));
            inorderHelper(node.right);
        }
    }
}
