import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob {
    private String SHA1;
    File originalFile;
    String originalPath;
    String fileContents;
    // HashMap<>;

    public Blob(String filePathString) throws IOException, NoSuchAlgorithmException {
        originalFile = new File(filePathString);
        originalPath = filePathString;
        fileContents = readFile(originalFile);
        SHA1 = getHash(fileContents);
    }

    public String getSHA1() {
        return SHA1;
    }

    public void add(String objectPath) throws FileNotFoundException {
        writeFile(fileContents, objectPath + SHA1);
    }

    public String getHash(String fileContents) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] MessageDigest = md.digest(fileContents.getBytes());
        BigInteger no = new BigInteger(1, MessageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;

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