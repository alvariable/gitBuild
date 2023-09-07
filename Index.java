import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Index {
    public void initializeProject() {
        // creates empty file named 'index'
        // creates a directory named 'objects'
    }

    public void addBlob(String filename) throws IOException {
        // creates a blob for the given filename
        Blob b = new Blob(filename);
        // saves the filename and Blob SHA1 as key/value pair
        // appends the pair to a list in a file named 'index'
    }

    public void removeBlob(String fileName) {
        // removes the filename and blob SHA1 from the key/value pair
        // deletes the blob saved in the 'objects' folder
    }

}
