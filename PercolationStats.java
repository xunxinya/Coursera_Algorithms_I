import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;


public class PercolationStats
{
	private double pStats[ ];//store threshold p in each trial
	private int n, trials;
	
	/*constructor*/
	public PercolationStats(int n_input, int trials_input)
	{  	
		/*transfer the inputs to public*/
		n = n_input;
		trials = trials_input;
		/*check if inputs are valid*/
		if (n<=0 || trials<=0)
			throw new IllegalArgumentException("\ninput must be greater than 0");
		/*construct array to store threshold values in trials*/
		pStats = new double[trials];

		/*run Percolation trials and store the stats*/
		for (int i=0; i<trials_input; i++)
		{
			Percolation myP = new Percolation(n_input);
			while (!myP.percolates())
			{
				/*randomly open a site*/
				int randomIndex = StdRandom.uniform(0,n_input*n_input);
				int row = randomIndex/n_input +1;
				int col = randomIndex%n_input +1;
				/*keep opening until the system percolates*/
				myP.open(row,col);
			}
			/*compute the threshold*/
			double p = (double)myP.numberOfOpenSites() / (double)(n_input*n_input);
			/*store the threshold in this trial*/
			pStats[i] = p;
		}
	}

	public double mean()
	{
		return StdStats.mean(pStats);
	}

	public double stddev()
	{
		return StdStats.stddev(pStats);
	}

	public double confidenceLo()
	{	
		double mean = this.mean();
		double stddev = this.stddev();
		double confidenceLo = mean - 1.96*stddev / Math.sqrt(trials);
		return confidenceLo;
	}

	public double confidenceHi()
	{
		double mean = this.mean();
		double stddev = this.stddev();
		double confidenceHi = mean + 1.96*stddev / Math.sqrt(trials);
		return confidenceHi;
	}	

	/*store threshold into the array of statistics*/
	private void pStats(int i, double p)
	{
		pStats[i] = p;
	}

	public static void main(String[] args)
	{	
		boolean input_valid = true;
		int n_input=0, trials_input=0;

		/*read input n and trials from command line*/
		try
		{
			n_input = Integer.parseInt(args[0]);
			trials_input = Integer.parseInt(args[1]);
		}
		/*check if arguments are included*/
		catch (Exception e)
		{
			System.out.print("missing input");
			input_valid = false;
		}

		if (input_valid)
		{
			PercolationStats myStats = new PercolationStats(n_input,trials_input);

			/*run Percolation trials and store the stats
			for (int i=0; i<trials_input; i++)
			{
				Percolation myP = new Percolation(n_input);
				while (!myP.percolates())
				{
					*randomly open a site*
					int randomIndex = StdRandom.uniform(0,n_input*n_input);
					int row = randomIndex/n_input +1;
					int col = randomIndex%n_input +1;
					*keep opening until the system percolates*
					myP.open(row,col);
				}
				*compute the threshold*
				double p = (double)myP.numberOfOpenSites() / (double)(n_input*n_input);
				*store the threshold in this trial*
				myStats.pStats(i, p);
			}*/		
			System.out.print("mean\t\t\t" + "= " + myStats.mean() + "\n");
			System.out.print("stddev\t\t\t" + "= " + myStats.stddev() + "\n");
			System.out.print("95% confidence interval\t" + "= [" + myStats.confidenceLo() +",\t" + myStats.confidenceHi() + "]\n");	
		}	
	}

}