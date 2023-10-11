import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static String treeInput = "tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b : dirTest";
    private static String treeInput2 = "blob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt";
    private static String treeSHA = "ee8612eaba3e603c9cb58e1d26a0b95ee3477652"; // hashed from treeInput
    private static String emptyContentSha = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
    // private static String treeSHATest =
    // "665e866eb831bfaca69e1901a66df21c573ca4e9";
    // private static String SHAOfTreeContents =
    // "cbf35d6d75461742452f914b2c20745f2a5fb0d7";
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

        dirPath = "./objects/";
        oP = Paths.get(dirPath); // creates Path
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
        FileUtil.deleteFile("tempTree");
        FileUtil.deleteDirectory("testDir");
        FileUtil.deleteDirectory("objects");
        FileUtil.deleteFile("Tree");
        FileUtil.deleteDirectory("testDir");
        FileUtil.deleteDirectory("testDir2");
        FileUtil.deleteDirectory("testDir3");
        FileUtil.deleteDirectory("testDir4");
        FileUtil.deleteDirectory("testDir5");
        FileUtil.deleteDirectory("test");
        FileUtil.deleteDirectory("dirTest");
        FileUtil.deleteDirectory("test2.txt");

    }

    @Test
    @DisplayName("Testing if Tree is being created")
    void testCreateTree() throws Exception {
        File treeFile = new File("Tree");
        assertTrue(treeFile.exists());
        // String testing = "testing my tree";
        /* write testing to treefile */
        // FileUtil.writeFile(testing, "Tree");
        /// String fileContents = FileUtil.readFile(treeFile);
    }

    // @Test
    // @DisplayName("test if i can write to the tree")
    // public void testWriteToTree() throws Exception {
    // // Tree test = new Tree();

    // File treeFile = new File("Tree");
    // assertTrue(treeFile.exists());

    // // porgramatically creates Files
    // File testFile = new File("TEST_FILE");
    // testFile.createNewFile();

    // String fileContents = FileUtil.readFile(testFile);
    // String fileSha = FileUtil.getHash(fileContents);

    // String newline = "blob : " + fileSha + " : " + testFile.getName();
    // // tree.add(newline);

    // }

    // @Test
    // @DisplayName("Test if addToTree method works correctly")
    // void testAddToTree() throws Exception {
    // // Run the person's code
    // // Index i = new Index();
    // // i.initializeProject();
    // // Tree myTree = new Tree();
    // treeTest.add("tree : bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b");
    // treeTest.add("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt");
    // treeTest.generateBlob();
    // File checking = new File("./objects/" + FileUtil.getHash(
    // "blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f : file1.txt\ntree :
    // bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b"));
    // // checks to make sure Tree file has been saved to objects folder with
    // correct
    // // SHA
    // assertTrue(checking.exists());
    // Scanner scanner = new Scanner(checking);
    // String fileContents = scanner.useDelimiter("\\A").next();
    // scanner.close();

    // // checks to make sure file contents were correctly saved to the tree file
    // // (tree/blob log)
    // assertTrue(fileContents.contains("tree :
    // bd1ccec139dead5ee0d8c3a0499b42a7d43ac44b")
    // && fileContents.contains("blob : 81e0268c84067377a0a1fdfb5cc996c93f6dcf9f :
    // file1.txt"));
    // }

    @Test
    @DisplayName("tree add test")
    void testTreeAdd() throws Exception {

        FileUtil.deleteFile("tree");
        treeTest.initializeTree();

        treeTest.add(treeInput);
        treeTest.add(treeInput2);

        String treeFileContents = FileUtil.readFile(new File("Tree"));
        System.out.println("___");

        assertEquals(treeFileContents, treeInput + "\n" + treeInput2);
    }

    @Test
    @DisplayName("test add method with just a file name")
    void testAddByFileName() throws Exception {
        treeTest.initializeTree();
        treeTest.eraseTreeContents();
        treeTest.add(file1Name);
        String treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals("blob : 372ea08cab33e71c02c651dbc83a474d32c676ea : test", treeFileContents);

    }

    @Test
    @DisplayName("test remove")
    void testRemove() throws Exception {
        FileUtil.deleteFile("Tree");
        treeTest.initializeTree();
        treeTest.add(file1Name);
        File blobFile = new File("./objects/372ea08cab33e71c02c651dbc83a474d32c676ea");
        assertTrue(blobFile.exists());

        String treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals("blob : 372ea08cab33e71c02c651dbc83a474d32c676ea : test", treeFileContents);
        treeTest.remove(file1Name);
        treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals("", treeFileContents);
        System.out.println("___");

        // treeTest.initializeTree();
        // treeTest.eraseTreeContents();
        // treeTest.add(file1Name);
        // String treeFileContents = FileUtil.readFile(new File("Tree"));
        // assertEquals("blob : 372ea08cab33e71c02c651dbc83a474d32c676ea : test",
        // treeFileContents);
        // treeTest.remove(file1Name);
        // treeFileContents = FileUtil.readFile(new File("Tree"));
        // assertEquals("", treeFileContents);
        // File blobFile = new
        // File("./objects/372ea08cab33e71c02c651dbc83a474d32c676ea");
        // assertTrue(blobFile.exists());
    }

    @Test
    @DisplayName("tree remove test")
    void testTreeRemove() throws Exception {
        FileUtil.deleteFile("Tree");
        treeTest.initializeTree();
        treeTest.add(treeInput);
        // treeTest.add(treeInput2);
        String treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals(treeInput, treeFileContents);
        treeTest.remove(treeInput);
        treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals("", treeFileContents);
        System.out.println("___");

    }

    @Test
    @DisplayName("tree blob test")
    void testTreeBlobGeneration() throws Exception {

        FileUtil.deleteFile("Tree");
        treeTest.initializeTree();

        treeTest.add(treeInput2);

        String hash = treeTest.generateBlob();
        System.out.println("___");
        String treeFileContents = FileUtil.readFile(new File("Tree"));
        assertEquals(treeInput2, treeFileContents);
        // assertEquals(hash, treeSHA);
    }

    @Test
    @DisplayName("test addDirectory w empty directory")
    void testAddDirectoryEmpty() throws Exception {
        // treeTest = new Tree();
        treeTest.eraseTreeContents();
        String dp = "testDir2";
        FileUtil.deleteDirectory(dp);
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        String hash = treeTest.addDirectory(dp);
        System.out.println("___");

        assertEquals(hash, emptyContentSha);
    }

    @Test
    @DisplayName("test get contents of tree after adding dir with one empty file.")
    void testGetContents() throws Exception {
        treeTest.eraseTreeContents();
        String dp = "testDir2";
        FileUtil.deleteDirectory(dp);
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        File emptyfile = new File(dp + "/" + file1Name);
        emptyfile.createNewFile();
        String lineEntryTemp = "blob : " + emptyContentSha + " : " + emptyfile.getName();
        treeTest.addDirectory(dp);
        // String targetHash = "824460f8b7176a728f60334533886f48c3ae7382";
        String contents = treeTest.getContents();
        System.out.println("___");

        assertEquals(lineEntryTemp, contents);
    }

    @Test
    @DisplayName("test addDirectory w ONE non empty file")
    void testAddDirectorySingleFile() throws Exception {

        // treeTest = new Tree();
        treeTest.eraseTreeContents();
        String dp = "testDir2";
        FileUtil.deleteDirectory(dp);
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        // Tree isdfjs = new Tree();
        // isdfjs.initializeTree();
        // isdfjs.eraseTreeContents();
        FileUtil.createFile(dp + "/" + file1Name);
        FileUtil.writeFile(file1Text, dp + "/" + file1Name);

        // String lineEntryTemp = "blob : " + emptyContentSha + " : " +
        // emptyfile.getName();
        // isdfjs.eraseTreeContents();
        String hash = treeTest.addDirectory(dp);
        String ideal = "824460f8b7176a728f60334533886f48c3ae7382";
        // String contents = treeTest.getContents();
        System.out.println("___");
        FileUtil.deleteDirectory(dp);
        assertEquals(hash, ideal);
    }

    @Test
    @DisplayName("test addDirectory with dir w/ two text files")
    void testAddDirectoryTextFiles() throws Exception {
        treeTest.eraseTreeContents();
        String dp = "testDir2";
        FileUtil.deleteDirectory(dp);// deletes directort
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        FileUtil.createFile(dp + "/" + file1Name);
        FileUtil.writeFile(file1Text, dp + "/" + file1Name);

        FileUtil.createFile(dp + "/" + file2Name);
        FileUtil.writeFile(file2Text, dp + "/" + file2Name);
        String hash = treeTest.addDirectory(dp);
        String idealContent = "blob : 372ea08cab33e71c02c651dbc83a474d32c676ea : test\nblob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt";
        String actualContent = treeTest.getContents();
        System.out.println("___");
        assertEquals(idealContent, actualContent);
        String ideal = "2385cc7911a7be12b77d35b2a303b905aeb77655";
        assertEquals(hash, ideal);

    }

    @Test
    @DisplayName("test addDirectory with dir w/ empty folder")
    void testAddDirectoryEmptyFolder() throws Exception {
        treeTest.eraseTreeContents();
        String dp = "testDir3";
        FileUtil.deleteDirectory(dp);
        Path oP = Paths.get(dp); // creates Path
        FileUtil.deleteDirectory(dp);// deletes directort
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        String emptyFolder = dp + "/emptyFolder";
        oP = Paths.get(emptyFolder);
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        String hash = treeTest.addDirectory(emptyFolder);
        FileUtil.deleteDirectory(dp);
        System.out.println("___");
        assertEquals(hash, emptyContentSha);
    }

    @Test
    @DisplayName("test addDirectory with dir w/ one folder w/ two files")
    void testAddDirectoryFolderWithFiles() throws Exception {
        treeTest.eraseTreeContents();

        String dp = "testDir4";
        FileUtil.deleteDirectory(dp);// deletes directort
        Path oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        dp = "dirTest/twoFileFolder";
        FileUtil.deleteDirectory(dp);// deletes directort
        oP = Paths.get(dp); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        FileUtil.createFile(dp + "/" + file1Name);
        FileUtil.writeFile(file1Text, dp + "/" + file1Name);

        FileUtil.createFile(dp + "/" + file2Name);
        FileUtil.writeFile(file2Text, dp + "/" + file2Name);
        treeTest.addDirectory("dirTest");
        String idealContent = "tree : 2385cc7911a7be12b77d35b2a303b905aeb77655 : twoFileFolder";
        String actualContent = treeTest.getContents();
        System.out.println("___");
        assertEquals(idealContent, actualContent);
        // String ideal = "2385cc7911a7be12b77d35b2a303b905aeb77655";
        // assertEquals(hash, ideal);

    }

    @Test
    @DisplayName("test addDirectory with dir w/ two files, folder w/ two files")
    void testAddDirectoryFolderAndFiles() throws Exception {
        treeTest.eraseTreeContents();
        String dp = "testDir5";
        FileUtil.deleteDirectory(dp);
        Path oP = Paths.get(dp); // creates Path
        FileUtil.deleteDirectory(dp);// deletes directort
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        String twoFileFolder = dp + "/twoFileFolder";
        FileUtil.deleteDirectory(twoFileFolder);
        oP = Paths.get(twoFileFolder);
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        FileUtil.createFile(twoFileFolder + "/" + file1Name);
        FileUtil.writeFile(file1Text, twoFileFolder + "/" + file1Name);

        FileUtil.createFile(twoFileFolder + "/" + file2Name);
        FileUtil.writeFile(file2Text, twoFileFolder + "/" + file2Name);
        FileUtil.createFile(dp + "/" + file1Name);
        FileUtil.writeFile(file1Text, dp + "/" + file1Name);

        FileUtil.createFile(dp + "/" + file2Name);
        FileUtil.writeFile(file2Text, dp + "/" + file2Name);

        String idealContent = "blob : 372ea08cab33e71c02c651dbc83a474d32c676ea : test\nblob : 98fa98f725d269076d1af0a978a308b6df672673 : test2.txt\ntree : 2385cc7911a7be12b77d35b2a303b905aeb77655 : twoFileFolder";
        treeTest.addDirectory(dp);
        String treeContent = treeTest.getContents();
        assertEquals(idealContent, treeContent);

    }

    // @Test
    @DisplayName("tests checkout")
    public void testCheckoutWithOneFile() throws Exception {
        Tree tree = new Tree();
        tree.eraseTreeContents();
        FileUtil.createFile("file1name");
        FileUtil.writeFile(file1Text, "file1Name");
        File file1 = new File("file1Name");
        assert (file1.exists());
        tree.add("file1Name");
        // System.out.println(FileUtil.readFile2("Tree"));
        Commit firstSave = new Commit("Arden", "first commit!!");
        FileUtil.deleteFile("file1Name");
        String commitSha = firstSave.getHash();
        String targetHash = "824460f8b7176a728f60334533886f48c3ae7382";
        File blob = new File("objects/372ea08cab33e71c02c651dbc83a474d32c676ea");
        assertTrue(blob.exists());
        String hashFromCommit = tree.checkout(commitSha);
        assertEquals(targetHash, hashFromCommit);
        ; // finds blob from sha

    }
}
