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

    String indexPath = "./index";
    File indexFile = new File(indexPath);

    String hashName;
    File tree;
    String treePath;

    // create file for tree
    public Tree() throws Exception {
        tree = new File("Tree");
        if (!tree.exists()) {
            tree.createNewFile();
        }
        treePath = tree.getPath();
    }

    public void add(String str) throws Exception { // adds this to THE TREE FILE outside objects folder

        // if last no newline

        FileWriter fw = new FileWriter(tree);

        // check if file empty
        if (tree.length() == 0)
            fw.append(str);
        else
            fw.append("\n" + str);

        fw.close();
    }

    public void remove(String str) throws Exception { // removes this from THE TREE FILE

        BufferedReader bf = new BufferedReader(new FileReader(tree));
        StringBuilder text = new StringBuilder();

        // this will be put into new index file

        // locate
        while (bf.ready()) {
            // System.out.println("ready");
            String line = bf.readLine();
            // System.out.println(line.length());

            // locate name
            int i = line.indexOf(str);

            // builds string to put in new indexfile
            if (i < 0) {
                // System.out.println(line.substring(0, index - 1));
                if (text.length() == 0)
                    text.append(line);
                else
                    text.append("\n" + line);
            }
        }

        bf.close();

        tree.delete();
        tree.createNewFile();

        FileWriter fw = new FileWriter(tree);
        // System.out.println("write");
        fw.write(text.toString()); // account for "\n" at end

        fw.close();
    }

}
