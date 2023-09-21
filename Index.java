import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

public class Index {
    File index;
    HashMap<String, String> gitMap = new HashMap<String, String>();
    String objectPath;

    public void initializeProject() throws IOException {
        objectPath = "./objects/";
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        /**
         * Note:
         * index is not supposed to be inside objects folder
         */
        Path iP = Paths.get("index");
        if (!Files.exists(iP)) {
            index = new File("index");
            index.createNewFile();
        }
    }

    public void addBlob(String filename) throws Exception {
        // creates a blob for the given filename
        Blob b = new Blob(filename);
        String hash = b.getSHA1(); // creates hash var that stores SHA1
        // saves the filename and Blob SHA1 as key/value pair
        gitMap.put(filename, hash);
        // appends the pair to a list in a file named 'index'
        // PrintWriter pw = new PrintWriter(index);
        // pw.print(filename + " : " + hash); // prints the hash : filename
        // pw.close(); // closes printwriter
        writeIndex();
        b.add(objectPath + "/"); // adds blob to directory
    }

    public void removeBlob(String fileName) throws IOException {
        // checks if blob exists
        // removes the filename and blob SHA1 from the key/value pair
        gitMap.remove(fileName);
        // deletes the blob saved in the 'objects' folder
        // rewrites the index
        writeIndex();
    }

    public void writeIndex() throws IOException {
        FileOutputStream fos = new FileOutputStream("index");
        File file = new File("index");
        PrintWriter pw = new PrintWriter(file);
        Set<String> fileSet = gitMap.keySet();

        int i = 0;
        for (String k : fileSet) {
            if (i > 0)
                pw.print("\n");
            pw.print(k);
            pw.print(" : " + gitMap.get(k));

            i++;

        }
        pw.close();
        fos.close();
    }

}
