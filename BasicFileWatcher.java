
import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class BasicFileWatcher {
    Path directoryToWatch;
    WatchKey key;
    WatchService watcher;

    public BasicFileWatcher(String directoryToWatch){
        try {
            this.watcher = FileSystems.getDefault().newWatchService();
            this.directoryToWatch = Paths.get(directoryToWatch);
            this.key = this.directoryToWatch.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String getDirectoryToWatchString(){
        return directoryToWatch.toString();
    }
}

// https://docs.oracle.com/javase/tutorial/essential/io/notification.html