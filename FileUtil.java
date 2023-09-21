import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import Utilities.FileUtils;

public class FileUtil {
    public static String readFile(File fileName) throws IOException, URISyntaxException {
        return FileUtils.readFile(fileName.getPath());
    }

    public static void writeFile(String str, String path) throws FileNotFoundException, URISyntaxException {
        FileUtils.writeFile(str, path);

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

    public static String getHash(String fileContents) throws Exception {
        return FileUtils.sha1(fileContents);

    }

    public static void deleteFile(String fileName) throws URISyntaxException {
        FileUtils.deleteFile(fileName);
    }

    public static void deleteDirectory(String dirName) throws Exception {

        FileUtils.deleteDirectory(dirName);

    }

    public static void createFile(String fileName) throws Exception {
        FileUtils.createFile(fileName);
    }

}