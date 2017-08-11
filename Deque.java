import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdOut;

/*linked-list implementation*/
public class Deque<Item> implements Iterable<Item>
{
	private Node first, last;
	private int count;

	public Deque()
	{
		first = new Node();
		last = first;
	}

	private class Node 
	{
		Item item;
		Node next;
		Node previous;
	}

	public boolean isEmpty()
	{
		return first.item == null;
	}

	public int size()
	{
		return count;
	}

	public void addFirst(Item item)
	{
		if (item == null)
		{
			throw new java.lang.NullPointerException("can't add null item\n");
		}

		if (first.item == null)
		{
			first.item = item;
			count++;
		}

		else
		{
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
			oldfirst.previous = first;
			count++;
		}
	}

	public void addLast(Item item)
	{
		if (item == null)
		{
			throw new java.lang.NullPointerException("cant add null item\n");
		}

		if (first.item == null)
		{
			first.item = item;	
			count++;
		}

		else
		{
			Node oldlast = last;
			last = new Node();
			last.item = item;
			oldlast.next = last;
			last.previous = oldlast;
			last.next = null;
			count++;
		}
	}

	public Item removeFirst()
	{
		if (this.isEmpty())
		{
			throw new java.util.NoSuchElementException("cant remove from empty deque\n");
		}

		Item item = first.item;
		
		if (first.next == null)
		{
			first.item = null;	
		}

		else
		{
			first = first.next;
			first.previous = null;
		}

		count--;
		return item;
	}

	public Item removeLast()
	{
		if (this.isEmpty())
		{
			throw new java.util.NoSuchElementException("cant remove from empty deque\n");
		}
		
		Item item = last.item;
		
		if (last.previous == null)	
		{
			last.item = null;
		}

		else 
		{
			last = last.previous;
			last.next = null;
		}

		count--;
		return item;
	}

	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		private int i = 0;

		public boolean hasNext()
		{
			return i < count;
		}

		public Item next()
		{
			if (this.hasNext())
			{
				Item item = current.item;
				current = current.next;
				i++;
				return item;
			}
			else
			{
				throw new java.util.NoSuchElementException("no next item to iterate\n");
			}
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException("remove method not supported in iterator\n");
		}

	}

	public static void main(String[] args) 
	{
		 /*
		Deque<Integer> myD = new Deque<Integer>();

		
		int[] array = new int[5];
		for (int i=0;i<5;i++)
		{
			array[i] = i;
		}   
		
		myD.addFirst(array[0]);
		myD.addLast(array[1]);
		myD.addFirst(array[2]);
		myD.addLast(array[3]);
		myD.addFirst(array[4]);
		myD.removeFirst();

		StdOut.println(myD.size());

		for (int x : myD)
		{
			StdOut.println(x);
		}
		*/
		
	}

}

/*Array implementation
public class Deque<Item> implements Iterable<Item>
{
	private Item deQ[];
	private int first = 0, last = 0;

	private void resizeEnd(int capacity)
	{
		String[] copy = new String[capacity];
		for (int i = first; i <= last ; i++)
			copy[i] = deQ[i];
		deQ = copy;
	}

	private void expandFront(int capacity)
	{
		String[] copy = new String[capacity];
		for (int i = last-first+1; i < capacity; i++)
			copy[i] = deQ[i];
		first = last-first+1;
		last = last+first;
		deQ = copy;
	}

	private void shrinkFront(int capacity)
	{
		String[] copy = new String[capacity];
		for (int i = (first-1)/2; i<capacity; i++)
			copy[i] = deQ[i];
		deQ = copy;
		first = (first-1)/2;
		last = capacity-1;
	}

	public Deque()
		deQ = (Item[]) new Object[1];
		 
	public boolean isEmpty()
		return deQ[first] == null;

	public int size()
	{	
		if (!isEmpty())
			return (last - first + 1);
		else 
			return 0;
	}

	public void addFirst(Item item)
	{
		if (item == null)
			throw new java.lang.NullPointerException;
		if (!isEmpty())
		{
			if (first == 0)
				this.expandFront(deQ.length+(last-first+1)); 
			deQ[--first] = item;
		}
		else
			deQ[first] = item;
		
	}

	public void addLast(Item item)
	{
		if (item == null)
			throw new java.lang.NullPointerException;
		if (!isEmpty())
		{
			if (last == (deQ.length-1))
				this.resizeEnd(deQ.length+(last-first+1));
			deQ[++last] = item;
		}
		else
			deQ[last] = item;
	}

	public Item removeFirst()
	{
		if (!this.isEmpty())
		{
			Item item = deQ[first];
			deQ[first++] = null;
			if ( (last-first+1) < (last+1)/4 )
				this.shrinkFront(deQ.length-(last+1)/2);
			return item;
		}
		else
			throw new java.util.NoSuchElementException;
	}

	public Item removeLast()
	{
		if (!this.isEmpty)
		{
			Item item = deQ[last];
			deQ[last--] = null;
			if ( (last-first+1) < (deQ.length-first)/4 )
				this.resizeEnd(deQ.length-(last-first+1)*2);
			return item;
		}
		else
			throw new java.util.NoSuchElementException;
	}

	public Iterator<Item> iterator()
	{
		return new Array_Iterator();
	}

	private class Array_Iterator implements Iterator<Item>
	{
		private int i = first;

		boolean hasnext()
		{
			return i <= last;
		}

		Item next()
		{
			if (hasnext())
				return deQ[i++];
			else
				throw new java.util.NoSuchElementException;
		}

		void remove()
		{
			throw new java.lang.UnsupportedOperationException;
		}
	}

	public static void main(String[] args) 
	{
		 	
	}

}
*/


