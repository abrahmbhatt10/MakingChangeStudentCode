import org.junit.jupiter.api.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MakingChangeTest {

    private int target;
    private int[] coins;

    @Test
    public void testCorrectSmall() {
        setTestData(0);
    }

    @Test
    public void testCorrectLarge() {
        setTestData(3);
    }

    @Test
    public void testCorrectLargest() {
        setTestData(2);
    }

    @Test
    @Timeout(value = 15000, unit = TimeUnit.MILLISECONDS)
    public void testEfficient() {
        setTestData(4);
    }

    @Test
    @Timeout(value = 15000, unit = TimeUnit.MILLISECONDS)
    public void testEfficientLarge() {
        setTestData(5);
    }

    @Test
    @Timeout(value = 15000, unit = TimeUnit.MILLISECONDS)
    public void testEfficientLargest() {
        setTestData(6);
    }

    private void setTestData(int testNumber) {
        // Open files
        try {
            BufferedReader testReader = new BufferedReader(new FileReader("test_files/" + testNumber + ".txt"));
            BufferedReader answerReader = new BufferedReader(new FileReader("test_files/" + testNumber + "_answers.txt"));

            // Read in the data, then run.
            long answer = Long.parseLong(answerReader.readLine());
            loadTest(testReader);
            assertEquals(answer, OldMakingChange.countWays(target, coins),
                    "Test " + testNumber + " failed: should return " + answer);

        } catch (IOException e) {
            System.out.println("Error opening test file #" + testNumber);
            e.printStackTrace();
        }
    }

    private void loadTest(BufferedReader br) {
        String line;
        try {
            line = br.readLine();
            String[] parts = line.trim().split("\\s+");

            // Update instance variables with test data
            target = Integer.parseInt(parts[0]);
            int numCoins = Integer.parseInt(parts[1]);
            coins = new int[numCoins];


            line = br.readLine();
            parts = line.trim().split("\\s+");
            for (int i = 0; i < numCoins; i++) {
                coins[i] = Integer.parseInt(parts[i]);
            }
        } catch (IOException e) {
            System.out.println("Error opening test file");
            e.printStackTrace();
        }
    }
}
