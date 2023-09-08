import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Blob {
    private String SHA1;
    File originalFile;
    String originalPath;
    // HashMap<>;

    public Blob(String filePathString) throws IOException {
        originalFile = new File(filePathString);
        originalPath = filePathString;
        SHA1 = getSHA1(originalFile);
    }

    public String getSHA1(File file) throws IOException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(file);
            byte[] dataBytes = new byte[1024];
            int n = 0;
            while (n != fis.read(dataBytes)) {
                md.update(dataBytes, 0, n);
            }
            byte[] mdbytes = md.digest();
            fis.close();
            return bytesToHex(mdbytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes)
            hex.append(String.format("%02x", b));
        return hex.toString();
    }

    public void add(String newPath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(originalPath));
        StringBuilder string = new StringBuilder();
        // convert original file to String A
        // convert string A to new file with name SHA1
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        String a = string.toString();
        // write a new file to disk inside an 'object class'
        File copy = new File(newPath + SHA1); // new file name is the SHA1,
        PrintWriter pw = new PrintWriter(copy);
        pw.write(a); // file contains the same content as original

        pw.close();
    }

    public void remove(String path) throws IOException {
        Files.deleteIfExists(path);

    }

    public String getSHA1Code() throws IOException {
        System.out.println(SHA1);
        return SHA1;
    }

}