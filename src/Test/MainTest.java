package Test;

import org.junit.jupiter.api.Test;
import Main.Main;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// Test class for Main class
class MainTest {

    // Test registering a user and showing all movies
    @Test
    void testRegisterUserAndShowAllMovies() {
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        try {
            // Simulate user input
            String userInput = "2\ntest@example.com\n1\nThe\ntitle\n9\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
            System.setIn(inputStream);

            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            System.setOut(printStream);

            // Run the Main class
            Main.main(null);

            // Assert console output
            String consoleOutput = outputStream.toString();
            assertTrue(consoleOutput.contains("User registered successfully."));
            assertTrue(consoleOutput.contains("The Shawshank Redemption"));
            assertTrue(consoleOutput.contains("The Godfather"));
            // Add more assertions as needed
        } finally {
            // Restore original input and output streams
            System.setIn(stdin);
            System.setOut(stdout);
        }
    }

}
