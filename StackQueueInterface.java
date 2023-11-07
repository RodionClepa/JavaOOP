public interface StackQueueInterface {
    public boolean isFull();
    public boolean isEmpty();
    public void pushOperation(Object element);
    public void popOperation();
    public void peekOperation();
    public void printAll(); 
}