
public class QueueArrayDown implements StackQueueInterface {
    private Object[] list;
    private int capacity;
    private int count;

    public QueueArrayDown(int capacity){
        this.capacity = capacity;
        this.list = new Object[this.capacity];
        this.count = this.capacity-1;
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
    public void pushOperation(Object element){
        if(isFull()){
            System.out.println("Not enough capacity!");
        }
        else{
            list[count]=element;
            count-=1;
        }
    }
    @Override
    public void popOperation(){
        if(isEmpty()){
            System.out.println("Queue is empty!");
        }
        else{
            for (int i = capacity-1; i > count+1; i--) {
                list[i] = list[i-1];
            }
            count+=1;
        }
    }
    @Override
    public Object peekOperation(){
        if(isEmpty()){
            System.out.println("queue is empty!");
            return null;
        }
        else{
            return list[capacity-1];
        }
    }
    @Override
    public void printAll(){
        if(isEmpty()){
            System.out.println("queue is empty!");
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
}