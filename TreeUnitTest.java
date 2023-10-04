import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import Utilities.FileUtils;

public class TreeUnitTest {
    private Tree treeTest = new Tree();

    private static String file1Name = "test";
    private static String file1Text = "text"; // 372ea08cab33e71c02c651dbc83a474d32c676ea

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn"; // 98fa98f725d269076d1af0a978a308b6df672673

    private static String treeInput = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b";
    private static String treeInput2 = "blob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt";
    private static String treeSHA = "ee8612eaba3e603c9cb58e1d26a0b95ee3477652"; // hashed from treeInput
    private static String emptyContentSha = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
    private static String treeSHATest = "665e866eb831bfaca69e1901a66df21c573ca4e9";
    private static String SHAOfTreeContents = "cbf35d6d75461742452f914b2c20745f2a5fb0d7";
    static File folder;
    static String dirPath;

    // presumably creates files to test with
    @BeforeAll
    static void setUpBeforeClass() throws Exception {

        // delete all files if still around

        // FileUtils.deleteFile("index");
        // FileUtils.deleteDirectory("objects");

        folder = new File("./Test");
        folder.mkdir();

        dirPath = "./dirTest/";
        Path oP = Paths.get(dirPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        // folderPath = folder.getAbsolutePath();

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
    @DisplayName("test addDirectory w ONE empty file")
    void testAddDirectorySingleFile() throws Exception {

        File file1 = new File(dirPath +  file1Name);
      //  PrintWriter pw = new PrintWriter(file1);
        //pw.print(file1Text);
        //pw.close();
        // pw = new PrintWriter(file2);
        // pw.print(file2Text);
        // pw.close();
        // FileUtil.writeFile(file1Text, file1Name);
        // FileUtil.writeFile(file2Text, file2Name);

        // FileUtil.createFile("Test/" + file1Name);
        // FileUtil.writeFile("Test/" + file1Text, file1Name);
        // FileUtil.createFile("Test/" + file2Name);
        // FileUtil.writeFile("Test/" + file2Text, file2Name);
        // treeTest.add(treeInput);
        // treeTest.add(treeInput2);
        String hash = treeTest.addDirectory(dirPath);
        String ideal = "824460f8b7176a728f60334533886f48c3ae7382";
        assertEquals(ideal, hash);
    }

    @Test
    @DisplayName("test addDirectory w just files")
    void testAddDirectoryFiles() throws Exception {

        File file1 = new File(dirPath +file1Name);
        File file2 = new File(dirPath + file2Name);
        PrintWriter pw = new PrintWriter(file1);
        pw.print(file1Text);
        pw.close();
        pw = new PrintWriter(file2);
        pw.print(file2Text);
        pw.close();
        // FileUtil.writeFile(file1Text, file1Name);
        // FileUtil.writeFile(file2Text, file2Name);

        // FileUtil.createFile("Test/" + file1Name);
        // FileUtil.writeFile("Test/" + file1Text, file1Name);
        // FileUtil.createFile("Test/" + file2Name);
        // FileUtil.writeFile("Test/" + file2Text, file2Name);
        // treeTest.add(treeInput);
        // treeTest.add(treeInput2);
        String hash = treeTest.addDirectory(dirPath);
        String ideal = "ada07138b2f4f7ffdc157be46dd1c59ddc0a96c2";
        assertEquals(ideal, hash);
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
