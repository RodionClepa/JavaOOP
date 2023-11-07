public class StackArrayDown implements StackQueueInterface{

    private Object[] list;
    private int capacity;
    private int count;

    public StackArrayDown(int capacity){
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
            int i = count;
            System.out.println("========================================================");
            while(i!=capacity-1){
                System.out.println(list[i+1]);
                i+=1;
            }
        }
    }

}