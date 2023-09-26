import java.io.File;
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
        initializeCommit();
    }

    public Commit(String SHA, String author, String summary) throws Exception {
        this.author = author;
        this.summary = summary;
        this.parentSHA = SHA;
        createDate();
        initializeCommit();
    }

    private void initializeCommit() throws Exception {
        if (!commit.exists()) {
            commit.createNewFile();
        }
        commitPath = commit.getPath();
    }

    public void write() throws IOException {
        FileWriter fw = new FileWriter(commit, true);

        fw.append(treeSHA + "\n");
        fw.append(parentSHA + "\n");
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
