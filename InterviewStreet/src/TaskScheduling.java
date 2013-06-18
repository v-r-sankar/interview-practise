import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class TaskScheduling {

	static class DiMi implements Comparable<DiMi> {
		private int di;
		private int mi;

		@Override
		public int compareTo(DiMi obj) {
			int diff1 = di - obj.di;
			if (diff1 != 0) {
				return diff1;
			} else {
				return mi - obj.mi;
			}
		}

		@Override
		public String toString() {
			return "DiMi [di=" + di + ", mi=" + mi + "]";
		}

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int t = in.nextInt();

		TreeSet<DiMi> ts = new TreeSet<DiMi>();
		int arr[] = new int[t];
		int mixMaxD = 0;
		for (int i = 0; i < t; i++) {
			DiMi e = new DiMi();
			e.di = in.nextInt();
			e.mi = in.nextInt();
			ts.add(e);
			arr[i] = getMinMaxDI(ts);
		}

		for (int i = 0; i < t; i++) {
			System.out.println(-1 * arr[i]);
		}

	}

	private static int getMinMaxDI(TreeSet<DiMi> ts) {
		Iterator<DiMi> it = ts.iterator();
		int delay = 0;
		int maxDelay = 0;
		while (it.hasNext()) {
			DiMi next = it.next();
			int localDelay = next.di - next.mi - delay;
			if (localDelay < maxDelay) {
				maxDelay = localDelay;
			}
			delay = delay + next.mi;
		}
		return maxDelay;
	}

}
