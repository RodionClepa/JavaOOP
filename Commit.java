import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Commit {
    private List<FileStatusEntry> fileStatusEntries;
    private LocalDateTime createdTimestamp;

    public Commit(Commit lastCommit){
        this.fileStatusEntries = new ArrayList<>();
        for (FileStatusEntry entry : lastCommit.fileStatusEntries) {
            this.fileStatusEntries.add(new FileStatusEntry(entry.getFilename(), entry.getStatus()));
        }
        this.createdTimestamp = null;
        // To remove unnecessary files
        this.eraseStatusDeleted();
        // To make rest of files 'No Change'
        this.clearAllEntryStatus();
    }

    public Commit(String [] content){
        this.fileStatusEntries = new ArrayList<>();
        this.createdTimestamp = LocalDateTime.now();
        for (int i = 0; i < content.length; i++) {
            fileStatusEntries.add(new FileStatusEntry(content[i], "No Change"));
        }
        System.out.println("Initial snapshot");
        printAllFileStatusEntry();
    }

    public void printAllFileStatusEntry(){
        FileStatusEntry file;
        System.out.println("---------------------------------------------------------------");
        System.out.println("Created Snapshot at: " + createdTimestamp);
        for (int i = 0; i < fileStatusEntries.size(); i++) {
            file = fileStatusEntries.get(i);
            System.out.print(file.toString());
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

    public void clearAllEntryStatus(){
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
    public List<FileStatusEntry> getFileStatusEntries() {
        return fileStatusEntries;
    }
}
