import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

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

    public void proccessEvents(List<Snapshot> snapshots){
        Snapshot latestSnapshot;
        Snapshot prevSnapshot;
        while(true){
            prevSnapshot = snapshots.get(snapshots.size() - 2);
            latestSnapshot = snapshots.get(snapshots.size()-1);
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                System.err.println(e);
                return;
            }
            List<WatchEvent<?>> events = key.pollEvents();
            for (int i = 0; i < events.size()-1; i++) {
                String context = events.get(i).context().toString();
                String nextContext = events.get(i+1).context().toString();
                System.out.println(context+" "+nextContext);
                if(context.equals(nextContext)){
                    events.remove(i+1);
                    i--;
                }
            }

            for (WatchEvent<?> event : events) {
                WatchEvent.Kind<?> kind = event.kind();
                Path context = (Path) event.context();

                // Handle different event types
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println("created");
                    latestSnapshot.addNewEntry("Created", context.toString());
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    latestSnapshot.changeEntryStatus("Deleted", context.toString());
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    if(prevSnapshot.checkIfFileIsInSnapshot(context.toString())){
                        System.out.println("changed");
                        latestSnapshot.changeEntryStatus("Changed", context.toString());
                    }
                }
            }
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

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