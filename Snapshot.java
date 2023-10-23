import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Snapshot {
    private List<FileStatusEntry> fileStatusEntries;
    private LocalDateTime createdTimestamp;

    public Snapshot(Snapshot latestSnapshot){
        this.fileStatusEntries = new ArrayList<>(latestSnapshot.fileStatusEntries);
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
        FileStatusEntry file;
        System.out.println("---------------------------------------------------------------");
        System.out.println("Created Snapshot at: " + createdTimestamp);
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            file = fileStatusEntries.get(i);
            System.out.print(String.format(logFormat, file.getFilename(), file.getStatus(), file.getUpdatedTimeString()));
        }
        System.out.println("---------------------------------------------------------------");
    }

    public void addNewEntry(String status, String context){
        fileStatusEntries.add(new FileStatusEntry(context, status));
    }

    public void changeEntryStatus(String status, String filename){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            FileStatusEntry fileEntry = fileStatusEntries.get(i);
            if(fileEntry.getFilename().equals(filename)){
                if(status.equals("Deleted") && !fileEntry.getIsInPreviousSnapshot()){
                    fileStatusEntries.remove(i);
                    System.out.println("Worked delete");
                }
                else{
                    fileEntry.changeStatus(status);
                }
                break;
            }
        }
    }

    public void changeAllEntryStatus(){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            fileStatusEntries.get(i).changeStatus("No Change");
        }
    }

    private void setAllFileInPrevSnapshot(){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            fileStatusEntries.get(i).changeIsInPreviousSnapshot();
        }
    }


    public void addTimestamp(){
        this.createdTimestamp = LocalDateTime.now();
    }

    public boolean checkIfFileIsInSnapshot(String filename){
        String tempFileName;
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            tempFileName = fileStatusEntries.get(i).getFilename();
            if(tempFileName.equals(filename)){
                System.out.println("Coindence: "+ tempFileName+" "+filename);
                return true;
            }
        }
        return false;
    }
    public void eraseStatusDeleted(){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            if(fileStatusEntries.get(i).getStatus().equals("Deleted")){
                fileStatusEntries.remove(i);
            }
        }
    }
}
