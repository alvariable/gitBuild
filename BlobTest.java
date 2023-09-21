import static org.junit.jupiter.api.Assertions.assertEquals;

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

import org.junit.jupiter.api.Test;

public class BlobTest {
    @Test
    void testAdd() throws Exception {

        Blob blob = new Blob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt");
        blob.add("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects");
        Path f = Paths.get("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/");

        assertEquals(Files.exists(f), true);

    }

    @Test
    void testGetContents() {

    }

    @Test
    void testGetSHA1() throws Exception {

        String path = "/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/hi.txt";
        Blob blob = new Blob(path);
        // Blob blob = new Blob("/Users/lilbarbar/Desktop/Honors
        // Topics/Arden_Amazing_Git/hi.txt");
        assertEquals(blob.getSHA1(), "40bd001563085fc35165329ea1ff5c5ecbdbbeef"); // verified online from
                                                                                  // https://codebeautify.org/sha1-hash-generator

    }
}
