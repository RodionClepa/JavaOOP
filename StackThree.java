import java.util.Scanner;

public class StackThree {
    private Node top;

    public boolean isEmpty(){
        if(top==null){
            return true;
        }
        else{
            return false;
        }
    }

    public void pushOperation(String element){
        try {
            Node newNode = new Node(Integer.parseInt(element));
            newNode.prev = top;
            top = newNode;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            return;
        }   
    }

    public void popOperation(){
        if(top == null){
            System.out.println("Stack is empty!");
        }
        else{
            top = top.prev;
        }
    }

    public void peekOperation(){
        if(top == null){
            System.out.println("Stack is empty!");
        }
        else{
            System.out.println(top.element);
        }
    }
    public static void main(String[] args) {
        StackOne stack = new StackOne();
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
