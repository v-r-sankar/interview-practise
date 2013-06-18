import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class ThreeInArray {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int[] intArr = null;
			String line1 = br.readLine();
			int arrayItems = Integer.parseInt(line1);

			String line2 = br.readLine();
			String[] arr = line2.split(" ");
			if (arr.length == arrayItems) {
				intArr = new int[arrayItems];
				for (int i = 0; i < arrayItems; i++) {
					intArr[i] = Integer.parseInt(arr[i]);
				}
			}
			if (intArr != null) {
				usingMinMaxCountLinear(intArr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main1(String[] args) {
		int index = 1000;
		int max = 500;
		int a[] = new int[index];
		Random r = new Random();
		for (int i = 0; i < index; i++) {
			int abs = Math.abs(r.nextInt(max));
			// System.out.print(abs + ",");
			a[i] = abs;
		}
		usingMinMaxCountLinear(a);
	}

	private static void usingMinMaxCountLinear(int[] intArr) {
		if (intArr != null) {
			TreeMap<Integer, TwoOccurances> lowerMap = new TreeMap<Integer, TwoOccurances>();
			List<Integer> lowerList = new ArrayList<Integer>();
			for (int i = 0; i < intArr.length; i++) {
				TwoOccurances value = null;// new TwoOccurances();
				value = lowerMap.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					lowerMap.put(intArr[i], value);
					int idx = Arrays.binarySearch(
							lowerList.toArray(new Integer[0]), intArr[i]);
					if (idx < 0) {
						idx = -(idx + 1);
					}
					lowerList.add(idx, intArr[i]);
					value.firstOccurance = idx;
				} else {
					int idx = Arrays.binarySearch(
							lowerList.toArray(new Integer[0]), intArr[i]);
					value.secondOccurance = idx;
				}
			}

			TreeMap<Integer, TwoOccurances> higherMap = new TreeMap<Integer, TwoOccurances>();
			List<Integer> higherList = new ArrayList<Integer>();
			for (int i = intArr.length - 1; i > -1; i--) {
				TwoOccurances value = null;// new TwoOccurances();
				value = higherMap.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					higherMap.put(intArr[i], value);
					int idx = Arrays.binarySearch(
							higherList.toArray(new Integer[0]), intArr[i]);
					if (idx < 0) {
						idx = -(idx + 1);
					}
					higherList.add(idx, intArr[i]);
					value.firstOccurance = higherList.size() - 1 - idx;
				} else {
					value.secondOccurance = value.firstOccurance;
					int idx = Arrays.binarySearch(
							higherList.toArray(new Integer[0]), intArr[i]);
					value.firstOccurance = higherList.size() - 1 - idx;
				}
			}

			long totalCount = 0;
			for (Map.Entry<Integer, TwoOccurances> entry : lowerMap.entrySet()) {
				Integer key = entry.getKey();
				TwoOccurances lowerValue = entry.getValue();
				TwoOccurances higerValue = higherMap.get(key);
				if (higerValue != null) {
					if (lowerValue.firstOccurance != -1
							&& higerValue.firstOccurance != -1) {
						totalCount += lowerValue.firstOccurance
								* higerValue.firstOccurance;
					}
					if (lowerValue.secondOccurance != -1
							&& higerValue.secondOccurance != -1) {
						totalCount += (lowerValue.secondOccurance - lowerValue.firstOccurance)
								* higerValue.secondOccurance;
					}
				}
			}
			System.out.println(totalCount);
		}
	}
}
