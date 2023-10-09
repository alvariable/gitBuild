import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Blob {
    private String SHA1;
    File originalFile;
    String originalPath;
    String fileContents;
    String objectPath = "./objects/";
    // HashMap<>;

    public Blob(String filePathString) throws Exception {
        originalFile = new File(filePathString);
        originalPath = filePathString;
        fileContents = readFile(originalFile);
        SHA1 = createHash(fileContents);
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
    }

    public String getSHA1() {
        return SHA1;
    }

    public String getContents() {
        return fileContents;
    }

    public void add(String objectPath) throws Exception {
        // makes directory if no exist
        // objectPath = "./objects/";
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path
        writeFile(fileContents, objectPath + SHA1);
    }

    public String createBlob() throws FileNotFoundException {
        writeFile(fileContents, objectPath + SHA1);
        return SHA1;
    }

    private String createHash(String fileContents) throws Exception {
        return FileUtil.getHash(fileContents);
    }

    private String readFile(File fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder string = new StringBuilder();
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        return string.toString();
    }

    private void writeFile(String str, String path) throws FileNotFoundException {
        // creates a file from file path parameter, file name = sha1
        File file = new File(path);
        PrintWriter pw = new PrintWriter(file);
        pw.write(str);
        pw.close();
    }
}