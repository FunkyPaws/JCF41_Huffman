package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.HuffmanEncoded;
import logic.Node;
import sun.security.util.BitArray;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanApplication {
    private static int alphabetSize = 256;
    //Ascii

    public HuffmanEncoded compress(String text) {
        int[] freq = BuildFrequencyTable(text);
        Node root = buildTree(freq);
        Map<Character, String> lookupTable = buildLookUpTable(root);

        return null;
    }

    public String decompress(HuffmanEncoded result) {
        return null;
    }

    public static Map<Character, String> buildLookUpTable(Node root) {
        Map<Character, String> lookupTable = new HashMap<>();

        buildLookupTableImpl(root, "", lookupTable);

        return lookupTable;
    }

    public static void buildLookupTableImpl(Node node, String s, Map<Character, String> lookupTable) {
        if (!node.isLeaf()) {
            buildLookupTableImpl(node.getLeft(), s + '0', lookupTable);
            buildLookupTableImpl(node.getRight(), s + '1', lookupTable);
        } else {
            lookupTable.put(node.getCharacter(), s);
        }
    }

    private static Node buildTree(int[] freq) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (char c = 0; c < alphabetSize; c++) {
            if (freq[c] > 0) {
                priorityQueue.add(new Node(c, freq[c], null, null));
            }
        }

        if (priorityQueue.size() == 1) {
            priorityQueue.add(new Node('\0', 1, null, null));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            Node parent = new Node('\0', left.getFrequency() + right.getFrequency(), left, right);
            priorityQueue.add(parent);
        }
        return priorityQueue.poll();
    }

    public static int[] BuildFrequencyTable(String text) {
        int[] freq = new int[alphabetSize];
        for (char character : text.toCharArray()) {
            freq[character]++;
        }
        return freq;
    }

    public static void main(String[] args) {
        String test = "In deze opdracht komen de volgende onderwerpen aan bod";
        int[] i = BuildFrequencyTable(test);
        Node node = buildTree(i);
        Map<Character, String> lookup = buildLookUpTable(node);
        System.out.println(node);

    }
}
