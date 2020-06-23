import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Index extends Thread {

    public List<File> files;
    public HashMap<String, HashSet<File>> Index = new HashMap<>();
    public HashMap<String, HashSet<File>> getIndex() {
        return Index;
    }

    public Index(List<File> files){
        this.files = files;
    }

    @Override
    public void run() {
        for (File allFile : files){
            try {
                BufferedReader file = new BufferedReader(new FileReader(allFile));
                String line = file.readLine().replaceAll("[^a-zA-Z ]", "").toLowerCase().trim();
                ArrayList<String> content = new ArrayList<>();
                Collections.addAll(content, line.split("\\s+"));

                for (String word : content){
                    if (!Index.containsKey(word)){
                        Index.put(word, new HashSet<File>());
                    }
                    Index.get(word).add(allFile.getAbsoluteFile());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
