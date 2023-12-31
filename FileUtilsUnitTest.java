import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileUtilsUnitTest {
    private static String file1Name = "test";
    // private static String file1Text = "text";
    // private static String file1SHA = "372ea08cab33e71c02c651dbc83a474d32c676ea";

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn";
    private static String file2SHA = "98fa98f725d269076d1af0a978a308b6df672673";

    // clears out everything post tests
    @AfterAll
    static void tearDownAfterClass() throws Exception {

        // delete all files
        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");
    }

    @Test
    void testCountCharacters() {

    }

    @Test
    @DisplayName("tests if file exists after creating")
    static void testCreateFile() throws Exception {
        FileUtil.createFile(file1Name);
        File file1 = new File(file1Name);
        assertTrue(file1.exists());
    }

    @Test
    void testDeleteDirectory() {

    }

    @Test
    void testDeleteFile() {

    }

    @Test
    @DisplayName("test hash generation")
    static void testGetSha() throws Exception {
        FileUtil.createFile(file2Name);
        FileUtil.writeFile(file2Text, file2Name);
        Blob t = new Blob(file2Name);
        String hash = t.getSHA1();
        assertEquals(hash, file2SHA);
    }

    @Test
    void testReadFile() {

    }

    @Test
    void testReadFile2() {

    }

    @Test
    void testWriteFile() {

    }

    @Test
    @DisplayName("test if file contents matches input")
    static void testReadAndWriteFile() throws Exception {
        FileUtil.createFile(file2Name);
        FileUtil.writeFile(file2Text, file2Name);

    }
}
