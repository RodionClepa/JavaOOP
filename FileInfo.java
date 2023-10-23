import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

import javax.imageio.ImageIO;

public class FileInfo extends FileManager{
    public static void printFileInfo(String filename, String directoryPathString){
        String content[] = getFolderContent(directoryPathString);
        for (int i = 0; i < content.length; i++) {
            if(content[i].startsWith(filename)){
                System.out.println("-------------------------------------");
                getFileInfo(directoryPathString+"/"+content[i]);
                System.out.println("-------------------------------------");
            }
        }
    }

    private static boolean isImage(String filePathString) {
        String lowerCasePath = filePathString.toLowerCase();
        return lowerCasePath.endsWith(".png") || lowerCasePath.endsWith(".jpg") || lowerCasePath.endsWith(".jpeg") || lowerCasePath.endsWith(".jiff") || lowerCasePath.endsWith(".jpe");
    }
    private static void getImageSize(String filePathString){
        try {
            File imageFile = new File(filePathString);
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            System.out.println("Image Width: " + width);
            System.out.println("Image Height: " + height);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isTxt(String filePathString){
        String lowerCasePath = filePathString.toLowerCase();
        return lowerCasePath.endsWith(".txt");
    }

    private static void getTxtInfo(String filePathString){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathString));
            int lineCount = 0;
            int charCount = 0;
            String line;
            String[] words;
            int wordCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                charCount += line.length();
                words = line.split("\\s+");

                // Handle when txt have empty lines
                if(words.length == 1 && words[0].equals("")){
                    continue;
                }
                wordCount += words.length;
            }

            reader.close();
            System.out.println("Line count: " + lineCount);
            System.out.println("Character count: "+ charCount);
            System.out.println("Character count: "+ wordCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            if(isImage(filePathString)){
                getImageSize(filePathString);
            }
            else if(isTxt(filePathString)){
                getTxtInfo(filePathString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
