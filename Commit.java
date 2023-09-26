import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Commit {
    String author;
    String summary;
    String parentSHA = "";
    String date;

    Tree origin = new Tree();
    String treeSHA = origin.getHash();

    File commit;
    String commitPath;

    public Commit(String author, String summary) throws Exception {
        this.author = author;
        this.summary = summary;
        createDate();
    }

    public Commit(String SHA, String author, String summary) throws Exception {
        this.author = author;
        this.summary = summary;
        this.parentSHA = SHA;
        createDate();
    }

    private void initializeCommit() throws Exception {
        if (!commit.exists()) {
            commit.createNewFile();
        }
        commitPath = commit.getPath();
    }

    public void write() throws Exception {
        FileWriter fw = new FileWriter(commit, true);

        fw.append(treeSHA + "\n");
        fw.append(parentSHA + "\n");
        File index = origin.getIndex();
        String extSHA = this.createHash(this.readFile(index));
        fw.append(extSHA + "\n");
        fw.append(author + "\n");
        fw.append(date + "\n");
        fw.append(summary);

        commitPath = "./objects/";
    }

    private String readFile(File fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder string = new StringBuilder();
        while (reader.ready()) {
            string.append((char) reader.read());
        }
        reader.close();
        return string.toString();
    }

    public String createHash(String fileContents) throws Exception {
        return FileUtil.getHash(fileContents);

    }

    public String getDate() {
        return date;
    }

    private void createDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = new Date();
        date = formatter.format(today);
    }
}
