import java.util.Scanner;

public class QueueArrayDown implements StackQueueInterface {
    private int[] list;
    private int capacity;
    private int count;

    public QueueArrayDown(String capacity){
        try {
            this.capacity = Integer.parseInt(capacity);
            this.list = new int[this.capacity];
            this.count = this.capacity-1;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format");
            System.exit(0);
        }
    }
    
    @Override
    public boolean isFull(){
        if(count == -1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean isEmpty(){
        if(count==capacity-1){
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
                count-=1;
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
            count+=1;
        }
    }
    @Override
    public void peekOperation(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            System.out.println(list[count+1]);
        }
    }
    @Override
    public void printAll(){
        if(isEmpty()){
            System.out.println("Stack is empty!");
        }
        else{
            int i = capacity-1;
            System.out.println("========================================================");
            while(i!=count){
                System.out.println(list[i]);
                i-=1;
            }
        }
    }

    public static void main(String[] args) {
        String cmd = "";
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity: ");
        cmd = scanner.nextLine();
        QueueArrayDown stack = new QueueArrayDown(cmd);
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
            else if(cmd.equals("4")){
                stack.peekOperation();
            }
        }
        scanner.close();
    }
}
