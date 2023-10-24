package FileInspector;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public abstract class FileInfo{

    public abstract void getDetailInfo(String filePathString);
    
    private static void getFileInfo(String filePathString){
        try {
            Path path = Paths.get(filePathString);
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            FileTime creationTime = attributes.creationTime();
            FileTime lastModifiedTime = attributes.lastModifiedTime();
            String fileName = path.getFileName().toString();
            String fileExtension = "";
            int dotIndex = fileName.indexOf('.');
            for (int i = dotIndex+1; i < fileName.length(); i++) {
                fileExtension+=fileName.charAt(i);
            }
            System.out.println("File Name: " + fileName);
            System.out.println("File Extension: " + fileExtension);
            System.out.println("Creation Time: " + creationTime.toString());
            System.out.println("Last Modified Time: " + lastModifiedTime.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printInfo(String filePathString){
        getFileInfo(filePathString);
        getDetailInfo(filePathString);
    }
}
