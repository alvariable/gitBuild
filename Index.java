import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;

public class Index {
    File index;
    HashMap<String, String> gitMap = new HashMap<String, String>();
    String objectPath;

    public void initializeProject() {
        objectPath = "./gitBuild/Objects";
        index = new File(objectPath + "/index");
    }

    public void addBlob(String filename) throws IOException, NoSuchAlgorithmException {
        // creates a blob for the given filename
        Blob b = new Blob(filename);
        String hash = b.getSHA1(); // creates hash var that stores SHA1
        // saves the filename and Blob SHA1 as key/value pair
        gitMap.put(filename, hash);
        // appends the pair to a list in a file named 'index'
        PrintWriter pw = new PrintWriter(index);
        pw.print(filename + " : " + hash); // prints the hash : filename
        pw.close(); // closes printwriter
        b.add(objectPath + "/"); // adds blob to directory
    }

    public void removeBlob(String fileName) throws IOException {
        // checks if blob exists
        // removes the filename and blob SHA1 from the key/value pair
        gitMap.remove(fileName);
        // deletes the blob saved in the 'objects' folder
        // rewrites the file
        writeIndex();
    }

    public void writeIndex() throws IOException {
        FileOutputStream fos = new FileOutputStream(
                objectPath);
        File file = new File(objectPath + "/index");
        PrintWriter pw = new PrintWriter(file);
        Set<String> fileSet = gitMap.keySet();
        for (String k : fileSet) {
            pw.print(k);
            pw.println(" : " + gitMap.get(k));
        }
        pw.close();
        fos.close();
    }

}
