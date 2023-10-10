import static org.junit.jupiter.api.Assertions.assertEquals;

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


    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        String objectPath = "./objects/";
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        FileUtil.deleteFile("index");
        FileUtil.deleteDirectory("objects");
        FileUtil.deleteFile("Tree");
        FileUtil.deleteFile("test2.txt");
        FileUtil.deleteFile("test");

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
        commit = new Commit("Chris Weng", "Test commit");
        String commitContent = commit.write();
        String idealContent = FileUtil.getHash("") + "\n\n\nChris Weng\n" + commit.getDate() + "\nTest commit";
        assertEquals(idealContent, commitContent);
    }

    @Test
    @DisplayName("tests add next sha to parent commit")
    public void testAddCurrentCommitToPreviousCommit() throws Exception {
        Commit parent = new Commit("Arden C. Doyle", "parent commit");
        //gets content from parent commit
        String parentSha = parent.getHash();
        String parentContent = FileUtil.readFile2("./objects/" + parentSha);
        Commit child = new Commit(parentSha, "Arden C. Doyle", "child commit");
        String target = emptyContentSha + "\n\n" + child.getHash() + "\nArden C. Doyle\n" + child.getDate() + "\nparent commit" ;
        parentContent = FileUtil.readFile2("./objects/" + parentSha);
        assertEquals(target, parentContent);
    }
    
    @Test
    @DisplayName("tests add next sha to parent commit")
    public void testAddThirdGenShaToSecondGenCommit() throws Exception {
        Commit parent = new Commit("Arden C. Doyle", "parent commit");
        //gets content from parent commit
        String parentSha = parent.getHash();
        String parentContent = FileUtil.readFile2("./objects/" + parentSha);
        Commit child = new Commit(parentSha, "Arden C. Doyle", "child commit");
        String target = emptyContentSha + "\n\n" + child.getHash() + "\nArden C. Doyle\n" + child.getDate() + "\nparent commit" ;
        parentContent = FileUtil.readFile2("./objects/" + parentSha);
        assertEquals(target, parentContent);
    }
    @Test
    @DisplayName("Tests create commit with two files?")
    public void testOneCommit() {
        // create a test which create 1 commit
        // include at least 2 files into the commit
        // verify the commit has correct tree, prev, and next sha1s
    }

    @Test
    @DisplayName("Tests create commit with two files?")
    public void testTwoCommits() {
        // create a test which creates 2 commits
        // include at least 2 files into the commit
        // verify the commits has correct tree, prev, and next sha1s
        // verify the Tree contents are correct
    }

    @Test
    @DisplayName("Tests create commit with two files?")
    public void testFourCommits() {
        // create a test which creates 4 commits
        // each commit must contain at least 2 new files, all of which have unique file data
        // 2 commits must contain at least one new folder
        //test tree contents, commit contents for prev and next trees
    }

}
