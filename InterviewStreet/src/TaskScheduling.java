import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TaskScheduling {

	static class DiMi implements Comparable<DiMi> {
		private int di;
		private int mi;
		private int stepCompleted;

		@Override
		public int compareTo(DiMi obj) {
			int diff1 = di - obj.di;
			if (diff1 != 0) {
				return diff1;
			}
			int i = mi - obj.mi;
			if (i == 0) {
				return -1;
			}
			return i;
		}

		public int getDelay() {
			int delay = stepCompleted - di;
			if (delay < 0) {
				delay = 0;
			}
			return delay;
		}

		@Override
		public String toString() {
			return "DiMi [di=" + di + ", mi=" + mi + "]";
		}

	}

	public static void main1(String[] args) {
		int index = 100000;
		int max = 100000;
		int a[] = new int[index];
		List<Integer> lst = new ArrayList<>();
		List<Integer> lst2 = new ArrayList<>();
		Arrays.fill(a, -1);
		Random r = new Random();
		long time1 = 0, time2 = 0;
		;
		for (int i = 0; i < index; i++) {
			long stime = System.currentTimeMillis();
			int abs = Math.abs(r.nextInt(max));
			// System.out.print(abs + ",");
			int idx = Arrays.binarySearch(lst.toArray(new Integer[0]), abs);
			if (idx < 0) {
				idx = -(idx + 1);
			}
			lst.add(idx, abs);
			time1 += (System.currentTimeMillis() - stime);

			stime = System.currentTimeMillis();
			lst2.add(abs);
			Collections.sort(lst2);
			time2 += (System.currentTimeMillis() - stime);
		}
		System.out.println("Time1 = " + time1 + "\nTime2 = " + time2);
		// System.out.println(lst);
		// System.out.println(lst2);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int t = in.nextInt();

		List<DiMi> lst = new ArrayList<DiMi>();
		int arr[] = new int[t];
		for (int i = 0; i < t; i++) {
			DiMi e = new DiMi();
			e.di = in.nextInt();
			e.mi = in.nextInt();
			addDiMi(lst, e);
			arr[i] = getMinMaxDI(lst);
		}

		for (int i = 0; i < t; i++) {
			System.out.println(arr[i]);
		}

	}

	private static int addDiMi(List<DiMi> lst, DiMi e) {
		int idx = Arrays.binarySearch(lst.toArray(new DiMi[0]), e);
		if (idx < 0) {
			idx = -(idx + 1);
			lst.add(idx, e);
			for (int i = idx; i < lst.size(); i++) {
				DiMi diMi = lst.get(i);
				if (i == 0) {
					diMi.stepCompleted = diMi.mi;
				} else {
					diMi.stepCompleted = lst.get(i - 1).stepCompleted + diMi.mi;
				}
			}
		} else {
			System.out.println("error");
		}
		return idx;
	}

	private static int getMinMaxDI(List<DiMi> ts) {
		Iterator<DiMi> it = ts.iterator();
		int maxDelay = 0;
		while (it.hasNext()) {
			DiMi next = it.next();
			int localDelay = next.getDelay();
			if (localDelay > maxDelay) {
				maxDelay = localDelay;
			}
		}
		return maxDelay;
	}

}
