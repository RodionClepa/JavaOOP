import java.util.Scanner;

public class StackTwo {

    private int[] list;
    private int count;
    private int capacity;

    public StackTwo(String capacity){
        try {
            this.capacity = Integer.parseInt(capacity);
            this.list = new int[this.capacity];
            this.count = 0;
        } catch (NumberFormatException e) {
            System.out.println("Invalod number format");
            System.exit(0);
        }
    }
    
    public boolean isFull(){
        if(count==capacity){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmpty(){
        if(count==0){
            return true;
        }
        else{
            return false;
        }
    }

    public void pushOperation(String element){
        if(isFull()){
            System.out.println("Not enough capacity!");
        }
        else{
            try {
                list[count]=Integer.parseInt(element);
                count+=1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        }
    }
    public void popOperation(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            count-=1;
        }
    }
    public void peekOperation(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            System.out.println(list[count-1]);
        }
    }
    public static void main(String[] args) {
        String cmd;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the new integer: ");
        cmd = scanner.nextLine();
        StackTwo stack = new StackTwo(cmd);
        cmd = "";
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
