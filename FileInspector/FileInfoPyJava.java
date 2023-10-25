package FileInspector;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileInfoPyJava extends FileInfo{
    @Override
    public void getDetailInfo(String filePathString){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathString));
            int lineCount = 0;
            String line;
            int classCount = 0;
            while ((line = reader.readLine()) != null) {
                lineCount++;
                if (line.startsWith("class ") || line.startsWith("public class ") || line.startsWith("private class ")) {
                    classCount++;
                }
            }

            reader.close();
            System.out.println("Line count: " + lineCount);
            System.out.println("Class count: " + classCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
