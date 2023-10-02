import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggingSystem {
    public static void log(String type, String message){
        try {
            FileWriter logWriter = new FileWriter("logs.txt", true);
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = timeNow.format(formatter);
            String logFormat = "[%s] %7s %s%n";
            logWriter.write(String.format(logFormat, formattedDateTime, type, message));
            logWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
