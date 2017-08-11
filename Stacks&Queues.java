public class StackOfStrings
{
	public StackOfStrings()
	{

	} 

	public void push(String item)
	{

	}

	public String pop()
	{

	}
	
	public boolean isEmpty()
	{

	}

}

public static void main(String[] args)
{
	StackOfStrings stack = new StackOfStrings();
	while (!StdIn.isEmpty())
	{
		String s = StdIn.readString();
		if (s.equals("-")) 
			StdOut.print(stack.pop());
		else
			stack.push(s);
	}
}

public class LinkedStackOfStrings
{
	private Node first = null;

	private class Node
	{
		String item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public void push(String item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public String pop()
	{
		String item = first.item
		first = first.next;
		return item;
	}

}

public class FixedCapacityStackOfStrings
{
	private String[] s;
	private int N = 0;

	public FixedCapacityStackOfStrings(int capacity)
	{
		s = new String[capacity];
	}

	public boolean isEmpty()
	{
		return N == 0;
	}

	public void push(String item)
	{
		s[N++] = item;
		/*		array[i++] equals to: 
					array[i];
					i++;
		while 	array[++i] equals to:
					i++;
					array[i];
		*/
	}

	public String pop()
	{
		String item = s[--N];
		s[N] = null;
		return item;
	}

}

public class ResizingArrayStackOfStrings
{
	private String[] s;
	private int N = 0;

	public ResizingArrayStackOfStrings()
	{
		s = new String[1];
	}

	public void push(String item)
	{
		if (N== s.length)
			resize(2*s.length);
		s[N++] = item;
	}

	private void resize(int capacity)
	{
		String[] copy = new String[capacity];
		for (int i = 0; i < N; i++)
			copy[i] = s[i];
		s = copy;
	}

	public String pop()
	{
		String item = s[--N];
		s[N] = null;
		if (N>0 && N == s.length/4) 
			resize(s.length/2);
		return item;
	}
}

public class LinkedQueueOfStrings
{
	private Node first, last;

	private class Node()
	{
		String item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public void enqueue(String item)
	{
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty())
			first = last;
		else 
			oldlast.next = last;
	}

	public String dequeue()
	{
		String item = first.item;
		first = first.next;
		if (isEmpty())
			last = null;
		return item;
	}

}

/*parameterized stack*/
Stack<Apple> s = new Stack<Apple>();
Apple a = new Apple();
Orange b = new Orange();
s.push(a);
s.push(b);//welcome compile time errors (avoid run time errors)
a = s.pop();

public class Stack<Item>
{
	private Node first = null;

	private class Node
	{
		Item item;
		Node next;
	}

	public boolean isEmpty()
		return first == null;

	public void push (Item item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public Item pop()
	{
		Item item = first.item;
		first = first.next;
		return item;
	}
}

public class FixedCapacityStack<Item>
{
	private Item[] s;
	private int N = 0;

	public FixedCapacityStack(int capacity)
	{
		s = (Item[]) new Object[capacity];
	}

	public boolean isEmpty()
		return N == 0;

	public void push (Item item)
		s[N++] = item;

	public Item pop()
		return s[--N];

}


//*ITERATOR*//

public interface Iterable<Item>
{
	Iterator<Item> iterator();
}

public interface Iterator<Item>
{
	boolean hasNext();
	Item next();
}

/*for each statement*/
/*shorthand*/
for (String s ： stack)
	StdOut.println(s);
/*longhand equivalent code
Iterator<String> i = stack.iterator();
while (i.hasNext())
{
	String s = i.next();
	StdOut.println(s);
}
*/


import java.util.Iterator;

/*linked-list implementation*/
public class Stack<Item> implements Iterable<Item>
{
	/*....*/
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;

		public boolean hasNext()
			return current != null;

		public Item next()
		{
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}

/*array implementation*/
public class Stack<Item> implements Iterable<Item>
{
	/*...*/
	public Iterator<Item> iterator()
	{
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item>
	{
		private int i = N;

		public boolean hasNext()
			return i>0;

		public Item next()
			return s[--i];
	}
}

/*BAG*/
public class Bag<Item> implements Iterable<Item>
{
	Bag()
	void add(Item x)
	int size()
	Iterable<Item> iterator()
}