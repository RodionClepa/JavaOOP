import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QueueOne {
    private List<Integer> list;

    public QueueOne(){
        this.list = new ArrayList<>();
    }

    public void pushOperation(String element){
        try {
            list.add(Integer.parseInt(element));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }
    }

    public void peekOperation(){
        try {
            System.out.println(list.get(0));
        } catch (IndexOutOfBoundsException e) {
            System.out.println("List is empty");
        }
    }

    public void popOperation(){
        try {
            list.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("List is empty");
        }
    }

    public static void main(String[] args) {
        QueueOne stack = new QueueOne();
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        while(!cmd.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek, 0 - exit");
            cmd = scanner.nextLine();
            if(cmd.equals("1")){
                System.out.print("Enter the new integer: ");
                cmd = scanner.nextLine();
                stack.pushOperation(cmd);
            }
            else if(cmd.equals("2")){
                stack.popOperation();
            }
            else if(cmd.equals("3")){
                stack.peekOperation();
            }
        }
        scanner.close();
    }
}
