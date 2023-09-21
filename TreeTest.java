import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Set;

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

import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import java.util.Objects;

public class TreeTest {
    @Test
    void testAdd() throws Exception {

        Tree tree = new Tree();
        tree.initializeTree();
        tree.add("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/Tree");
        // Blob temp = new Blob("");

        String check2 = Blob.readFile(file);

        // BufferedReader reader = new BufferedReader(new FileReader(file));
        // StringBuilder string = new StringBuilder();
        // while (reader.ready()) {
        // string.append((char) reader.read());
        // }
        // reader.close();
        // return string.toString();

        assertEquals(file.exists(), true);
        assertEquals(check2.contains("hi.txt"), true);

    }

    @Test
    void testGenerateBlob() throws Exception {

        Tree tree = new Tree();
        tree.initializeTree();

        tree.generateBlob();
        File f = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects");

        Path p = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects");

        assertEquals(Files.exists(p), true);
        // assertEquals(t.exists(), true);

    }

    @Test
    void testInitializeTree() throws Exception {

        Tree tree = new Tree();
        tree.initializeTree();

        File t = new File("Tree");

        assertEquals(t.exists(), true);
    }

    @Test
    void testRemove() throws Exception {

        Tree tree = new Tree();
        tree.initializeTree();
        tree.add("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");
        tree.add("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/file.txt");

        File file = new File("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/Tree");
        // Blob temp = new Blob("");

        String check2 = Blob.readFile(file);

        // BufferedReader reader = new BufferedReader(new FileReader(file));
        // StringBuilder string = new StringBuilder();
        // while (reader.ready()) {
        // string.append((char) reader.read());
        // }
        // reader.close();
        // return string.toString();

        assertEquals(file.exists(), true);
        assertEquals(check2.contains("hi.txt"), true);

        tree.remove("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");
        tree.printBlobs();

        String check3 = Blob.readFile(file);

        assertEquals(!check3.contains("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt"), true);

        // tree.remove("d980522a9c313311b823dc46d3fa5eaa1f3a2958");

        // String check4 = Blob.readFile(file);

        // assertEquals(!check3.contains("d980522a9c313311b823dc46d3fa5eaa1f3a2958"),
        // true);

    }
}
