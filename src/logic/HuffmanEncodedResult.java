package logic;

import java.io.Serializable;

public class HuffmanEncodedResult implements Serializable {

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
