
public class FileStatusEntry {
    private String filename;
    private String status;

    public FileStatusEntry(String filename, String status){
        this.filename = filename;
        this.status = status;
    }
    public String getFilename(){
        return this.filename;
    }
    public String getStatus(){
        return this.status;
    }

    public void changeStatus(String status){
        this.status = status;
    }


    @Override
    public String toString(){
        String logFormat = "%-20s - %-30s %n";
        return String.format(logFormat, this.filename, this.status);
    }
}
