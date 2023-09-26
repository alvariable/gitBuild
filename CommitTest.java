import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

public class CommitTest {
    Commit commit = new Commit("Chris Weng", "Test commit");

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        String objectPath = "./objects/";
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");
    }

    @Test
    @DisplayName("Test Constructor")
    void testConstructor() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = new Date();
        String date = formatter.format(today);
        assertEquals(date, commit.getDate());
    }
}
