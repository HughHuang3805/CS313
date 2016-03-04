package Assignment2;

public class ArrayStack<AnyType> implements Stack<AnyType> {

	public static final int DEFAULT_CAPACITY = 1024;

	AnyType[] stack;
	private int topOfStack;
	int stackSize;

	public ArrayStack(){ 
		this(DEFAULT_CAPACITY); 
	}

	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity){
		topOfStack = -1;
		stack = (AnyType[]) new Object[capacity];
		stackSize = capacity;
	}

	public int size(){
		return topOfStack;
	}

	public boolean isEmpty(){
		return topOfStack == -1;
	}

	public void push(AnyType e) throws IllegalStateException{
		if(topOfStack + 1 < stackSize){
			topOfStack++;
			stack[topOfStack] = e;
		} else{
			System.out.println("The stack is full.");
		}
	}

	public AnyType top() throws IllegalStateException{
		return stack[topOfStack];
	}

	public AnyType pop() throws IllegalStateException{
		if(topOfStack >= 0){
			return stack[topOfStack--];
		} else{
			System.out.println("The stack is empty.");
			return null;
		}
	}

	public int getTopOfStack(){
		return topOfStack;
	}
}
