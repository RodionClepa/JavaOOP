public interface StackQueueInterface {
    public boolean isFull();
    public boolean isEmpty();
    public void pushOperation(String element);
    public void popOperation();
    public void peekOperation();
    public void printAll(); 
}