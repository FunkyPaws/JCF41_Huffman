package gui;

import logic.HuffmanEncodedResult;
import logic.Node;

public class HuffmanDecoder {

    public String decompress(HuffmanEncodedResult result) {
        StringBuilder decompressBuilder = new StringBuilder();

        Node current = result.getRoot();

        int i = 0;
        while (i < result.getEncodedData().length()) {
            while (!current.isLeaf()) {
                char bit = result.getEncodedData().charAt(i);
                if (bit == '1') {
                    current = current.getRight();
                } else if (bit == '0') {
                    current = current.getLeft();
                } else {
                    throw new IllegalArgumentException("Invalid bit in message " + bit);
                }
                i++;
            }
            decompressBuilder.append(current.getCharacter());
            current = result.getRoot();
        }
        return decompressBuilder.toString();
    }

    public static void main(String[] args) {
        String test = "In deze opdracht., komen de : volgende ? onderwerpen aan bod";
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanEncodedResult result = encoder.compress(test);
        HuffmanDecoder decoder = new HuffmanDecoder();

        System.out.println("decoded:  " + decoder.decompress(result));
    }
}
