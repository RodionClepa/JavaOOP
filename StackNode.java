
public class StackNode implements StackQueueInterface {
    private Node top;
    int capacity;
    int count;

    public StackNode(int capacity){
        this.capacity = capacity;
        this.count = 0;
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
    public void pushOperation(Object element){
        if(isFull()){
            System.out.println("Not enough capacity!");
        }
        else{
            try {
                Node newNode = new Node(element);
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
    public Object peekOperation(){
        if(top == null){
            System.out.println("Stack is empty!");
            return null;
        }
        else{
            return top.element;
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
}
