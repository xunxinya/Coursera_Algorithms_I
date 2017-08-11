import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;

public class Percolation 
{
	private boolean[ ] opened; // store the status of sit e open/blocked as true/false
	private int[ ] id, size; // store the parent of site
	private int n;

	/* constructor */
	public Percolation(int input)
	{
		if (input <= 0)
			throw new java.lang.IllegalArgumentException("input must be greater than 0\n");
		
		else
		{
			/* transfer the input to public */
			n = input;
			/* keep track of the open/block situation of each site */
			opened = new boolean[n*n];
			for (int i = 0; i < n*n; i++)
				opened[i] = false;
			/* keep track of the parent of each site, including virtual top and bottom site */
			id = new int[n*n + 2];
			for (int i = 0; i < (n*n+2); i++)
				id[i] = i;
			/* keep track of the size of connected union each site belongs to */
			size = new int[n*n+2];
			for (int i = 0; i < (n*n); i++)
				size[i] = 1;
			for (int i = n*n; i < (n*n+2); i++)
				size[i] = i;
		}
	}

	/* find the root of each site*/
	private int root(int index)
	{
		while (id[index] != index)
		{
			id[index] = id[id[index]];
			index = id[index];
		}
		return index;
	}

	/*union open sites*/
	private void union(int a, int b)
	{
		int aRoot = this.root(a);
		int bRoot = this.root(b);
		if (size[aRoot] > size[bRoot])
		{
			id[bRoot] = aRoot;
			size[aRoot] += size[bRoot];
		}
		else
		{
			id[aRoot] = bRoot;
			size[bRoot] += size[aRoot];
		}

	}

	/*open site and union it with surrounding open sites*/
	public void open(int row, int col)
	{
		if (row>n || row<1 || col>n || col<1)
			throw new java.lang.IndexOutOfBoundsException ("can't open site out of range");
		else 
		{
			int index = n*(row-1)+col-1;
			if (!opened[index])
			{
				opened[index] = true;			
				/*union with site on top except for the first row*/
				if (row != 1)
					if (opened[index-n])
						this.union(index,(index-n));
				/*union with site on bottom except for the bottom row*/
				if (row != n)
					if (opened[index+n])
						this.union(index,(index+n));
				/*union with site on left except for the left coloum*/
				if (col != 1)
					if (opened[index-1])
						this.union(index,(index-1));			
				/*union with site on right except for the right column*/
				if (col != n)
					if (opened[index+1])
						this.union(index,(index+1));
				/*connect any open site on the first row to a virtual top site*/
				if (row == 1)
					this.union(n*n, index);
				/*connect any open site on the bottom row to a virtual bottom site
				if (row == n)
					this.union((n*n+1),index);
				*/
			} 
		}
	}

	/*check if certain site is open*/
	public boolean isOpen(int row, int col)
	{
		if (row>n || row<=0 || col>n || col<=0)
			throw new java.lang.IndexOutOfBoundsException ("can't check site out of range");
		else 
			return opened[n*(row-1)+col-1];
	}

	/*check if certain site is full*/
	public boolean isFull(int row, int col)
	{
		int index = n*(row-1)+col-1;
		if (row>n || row<=0 || col>n || col<=0)
			throw new java.lang.IndexOutOfBoundsException ("can't check site out of range");
		else if (!opened[index])
			return false;
		else
		{
			if (this.root(index) == this.root(n*n))
				return true;
			else
				return false;	
		}
	}

	/*check if system percolates*/
	public boolean percolates()
	{
		/*check if virtual top site is connected to any bottom site*/
		for (int i=n*(n-1); i<n*n; i++)
			if (this.root(i) == this.root(n*n))
				return true;

		return false;
	}

	/*check the number of open sites*/
	public int numberOfOpenSites()
	{
		int count = 0;
		for (int i = 0; i < n*n; i++)
			if (opened[i])
				count += 1;
		return count;	
	}

	/*test client (optional)*/
	public static void main(String[] args) 
	{
		
	} 

}