import org.junit.Before;

import java.util.Random;

public class PerformanceTest {

    private ILogic logic;
    private String string10k;
    private String string1m;

    @Before
    public void setUp() throws Exception {
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
}
