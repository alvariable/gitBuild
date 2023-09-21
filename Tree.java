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
import java.util.Objects;
import java.util.Set;

public class Tree {

    String indexPath = "./index";
    File indexFile = new File(indexPath);

    String hashName;
    File tree;
    String treePath;
    HashMap<String, String> blobs = new HashMap();

    // create file for tree
    public Tree() {
        tree = new File("Tree");

    }

    public void initializeTree() throws Exception {
        if (!tree.exists()) {
            tree.createNewFile();
        }
        treePath = tree.getPath();
    }

    public void add(String str) throws Exception { // adds this to THE TREE FILE outside objects folder

        // if last no newline
        // I'm using some of my code.
        Blob blob = new Blob(str);

        // FileWriter fw = new FileWriter(tree, true);

        // check if file empty

        // Blob blob = new Blob(name);
        // blob.makeFile();

        blobs.put(blob.originalPath, blob.SHA1);
        printBlobs();

    }

    public void remove(String str) throws Exception { // removes this from THE TREE FILE

        // BufferedReader bf = new BufferedReader(new FileReader(tree));
        // StringBuilder text = new StringBuilder();

        // // this will be put into new index file

        // // locate
        // while (bf.ready()) {
        // // System.out.println("ready");
        // String line = bf.readLine();
        // // System.out.println(line.length());

        // // locate name
        // int i = line.indexOf(str);

        // // builds string to put in new indexfile
        // if (i < 0) {
        // // System.out.println(line.substring(0, index - 1));
        // if (text.length() == 0)
        // text.append(line);
        // else
        // text.append("\n" + line);
        // }
        // }

        // bf.close();

        // tree.delete();
        // tree.createNewFile();

        // FileWriter fw = new FileWriter(tree);
        // // System.out.println("write");
        // fw.write(text.toString()); // account for "\n" at end

        // fw.close();

        blobs.remove(str);

        for (HashMap.Entry<String, String> entry : blobs.entrySet()) {
            if (Objects.equals(entry.getValue(), str) || entry.getValue().equals(str)) {
                blobs.remove(entry);
            }
        }

        printBlobs();

        // used my own code... sorry I just didn't understand yours...
    }

    public void generateBlob() throws Exception { // no delete old blob
        // String content;
        BufferedReader bf = new BufferedReader(new FileReader(tree));
        StringBuilder contents = new StringBuilder();
        while (bf.ready()) {
            contents.append((char) bf.read());
        }
        bf.close();

        String treeHash = Blob.createHash(contents.toString());

        // // create new file in objects folder
        // File treeBlob = new File("./objects/", treeHash);
        // treeBlob.createNewFile();

        // // write into blob
        // FileWriter fw = new FileWriter(treeBlob);
        // fw.write(contents.toString());

        // fw.close();

        Blob b = new Blob("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/Tree");
        b.add("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects");
        // Path p = Paths.get ("/Users/lilbarbar/Desktop/Honors
        // Topics/Arden_Amazing_Git/objects/Tree");
        b.writeFile("Tree", "/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects/Tree");

    }

    public String fileContents() throws IOException

    {
        // my code
        StringBuilder record = new StringBuilder("");
        BufferedReader br = new BufferedReader(
                new FileReader("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/objects/Tree"));

        while (br.ready()) {
            record.append((char) br.read());
        }

        br.close();
        String s = record.toString();
        return s;
    }

    public void printBlobs() {
        try {
            PrintWriter pw = new PrintWriter("/Users/lilbarbar/Desktop/Honors Topics/Arden_Amazing_Git/Tree");

            String s = "";
            for (HashMap.Entry<String, String> entry : blobs.entrySet()) {
                s += "Blob : " + entry.getKey() + " : " + entry.getValue() + "\n";
            }

            pw.print(s);
            pw.close();

        } catch (Exception e) {

        }

    }

}
