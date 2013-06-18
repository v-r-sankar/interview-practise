import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BillBoards {

	private static long[] ps;
	private static int k;
	private static long cache[][];
	private static Map<String, Long> cache1;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		k = in.nextInt();
		ps = new long[n];
		cache = new long[n][k];
		cache1 = new HashMap<String, Long>(n * k / 2);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				cache[i][j] = -1;
			}
		}
		for (int i = 0; i < n; i++) {
			ps[i] = in.nextInt();
		}

		System.out.println(recurse1(0, 0));
	}

	public static long recurse1(int index, int continous) {
		if (index == ps.length) {
			return 0;
		} else if (continous < k) {
			String key = index + "," + continous;
			Long a = cache1.get(key);
			if (a != null) {
				return a;
			} else {
				a = recurse1(index + 1, 0);
				long b = ps[index] + recurse1(index + 1, continous + 1);
				long max = (a > b) ? a : b;
				cache1.put(key, max);
				return max;
			}
		} else if (continous == k) {
			long max = recurse1(index + 1, 0);
			return max;
		}

		return 0;
	}

	public static long recurse2(int index, int continous) {
		if (index == ps.length) {
			return 0;
		} else if (continous < k) {
			long a = cache[index][continous];
			if (a != -1) {
				return a;
			} else {
				a = recurse1(index + 1, 0);
				long b = ps[index] + recurse1(index + 1, continous + 1);
				long max = (a > b) ? a : b;
				cache[index][continous] = max;
				return max;
			}
		} else if (continous == k) {
			long max = recurse1(index + 1, 0);
			return max;
		}

		return 0;
	}
}
