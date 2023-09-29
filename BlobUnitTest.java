import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlobUnitTest {
    private Index indexTest = new Index();
    private Tree treeTest = new Tree();

    private static String file1Name = "test";
    private static String file1Text = "text";
    private static String file1SHA = "372ea08cab33e71c02c651dbc83a474d32c676ea";

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn";

    // presumably creates files to test with
    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        // delete all files if still around

        // FileUtils.deleteFile("index");
        // FileUtils.deleteDirectory("objects");

        // set up files to use as test
        FileUtil.createFile(file1Name);
        FileUtil.writeFile(file1Text, file1Name);

        FileUtil.createFile(file2Name);
        FileUtil.writeFile(file2Text, file2Name);

    }

    // clears out everything post tests
    @AfterAll
    static void tearDownAfterClass() throws Exception {

        // delete all files
        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");

    }

    @Test
    @DisplayName("Test if adding a blob works")
    void testBlob() throws Exception {

        Blob blobTest = new Blob(file1Name);
        blobTest.add("objects");

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + file1SHA);
        assertTrue("Blob file to add not found", file_junit1.exists());

        // check file contents
        String indexFileContents = FileUtil.readFile(new File("objects/" + file1SHA));
        assertEquals("File contents of Blob don't match file contents pre-blobcreation",
                indexFileContents,
                blobTest.getContents());
    }

    // @Test
    // public void Testtest() throws Exception {
    // Path p = Paths.get("./bin");
    // String path = p.toFile().getAbsolutePath();
    // File f = new File(path);
    // String[] str = f.list();
    // String a = "";
    // for (String b : str) {
    // a += b + "\n";
    // }
    // throw new Exception(a);
    // }
}
