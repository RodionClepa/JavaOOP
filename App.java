import java.util.ArrayList;
import java.util.List;

public class App{
    public static void main(String[] args) {
        String directoryPath = "C:\\1univer\\Java\\Lab2";
        FileChangeWatcher watcher = new FileChangeWatcher(directoryPath);
        List<Snapshot> snapshots = new ArrayList<>();
        String contentOnRun[] = FileManager.getFolderContent(watcher.getDirectoryToWatchString());
        
        snapshots.add(new Snapshot(contentOnRun));
        watcher.proccessEvents();
    }
}