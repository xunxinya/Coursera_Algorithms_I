public static void main(String[] args) {
	int N = StdIn.readInt();
	QuickUnion uf = new QuickUnion(N);
	whiile (!StdIn.isEmpty()){
		int p = StdIn.readInt();
		int q = StdIn.readInt();
		if (!uf.connected(p,q)) {
			uf.union(p,q);
			StdOut.printlin ("union" + p + " " + q);
		}
	}
}
 
 /*Quick-find*/
public class QuickFindUF
{
	/*create an array pointer id*/
	private int[] id;
 	
 	/*constructor to assign original value to each of the N points */
 	/*constructor the same name as class name*/
	public QuickFindUF（int N)
	{
		/*allocate a memory of length size-of-int * N */
		id = new int[N];
		for (int i=0; i <N; i++)
			id[i]=i; 
	}

	/*method to check if two point is connected*/
	public boolean connected(int p, int q)
		return id[p] == id[q];
	

	/*Quick-find*/
	/*method to connect 2 points or as well combine 2 sets */
	/*void return as method can change class member (array) through in-class pointer(id)*/
	public void union(int p, int q) 
	{
		if  ( connected(p,q) )
			return;
		else {
			int p_id = id[p];
			int q_id = id[q];
			/*cant use N here as not defined in this class*/
			for (int i=0; i<id.length; i++) {
				if (id[i]==q_id) 
					id[i]=p_id;
				
			}

		}

	}
}

/*Quick-union*/
public class QuickUnion
{
	private int[] id;
	private int[] sz;

	public QuickUnion（int N)
	{
		id = new int[N];
		sz = new int[N];
		for (int i=0; i <N; i++)
			id[i]=i; 
			sz[i]=0;
	}

	/*root of i is id[id[id[id[.....id[i]]]]] */
	private int root(int i){
		while (i != id[i])
		{
			/*path compression*/
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	/*method to check if two point is connected - they have the same root*/
	public boolean connected (int p, int q){
		return root(p) == root(q);
	}

	/*keep track of tree depth*/
	private int[] sz;
	sz = new int[id.length];
	for (int s=0 ) 
		sz[i] = 1;

	/*method to set p's root to q's root*/
	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		/*unweighted
		id[i] = j; 
		*/
		/*weighted*/
		if (i == j) return;
		if (sz[i] < sz[j]) {
			id[i] = j; 
			sz[j] += sz[i];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
}
/*Summary

#M union-find operations on a set of N objects

#Algorithms				#Worst-case time
Quick-find						M N
Quick-union						M N
weighted QU 					N + M logN
QU+path compression				N + M logN
weighted QU+path compression	N + M lg*N


*/