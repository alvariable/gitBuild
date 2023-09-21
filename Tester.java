import java.io.File;
import java.io.IOException;
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

public class Tester {
    public static void main(String[] args) throws Exception, IOException, NoSuchAlgorithmException {
        Index init = new Index();
        init.initializeProject();
        init.addBlob("testFile.txt");
        init.addBlob("file.txt");
        Blob blob = new Blob("testFile.txt");
        blob.add("objects");

        init.removeBlob("file.txt");

        Tree tree = new Tree();
        tree.initializeTree();

        tree.add("thing : 2093103821038210");
        tree.add("thing : 2093103821038210");
        // tree.remove("thing : 2093103821038210");
        tree.generateBlob();

        System.out.println("test done");
    }
}
