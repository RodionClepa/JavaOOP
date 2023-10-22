import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App{
    Scanner scanner = new Scanner(System.in);

    public String takeUserInput(){
        String cmd = scanner.nextLine();
        return cmd;
    }

    public void run(){
        String directoryPath = "C:\\1univer\\Java\\Lab2\\folder";
        FileWatcher watcher = new FileWatcher(directoryPath);
        List<Snapshot> snapshots = new ArrayList<>();
        String contentOnRun[] = FileManager.getFolderContent(watcher.getDirectoryToWatchString());
        
        snapshots.add(new Snapshot(contentOnRun));
        Snapshot latestSnapshot = snapshots.get(snapshots.size()-1);
        snapshots.add(new Snapshot(latestSnapshot));
        for (int i = 0; i < snapshots.size(); i++) {
            snapshots.get(i).printAllFileStatusEntry();
        }
        Thread userInputThread = new Thread(() -> {
            String userInput = "";
            while (true) {
                userInput = takeUserInput();
                // Process user input here
                if(userInput.equals("commit")){
                    
                }
                if(userInput.equals("info")){
                    
                }
                else if (userInput.equals("exit")) {
                    System.exit(0);
                }
            }
        });
        userInputThread.start();

        // Create and start the thread for event processing
        Thread eventProcessingThread = new Thread(() -> {
            watcher.proccessEvents(latestSnapshot, snapshots.get(snapshots.size() - 1));
        });
        eventProcessingThread.start();

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