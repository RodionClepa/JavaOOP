import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private List<FileStatusEntry> fileStatusEntries;
    private LocalDateTime createdTimestamp;

    public Snapshot(Snapshot latestSnapshot){
        this.fileStatusEntries = latestSnapshot.fileStatusEntries;
        this.createdTimestamp = null;
        setAllFileInPrevSnapshot();
    }

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
        System.out.println("---------------------------------------------------------------");
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            file = fileStatusEntries.get(i);
            System.out.print(String.format(logFormat, file.getFilename(), file.getStatus(), file.getOnDateTimeString()));
        }
        System.out.println("---------------------------------------------------------------");
    }

    public void addNewEntry(String status, Path context){
        fileStatusEntries.add(new FileStatusEntry(context.toString(), status));
    }

    public void changeEntryStatus(String status, String filename){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            FileStatusEntry fileEntry = fileStatusEntries.get(i);
            if(fileEntry.getFilename().equals(filename)){
                if(status.equals("Deleted") && !fileEntry.getIsInPreviousSnapshot()){
                    fileStatusEntries.remove(i);
                }
                else{
                    fileEntry.changeStatus(status);
                }
                break;
            }
        }
    }

    private void setAllFileInPrevSnapshot(){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            fileStatusEntries.get(i).changeIsInPreviousSnapshot();
        }
    }
}
