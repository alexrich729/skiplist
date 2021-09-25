/**
 * Skip List.
 *
 * @author Alex Richardson
 * @version September 12, 2021
 */

public class SkipList<Item extends Comparable<Item>> {
    /** probability that you make a new node */
    private double probability;
    /** always one greater than height of most promoted node */
    private int height = 0;
    private Node topLeft = null;


    public SkipList() {
        addLevel();
        probability = 1.0/2.0;
    }

    public SkipList(double probability) {
        addLevel();
        this.probability = probability;
    }

    /**
     * Searches greatest value that is less than or equal to the key, and returns its Node. If there is no node with lesser
     * value, then returns lowest-level head
     * @param key       Item to search for
     */
    public Node search(Item key) {
        Node<Item> current = topLeft;

        while (current.getDown() != null) {
            current = current.getDown();
            while (current.getRight() != null && key.compareTo(current.getRight().getItem()) >= 0) {
                current = current.getRight();
            }
        }

        return current;
    }


    /**
     * Inserts new node into Skip List
     * @param key       Item to insert
     * @return          Node, highest node where Item is inserted
     */
    public Node insert(Item key) {
        /*
         * Uses search method to find where to insert new node on bottom level. Calls promote() to potentially promote
         * new node. Returns highest node inserted.
         */
        Node location = search(key);
        if (height <= 1)
            addLevel();
        Node current = new Node(key, 1);
        current.setRight(location.getRight());
        if (location.getRight() != null)
            location.getRight().setLeft(current);
        current.setLeft(location);
        location.setRight(current);

        return promote(current);

    }

    /**
     * Deletes all nodes with value of key.
     * @param key       Item with value to target
     */
    public void delete(Item key) {
        /*
         * Step 1: Uses search method to find bottom node with key which becomes current node.
         * Step 2: Use compare to make sure it is equal. If not return.
         * Step 3: Makes current's left node point to current's right node.
         * Step 4: Then the current node becomes its upper node.
         * Step 5: If current's left node is a header node (left node is null) and right node is equal to null, then make
         * topLeft equal to this header, and make topLeft's top node equal to null.
         * Step 6: Repeat from step 3 as long as current.getTop() != null.
         * Step 7: Repeat from step 1.
         */
        Node current;
        while (true) {
            current = search(key);
            if (current.getLeft() == null || current.getItem().compareTo(key) != 0)
                return;
            boolean shortened = false;
            while (current != null && !shortened) {
                current.getLeft().setRight(current.getRight());
                if (current.getLeft().getLeft() == null && current.getRight() == null) {
                    topLeft = current.getLeft();
                    topLeft.setUp(null);
                    shortened = true;
                }
                current = current.getUp();
            }
        }
    }

    /**
     * Randomly decides if object is to promoted. If it is, promotes object and recursively calls itself.
     * @param previous      Node which is being considered for promotion
     * @return              Node, highest node inserted or previous if no promotions done
     */
    private Node promote(Node previous) {
        // don't promote if random number is greater than or equal to probability
        if (Math.random() >= probability)
            return previous;

        if (previous.getHeight() + 1 >= height)
            addLevel();
        Node current = new Node(previous.getItem(), previous.getHeight() + 1);

        Node leftNode = findLeftNode(previous);

        // insert current node in between leftNode and whatever was on its right
        current.setRight(leftNode.getRight());
        if (current.getRight() != null)
            current.getRight().setLeft(current);
        current.setLeft(leftNode);
        leftNode.setRight(current);

        current.setDown(previous);
        previous.setUp(current);

        return promote(current);
    }

    /**
     * Find the first node to the left of original node and at the next level up.
     * @param original      Node to start searching from
     * @return              Node that meets conditions
     */
    private Node findLeftNode(Node original) {
        if (original.getLeft() == null)
            throw new AssertionError("Called findLeftNode() with head node. Don't.");
        Node current = original;
        // runs backward until finds a node that can go up
        while (current.getLeft() != null && current.getUp() == null) {
            current = current.getLeft();
        }
        current = current.getUp();
        if (current == null)
            throw new AssertionError("Somehow head is not taller");

        return current;

    }

    /**
     * Adds new level to skip list.
     */
    private void addLevel() {
        height++;
        Node newHead = new Node(null, height);
        if (height == 1) {
            topLeft = newHead;
            return;
        }
        newHead.setDown(topLeft);
        topLeft.setUp(newHead);
        topLeft = newHead;
    }

    public String toStringFull() {
        Node head = topLeft;
        Node current;
        String result = "";
        while (head.getDown() != null) {
            head = head.getDown();
            current = head;
            while(current.getRight() != null) {
                current = current.getRight();
                result += current + "  ";
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public String toString() {
        Node current = topLeft;
        String result = "";
        while (current.getDown() != null) {
            current = current.getDown();
        }
        while (current.getRight() != null) {
            current = current.getRight();
            result += current.getItem() +",";
        }
        return result;
    }
}
