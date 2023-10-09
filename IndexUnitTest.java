import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IndexUnitTest {
    static private Index indexTest = new Index();
    static private Tree treeTest;

    private static String file1Name = "test";
    private static String file1Text = "text";
    private static String file1SHA = "372ea08cab33e71c02c651dbc83a474d32c676ea";

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn";
    private static String file2SHA = "98fa98f725d269076d1af0a978a308b6df672673";

   // private static String treeInput = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
  //  private static String treeInput2 = "blob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt";
  //  private static String treeSHA = "ee8612eaba3e603c9cb58e1d26a0b95ee3477652"; // hashed from treeInput

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
        FileUtil.deleteFile("test");
        FileUtil.deleteFile("test2.txt");
        FileUtil.deleteFile("tree");
    }

    @Test
    @DisplayName("[8] Test if initialize and objects are created correctly")
    void testInitialize() throws Exception {
        // Run the person's code
        // TestHelper.runTestSuiteMethods("testInitialize");
        treeTest = new Tree();

        indexTest.initializeProject();
        treeTest.initializeTree();

        // check if the file exists
        File indexFile = new File("index");
        indexFile.createNewFile();

        Path objPath = Paths.get("objects");
        File objectsFile = new File(objPath.toString());
        objectsFile.createNewFile();

        File treeFile = new File("Tree");
        treeFile.createNewFile();

        assertTrue("index:", indexFile.exists());
        assertTrue("obj:", Files.exists(objPath));
        assertTrue("tree:", treeFile.exists());
    }

    @Test
    @DisplayName("index add test")
    void testIndexAdd() throws Exception {

        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");
        indexTest.initializeProject();

        String indexFileContents = FileUtil.readFile(new File("index"));
        assertEquals(indexFileContents,
                "");

        indexTest.addBlob(file1Name);
        indexTest.addBlob(file2Name);

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + file1SHA);
        assertTrue("Blob not found", file_junit1.exists());

        // check file contents
        indexFileContents = FileUtil.readFile(new File("index"));
        String target = file1SHA + " : " + file1Name + "\n" + file2SHA + " : " + file2Name;
        assertEquals(target, indexFileContents);
    }

    @Test
    @DisplayName("index remove test")
    void testIndexRemove() throws Exception {
        indexTest.addBlob(file1Name);
        indexTest.addBlob(file2Name);

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + file1SHA);
        assertTrue(file_junit1.exists());
        File file_junit2 = new File("objects/" + file2SHA);
        assertTrue("Blob file to add not found", file_junit2.exists());

        indexTest.removeBlob(file1Name);
        String target = file2SHA + " : " + file2Name;

        String indexFileContents = FileUtil.readFile(new File("index"));
        assertEquals(target, indexFileContents);

    }

}
