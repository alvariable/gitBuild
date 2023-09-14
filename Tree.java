import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;

public class Tree {

    String testFolderPath = ".\\test";
    String indexPath = ".\\test\\index";
    File indexFile = new File(indexPath);

    public void add(String str) throws Exception {

        int colonCounter = -1; // :D
        // determine # of ':'
        int i = 0;
        while (i >= 0) {
            colonCounter++;
            i = str.indexOf(':', i);
        }
        // throw error if improper # of ':'
        if (colonCounter >= 3)
            throw new Exception("Bruh invalid input");

        // add
        FileWriter fw = new FileWriter(new File(indexPath));

        fw.append(str);
    }

    // // what to do if 0 colon found
    // private void noColon(String str) {

    // }

    // // what to do if 1 colon found
    // private void oneColon(String str) {

    // }

    // // what to do if 2 colon found
    // private void twoColon(String str) {

    // }

    public void remove(String str) throws Exception {

    }

    public void write() {

    }

}
