package ParenthesisChecker;

/** Donovan Colton
 *  CS3410
 *  SPR2016
 *  Program3 / GenericStack.java
 */
public class GenericStack<T> {
    
    private final int SIZE = 1000;
    private T stack[];
    private int top = -1;

    public GenericStack(){
        stack = (T[]) new Object[SIZE];
    }
    
    public  void push(T object){
        stack[++top] = object;
    }
    
    public T pop(){
        if (top == -1)
            return null;
        T topElement = stack[top--];
        return topElement;
    }
    
    public T peek(){
        if (top == -1)
            return null;
        else{
            T topElement = stack[top];
            return topElement;
        }
    }
}
