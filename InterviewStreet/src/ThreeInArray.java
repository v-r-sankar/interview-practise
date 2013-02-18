import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
				// usingTreeSets(intArr);
				// usingHashSets(intArr);
				// usingMinMaxCount(intArr);
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
			TreeMap<Integer, TwoOccurances> map = new TreeMap<Integer, TwoOccurances>();
			for (int i = 0; i < intArr.length; i++) {
				TwoOccurances value = null;// new TwoOccurances();
				value = map.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					map.put(intArr[i], value);
					value.firstOccurance = i;
				} else {
					value.secondOccurance = i;
				}
			}
			long totalCount = 0;
			List<Map.Entry<Integer, TwoOccurances>> list = new ArrayList<Map.Entry<Integer, TwoOccurances>>(
					map.entrySet());
			for (int index = 0; index < list.size(); index++) {
				int minCountFirst = 0;
				int maxCountFirst = 0;
				int minCountSecond = 0;
				int maxCountSecond = 0;

				Map.Entry<Integer, TwoOccurances> entry = list.get(index);
				TwoOccurances base = entry.getValue();

				for (int k = index - 1; k > -1; k--) {
					TwoOccurances smaller = list.get(k).getValue();
					if ((smaller.secondOccurance != -1 && base.firstOccurance > smaller.secondOccurance)
							|| base.firstOccurance > smaller.firstOccurance) {
						minCountFirst++;
					}
					if ((smaller.secondOccurance != -1 && base.secondOccurance > smaller.secondOccurance)
							|| base.secondOccurance > smaller.firstOccurance) {
						minCountSecond++;
					}
				}

				if (minCountFirst > 0 || minCountSecond > 0) {
					for (int k = index + 1; k < list.size(); k++) {
						TwoOccurances larger = list.get(k).getValue();
						if (base.firstOccurance < larger.firstOccurance
								|| base.firstOccurance < larger.secondOccurance) {
							maxCountFirst++;
						}
						if (base.secondOccurance != -1
								&& (base.secondOccurance < larger.firstOccurance || base.secondOccurance < larger.secondOccurance)) {
							maxCountSecond++;
						}
					}
					totalCount += (minCountFirst * maxCountFirst)
							+ ((minCountSecond - minCountFirst) * maxCountSecond);
				}
			}
			System.out.println(totalCount);
		}
	}
}