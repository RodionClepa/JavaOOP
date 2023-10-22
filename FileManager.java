import java.io.File;

public class FileManager {


    public static String[] getFolderContent(String directoryPathString){
        File directoryPath = new File(directoryPathString);
        String contentOnRun[] = directoryPath.list();
        return contentOnRun;
    }
}
