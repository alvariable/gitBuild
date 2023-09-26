public class Commit {
    String author;
    String date;
    String parentSHA;

    public Commit(String author, String date) {
        this.author = author;
        this.date = date;
    }

    public Commit(String SHA, String author, String date) {
        this.author = author;
        this.date = date;
        this.parentSHA = SHA;
    }

    public String createHash(String fileContents) throws Exception {
        return FileUtil.getHash(fileContents);

    }

    public String getDate() {
        return date;
    }
}
