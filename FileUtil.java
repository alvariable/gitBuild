import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtil {
    public static String readFile(File fileName) throws IOException, URISyntaxException {
        String fN = fileName.getName();
        return readFile2(fN);
    }

    public static String readFile2(String f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            sb.append((char) br.read());
        }
        br.close();
        return sb.toString();
    }

    public static void writeFile(String str, String path) throws URISyntaxException, IOException {
        File file1 = new File(path);
        file1.createNewFile();
        PrintWriter pw = new PrintWriter(file1);
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

    public static String getHash(String input) throws NoSuchAlgorithmException {
        try {
            // getInstance() method is called with algorithm SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            // digest() method is called
            // to calculate message digest of the input string
            // returned as array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);

            // Add preceding 0s to make it 32 bit
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            // return the HashText
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteFile(String fileName) throws URISyntaxException {
        // FileUtils.deleteFile(fileName);
        File file1 = new File(fileName);
        file1.delete();
    }

    public static void deleteDirectory(String dirName) throws Exception {
        File directory = new File(dirName);
        File[] fileList = directory.listFiles();
        if (fileList != null)
            for (File subfile : fileList) {
                if (subfile.isDirectory())
                    deleteDirectory(subfile.getName());
                else
                    subfile.delete();
            }
    }
    

    public static void createFile(String fileName) throws Exception {
        File file1 = new File(fileName);
        file1.createNewFile();
    }

}