import java.util.Scanner;

public class QueueArrayUp implements StackQueueInterface {
    private int[] list;
    private int count;
    private int capacity;

    public QueueArrayUp(String capacity){
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
            System.out.println("queue is empty!");
        }
        else{
            for (int i = 0; i < count-1; i++) {
                list[i] = list[i+1];
            }
            count-=1;
        }
    }
    @Override
    public void peekOperation(){
        if(isEmpty()){
            System.out.println("queue is empty!");
        }
        else{
            System.out.println(list[0]);
        }
    }
    @Override
    public void printAll(){
        if(isEmpty()){
            System.out.println("queue is empty!");
        }
        else{
            int i = 0;
            System.out.println("========================================================");
            while(i<count){
                System.out.println(list[i]);
                i+=1;
            }
        }
    }
    
    public static void main(String[] args) {
        String cmd;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the capacity: ");
        cmd = scanner.nextLine();
        QueueArrayUp queue = new QueueArrayUp(cmd);
        cmd = "";
        while(!cmd.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek, 4 - print all, 0 - exit");
            cmd = scanner.nextLine();
            if(cmd.equals("1")){
                System.out.print("Enter the new integer: ");
                cmd = scanner.nextLine();
                queue.pushOperation(cmd);
            }
            else if(cmd.equals("2")){
                queue.popOperation();
            }
            else if(cmd.equals("3")){
                queue.peekOperation();
            }
            else if(cmd.equals("4")){
                queue.printAll();
            }
        }
        scanner.close();
    }
}
