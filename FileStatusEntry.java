import java.time.LocalDateTime;

public class FileStatusEntry {
    private String filename;
    private String status;
    private LocalDateTime onDateTime;
    private boolean isInPreviousSnapshot;

    public FileStatusEntry(String filename, String status){
        this.filename = filename;
        this.status = status;
        this.onDateTime = LocalDateTime.now();
        this.isInPreviousSnapshot = false;
    }

    public FileStatusEntry(String filename, String status, LocalDateTime onDateTime){
        this.filename = filename;
        this.status = status;
        this.onDateTime = onDateTime;
        this.isInPreviousSnapshot = false;
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
        return filename+" "+status+" "+onDateTime.toString();
    }
}
