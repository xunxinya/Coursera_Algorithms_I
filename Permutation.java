import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation
{
	public static void main(String[] args) 
	{
		String input;
		int	k = Integer.parseInt(args[0]);
		int n = 0, count = 0;

		RandomizedQueue<String> myQ = new RandomizedQueue<String>();

		while (!StdIn.isEmpty())
		{
			input = StdIn.readString();
			myQ.enqueue(input);
			n++;
		}

		for (String s : myQ)
		{
			if (count < k && count < n)
			{
				StdOut.println(s);
				count++;
			}
		}
			
	}
}