import java.util.Scanner;

public class App {

    private Scanner scanner = new Scanner(System.in);

    private String takeUserInput(){
        String cmd = scanner.nextLine();
        return cmd;
    }

    private int takeCapacity(){
        String userInput = "";
        System.out.print("Enter the capacity: ");
        userInput = takeUserInput();
        try {
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            System.out.println("Invalid number format!");
            System.exit(1);
            return -1;
        } 
    }

    public void runCommands(StackQueueInterface dataStructure){
        String userInput = "";
        while(!userInput.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek, 4 - print all, 0 - exit");
            userInput = takeUserInput();
            if(userInput.equals("1")){
                System.out.print("Enter the new Element: ");
                userInput = takeUserInput();
                dataStructure.pushOperation(userInput);
            }
            else if(userInput.equals("2")){
                dataStructure.popOperation();
            }
            else if(userInput.equals("3")){
                System.out.println(dataStructure.peekOperation());
            }
            else if(userInput.equals("4")){
                dataStructure.printAll();
            }
        }
        scanner.close();
        System.exit(0);
    }

    public void selectType(){
        String userInput = "";

        while(!userInput.equals("0")){
            System.out.println("1 - Stack Array Down\n2 - Stack Array Up\n3 - Stack Link\n4 - Queue Array Down\n5 - Queue Array Up\n6 - Queue Link\n0 - exit");
            userInput = takeUserInput();
            if(userInput.equals("1")){
                StackArrayDown dataStructure = new StackArrayDown(takeCapacity());
                runCommands(dataStructure);
            }
            else if(userInput.equals("2")){
                StackArrayUp dataStructure = new StackArrayUp(takeCapacity());
                runCommands(dataStructure);
            }
            else if(userInput.equals("3")){
                StackNode dataStructure = new StackNode(takeCapacity());
                runCommands(dataStructure);
            }
            else if(userInput.equals("4")){
                QueueArrayDown dataStructure = new QueueArrayDown(takeCapacity());
                runCommands(dataStructure);
            }
            else if(userInput.equals("5")){
                QueueArrayUp dataStructure = new QueueArrayUp(takeCapacity());
                runCommands(dataStructure);
            }
            else if(userInput.equals("6")){
                QueueArrayDown dataStructure = new QueueArrayDown(takeCapacity());
                runCommands(dataStructure);
            }
        }
    }
    public static void main(String[] args) {
        App app = new App();
        app.selectType();
    }
}


