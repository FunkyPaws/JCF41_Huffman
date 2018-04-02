package logic;

import java.io.Serializable;
import java.util.BitSet;

public class HuffmanEncodedResult implements Serializable {

    private BitSet encodedData;
    private Node root;

    public HuffmanEncodedResult(BitSet encodedData, Node root) {
        this.encodedData = encodedData;
        this.root = root;
    }

    public BitSet getEncodedData() {
        return encodedData;
    }

    public Node getRoot() {
        return root;
    }
}
