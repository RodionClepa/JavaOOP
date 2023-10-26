
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;

public class EnhancedFileWatcher extends BasicFileWatcher {
    public EnhancedFileWatcher(String directoryToWatch) {
        super(directoryToWatch);
    }
    public void processEvents(List<Commit> commits){
        Commit lastCommit;
        Commit prevCommit;
        while(true){            
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
                String contextString = context.toString();

                prevCommit = commits.get(commits.size() - 2);
                lastCommit = commits.get(commits.size()-1);

                // Handle different event types
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    lastCommit.addNewEntry("Created", contextString);
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    if(prevCommit.checkIfFileIsInSnapshot(contextString)){
                        lastCommit.changeEntryStatus("Deleted", contextString);
                    }
                    else{
                        lastCommit.removeFileByName(contextString);
                    }
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    try {

                        // Get file's created time and time from prev snapshot and convert to UTC
                        Path path = Paths.get(directoryToWatch + "/" + context);
                        BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
                        FileTime creationTime = attributes.creationTime();
                        ZoneId zoneId = ZoneId.systemDefault();
                        ZoneOffset currentZoneOffset = zoneId.getRules().getOffset(Instant.now());
                        FileTime prevCommitCreatedTime = FileTime.from(prevCommit.getCreatedTimestamp().toInstant(currentZoneOffset));

                        if (creationTime.compareTo(prevCommitCreatedTime) < 0 && prevCommit.checkIfFileIsInSnapshot(contextString) && !prevCommit.getStatusByFilename(contextString).equals("Deleted") && !lastCommit.getStatusByFilename(contextString).equals("Deleted")) {
                            lastCommit.changeEntryStatus("Changed", contextString);
                        }
                    } catch (Exception e) {
                        System.out.println("Error: problem with reading file, maybe file was deleted");
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
}
