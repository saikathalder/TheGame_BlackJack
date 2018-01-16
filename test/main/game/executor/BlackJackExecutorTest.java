package main.game.executor;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Test class for BlackJackExecutor
 */
public class BlackJackExecutorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Method under test main()
     * Scenario : When no file provided
     */
    @Test
    public void testExecuteBlackJackGameWithOutFile() throws Exception {
        String[] args = {};
        BlackJackExecutor.main(args);
        String outputStream = outContent.toString();
        Assert.assertTrue(outputStream.contains("No files given."));
    }

    /**
     * Method under test main()
     * Scenario : When file name provided as null
     */
    @Test
    public void testExecuteBlackJackGameWhenFileNameIsNull() throws Exception {
        String[] args = {null};
        expectedException.expect(IOException.class);
        expectedException.expectMessage("No file name provided..");
        BlackJackExecutor.main(args);
    }

    /**
     * Method under test main()
     * Scenario : When file name provided as empty string
     */
    @Test
    public void testExecuteBlackJackGameWhenFileNameIsEmptyString() throws Exception {
        String[] args = {""};
        expectedException.expect(IOException.class);
        expectedException.expectMessage("No file name provided..");
        BlackJackExecutor.main(args);
    }

    /**
     * Method under test main()
     * Scenario : When proper file name is provided
     */
    @Test
    public void testExecuteBlackJackGameWhenProperFileNameProvided() throws Exception {
        String[] args = {"./src/main/resources/Blackjackfile"};
        BlackJackExecutor.main(args);
        String outputStream = outContent.toString();
        Assert.assertTrue(outputStream.contains(args[0]));
        Assert.assertTrue(outputStream.contains("Winner is :Dealer"));
    }

    /**
     * Method under test main()
     * Scenario : When multiple file name provided
     * Outcome : Only first file name will be considered
     */
    @Test
    public void testExecuteBlackJackGameWhenMulipleFileNameProvided() throws Exception {
        String[] args = {"./src/main/resources/Blackjackfile","./src/main/resources/Blackjackfile_Empty"};
        BlackJackExecutor.main(args);
        String outputStream = outContent.toString();
        Assert.assertFalse(outputStream.contains(args[1]));
    }
}