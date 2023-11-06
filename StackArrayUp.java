import java.util.Scanner;
// Array up
public class StackArrayUp implements StackQueueInterface {

    private int[] list;
    private int count;
    private int capacity;

    public StackArrayUp(String capacity){
        try {
            this.capacity = Integer.parseInt(capacity);
            this.list = new int[this.capacity];
            this.count = 0;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            System.exit(0);
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
    public boolean isEmpty(){
        if(count==0){
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
                list[count]=Integer.parseInt(element);
                count+=1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
            }
        }
    }
    @Override
    public void popOperation(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            count-=1;
        }
    }
    @Override
    public void peekOperation(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            System.out.println(list[count-1]);
        }
    }
    @Override
    public void printAll(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            int i = count;
            System.out.println("========================================================");
            while(i>0){
                System.out.println(list[i-1]);
                i-=1;
            }
        }
    }
    
    public static void main(String[] args) {
        String cmd;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity: ");
        cmd = scanner.nextLine();
        StackArrayUp stack = new StackArrayUp(cmd);
        cmd = "";
        while(!cmd.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek, 4 - print all, 0 - exit");
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
