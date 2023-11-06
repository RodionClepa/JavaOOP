import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StackOne{

    List<Integer> list;

    public StackOne(){
        this.list = new ArrayList<>();
    }

    public void pushOperation(String element){
        try {
            list.add(Integer.parseInt(element));
        } catch (NumberFormatException e) {
            System.out.println("Invalod number format");
        }
    }

    public void peekOperation(){
        System.out.println(list.get(list.size()-1));
    }

    public void popOperation(){
        list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        StackOne stack = new StackOne();
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        while(!cmd.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek");
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