import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DifferenceEqualsK {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line1 = br.readLine();
			String[] arr = line1.split(" ");
			int[] intArr = null;
			int difference = -1;
			if (arr.length >= 2) {
				int arrayItems = Integer.parseInt(arr[0]);
				difference = Integer.parseInt(arr[1]);

				String line2 = br.readLine();
				arr = line2.split(" ");
				if (arr.length == arrayItems) {
					intArr = new int[arrayItems];
					for (int i = 0; i < arrayItems; i++) {
						intArr[i] = Integer.parseInt(arr[i]);
					}
				}
			}
			if (intArr != null && difference >0) {
				Set<Integer> set = new HashSet<Integer>(intArr.length*3/2);
				for (int i : intArr) {
					set.add(i);
				}
				int count = 0;
				for (Integer elem : set) {
					int key = elem + difference;
					if (set.contains(key)) {
						count ++;
					}
				}
				System.out.println(count);
			}
			//interativeApproach(intArr, difference);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
		}
	}

	private static void interativeApproach(int[] intArr, int difference) {
		if (intArr != null && difference >= 0) {
			int count = 0;
			Arrays.sort(intArr);
			int i = 0, j = intArr.length-1;
			while (i < j) {
				int diff = intArr[i] - intArr[j] + difference;
				if (diff == 0) {
					count ++;
					i++;
					j--;
				} else if (diff > 0) {
					i++;
				} else {
					j--;
				}
			}
			System.out.println(count);
		}
	}
}