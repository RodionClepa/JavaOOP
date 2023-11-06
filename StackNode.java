import java.util.Scanner;

public class StackNode implements StackQueueInterface {
    private Node top;
    int capacity;
    int count;

    public StackNode(String capacity){
        try {
            this.capacity = Integer.parseInt(capacity);
            this.count = 0;
        } catch (NumberFormatException e) {
            System.out.println("Invalod number format");
            System.exit(0);
        }
    }
    @Override
    public boolean isEmpty(){
        if(top==null){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public boolean isFull(){
        if(count==capacity){
            return true;
        }
        else{
            return false;
        }
    }
    @Override
    public void pushOperation(String element){
        if(isFull()){
            System.out.println("Not enough capacity!");
        }
        else{
            try {
                Node newNode = new Node(Integer.parseInt(element));
                newNode.pointNode = top;
                top = newNode;
                count+=1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
                return;
            }
        }
    }
    @Override
    public void popOperation(){
        if(top == null){
            System.out.println("Stack is empty!");
        }
        else{
            top = top.pointNode;
            count--;
        }
    }
    @Override
    public void peekOperation(){
        if(top == null){
            System.out.println("Stack is empty!");
        }
        else{
            System.out.println(top.element);
        }
    }
    @Override
    public void printAll(){
        Node currentNode = top;
        System.out.println("====================================");
        while(currentNode!=null){
            System.out.println(currentNode.element);
            currentNode = currentNode.pointNode;
        }
    }
    public static void main(String[] args) {
        String cmd;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity: ");
        cmd = scanner.nextLine();
        StackNode stack = new StackNode(cmd);
        cmd = "";
        while(!cmd.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek, 4 - print All,0 - exit");
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
            else if(cmd.equals("4")){
                stack.printAll();
            }
        }
        scanner.close();
    }
}
