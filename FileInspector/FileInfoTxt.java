package FileInspector;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileInfoTxt extends FileInfo {
    @Override
    public void getDetailInfo(String filePathString){
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
            System.out.println("Word count: "+ wordCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
