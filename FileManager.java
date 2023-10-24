import java.io.File;

import FileInspector.FileInfoImage;
import FileInspector.FileInfoTxt;

public class FileManager {

    public static String[] getFolderContent(String directoryPathString){
        File directoryPath = new File(directoryPathString);
        String contentOnRun[] = directoryPath.list();
        return contentOnRun;
    }

    private static boolean isImage(String filePathString) {
        String lowerCasePath = filePathString.toLowerCase();
        return lowerCasePath.endsWith(".png") || lowerCasePath.endsWith(".jpg") || lowerCasePath.endsWith(".jpeg") || lowerCasePath.endsWith(".jiff") || lowerCasePath.endsWith(".jpe");
    }

    private static boolean isTxt(String filePathString){
        String lowerCasePath = filePathString.toLowerCase();
        return lowerCasePath.endsWith(".txt");
    }

    public static void printFileInfo(String filename, String directoryPathString){
        String content[] = getFolderContent(directoryPathString);
        String filePathString;
        FileInfoImage imageManager = new FileInfoImage();
        FileInfoTxt txtManager = new FileInfoTxt();
        for (int i = 0; i < content.length; i++) {
            if(content[i].startsWith(filename)){
                filePathString = directoryPathString+"/"+content[i];
                System.out.println("-------------------------------------");
                if(isImage(filePathString)){
                    imageManager.printInfo(filePathString);
                }
                else if(isTxt(filePathString)){
                    txtManager.printInfo(filePathString);
                }
                System.out.println("-------------------------------------");
            }
        }
    }
}
