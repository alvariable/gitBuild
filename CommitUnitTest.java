import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommitUnitTest {
    static Commit commit;

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
        FileUtil.deleteFile("Tree");
        FileUtil.deleteFile("test2.txt");
        FileUtil.deleteFile("test");

    }

    @Test
    @DisplayName("Test Constructor")
    public void testConstructor() throws Exception {
        commit = new Commit("Chris Weng", "Test commit");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = new Date();
        String date = formatter.format(today);
        assertEquals(date, commit.getDate());
    }

    @Test
    void testCreateHash() {

    }

    @Test
    void testGetDate() {

    }

    @Test
    void testGetFileName() {

    }

    @Test
    void testReadFile() {

    }

    @Test
    @DisplayName("Test write method")
    public void testWrite() throws Exception {
        commit = new Commit("Chris Weng", "Test commit");
        String commitContent = commit.write();
        String idealContent = FileUtil.getHash("") + "\n\n\nChris Weng\n" + commit.getDate() + "\nTest commit";
        assertEquals(idealContent, commitContent);
    }

}
