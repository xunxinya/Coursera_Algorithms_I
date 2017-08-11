import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.NullPointerException;
import java.lang.UnsupportedOperationException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[ ] randomQ;
	private int n = 0;
	private int[] order;

	public RandomizedQueue()
	{
		randomQ = (Item[]) new Object[1];
	}

	public boolean isEmpty()
	{
		return n == 0;
	}

	public int size()
	{
		return n;
	}

	private void resize(int capacity)
	{
		Item[ ] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = randomQ[i];
		randomQ = copy;
	}

	public void enqueue (Item item)
	{
		if (item == null)
			throw new java.lang.NullPointerException("cant add null item\n");

		if (n == randomQ.length)
			this.resize (2*randomQ.length);
		randomQ[n++] = item;
	}


	public Item dequeue()
	{
		if (this.isEmpty())
			throw new java.util.NoSuchElementException("cant remove from empty queue\n");

		int rdm = StdRandom.uniform(n);
		Item item = randomQ[rdm];
		randomQ[rdm] = randomQ[n-1];
		if (n < randomQ.length/4)
			this.resize(randomQ.length/2);
		n--;
		return item;
	}

	public Item sample()
	{
		if (this.isEmpty())
			throw new java.util.NoSuchElementException("cant sample from empty queue");

		int s = StdRandom.uniform(n);
		return randomQ[s];
	}

	public Iterator<Item> iterator()
	{
		order = new int[n];
		for (int i = 0; i < n; i++)
			order[i] = i;
		StdRandom.shuffle(order);

		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item>
	{
		
		private int count = 0;
	
		public boolean hasNext()
		{
			return count < n;
		}

		public Item next()
		{
			if (this.hasNext())
				return randomQ[order[count++]];

			else
				throw new java.util.NoSuchElementException("no next item to iterate\n");
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException("remove method not supported in iterator\n");
		}

	}

}