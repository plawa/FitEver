package logic.diet;

public class KnapsackProblemDynamicProgrammingSolver {

	static int max(int a, int b) {
		return (a > b) ? a : b;
	}/*

	// Returns the maximum value that can be put in a knapsack of capacity W
	static int knapSack(int capacity, int wt[], int val[], int n) {
		// Base Case
		if (n == 0 || capacity == 0)
			return 0;

		// If weight of the nth item is more than Knapsack capacity W, then
		// this item cannot be included in the optimal solution
		if (wt[n - 1] > capacity)
			return knapSack(capacity, wt, val, n - 1);

		// Return the maximum of two cases:
		// (1) nth item included
		// (2) not included
		else
			return max(val[n - 1] + knapSack(capacity - wt[n - 1], wt, val, n - 1), knapSack(capacity, wt, val, n - 1));
	}
`*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
