import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import Utilities.FileUtils;

public class TreeUnitTest {
    private Tree treeTest = new Tree();

    private static String file1Name = "test";
    private static String file1Text = "text";

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn";

    private static String treeInput = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
    private static String treeInput2 = "blob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt";
    private static String treeSHA = "ee8612eaba3e603c9cb58e1d26a0b95ee3477652"; // hashed from treeInput
    private static String emptyContentSha = "";
    private static String treeSHATest = "665e866eb831bfaca69e1901a66df21c573ca4e9";
    private static String SHAOfTreeContents = "cbf35d6d75461742452f914b2c20745f2a5fb0d7";

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

        // // delete all files
        // FileUtil.deleteFile("index");
        // FileUtil.deleteDirectory("objects");

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

    @Test
    @DisplayName("test addDirectory w empty directory")
    void testAddDirectoryEmpty() throws Exception {
        // create directory
        // Directory temp = new Directory();
        File f = new File("Test");
        f.mkdir();
        String hash = treeTest.addDirectory("Test");
        assertEquals(hash, emptyContentSha);
    }

    @Test
    @DisplayName("test addDirectory w just files")
    void testAddDirectoryFiles() throws Exception {
        File folder = new File("Test");
        folder.mkdir();
        // treeTest.add(treeInput);
        // treeTest.add(treeInput2);
        String hash = treeTest.addDirectory("Test");
        assertEquals(hash, treeSHATest);
    }

    @Test
    @DisplayName("test addDirectory w folder")
    void testAddDirectoryFolder() throws Exception {
        File directory = new File("tree");
        directory.mkdir();
        File folder = new File("tree/folder");
        folder.mkdir();
        File file1 = new File(directory, "file1.txt");
        FileUtil.writeFile(file1Text, "file1.txt");
        File file2 = new File(directory, "file2.txt");
        FileUtil.writeFile(file2Text, "file2.txt");
        String hash = treeTest.addDirectory("tree");
        assertEquals(hash, "iyglkjgh");
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
