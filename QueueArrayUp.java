
public class QueueArrayUp implements StackQueueInterface {
    private Object[] list;
    private int count;
    private int capacity;

    public QueueArrayUp(int capacity){
        this.capacity = capacity;
        this.list = new Object[this.capacity];
        this.count = 0;
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
    public void pushOperation(Object element){
        if(isFull()){
            System.out.println("Not enough capacity!");
        }
        else{
            list[count]=element;
            count+=1;
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
    
}
