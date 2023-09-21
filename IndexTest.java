import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class IndexTest {
    @Test
    void testAddBlob() throws Exception {

        Index i = new Index();
        i.initializeProject();

        i.addBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");

        i.writeIndex();

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        Path fileIndex = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        String check = Blob.readFile(file);

        assertEquals(check.contains("hi.txt"), true); // file needs to be long path to work

    }

    @Test
    void testInitializeProject() throws IOException {

        Index i = new Index();

        i.initializeProject();

        File objects = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects");
        File index = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects");
        assertEquals(objects.exists(), true);
        assertEquals(index.exists(), true);

    }

    @Test
    void testRemoveBlob() throws Exception {

        Index i = new Index();
        i.initializeProject();

        i.addBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");
        i.addBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/testFile.txt");

        i.writeIndex();

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        Path fileIndex = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        String check = Blob.readFile(file);

        assertEquals(check.contains("hi.txt"), check.contains("testFile.txt")); // file needs to be long path to work
        assertEquals(true, check.contains("testFile.txt"));

        i.removeBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");

        check = Blob.readFile(file);

        assertEquals(check.contains("hi.txt"), false); // file needs to be long path to work
        assertEquals(true, check.contains("testFile.txt"));

    }

    @Test
    void testWriteIndex() throws Exception {

        Index i = new Index();
        i.initializeProject();

        i.addBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");
        i.addBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/testFile.txt");

        i.writeIndex();

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        Path fileIndex = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        String check = Blob.readFile(file);

        assertEquals(check.contains("hi.txt"), check.contains("testFile.txt")); // file needs to be long path to work
        assertEquals(true, check.contains("testFile.txt"));

    }
}
