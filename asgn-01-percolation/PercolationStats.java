
public class PercolationStats {
	private Percolation percolation;
	private double [] 	thresholds;
	private double 		mean;
	private double		stddev;
	private double		conflo;
	private double		confhi;

    /* 
   	 * perform T independent computational experiments on an N-by-N grid
 	 */
    public PercolationStats(int N, int T) {
		int i, x, y;

		/* init private variables */
		thresholds 	= new double[T];
		mean 		= 0;
		stddev 		= 0;
		conflo 		= 0;
		confhi 		= 0;

		/* perform T experiments */
		for (i = 0; i < T; i ++) {
			thresholds[i] = 0;
			percolation = new Percolation(N);
			
			while (! percolation.percolates()) {
				x = StdRandom.uniform(N);
				y = StdRandom.uniform(N);

				//System.out.printf("%d, %d\n", x, y);
				percolation.open(x+1, y+1);
				thresholds[i] ++;
			}
		}		
	}

    /* 
 	 * sample mean of percolation threshold
 	 */
    public double mean() {
		mean = StdStats.mean(thresholds);

		return mean;
	}

    /* 
	 * sample standard deviation of percolation threshold
	 */
    public double stddev() {
		stddev = StdStats.stddev(thresholds);

		return stddev;
	}

    /* 
	 * returns lower bound of the 95% confidence interval
	 */ 
    public double confidenceLo() {
		double d = mean - 1.96 * stddev / Math.sqrt(thresholds.length);

		return d;
	}

    /* 
 	 * returns upper bound of the 95% confidence interval
	 */
    public double confidenceHi() {
		return mean + 1.96 * stddev / Math.sqrt(thresholds.length);
	}

    /* 
	 * test client, described below
	 */
    public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(200, 100);
		System.out.printf("mean                           = %f\n", ps.mean()/200/200);
		System.out.printf("stddev                         = %f\n", ps.stddev());
		//System.out.printf("95 percent confidence interval = %f\n", ps.confidenceLo());
		System.out.printf("95 percent confidence interval = %f, %f\n", ps.confidenceLo(), ps.confidenceHi());
	}
}
