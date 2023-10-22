import java.time.LocalDateTime;

public class FileStatusEntry {
    private String filename;
    private String status;
    private LocalDateTime onDateTime;

    public FileStatusEntry(String filename, String status){
        this.filename = filename;
        this.status = status;
    }

    public FileStatusEntry(String filename, String status, LocalDateTime onDateTime){
        this.filename = filename;
        this.status = status;
        this.onDateTime = onDateTime;
    }
    public String getFilename(){
        return this.filename;
    }
    public String getStatus(){
        return this.status;
    }
    public String getOnDateTimeString(){
        return this.onDateTime.toString();
    }
    @Override
    public String toString(){
        return filename+" "+status+" "+onDateTime.toString();
    }
}
