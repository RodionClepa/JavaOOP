import java.util.Scanner;

public class QueueNode implements StackQueueInterface {
    private Node first;
    int capacity;
    int count;

    public QueueNode(String capacity){
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
        if(first==null){
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
                if(first==null){
                    first = newNode;
                }
                else{
                    Node currentNode = first;
                    while(currentNode.pointNode!=null){
                        currentNode = currentNode.pointNode;
                    }
                    currentNode.pointNode = newNode;
                }
                count+=1;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format");
                return;
            }
        }
    }
    @Override
    public void popOperation(){
        if(first == null){
            System.out.println("queue is empty!");
        }
        else{
            first = first.pointNode;
            count--;
        }
    }
    @Override
    public void peekOperation(){
        if(first == null){
            System.out.println("queue is empty!");
        }
        else{
            System.out.println(first.element);
        }
    }
    @Override
    public void printAll(){
        Node currentNode = first;
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
        QueueNode queue = new QueueNode(cmd);
        cmd = "";
        while(!cmd.equals("0")){
            System.out.println("1 - push, 2 - pop, 3 - peek, 4 - print All,0 - exit");
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
