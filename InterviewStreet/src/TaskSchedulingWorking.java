import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TaskSchedulingWorking {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);


		int n = in.nextInt();
		List<DiMi> lst = new ArrayList<DiMi>();
		for (int i = 0; i < n; i++) {
			DiMi e = new DiMi();
			e.di = in.nextInt();
			e.mi = in.nextInt();
			e.index = i;
			lst.add(e);
		}

		
		int arr[] = processTaskScheduling(lst);
		
		for (int i = 0; i < n; i++) {
			System.out.println(arr[i]);
		}
	}

	protected static int[] processTaskScheduling(List<DiMi> lst) {
		int t = lst.size();
		int arr[] = new int[t];
		Collections.sort(lst);
		int currMaxIndex = getMinMaxDI(lst);
		int currMax = lst.get(currMaxIndex).delay;
		arr[t - 1] = currMax;

		int posarr[] = new int[t];
		for (int i = 0; i < t; i++) {
			DiMi diMi = lst.get(i);
			posarr[diMi.index] = i;
		}

		for (int i = t - 1; i > 0; i--) {
			int indexInSortedArray = posarr[i];
			DiMi diMi = lst.get(indexInSortedArray);
			int mi = diMi.mi;
			diMi.delay = 0;
			diMi.mi = 0;
			if (currMaxIndex > indexInSortedArray) {
				currMax -= mi;
				for (int j = 0; j < indexInSortedArray; j++) {
					if (lst.get(j).delay > currMax) {
						currMax = lst.get(j).delay;
						currMaxIndex = j;
					}
				}
				if (indexInSortedArray < t - 1) {
					for (int j = indexInSortedArray + 1; j <= currMaxIndex; j++) {
						lst.get(j).delay -= mi;
					}
				}
			} else if (currMaxIndex == indexInSortedArray) {
				int localCurrMax = currMax - mi;
				for (int j = 0; j < indexInSortedArray; j++) {
					if (lst.get(j).delay > localCurrMax) {
						localCurrMax = lst.get(j).delay;
						currMaxIndex = j;
					}
				}
				
				if (localCurrMax > (currMax - mi)){
					currMax = localCurrMax;
				} else {
					currMaxIndex = getMinMaxDI(lst);
					currMax = lst.get(currMaxIndex).delay;
				}
			}
			arr[i - 1] = currMax;
		}

		return arr;
	}

	private static int getMinMaxDI(List<DiMi> ts) {
		int maxDelay = 0;
		int maxDelayIndex = 0;
		int stepNum = 0;
		for (int i = 0; i < ts.size(); i++) {
			DiMi next = ts.get(i);
			stepNum += next.mi;
			int delayLocal = stepNum - next.di;
			if (delayLocal < 0) {
				delayLocal = 0;
			}
			next.delay = delayLocal;

			if (delayLocal > maxDelay) {
				maxDelay = delayLocal;
				maxDelayIndex = i;
			}
		}
		return maxDelayIndex;
	}

}
