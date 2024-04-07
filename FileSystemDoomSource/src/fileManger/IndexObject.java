package fileManger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IndexObject {

    public String root = "";
    public List<File> indexedFiles = new ArrayList<>();
    public List<File> failedIndexs = new ArrayList<>();

    // Allows the setting of objects values. Index files and the failed files.
    public IndexObject(String root, List<File> indexed, List<File> fail) {

        this.root = root;
        this.indexedFiles = indexed;
        this.failedIndexs = fail;
    }
}
