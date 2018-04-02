import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class PerformanceTest {

   // i interface
    private String string10k;
    private String string1m;

    @Before
    public void setUp() throws Exception {
        string10k = generateString(10000);
        string1m = generateString(1000000);
    }

    @Test
    public void getSorteerAction() throws Exception {
//        long startTime = System.nanoTime();
//        manager.splitString(DEFAULT_TEXT);
//        long resultTime = System.nanoTime() - startTime;
//        String logMessage = String.format("getSplitText - Time measured: %d nanoseconds", resultTime);
//        logger.log(Level.INFO, logMessage);
//
//        long startTime10k = System.nanoTime();
//        manager.splitString(string10k);
//        long resultTime10k = System.nanoTime() - startTime10k;
//        String logMessage10k = String.format("getSplitText 10k - Time measured: %d nanoseconds", resultTime10k);
//        logger.log(Level.INFO, logMessage10k);
//
//        long startTime1m = System.nanoTime();
//        manager.splitString(string1m);
//        long resultTime1m = System.nanoTime() - startTime1m;
//        String logMessage1m = String.format("getSplitText 1m - Time measured: %d nanoseconds", resultTime1m);
//        logger.log(Level.INFO, logMessage1m);
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
}
