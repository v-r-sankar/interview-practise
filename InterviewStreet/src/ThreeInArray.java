import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
				usingMinMaxCountLinearNew(intArr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public static void main1(String[] args) {
		int index = 10;
		int max = 50;
		int a[] = new int[index];
		Random r = new Random();
		for (int i = 0; i < index; i++) {
			int abs = Math.abs(r.nextInt(max));
			// System.out.print(abs + ",");
			a[i] = abs;
		}
		Arrays.toString(a);
		usingMinMaxCountLinearNew(a);
	}

	private static void usingMinMaxCountLinearNew(int[] intArr) {
		if (intArr != null) {
			int initialCapacity = intArr.length / 4;
			List<TwoOccurances> lowerList = new ArrayList<TwoOccurances>(
					initialCapacity);
			for (int i = 0; i < intArr.length; i++) {
				TwoOccurances value = new TwoOccurances();
				value.index = intArr[i];
				int idx = Arrays.binarySearch(
						lowerList.toArray(new TwoOccurances[0]), value);
				if (idx < 0) {
					idx = -(idx + 1);
					value.firstOccurance = idx;
					lowerList.add(idx, value);
				} else {
					value = lowerList.get(idx);
					value.secondOccurance = idx;
				}
			}

			List<TwoOccurances> higherList = new ArrayList<TwoOccurances>(
					initialCapacity);
			for (int i = intArr.length - 1; i > -1; i--) {
				TwoOccurances value = new TwoOccurances();
				value.index = intArr[i];
				int idx = Arrays.binarySearch(
						higherList.toArray(new TwoOccurances[0]), value);
				if (idx < 0) {
					idx = -(idx + 1);
					value.firstOccurance = higherList.size() - idx;
					higherList.add(idx, value);
				} else {
					value = higherList.get(idx);
					value.secondOccurance = value.firstOccurance;
					value.firstOccurance = higherList.size() - 1 - idx;
				}
			}

//			System.out.println(lowerList);
//			System.out.println(higherList);
			long totalCount = 0;
			TwoOccurances[] higherArray = higherList
					.toArray(new TwoOccurances[0]);
			for (TwoOccurances entry : lowerList) {
				TwoOccurances lowerValue = entry;
				int idx = Arrays.binarySearch(higherArray, entry);
				if (idx > -1) {
					TwoOccurances higerValue = higherArray[idx];
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
