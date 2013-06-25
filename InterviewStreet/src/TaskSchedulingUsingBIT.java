import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TaskSchedulingUsingBIT {

	private static class Point implements Comparable<Point> {
		private int index;
		private int maxDelay;

		@Override
		public int compareTo(Point o) {
			int diff = maxDelay - o.maxDelay;
			if (diff == 0) {
				return o.index - index;
			}
			return diff;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + index;
			result = prime * result + maxDelay;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point other = (Point) obj;
			if (index != other.index)
				return false;
			if (maxDelay != other.maxDelay)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Point [index=" + index + ", maxDelay=" + maxDelay + "]";
		}

	}

	public static void main1(String[] args) {
		int max = 10;
		int max_value = 1000;
		int arr[] = new int[max];
		Arrays.fill(arr, Integer.MIN_VALUE);

		Random r = new Random();

		for (int i = 0; i < max; i++) {
			int a = r.nextInt(max_value);

			int index = Arrays.binarySearch(arr, a);
			if (index < 0) {
				index = -(index + 1) - 1;
			}

			arr[index] = a;
		}

		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int num = in.nextInt();

		List<DiMi> lst = new ArrayList<DiMi>();
		for (int i = 0; i < num; i++) {
			DiMi e = new DiMi();
			e.di = in.nextInt();
			e.mi = in.nextInt();
			e.index = i;
			lst.add(e);
		}
		int[] arr = processTaskScheduling(lst);
		for (int i = 0; i < num; i++) {
			System.out.println(arr[i]);
		}

	}

	protected static int[] processTaskScheduling(List<DiMi> lst) {
		int n = lst.size();
		int arr[] = new int[n];
		Collections.sort(lst);

		int stepNum = 0;
		int currMaxIndex = 0;
		int currMax = 0;
		BIT bit = new BIT(n);
		List<Integer> maxIndexArr = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			DiMi next = lst.get(i);
			bit.update(i, next.mi);
			stepNum += next.mi;
			int delayLocal = stepNum - next.di;
			if (delayLocal < 0) {
				delayLocal = 0;
			}
			next.delay = delayLocal;
			if (delayLocal > currMax) {
				currMax = delayLocal;
				currMaxIndex = i;
				maxIndexArr.add(currMaxIndex);
			}
		}
		arr[n - 1] = currMax;

		int posarr[] = new int[n];
		for (int i = 0; i < n; i++) {
			DiMi diMi = lst.get(i);
			posarr[diMi.index] = i;
		}

		for (int i = n - 1; i > 0; i--) {
			int indexInSortedArray = posarr[i];
			DiMi diMi = lst.get(indexInSortedArray);
			int mi = diMi.mi;
			diMi.delay = 0;
			diMi.mi = 0;
			bit.update(indexInSortedArray, -mi);
			if (currMaxIndex > indexInSortedArray) {
				currMax -= mi;
				if (currMax < 0) {
					currMax = 0;
				}
				if (indexInSortedArray > 0) {
					int idx = Arrays.binarySearch(
							maxIndexArr.toArray(new Integer[0]),
							indexInSortedArray - 1);
					if (idx < 0) {
						idx = -(idx + 1);
					}
					for (int k = 0; k < idx; k++) {
						int index = maxIndexArr.get(k);
						int read = bit.read(index);
						DiMi previous = lst.get(index);
						int previousMax = read - previous.di;
						if (previousMax > currMax) {
							currMax = previousMax;
							currMaxIndex = index;
						}
					}
				}
			} else if (currMaxIndex == indexInSortedArray) {
				currMax = -1;
				stepNum = 0;
				maxIndexArr.clear();
				for (int j = 0; j < n; j++) {
					DiMi next = lst.get(j);
					if (next.mi == 0) {
						continue;
					}
					stepNum += next.mi;
					int delayLocal = stepNum - next.di;
					if (delayLocal < 0) {
						delayLocal = 0;
					}
					next.delay = delayLocal;
					if (delayLocal > currMax) {
						currMax = delayLocal;
						currMaxIndex = j;
						maxIndexArr.add(currMaxIndex);
					}
				}
			}
			arr[i - 1] = currMax;
		}
		return arr;
	}

}
