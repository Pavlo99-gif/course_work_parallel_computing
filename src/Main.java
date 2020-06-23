import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {

    static ArrayList<File> files = new Files().returnFiles();
    public static int NumCreatedThreads = 500;

    public static void main(String[] args) throws InterruptedException {

        Index[] arr_thread = new Index[NumCreatedThreads];
        HashMap<String, HashSet<File>> finalIndexSet = new HashMap<>();

        long starting_time = System.currentTimeMillis();

        //starting threads

        for (int i = 0; i < NumCreatedThreads; i++){
            List<File> workingArea = files.subList(files.size()/NumCreatedThreads * i,
                    i == (NumCreatedThreads - 1) ? files.size() : files.size() / NumCreatedThreads * (i + 1));
            arr_thread[i] = new Index(workingArea);
            arr_thread[i].start();
        }
        //joining threads

        for (int i = 0; i < NumCreatedThreads; i++){
            arr_thread[i].join();
        }

        //merging results of all threads into final 'finalIndexSet'
        for (int i = 0; i < NumCreatedThreads; i++){
            arr_thread[i].getIndex().forEach(
                    (key, value) -> finalIndexSet.merge(key, value,
                            (set1, set2) ->
                            {
                                HashSet<File> fileSet = new HashSet<>(set1);
                                fileSet.addAll(set2);
                                return fileSet;
                            }
                    ));
        }

        long ending_time = System.currentTimeMillis();

        System.out.println("TIME = " + (ending_time - starting_time));
        System.out.println(finalIndexSet.get("homicide"));
    }
}
