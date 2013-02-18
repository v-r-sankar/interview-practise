import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
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

	private static void usingMinMaxCountLinear(int[] intArr) {
		if (intArr != null) {
			TreeMap<Integer, TwoOccurances> lowerMap = new TreeMap<Integer, TwoOccurances>();
			for (int i = 0; i < intArr.length; i++) {
				TwoOccurances value = null;// new TwoOccurances();
				value = lowerMap.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					lowerMap.put(intArr[i], value);
					SortedMap<Integer, TwoOccurances> headMap = lowerMap
							.headMap(intArr[i]);
					value.firstOccurance = headMap.size();
				} else {
					SortedMap<Integer, TwoOccurances> headMap = lowerMap
							.headMap(intArr[i]);
					value.secondOccurance = headMap.size();
				}
			}

			TreeMap<Integer, TwoOccurances> higherMap = new TreeMap<Integer, TwoOccurances>();
			for (int i = intArr.length - 1; i > -1; i--) {
				TwoOccurances value = null;// new TwoOccurances();
				value = higherMap.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					higherMap.put(intArr[i], value);
					SortedMap<Integer, TwoOccurances> tailMap = higherMap
							.tailMap(intArr[i], false);
					value.firstOccurance = tailMap.size();
				} else {
					SortedMap<Integer, TwoOccurances> tailMap = higherMap
							.tailMap(intArr[i], false);
					value.secondOccurance = value.firstOccurance;
					value.firstOccurance = tailMap.size();
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
