import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {
    public String readFile(File fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder string = new StringBuilder();
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        return string.toString();
    }

    public void writeFile(String str, String path) throws FileNotFoundException {
        // creates a file from file path parameter
        File file = new File(path);
        PrintWriter pw = new PrintWriter(file);
        pw.write(str);
        pw.close();

    }

    public static int countCharacters(String fileName) throws IOException {
        int chars = 0;
        BufferedReader charRead = new BufferedReader(new FileReader(fileName));
        while (charRead.ready()) {
            charRead.read();
            chars++;
        }
        charRead.close();
        return chars;
    }

    public static String getSHA1(File file) throws IOException {
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
            System.out.println(bytesToHex(mdbytes));
            return bytesToHex(mdbytes);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes)
            hex.append(String.format("%02x", b));
        return hex.toString();
    }

}