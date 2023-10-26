import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App{
    private Scanner scanner = new Scanner(System.in);
    private List<Commit> commits;
    private EnhancedFileWatcher watcher;
    private String directoryPath = "C:\\1univer\\Java\\Lab2\\folder";

    public App(){
        this.watcher = new EnhancedFileWatcher(directoryPath);
        this.commits = new ArrayList<>();
    }

    private String takeUserInput(){
        String cmd = scanner.nextLine();
        return cmd;
    }

    private void runCommands(){
        String userInput = " ";
        while(!userInput.equals("exit")){
            userInput = takeUserInput();
            // Process user input here
            if(userInput.equals("commit")){
                commits.get(commits.size()-1).addTimestamp();
                commits.get(commits.size()-1).printAllFileStatusEntry();

                commits.add(new Commit(commits.get(commits.size()-1)));
            }
            else if(userInput.startsWith("info")){
                String filename = "";
                int startIndex = userInput.indexOf("<");
                if(startIndex==-1){
                    System.out.println("Invalid format - correct format info <filename>");
                }
                else{
                    for (int i = startIndex+1; i < userInput.indexOf(">"); i++) {
                        filename += userInput.charAt(i);
                    }
                    FileManager.printFileInfo(filename, watcher.getDirectoryToWatchString());
                }
                
            }
            else if(userInput.startsWith("logs")){
                System.out.println("Print all commits");
                for (int i = 0; i < commits.size()-1; i++) {
                    commits.get(i).printAllFileStatusEntry();
                }
            }
            else if(userInput.startsWith("status")){
                System.out.println("Current status");
                commits.get(commits.size()-1).printAllFileStatusEntry();
            }
            else if(userInput.startsWith("help")){
                System.out.println("info, info <filename>, logs, status, exit");
            }
        }
        scanner.close();
        System.exit(0);
    }

    private void printChangesSchedule(){
        Commit lastCommit = commits.get(commits.size()-1);
        List<FileStatusEntry> commitFiles = lastCommit.getFileStatusEntries();
        FileStatusEntry file = null;
        String toPrint = "";
        for (int i = 0; i < commitFiles.size(); i++) {
            file = commitFiles.get(i);
            if(!file.getStatus().equals("No Change")){
                toPrint += file.toString();
            }
        }
        if(!toPrint.equals("")){
            System.out.println("----------------------------------------------");
            System.out.println(toPrint);
        }
    }

    private void run(){
        String contentOnRun[] = FileManager.getFolderContent(watcher.getDirectoryToWatchString());
        commits.add(new Commit(contentOnRun));
        commits.add(new Commit(commits.get(commits.size()-1)));

        Thread userInputThread = new Thread(() -> {
            runCommands();
        });
        userInputThread.start();

        Thread eventProcessingThread = new Thread(() -> {
            watcher.processEvents(commits);
        });
        eventProcessingThread.start();
        
        // https://stackoverflow.com/questions/30839390/schedule-to-run-a-method-at-periodic-time%C2%B4
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        int periodSchedule = 5;
        scheduler.scheduleAtFixedRate(() -> this.printChangesSchedule(), 0, periodSchedule, TimeUnit.SECONDS);

        // Wait for both threads to finish before closing the scanner
        try {
            userInputThread.join();
            eventProcessingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}