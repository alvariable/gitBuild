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
        i.addBlob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        Path fileIndex = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/index");

        String check = Blob.readFile(file);

        assertEquals(check.contains("hi.txt"), true);

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
    void testRemoveBlob() {

    }

    @Test
    void testWriteIndex() {

    }
}
