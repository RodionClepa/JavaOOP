import java.time.LocalDateTime;

public class FileStatusEntry {
    private String filename;
    private String status;
    private LocalDateTime updatedTime;
    private boolean isInPreviousSnapshot;

    public FileStatusEntry(String filename, String status){
        this.filename = filename;
        this.status = status;
        this.updatedTime = LocalDateTime.now();
        this.isInPreviousSnapshot = false;

    }

    public FileStatusEntry(String filename, String status, LocalDateTime updatedTime){
        this.filename = filename;
        this.status = status;
        this.updatedTime = updatedTime;
        this.isInPreviousSnapshot = false;
    }
    public String getFilename(){
        return this.filename;
    }
    public String getStatus(){
        return this.status;
    }
    public String getUpdatedTimeString(){
        return this.updatedTime.toString();
    }

    public boolean getIsInPreviousSnapshot(){
        return this.isInPreviousSnapshot;
    }

    public void changeStatus(String status){
        this.status = status;
    }

    public void changeIsInPreviousSnapshot(){
        this.isInPreviousSnapshot = true;
    }


    @Override
    public String toString(){
        return filename+" "+status+" "+updatedTime.toString();
    }
}
