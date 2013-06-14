import java.util.Scanner;

public class BillBoards {

	/**
	 * @param args
	 */

	private static long maxProfit = 0;
	private static long[] ps;
	private static long[] maxremaining;
	private static int k;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		k = in.nextInt();
		ps = new long[n];
		maxremaining = new long[n];
		for (int i = 0; i < n; i++) {
			ps[i] = in.nextInt();
		}
		maxremaining[n - 1] = ps[n - 1];
		for (int i = n - 2; i > 0; i--) {
			maxremaining[i] = maxremaining[i + 1] + ps[i];
		}
		recurse(0, 0, 0);
		System.out.println(maxProfit);
	}

	public static void recurse(int profit, int index, int continous) {
		if (index == ps.length) {
			if (profit > maxProfit) {
				maxProfit = profit;
			}
			return;
		}
		if (profit + maxremaining[index] < maxProfit) {
			return;
		}
		if (continous < k) {
			recurse(profit, index + 1, 0);
			profit += ps[index];
			recurse(profit, index + 1, continous + 1);

		}
		if (continous == k) {
			recurse(profit, index + 1, 0);
		}
	}
}
