import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private List<FileStatusEntry> fileStatusEntries;
    private LocalDateTime createdTimestamp;

    public Snapshot(String [] content){
        this.fileStatusEntries = new ArrayList<>();
        this.createdTimestamp = LocalDateTime.now();
        System.out.println("Initial snapshot");
        for (int i = 0; i < content.length; i++) {
            fileStatusEntries.add(new FileStatusEntry(content[i], "No Change", createdTimestamp));
        }
        printAllFileStatusEntry();
    }

    public void printAllFileStatusEntry(){
        String logFormat = "%-20s - %-25s %s%n";
        System.out.println("Created Snapshot at: " + createdTimestamp);
        FileStatusEntry file;
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            file = fileStatusEntries.get(i);
            System.out.print(String.format(logFormat, file.getFilename(), file.getStatus(), file.getOnDateTimeString()));
        }
    }
}
