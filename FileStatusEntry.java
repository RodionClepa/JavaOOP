import java.time.LocalDateTime;

public class FileStatusEntry {
    private String filename;
    private String status;
    private LocalDateTime updatedTime;

    public FileStatusEntry(String filename, String status){
        this.filename = filename;
        this.status = status;
        this.updatedTime = LocalDateTime.now();
    }

    public FileStatusEntry(String filename, String status, LocalDateTime updatedTime){
        this.filename = filename;
        this.status = status;
        this.updatedTime = updatedTime;
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

    public void changeStatus(String status){
        this.status = status;
    }


    @Override
    public String toString(){
        return filename+" "+status+" "+updatedTime.toString();
    }
}
