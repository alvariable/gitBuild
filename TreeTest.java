import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Utilities.FileUtils;

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
    static File file1;
    static File file2;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        file1 = new File("test1.txt");
        file1.createNewFile();
        PrintWriter pw = new PrintWriter(file1);
        pw.write(file1Text);
        pw.close();
        file2 = new File("test1.txt");
        file2.createNewFile();
        pw = new PrintWriter(file1);
        pw.write(file1Text);
        pw.close();

        // delete all files if still around

        // FileUtilss.deleteFile("index");
        // FileUtilss.deleteDirectory("objects");

        // set up files to use as test
        // FileUtils.createFile(file1Name);
        /// FileUtils.writeFile(file1Text, file1Name);

        // FileUtils.createFile(file2Name);
        // FileUtils.writeFile(file2Text, file2Name);

    }

    // clears out everything post tests
    @AfterAll
    static void tearDownAfterClass() throws Exception {

        // delete all files

        file1.delete();

        // FileUtils.deleteFile("index");
        // FileUtils.deleteDirectory("objects");

    }

    @Test
    @DisplayName("tree add test")
    void testTreeAdd() throws Exception {

        // FileUtils.deleteFile("tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);
        treeTest.add(treeInput2);

        String treeFileContents = FileUtil.readFile2("Tree");

        assertEquals("File contents of tree don't match",
                treeFileContents,
                treeInput + "\n" + treeInput2);
    }

    @Test
    @DisplayName("tree remove test")
    void testTreeRemove() throws Exception {

        // FileUtils.deleteFile("Tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);
        treeTest.add(treeInput2);

        treeTest.remove(treeInput);

        String treeFileContents = FileUtil.readFile2("Tree");
        assertEquals("File contents of tree don't match",
                treeFileContents,
                treeInput2);
    }

    @Test
    @DisplayName("tree blob test")
    void testTreeBlobGeneration() throws Exception {

        // FileUtils.deleteFile("Tree");
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
