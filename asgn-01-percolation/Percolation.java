
public class Percolation {
	private enum Status {
		OPEN, BLOCKED, FULL
	}
	
	private Status[][] sites;
	private WeightedQuickUnionUF wquf;
	private int N;
	private int tvs, bvs;

	/*
 	 * create N-by-N grid, with all sites blocked
 	 */
    public Percolation(int N) {
		int i, j;

		/* check N */
		if (N <= 0) {
			System.out.printf("ERROR: illegal argument N %d", N);
			throw new IllegalArgumentException();
		}

		this.N 	= N;
		sites 	= new Status[N][N];
		wquf 	= new WeightedQuickUnionUF(N*N + 2);

		for (i = 0; i < N; i ++) {
			wquf.union(i, 			N*N);
			wquf.union(N*(N-1)+i, 	N*N+1);
		}
		tvs = N*N;
		bvs = N*N+1;

		for (i = 0; i < N; i ++) {
			for (j = 0; j < N; j ++) {
				sites[i][j] = Status.BLOCKED;
			}
		}
	}

	/*
	 * open site (row i, column j) if it is not already
	 */ 
    public void open(int i, int j) {
		int m;
		int n;

		if (isOpen(i, j))
			return;

		sites[i-1][j-1] = Status.OPEN;

		/* top */
		if (i > 1) {
			m = i - 1;
			n = j;

			wquf.union((i-1)*N+(j-1), (m-1)*N+(n-1));
		}
		

		/* bottom */
		if (i < N) {
			m = i + 1;
			n = j;

			//wquf.union(i*N+j-1, m*N+n-1);
			wquf.union((i-1)*N+(j-1), (m-1)*N+(n-1));
		}

		/* left */
		if (j > 1) {
			m = i;
			n = j - 1;

			//wquf.union(i*N+j-1, m*N+n-1);
			wquf.union((i-1)*N+(j-1), (m-1)*N+(n-1));
		}

		/* right */
		if (j < N) {
			m = i;
			n = j + 1;

			//wquf.union(i*N+j-1, m*N+n-1);
			wquf.union((i-1)*N+(j-1), (m-1)*N+(n-1));
		}
	}

	/*
	 * is site (row i, column j) open?
	 */ 
    public boolean isOpen(int i, int j) {
		if (sites[i-1][j-1] == Status.OPEN || sites[i-1][j-1] == Status.FULL) 
			return true;
		else
			return false;
	}

	/* 
	 * is site (row i, column j) full?
	 */ 
    public boolean isFull(int i, int j) {
		if (wquf.connected(i*N+j-1, N*N)) {
			return true;
		}
		else
			return false;
	}

	/*
	 * does the system percolate?
	 */ 
    public boolean percolates() {
		return wquf.connected(N*N, N*N+1);
	}

}
