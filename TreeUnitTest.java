import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//import Utilities.FileUtils;

public class TreeUnitTest {
    private static Tree treeTest;

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

        // folder = new File("./Test");
        // folder.mkdir();
        treeTest = new Tree();

        dirPath = "./dirTest/";
        Path oP = Paths.get(dirPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        // folderPath = folder.getAbsolutePath();

        // set up files to use as test
        FileUtil.createFile(file1Name);
        // FileUtil.writeFile(file1Text, file1Name);

        FileUtil.createFile(file2Name);
        FileUtil.writeFile(file2Text, file2Name);

    }

    @Test
    @DisplayName("Testing if Tree is being created")
    void testCreateTree() throws Exception {
        Tree newTree = new Tree();

        File treeFile = new File("Tree");

        assertTrue(treeFile.exists());
        String testing = "testing my tree";
        /* write testing to treefile */
        FileUtil.writeFile(testing, "Tree");

        String fileContents = FileUtil.readFile(treeFile);

    }

    @Test
    @DisplayName("test if i can write to the tree")
    public void testWriteToTree() throws Exception {
        Tree test = new Tree();

        File treeFile = new File("Tree");
        assertTrue(treeFile.exists());

        // porgramatically creates Files
        File testFile = new File("TEST_FILE");
        testFile.createNewFile();

        String fileContents = FileUtil.readFile(testFile);
        String fileSha = FileUtil.getHash(fileContents);

        String newline = "blob : " + fileSha + " : " + testFile.getName();
        // tree.add(newline);

    }

    // @Test
    // @DisplayName("Test if addToTree method works correctly")
    // void testAddToTree() throws Exception {
    //     // Run the person's code
    //     // Index i = new Index();
    //     // i.initializeProject();
    //     // Tree myTree = new Tree();
    //     treeTest.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
    //     treeTest.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
    //     treeTest.generateBlob();
    //     File checking = new File("./objects/" + FileUtil.getHash(
    //             "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt\ntree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b"));
    //     // checks to make sure Tree file has been saved to objects folder with correct
    //     // SHA
    //     assertTrue(checking.exists());
    //     Scanner scanner = new Scanner(checking);
    //     String fileContents = scanner.useDelimiter("\\A").next();
    //     scanner.close();

    //     // checks to make sure file contents were correctly saved to the tree file
    //     // (tree/blob log)
    //     assertTrue(fileContents.contains("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b")
    //             && fileContents.contains("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt"));
    // }

    // clears out everything post tests
    @AfterAll
    static void tearDownAfterClass() throws Exception {

        // // delete all files
        FileUtil.deleteFile("tree");
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
        assertEquals(treeFileContents, treeInput + "\n" + treeInput2);
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
        assertEquals(treeFileContents, treeInput2);
    }

    @Test
    @DisplayName("tree blob test")
    void testTreeBlobGeneration() throws Exception {

        FileUtil.deleteFile("Tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);

        String hash = treeTest.generateBlob();
        assertEquals(hash, treeSHA);
        // Check blob exists in the objects folder
        // File file_junit1 = new File("objects/" + treeSHA);
        // assertTrue("Blob file to add not found", file_junit1.exists());

        // check file contents
        // String treeFileContents = FileUtil.readFile(new File("objects/" + treeSHA));
        // assertEquals("File contents of treeBlob don't match file contents",
        // treeFileContents, treeInput);
    }

    @Test
    @DisplayName("test addDirectory w empty directory")
    void testAddDirectoryEmpty() throws Exception {
        // create directory
        // Directory temp = new Directory();
        File f = new File("Test");
        f.mkdir();
        treeTest.addDirectory("Test");
        String hash = treeTest.generateBlob();
        assertEquals(hash, emptyContentSha);
    }

    // @Test
    // @DisplayName("test addDirectory w one empty file")
    // void testAddDirectorySingleEmptyFile() throws Exception {
    //     String dp = "dirTestTester";
    //     Path oP = Paths.get(dp); // creates Path
    //     if (!Files.exists(oP)) // creates file if directory doesnt exist
    //         Files.createDirectories(oP); // creates Path
    //     // Tree isdfjs = new Tree();
    //     // isdfjs.initializeTree();
    //     // isdfjs.eraseTreeContents();
    //     File emptyfile = new File(dp + "/" + file1Name);
    //     emptyfile.createNewFile();
    //     String lineEntryTemp = "blob : " + emptyContentSha + " : " + emptyfile.getName();
    //     // isdfjs.eraseTreeContents();
    //     String hash = treeTest.addDirectory(dp);
    //     String ideal = "ecc3e337d8b096e74769e803033b85cd334b5ad0";
    //     // String contents = treeTest.getContents();
    //     assertEquals(ideal, hash);
    //     // tests hash
    //     // assertEquals(ideal, hash);
    //     // dirPath = "./dirTest/";
    //     // Path oP = Paths.get(dirPath); // creates Path
    //     // if (!Files.exists(oP)) // creates file if directory doesnt exist
    //     // Files.createDirectories(oP); // creates Path
    //     // File directory = new File(dirPath);
    //     // if (!directory.exists()) // creates file if directory doesnt exist
    //     // directory.mkdirs(); // creates Path

    //     // assertTrue(directory.exists());

    //     // File emptyFile = new File(dirPath + "/emptyFile");

    //     // treeTest.addDirectory(dirPath);
    //     // String targetHash = "65c6d1eb9e22464ced06c2f6185c86736572331d";
    //     // String treeFileContents = FileUtil.readFile(new File("Tree"));
    //     // String myTreeHash = FileUtil.getHash(treeFileContents);
    //     // assertEquals(targetHash, myTreeHash);
    // }

    // @Test
    // @DisplayName("test get contents of tree after adding dir with one empty
    // file")
    // void testGetContentsForAddDirectorySingleEMptyFIle() throws Exception {
    // treeTest = new Tree();
    // File d = new File("Test");
    // d.mkdir();
    // File emptyFile = new File("Test/emptyFile");
    // String targetEntryLine = "blob : da39a3ee5e6b4b0d3255bfef95601890afd80709 :
    // emtpyFile";
    // treeTest.addDirectory("Test");
    // treeTest.generateBlob();
    // String generatedEntryLine = treeTest.getContents();
    // assertEquals(generatedEntryLine, targetEntryLine);
    // }

    @Test
    @DisplayName("test get contents of tree after adding dir with one empty file.")
    void testGetContents() throws Exception {
        String dp = "dirTestTester";
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        // Tree isdfjs = new Tree();
        // isdfjs.initializeTree();
        // isdfjs.eraseTreeContents();
        File emptyfile = new File(dp + "/" + file1Name);
        emptyfile.createNewFile();
        String lineEntryTemp = "blob : " + emptyContentSha + " : " + emptyfile.getName();
        // isdfjs.eraseTreeContents();
        String hash = treeTest.addDirectory(dp);
        String ideal = "ecc3e337d8b096e74769e803033b85cd334b5ad0";
        String contents = treeTest.getContents();
        assertEquals(lineEntryTemp, contents);
        // tests hash
        // assertEquals(ideal, hash);
    }

    @Test
    @DisplayName("test addDirectory w ONE empty file")
    void testAddDirectorySingleFile() throws Exception {
        String dp = "./dirTestTester/";
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        File emptyFile = new File(dp + "empty");
        String lineEntryTemp = "blob : " + emptyContentSha + " : " + emptyFile.getName();
        // PrintWriter pw = new PrintWriter(file1);
        // pw.print("");
        // pw.close();
        // pw = new PrintWriter(file2);
        // pw.print(file2Text);
        // pw.close();
        // FileUtil.writeFile(file1Text, file1Name);
        // FileUtil.writeFile(file2Text, file2Name);

        FileUtil.createFile("Test/" + file1Name);
        FileUtil.writeFile("Test/" + file1Text, file1Name);
        FileUtil.createFile("Test/" + file2Name);
        // FileUtil.writeFile("Test/" + file2Text, file2Name);
        // treeTest.add(treeInput);
        // treeTest.add(treeInput2);
        // isdfjs.eraseTreeContents();
        // String hash = treeTest.addDirectory(dp);
        String ideal = "27d2b1fb06d081719e37704cebe9d677b93fe964";
        assertEquals(ideal, FileUtil.getHash(lineEntryTemp));
        String hash = treeTest.addDirectory("Test");
        assertEquals(hash, emptyContentSha);
    }

    // @Test
    // @DisplayName("test addDirectory w just files")
    // void testAddDirectoryFiles() throws Exception {

    // File file1 = new File(dirPath + file1Name);
    // File file2 = new File(dirPath + file2Name);
    // PrintWriter pw = new PrintWriter(file1);
    // pw.print(file1Text);
    // pw.close();
    // pw = new PrintWriter(file2);
    // pw.print(file2Text);
    // pw.close();
    // // FileUtil.writeFile(file1Text, file1Name);
    // // FileUtil.writeFile(file2Text, file2Name);

    // // FileUtil.createFile("Test/" + file1Name);
    // // FileUtil.writeFile("Test/" + file1Text, file1Name);
    // // FileUtil.createFile("Test/" + file2Name);
    // // FileUtil.writeFile("Test/" + file2Text, file2Name);
    // // treeTest.add(treeInput);
    // // treeTest.add(treeInput2);
    // String hash = treeTest.addDirectory(dirPath);
    // String ideal = "2385cc7911a7be12b77d35b2a303b905aeb77655";
    // assertEquals(ideal, hash);
    // }

    // @Test
    // @DisplayName("test addDirectory w just files")
    // void testAddDirectoryEmptyFile() throws Exception {

    // // File file1 = new File(dirPath + file1Name);
    // File emptyFileeee = new File(dirPath + "sarahhhhh");
    // // FileUtil.writeFile(file1Text, file1Name);
    // // FileUtil.writeFile(file2Text, file2Name);

    // // FileUtil.createFile("Test/" + file1Name);
    // // FileUtil.writeFile("Test/" + file1Text, file1Name);
    // // FileUtil.createFile("Test/" + file2Name);
    // // FileUtil.writeFile("Test/" + file2Text, file2Name);
    // // treeTest.add(treeInput);
    // // treeTest.add(treeInput2);
    // treeTest.eraseTreeContents();
    // String hash = treeTest.addDirectory(dirPath);
    // String ideal = "24f4c1129102d85cb2b14c4d6cf6b358f8e933b9";
    // assertEquals(ideal, hash);

    // }

    // @Test
    // @DisplayName("test addDirectory w directory with an empty folder")
    // void testAddDirectoryFolder() throws Exception {
    // // create directory
    // // Directory temp = new Directory();
    // File f = new File("Test");
    // f.mkdir();
    // File emptyFolder = new File("Test/emptyFolder");
    // emptyFolder.mkdir();
    // String idealHash = "063f162a2402393fc74c4dc4c1a91f5b45d0debf";
    // // String hash = treeTest.addDirectory("Test");
    // String hash = treeTest.generateBlob();
    // assertEquals(hash, emptyContentSha);
    // }
    // // @Test
    // // public void Testtest() throws Exception {
    // // Path p = Paths.get("./bin");
    // // String path = p.toFile().getAbsolutePath();
    // // File f = new File(path);
    // // String[] str = f.list();
    // // String a = "";
    // // for (String b : str) {
    // // a += b + "\n";
    // // }
    // // throw new Exception(a);
    // // }
}
