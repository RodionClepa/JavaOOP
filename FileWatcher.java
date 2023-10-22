import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatcher {
    Path directoryToWatch;
    WatchKey key;
    WatchService watcher;

    public FileWatcher(String directoryToWatch){
        try {
            this.watcher = FileSystems.getDefault().newWatchService();
            this.directoryToWatch = Paths.get(directoryToWatch);
            this.key = this.directoryToWatch.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void proccessEvents(Snapshot prevSnapshot, Snapshot latestSnapshot){
        while(true){
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                System.err.println(e);
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();
                Path context = (Path) event.context();

                // Handle different event types
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    latestSnapshot.addNewEntry("Created", context);
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    latestSnapshot.changeEntryStatus("Deleted", context.toString());
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    latestSnapshot.changeEntryStatus("Changed", context.toString());
                }
            }

            // reset the key is neccesary if you want to keep track on other changes
            boolean valid = key.reset();
            if (!valid) {
                break; // Exit the loop if the key is no longer valid
            }
        }
    }

    public String getDirectoryToWatchString(){
        return directoryToWatch.toString();
    }
}

// https://docs.oracle.com/javase/tutorial/essential/io/notification.html