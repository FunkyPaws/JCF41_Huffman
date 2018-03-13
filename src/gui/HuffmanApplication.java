package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.HuffmanEncoded;
import sun.security.util.BitArray;

public class HuffmanApplication {
    private static int alphabetSize = 256;
    //Ascii

    public HuffmanEncoded compress(String text) {
        int[] freq = BuildTable(text);


        return null;
    }

    public String decompress(HuffmanEncoded result){
        return null;
    }

    public static int[] BuildTable(String text){
        int[] freq = new int[alphabetSize];
        for(char character : text.toCharArray()){
            freq[character]++;
        }
        return freq;
    }

    public static void main(String[] args) {
        String test = "In deze opdracht komen de volgende onderwerpen aan bod";
        int[] testMethode = BuildTable(test);
        for (int i : testMethode){
            System.out.println(i);
        }
    }
}
