import static java.nio.file.StandardWatchEventKinds.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
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
            // It is neccesary for keep eye track on changes like commit and to get 2 last 
            prevSnapshot = snapshots.get(snapshots.size() - 2);
            latestSnapshot = snapshots.get(snapshots.size()-1);
            
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                System.err.println(e);
                return;
            }

            List<WatchEvent<?>> events = key.pollEvents();

            for (WatchEvent<?> event : events) {
                WatchEvent.Kind<?> kind = event.kind();
                Path context = (Path) event.context();

                // Handle different event types
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    latestSnapshot.addNewEntry("Created", context.toString());
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    latestSnapshot.changeEntryStatus("Deleted", context.toString());
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    try {
                        Path path = Paths.get(directoryToWatch + "/" + context);
                        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
                        FileTime creationTime = attributes.creationTime();
                        ZoneId zoneId = ZoneId.systemDefault();
                        ZoneOffset currentZoneOffset = zoneId.getRules().getOffset(Instant.now());
                        FileTime prevSnapshotCreatedTime = FileTime.from(prevSnapshot.getCreatedTimestamp().toInstant(currentZoneOffset));
                        if (creationTime.compareTo(prevSnapshotCreatedTime) < 0 && prevSnapshot.checkIfFileIsInSnapshot(context.toString())) {
                            latestSnapshot.changeEntryStatus("Changed", context.toString());
                        }
                    } catch (Exception e) {
                        System.out.println("Error: problem with reading file");
                    }
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