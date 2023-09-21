import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExampleTester {

    private Index indexTest = new Index();
    private Tree treeTest = new Tree();

    private static String file1Name = "test";
    private static String file1Text = "text";
    private static String file1SHA = "372ea08cab33e71c02c651dbc83a474d32c676ea";

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn";
    private static String file2SHA = "98fa98f725d269076d1af0a978a308b6df672673";

    private static String treeInput = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
    private static String treeInput2 = "blob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt";
    private static String treeSHA = "ee8612eaba3e603c9cb58e1d26a0b95ee3477652"; // hashed from treeInput

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
    @DisplayName("[8] Test if initialize and objects are created correctly")
    void testInitialize() throws Exception {
        // Run the person's code
        // TestHelper.runTestSuiteMethods("testInitialize");

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

    @Test
    @DisplayName("index add test")
    void testIndexAdd() throws Exception {

        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");
        indexTest.initializeProject();

        String indexFileContents = FileUtil.readFile(new File("index"));
        assertEquals("File contents of index don't match inputs", indexFileContents,
                "");

        indexTest.addBlob(file1Name);

        indexTest.addBlob(file2Name);

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + file1SHA);
        assertTrue("Blob not found", file_junit1.exists());

        // check file contents
        indexFileContents = FileUtil.readFile(new File("index"));
        assertEquals("File contents of index don't match inputs", indexFileContents,
                file1Name + " : " + file1SHA + file2Name + " : " + file2SHA);
    }

    @Test
    @DisplayName("index remove test")
    void testIndexRemove() throws Exception {
        indexTest.addBlob(file1Name);
        indexTest.addBlob(file2Name);

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + file1SHA);
        assertTrue("Blob file to add not found", file_junit1.exists());
        File file_junit2 = new File("objects/" + file2SHA);
        assertTrue("Blob file to add not found", file_junit2.exists());

        indexTest.removeBlob(file1Name);

        String indexFileContents = FileUtil.readFile(new File("index"));
        assertEquals("File contents of index don't match inputs", indexFileContents,
                file2Name + " : " + file2SHA);

    }

    @Test
    @DisplayName("tree add test")
    void testTreeAdd() throws Exception {

        FileUtil.deleteFile("tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);
        treeTest.add(treeInput2);

        String treeFileContents = FileUtil.readFile(new File("Tree"));

        assertEquals("File contents of tree don't match",
                treeFileContents,
                treeInput + "\n" + treeInput2);
    }

    @Test
    @DisplayName("tree remove test")
    void testTreeRemove() throws Exception {

        FileUtil.deleteFile("Tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);
        treeTest.add(treeInput2);

        treeTest.remove(treeInput);

        String treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals("File contents of tree don't match",
                treeFileContents,
                treeInput2);
    }

    @Test
    @DisplayName("tree blob test")
    void testTreeBlobGeneration() throws Exception {

        FileUtil.deleteFile("Tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);

        treeTest.generateBlob();

        // Check blob exists in the objects folder
        File file_junit1 = new File("objects/" + treeSHA);
        assertTrue("Blob file to add not found", file_junit1.exists());

        // check file contents
        String treeFileContents = FileUtil.readFile(new File("objects/" + treeSHA));
        assertEquals("File contents of treeBlob don't match file contents",
                treeFileContents,
                treeInput);

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
