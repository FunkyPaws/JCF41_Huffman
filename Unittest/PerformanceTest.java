import logic.HuffmanManager;
import logic.IEncode;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PerformanceTest {

    private IEncode iEncode;
    private String string10k;
    private String string1m;

    private static final int testAmount = 4;

    private static final Logger LOGGER = Logger.getLogger(PerformanceTest.class.getName());

    @Before
    public void setUp() throws Exception {
        iEncode = new HuffmanManager();
        string10k = generateString(10000);
        string1m = generateString(1000000);
    }

    private String generateString(int numberOfWords) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfWords; i++) {
            String name = " " + rnd.nextInt();
            if ((i % 5) == 0) {
                sb.append("\n");
            }
            sb.append(name + "");
        }
        return sb.toString();
    }

    private long averageTestTime(Long[] times) {
        long time = 0;
        for (Long aLong : times) {
            time += aLong;
        }
        return time / times.length;
    }

    @Test
    public void testEncode() throws Exception {

        //10k test
        Long[] allTimes10k = new Long[testAmount];
        for (int i = 0; testAmount > i; i++) {
            long beginTime = System.nanoTime();
            iEncode.compress(string10k, new File("10k"));
            allTimes10k[i] = System.nanoTime() - beginTime;
        }
        long avg10kTime = averageTestTime(allTimes10k);
        LOGGER.log(Level.INFO, "10k encode average time in nanoseconds: " + avg10kTime);

        //mil test
        Long[] allTimesMil = new Long[testAmount];
        for (int i = 0; testAmount > i; i++) {
            long beginTime = System.nanoTime();
            iEncode.compress(string1m, new File("mil"));
            allTimesMil[i] = System.nanoTime() - beginTime;
        }
        long avgMilTime = averageTestTime(allTimesMil);
        LOGGER.log(Level.INFO, "mil encode average time in nanoseconds: " + avgMilTime);
    }

    @Test
    public void testDecode() throws Exception {

        //10k test
        Long[] allTimes10k = new Long[testAmount];
        for (int i = 0; testAmount > i; i++) {
            long beginTime = System.nanoTime();
            iEncode.decompress(new File("10k"));
            allTimes10k[i] = System.nanoTime() - beginTime;
        }
        long avg10kTime = averageTestTime(allTimes10k);
        LOGGER.log(Level.INFO, "10k decode average time in nanoseconds: " + avg10kTime);

        //mil test
        Long[] allTimesMil = new Long[testAmount];
        for (int i = 0; testAmount > i; i++) {
            long beginTime = System.nanoTime();
            iEncode.decompress(new File("mil"));
            allTimesMil[i] = System.nanoTime() - beginTime;
        }
        long avgMilTime = averageTestTime(allTimesMil);
        LOGGER.log(Level.INFO, "mil decode average time in nanoseconds: " + avgMilTime);
    }
}