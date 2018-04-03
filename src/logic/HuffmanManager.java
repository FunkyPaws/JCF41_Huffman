package logic;

import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanManager implements IEncode {

    private static int alphabetSize = 256;
    //Ascii
    //Why the size. To support all the ascii characters even if not used.

    //compress and decompress
    @Override
    public void compress(String text, File fileLocation) throws IOException {
        int[] frequency = buildFrequencyTable(text);
        Node root = buildTree(frequency);
        Map<Character, String> lookupTable = buildLookUpTable(root);
        HuffmanEncodedResult result = new HuffmanEncodedResult(generateData(text, lookupTable), root);

        // add write
        writeToFile(result, fileLocation);
    }

    @Override
    public String decompress(File filename) throws IOException {
        HuffmanEncodedResult result = readFromFile(filename);
        StringBuilder decompressBuilder = new StringBuilder();
        BitSet set = result.getEncodedData();

        for (int i = 0; set.length() - 1 > i; ) {
            Node current = result.getRoot();
            while (!current.isLeaf()) {
                if (set.get(i)) {
                    current = current.getRight();
                } else {
                    current = current.getLeft();
                }
                i++;
            }
            decompressBuilder.append(current.getCharacter());
        }
        return decompressBuilder.toString();
    }


    //build tree
    private static BitSet generateData(String text, Map<Character, String> lookupTable) {
        int postion = 0;
        BitSet bitSet = new BitSet();
        for (char character : text.toCharArray()) {
            String s = lookupTable.get(character);
            for (char c : s.toCharArray()) {
                if (c == '1') {
                    // alles is standaard een 0 in een bitset
                    bitSet.set(postion);
                }
                postion++;
            }
        }
        bitSet.set(postion);
        return bitSet;
    }

    private static int[] buildFrequencyTable(String text) {
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

    private static Map<Character, String> buildLookUpTable(Node root) {
        Map<Character, String> lookupTable = new HashMap<>();
        buildLookupTableImpl(root, "", lookupTable);

        return lookupTable;
    }

    private static void buildLookupTableImpl(Node node, String s, Map<Character, String> lookupTable) {
        if (!node.isLeaf()) {
            buildLookupTableImpl(node.getLeft(), s + '0', lookupTable);
            buildLookupTableImpl(node.getRight(), s + '1', lookupTable);
        } else {
            lookupTable.put(node.getCharacter(), s);
        }
    }


    //read and write
    public void writeToFile(HuffmanEncodedResult result, File file) throws IOException {

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(result);
        }
    }

    public HuffmanEncodedResult readFromFile(File filename) throws IOException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            Object object = objectInputStream.readObject();
            if (object instanceof HuffmanEncodedResult) {
                return (HuffmanEncodedResult) object;
            }
        } catch (ClassNotFoundException ignored) {

        }
        throw new InvalidClassException("Incorrect object found, object was not an instance of HuffmanEncodedResult");
    }


    //test area
    public static void main(String[] args) throws IOException {
        String test = "In deze opdracht., komen de : volgende ? onderwerpen aan bod";


        //new HuffmanManager().compress(test, new File("TestFileHuffman"));
        String message = new HuffmanManager().decompress(new File("TestFileHuffman"));
        System.out.println(message);
    }
}