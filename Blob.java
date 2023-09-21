import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import java.util.zip.Deflater;

public class Blob {
    public String SHA1;
    File originalFile;
    public String originalPath;
    String fileContents;
    // HashMap<>;

    public Blob(String filePathString) throws Exception {

        originalPath = filePathString;
        originalFile = new File(originalPath);
        fileContents = readFile(originalFile);
        SHA1 = createHash(fileContents);
    }

    public String getSHA1() {
        return SHA1;
    }

    public String getContents() {
        return fileContents;
    }

    public void add(String objectPath) throws Exception {

        // makes directory if no exist
        objectPath = "/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects/";
        Path oP = Paths.get(objectPath); // creates Path
        if (!Files.exists(oP)) // creates file if directory doesnt exist
            Files.createDirectories(oP); // creates Path

        writeFile(fileContents, objectPath + SHA1);
    }

    public static String createHash(String fileContents) throws Exception {

        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(fileContents.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();

        // return FileUtil.getHash(fileContents);

    }

    public static String readFile(File fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder string = new StringBuilder();
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        return string.toString();
    }

    public void writeFile(String str, String path) throws FileNotFoundException {
        // creates a file from file path parameter, file name = sha1
        File file = new File(path);
        PrintWriter pw = new PrintWriter(file);
        pw.write(str);
        pw.close();

    }

}