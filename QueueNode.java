
public class QueueNode implements StackQueueInterface {
    private Node first;
    int capacity;
    int count;

    public QueueNode(String capacity){
        this.capacity = Integer.parseInt(capacity);
        this.count = 0;
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
    public void pushOperation(Object element){
        if(isFull()){
            System.out.println("Not enough capacity!");
        }
        else{
            try {
                Node newNode = new Node(element);
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
    public Object peekOperation(){
        if(first == null){
            System.out.println("queue is empty!");
            return null;
        }
        else{
            return first.element;
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
}
