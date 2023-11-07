// Array up
public class StackArrayUp implements StackQueueInterface {

    private Object[] list;
    private int count;
    private int capacity;

    public StackArrayUp(int capacity){
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
            list[count]= element;
            count+=1;
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
    
}
