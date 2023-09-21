import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeTest {
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

}
