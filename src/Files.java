import java.io.File;
import java.util.ArrayList;

public class Files {

    ArrayList<File> files = new ArrayList<>();

    File[] directories = {new File("D:\\3_s\\paralel\\course\\files\\test_neg"),
            new File("D:\\3_s\\paralel\\course\\files\\test_pos"),
            new File("D:\\3_s\\paralel\\course\\files\\train_neg"),
            new File("D:\\3_s\\paralel\\course\\files\\train_pos"),
            new File("D:\\3_s\\paralel\\course\\files\\unsup")};

    public ArrayList<File> returnFiles(){

        for (File folder : directories){
            for (File file : folder.listFiles()){
                files.add(file.getAbsoluteFile());
            }
        }
        return files;
    }
}
