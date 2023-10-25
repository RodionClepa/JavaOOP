import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Snapshot {
    public List<FileStatusEntry> fileStatusEntries;
    private LocalDateTime createdTimestamp;

    public Snapshot(Snapshot latestSnapshot){
        this.fileStatusEntries = new ArrayList<>();
        FileStatusEntry fileTemp;
        for (int i = 0; i < latestSnapshot.fileStatusEntries.size(); i++) {
            fileTemp = latestSnapshot.fileStatusEntries.get(i);
            this.fileStatusEntries.add(new FileStatusEntry(fileTemp.getFilename(), fileTemp.getStatus(), fileTemp.getUpdatedTime()));
        }
        this.createdTimestamp = null;
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
                fileEntry.changeStatus(status);
                break;
            }
        }
    }
    public void removeFileByName(String filename){
        Iterator<FileStatusEntry> iterator = fileStatusEntries.iterator();
        while (iterator.hasNext()) {
            FileStatusEntry fileEntry = iterator.next();
            if (fileEntry.getFilename().equals(filename)) {
                iterator.remove();
            }
        }
    }

    public void changeAllEntryStatus(){
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            fileStatusEntries.get(i).changeStatus("No Change");
        }
    }



    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }
    public void addTimestamp(){
        this.createdTimestamp = LocalDateTime.now();
    }

    public boolean checkIfFileIsInSnapshot(String filename){
        String tempFileName;
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            tempFileName = fileStatusEntries.get(i).getFilename();
            if(tempFileName.equals(filename)){
                return true;
            }
        }
        return false;
    }
    public void eraseStatusDeleted(){
        Iterator<FileStatusEntry> iterator = fileStatusEntries.iterator();
        while (iterator.hasNext()) {
            FileStatusEntry entry = iterator.next();
            if (entry.getStatus().equals("Deleted")) {
                iterator.remove();
            }
        }
        
    }
    public String getStatusByFilename(String filename){
        String tempFileName;
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            tempFileName = fileStatusEntries.get(i).getFilename();
            if(tempFileName.equals(filename)){
                return fileStatusEntries.get(i).getStatus();
            }
        }
        return "No such file";
    }
}
