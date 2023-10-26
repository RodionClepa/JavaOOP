package FileInspector;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileInfoPython extends FileInfo {
    @Override
    public void getDetailInfo(String filePathString){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathString));
            int lineCount = 0;
            int classCount = 0;
            int methodCount = 0;
            String line;
        
            while ((line = reader.readLine()) != null) {
                lineCount++;
        
                // Check for class definitions
                if (line.startsWith("class ") || line.startsWith("public class ") || line.startsWith("private class ")) {
                    classCount++;
                }
        
                // Check for method definitions (assuming Python)
                if (line.trim().startsWith("def ")) {
                    methodCount++;
                }
            }
        
            reader.close();
            System.out.println("Line count: " + lineCount);
            System.out.println("Class count: " + classCount);
            System.out.println("Method count: " + methodCount);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
