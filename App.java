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
        for (int i = 0; i < snapshots.size(); i++) {
            snapshots.get(i).printAllFileStatusEntry();
        }
        String contentOnRun[] = FileManager.getFolderContent(watcher.getDirectoryToWatchString());
        snapshots.add(new Snapshot(contentOnRun));
        snapshots.add(new Snapshot(snapshots.get(snapshots.size()-1)));

        Thread userInputThread = new Thread(() -> {
            String userInput = "";
            while (true) {
                userInput = takeUserInput();
                // Process user input here
                if(userInput.equals("commit")){
                    snapshots.get(snapshots.size()-1).addTimestamp();
                    snapshots.get(snapshots.size()-1).printAllFileStatusEntry();
                    snapshots.get(snapshots.size()-1).eraseStatusDeleted();
                    snapshots.get(snapshots.size()-1).changeAllEntryStatus();
                    snapshots.get(snapshots.size()-2).eraseStatusDeleted();
                    snapshots.get(snapshots.size()-2).changeAllEntryStatus();

                    snapshots.add(snapshots.get(snapshots.size()-1));
                }
                if(userInput.startsWith("info")){
                    String filename = "";
                    int startIndex = userInput.indexOf("<");
                    if(startIndex==-1){
                        System.out.println("Invalid format - correct format info <filename>");
                    }
                    else{
                        for (int i = startIndex+1; i < userInput.indexOf(">"); i++) {
                            filename += userInput.charAt(i);
                        }
                        FileManager.printFileInfo(filename+".", watcher.getDirectoryToWatchString());
                    }
                    
                }
                else if (userInput.equals("exit")) {
                    System.exit(0);
                }
            }
        });
        userInputThread.start();

        Thread eventProcessingThread = new Thread(() -> {
            watcher.proccessEvents(snapshots);
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