package logic;

public class Node implements Comparable<Node> {
    private char character;
    private int frequency;
    private Node left;
    private Node right;

    public Node(char character, int frequency, Node left, Node right) {
        this.character = character;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public char getCharacter() {
        return character;
    }

    public int getFrequency() {
        return frequency;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }


    public boolean isLeaf() {
        //if there are no left or right children means its a leaf.
        return this.left == null && this.right == null;
    }

    @Override
    public int compareTo(Node o) {
        int frequencyCompare = Integer.compare(this.frequency, o.frequency);
        if (frequencyCompare != 0) {
            return frequencyCompare;
        }
        return Integer.compare(this.character, o.character);
    }
}
