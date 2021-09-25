public class Node<Item extends Comparable<Item>> implements Comparable<Node<Item>> {
    public Item item;

    public Node<Item> right;
    public Node<Item> left;
    public Node<Item> up;
    public Node<Item> down;

    private int height;

    public Node(Item item, int height) {
        this.item = item;
        right = null;
        left = null;
        up = null;
        down = null;
        this.height = height;
    }

    public int compareTo(Node<Item> o) {
        return item.compareTo(o.getItem());
    }

    public Item getItem() {
        return item;
    }

    public Node<Item> getRight() {
        return right;
    }

    public void setRight(Node<Item> right) {
        this.right = right;
    }

    public Node<Item> getLeft() {
        return left;
    }

    public void setLeft(Node<Item> left) {
        this.left = left;
    }

    public Node<Item> getUp() {
        return up;
    }

    public void setUp(Node<Item> up) {
        this.up = up;
    }

    public Node<Item> getDown() {
        return down;
    }

    public void setDown(Node<Item> down) {
        this.down = down;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                ", right=" + (right != null ? right.getItem() : "null")+
                ", left=" + (left != null ? left.getItem() : "null") +
                ", up=" + (up != null ? up.getItem() : "null") +
                ", down=" + (down != null ? down.getItem() : "null") +
                ", height=" + height +
                '}';
    }
}
