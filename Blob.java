import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.management.RuntimeErrorException;

public class Blob {
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

    public static void writeNewFile() {
        // write a new file to disk inside an 'object class'
        // new file name is the SHA1, file contains the same content as original
    }

    public static String getSHA1Code() {
        return null;
    }

}