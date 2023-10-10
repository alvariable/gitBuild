import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CommitUnitTest {
    static Commit commit;
    private static String emptyContentSha = "da39a3ee5e6b4b0d3255bfef95601890afd80709";
    private static String file1Name = "test";
    private static String file1Text = "text"; // 372ea08cab33e71c02c651dbc83a474d32c676ea

    private static String file2Name = "test2.txt";
    private static String file2Text = "dsfhaidsfonfongorlgjrwlkfn"; // 98fa98f725d269076d1af0a978a308b6df672673

    private static String file3Name = "folderWithStuff/test3";
    private static String file3Text = "WHAT IS EVEN GOING ON? PLEASE."; // 9f7af4b0faced81bae7630d5ac2b90f9f6adee3d

    private static String file4Name = "folderWithStuff/test4";
    private static String file4Text = "alksdjf"; // ce8ce25f715fb7f52ae357afc7a5d308f10db53a

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        String objectPath = "./objects/";
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        FileUtil.createFile(file1Name);
        FileUtil.writeFile(file1Text, file1Name);

        FileUtil.createFile(file2Name);
        FileUtil.writeFile(file2Text, file2Name);

        String folderPath = "folderWithStuff";
        oP = Paths.get(folderPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        FileUtil.createFile(file3Name);
        FileUtil.writeFile(file3Text, file3Name);

        FileUtil.createFile(file4Name);
        FileUtil.writeFile(file4Text, file4Name);

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");
        FileUtil.deleteFile("Tree");
        FileUtil.deleteFile("test2.txt");
        FileUtil.deleteFile("test");
        FileUtil.deleteDirectory("folderWithStuff");
        FileUtil.deleteFile("test3");
        FileUtil.deleteFile("test4");
        FileUtil.deleteFile("HEAD");

    }

    @Test
    @DisplayName("Test get date")
    public void testConstructor() throws Exception {
        commit = new Commit("Chris Weng", "Test commit");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = new Date();
        String date = formatter.format(today);
        assertEquals(date, commit.getDate());
    }

    @Test
    void testCreateHash() {

    }

    @Test
    void testGetDate() {

    }

    @Test
    void testGetFileName() {

    }

    @Test
    void testReadFile() {

    }

    @Test
    @DisplayName("Test write method")
    public void testWrite() throws Exception {
        Tree draftIndex = new Tree();
        commit = new Commit("Chris Weng", "Test commit");
        String commitContent = commit.write();
        String idealContent = FileUtil.getHash(draftIndex.getContents()) + "\n\n\nChris Weng\n" + commit.getDate()
                + "\nTest commit";
        assertEquals(idealContent, commitContent);
    }

    public void testCommit(Commit commit) {

    }

    @Test
    @DisplayName("tests add next sha to parent commit")
    public void testAddCurrentCommitToPreviousCommit() throws Exception {
        Commit parent = new Commit("Arden C. Doyle", "parent commit");
        // gets content from parent commit
        String parentSha = parent.getHash();
        String parentContent = FileUtil.readFile2("./objects/" + parentSha);
        Commit child = new Commit(parentSha, "Arden C. Doyle", "child commit");
        String target = emptyContentSha + "\n\n" + child.getHash() + "\nArden C. Doyle\n" + child.getDate()
                + "\nparent commit";
        parentContent = FileUtil.readFile2("./objects/" + parentSha);
        assertEquals(target, parentContent);
    }

    @Test
    @DisplayName("tests add next sha to parent commit")
    public void testAddThirdGenShaToSecondGenCommit() throws Exception {
        Tree draftIndex = new Tree();
        Commit parent = new Commit("Arden C. Doyle", "parent commit");
        // gets content from parent commit
        String parentSha = parent.getHash();
        String parentContent = FileUtil.readFile2("./objects/" + parentSha);
        Commit child = new Commit(parentSha, "Arden C. Doyle", "child commit");
        String target = FileUtil.getHash(draftIndex.getContents()) + "\n\n" + child.getHash() + "\nArden C. Doyle\n"
                + child.getDate()
                + "\nparent commit";
        parentContent = FileUtil.readFile2("./objects/" + parentSha);
        assertEquals(target, parentContent);
    }

    @Test
    @DisplayName("Tests create commit with two files?")
    public void testOneCommit() throws Exception {
        Tree draftIndex = new Tree();
        // draftIndex.add(file1Name);
        draftIndex.add(file2Name);
        Commit commitWithTwoFiles = new Commit("Arden C. Doyle", "test commit with one files");
        String idealContent = FileUtil.getHash(draftIndex.getContents()) + "\n\n\nArden C. Doyle\n"
                + commitWithTwoFiles.getDate()
                + "\ntest commit with one files";
        String commitContent = commitWithTwoFiles.write();
        assertEquals(idealContent, commitContent);
        // create a test which create 1 commit
        // include at least 2 files into the commit
        // verify the commit has correct tree, prev, and next sha1s
    }

    @Test
    @DisplayName("Tests create commit with two files?")
    public void testTwoCommits() throws Exception {
        // create a test which creates 2 commits
        // include at least 2 files into the commit
        // verify the commits has correct tree, prev, and next sha1s
        // verify the Tree contents are correct
        Tree draftIndex = new Tree();
        draftIndex.add(file1Name);
        draftIndex.add(file2Name);
        Commit commitWithTwoFiles = new Commit("Arden C. Doyle", "test commit with two files");
        String idealContent = FileUtil.getHash(draftIndex.getContents()) + "\n\n\nArden C. Doyle\n"
                + commitWithTwoFiles.getDate()
                + "\ntest commit with two files";
        String commitContent = commitWithTwoFiles.write();
        assertEquals(idealContent, commitContent);
    }

    @Test
    @DisplayName("Tests create commit with one folder?")
    public void testFourCommits() throws Exception {
        // create a test which creates 4 commits
        // each commit must contain at least 2 new files, all of which have unique file
        // data
        // 2 commits must contain at least one new folder
        // test tree contents, commit contents for prev and next trees
        Tree draftIndex = new Tree();
        draftIndex.add(file1Name);
        draftIndex.add(file2Name);
        draftIndex.addDirectory("folderWithTree");
        // draftIndex.add(file3Name);
        // draftIndex.add(file4Name);
        Commit commitWithTwoFiles = new Commit("Arden C. Doyle", "test commit with one folder");
        String idealContent = FileUtil.getHash(draftIndex.getContents()) + "\n\n\nArden C. Doyle\n"
                + commitWithTwoFiles.getDate()
                + "\ntest commit with one folder";
        String commitContent = commitWithTwoFiles.write();
        assertEquals(idealContent, commitContent);
    }

    public void commitWithFolder() throws Exception {

    }
}
