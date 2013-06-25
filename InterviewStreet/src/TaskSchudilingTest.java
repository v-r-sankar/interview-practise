import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TaskSchudilingTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Random r = new Random();

		int n = 10000;
		int n2 = 10000;
		int n3 = 10000;

		List<DiMi> lst = new ArrayList<DiMi>();
		// for (int i = 0; i < n; i++) {
		// DiMi d = new DiMi();
		// d.mi = Math.abs(r.nextInt(n2)) + 1;
		// d.di = d.mi + Math.abs(r.nextInt(n3));
		// lst.add(d);
		// }

		Scanner in = new Scanner(System.in);

		int num = in.nextInt();

		lst.clear();
		for (int i = 0; i < num; i++) {
			DiMi e = new DiMi();
			e.di = in.nextInt();
			e.mi = in.nextInt();
			e.index = i;
			lst.add(e);
		}

		// System.out.println(lst);
		List<DiMi> lst1 = new ArrayList<DiMi>();
		for (int i = 0; i < num; i++) {
			DiMi d = new DiMi();
			DiMi diMi = lst.get(i);
			d.mi = diMi.mi;
			d.di = diMi.di;
			d.index = diMi.index;
			lst1.add(d);
		}
		long startTime = System.currentTimeMillis();
		int[] arr1 = null;
		arr1 = TaskSchedulingUsingBIT.processTaskScheduling(lst1);
		System.out.println("Time taken by new approach = " + (System.currentTimeMillis() - startTime));
		
		startTime = System.currentTimeMillis();
		List<DiMi> lst2 = new ArrayList<DiMi>();
		for (int i = 0; i < num; i++) {
			DiMi d = new DiMi();
			DiMi diMi = lst.get(i);
			d.mi = diMi.mi;
			d.di = diMi.di;
			d.index = diMi.index;
			lst2.add(d);
		}
		int[] arr2 = TaskSchedulingWorking.processTaskScheduling(lst2);
		System.out.println("Time taken by old approach = " + (System.currentTimeMillis() - startTime));
//		System.out.println(Arrays.toString(arr1));
//		System.out.println(Arrays.toString(arr2));
		System.out.println(Arrays.equals(arr1, arr2));
	}

}
