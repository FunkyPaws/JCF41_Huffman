package logic;

public class HuffmanEncodedResult {

    private String encodedData;
    private Node root;

    public HuffmanEncodedResult(String encodedData, Node root) {
        this.encodedData = encodedData;
        this.root = root;
    }

    public String getEncodedData() {
        return encodedData;
    }

    public Node getRoot() {
        return root;
    }
}
