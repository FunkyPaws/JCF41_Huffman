package logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class IEncodeTest {

    private static String text1;
    private static File file1;

    private static String text2;
    private static File file2;

    private static IEncode encode;

    @Before
    public void setUp() throws Exception {
        text1 = "In deze opdracht., komen de : volgende ? onderwerpen aan bod";
        file1 = new File("file1");

        text2 = "text 2 iets bla hallo";
        file2 = new File("file2");

        encode = new HuffmanManager();
    }


    public void compressTest() throws Exception {
        encode.compress(text1, file1);
        encode.compress(text2, file2);
    }


    public void decompressTest() throws Exception {
        String decoded1 = encode.decompress(file1);
        String decoded2 = encode.decompress(file2);

        System.out.println(decoded1);
        System.out.println(decoded2);


        Assert.assertEquals(text1, decoded1);
        Assert.assertEquals(text2, decoded2);
    }

    @Test
    public void TestCoding() throws Exception {
        compressTest();
        decompressTest();
    }
}