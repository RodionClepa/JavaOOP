public interface StackQueueInterface {
    public boolean isFull();
    public boolean isEmpty();
    public void pushOperation(Object element);
    public void popOperation();
    public Object peekOperation();
    public void printAll(); 
}