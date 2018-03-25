package gui;

import logic.HuffmanEncodedResult;
import logic.Node;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanEncoder {
    private static int alphabetSize = 256;
    //Ascii
    //Why the size. To support all the ascii characters even if not used.

    public HuffmanEncodedResult compress(String text) {
        int[] frequency = BuildFrequencyTable(text);
        Node root = buildTree(frequency);
        Map<Character, String> lookupTable = buildLookUpTable(root);

        return new HuffmanEncodedResult(generateData(text, lookupTable), root);
    }

    private static String generateData(String text, Map<Character, String> lookupTable) {
        StringBuilder builder = new StringBuilder();
        for (char character : text.toCharArray()) {
            builder.append(lookupTable.get(character));
        }
        return builder.toString();
    }

    public static int[] BuildFrequencyTable(String text) {
        int[] frequency = new int[alphabetSize];
        for (char character : text.toCharArray()) {
            frequency[character]++;
        }
        return frequency;
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

    public static void main(String[] args) {
        String test = "In deze opdracht., komen de : volgende ? onderwerpen aan bod";
        HuffmanEncoder encoder = new HuffmanEncoder();
        HuffmanEncodedResult result = encoder.compress(test);

        System.out.println("encoded: " + result.getEncodedData());
    }
}
