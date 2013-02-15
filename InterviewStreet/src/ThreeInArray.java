import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

class Point {
	public int x;
	public int y;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}

class Triplet {
	int x;
	int y;
	int z;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
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
		Triplet other = (Triplet) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Triplet [x=" + x + ", y=" + y + ", z=" + z + "]";
	}

}

class TwoOccurances {
	public int firstOccurance = -1;
	public int secondOccurance = -1;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + firstOccurance;
		result = prime * result + secondOccurance;
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
		TwoOccurances other = (TwoOccurances) obj;
		if (firstOccurance != other.firstOccurance)
			return false;
		if (secondOccurance != other.secondOccurance)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TwoOccurances [firstOccurance=" + firstOccurance
				+ ", secondOccurance=" + secondOccurance + "]";
	}
}

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
				//usingMinMaxCount(intArr);
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
			int minArr[] = new int[intArr.length];
			int maxArr[] = new int[intArr.length];
			TreeMap<Integer, TwoOccurances> map = new TreeMap<Integer, TwoOccurances>();
			for (int i = 0; i < intArr.length; i++) {
				TwoOccurances value = null;// new TwoOccurances();
				value = map.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					map.put(intArr[i], value);
					value.firstOccurance = i;
					// Entry<Integer, TwoOccurances> lowerEntry = map
					// .lowerEntry(intArr[i]);
					// if (lowerEntry != null) {
					// TwoOccurances value2 = lowerEntry.getValue();
					// value.firstOccurance = value2.firstOccurance + 1;
					// }
				} else {
					// Entry<Integer, TwoOccurances> lowerEntry = map
					// .lowerEntry(intArr[i]);
					// if (lowerEntry != null) {
					// TwoOccurances value2 = lowerEntry.getValue();
					// value.secondOccurance = value2.secondOccurance + 1;
					// }
					value.secondOccurance = i;
				}
				// System.out.println("Iteration " + i + "\t value=" +
				// intArr[i]);
				// System.out.println(map);
			}
			// System.out.println(map);
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
				// System.out.print("Number " + i);
				// System.out.print("\tMin Count " + minCount);
				// System.out.print("\tMax Count " + maxCount);
				// System.out.print("\tMin Second Count " + minCountSecond);
				// System.out.println("\tMax Second count " + maxCountSecond);
			}
			System.out.println(totalCount);
		}
	}

	private static void usingMinMaxCount(int[] intArr) {
		if (intArr != null) {
			int minArr[] = new int[intArr.length];
			int maxArr[] = new int[intArr.length];
			TreeMap<Integer, TwoOccurances> map = new TreeMap<Integer, TwoOccurances>();
			for (int i = 0; i < intArr.length; i++) {
				TwoOccurances value = null;// new TwoOccurances();
				value = map.get(intArr[i]);
				if (value == null) {
					value = new TwoOccurances();
					map.put(intArr[i], value);
					value.firstOccurance = i;
					// Entry<Integer, TwoOccurances> lowerEntry = map
					// .lowerEntry(intArr[i]);
					// if (lowerEntry != null) {
					// TwoOccurances value2 = lowerEntry.getValue();
					// value.firstOccurance = value2.firstOccurance + 1;
					// }
				} else {
					// Entry<Integer, TwoOccurances> lowerEntry = map
					// .lowerEntry(intArr[i]);
					// if (lowerEntry != null) {
					// TwoOccurances value2 = lowerEntry.getValue();
					// value.secondOccurance = value2.secondOccurance + 1;
					// }
					value.secondOccurance = i;
				}
				// System.out.println("Iteration " + i + "\t value=" +
				// intArr[i]);
				// System.out.println(map);
			}
			// System.out.println(map);
			int totalCount = 0;
			for (Map.Entry<Integer, TwoOccurances> entry : map.entrySet()) {
				int minCount = 0;
				int maxCount = 0;
				int minCountSecond = 0;
				int maxCountSecond = 0;
				int i = entry.getKey();
				TwoOccurances base = entry.getValue();
				Entry<Integer, TwoOccurances> lowerEntry = map.lowerEntry(i);
				while (lowerEntry != null) {
					TwoOccurances smaller = lowerEntry.getValue();
					if ((smaller.secondOccurance != -1 && base.firstOccurance > smaller.secondOccurance)
							|| base.firstOccurance > smaller.firstOccurance) {
						minCount++;
					}
					if ((smaller.secondOccurance != -1 && base.secondOccurance > smaller.secondOccurance)
							|| base.secondOccurance > smaller.firstOccurance) {
						minCountSecond++;
					}
					lowerEntry = map.lowerEntry(lowerEntry.getKey());
				}

				if (minCount > 0 || minCountSecond > 0) {
					Entry<Integer, TwoOccurances> higherEntry = map
							.higherEntry(i);
					while (higherEntry != null) {
						TwoOccurances larger = higherEntry.getValue();
						if (base.firstOccurance < larger.firstOccurance
								|| base.firstOccurance < larger.secondOccurance) {
							maxCount++;
						}
						if (base.secondOccurance != -1
								&& (base.secondOccurance < larger.firstOccurance || base.secondOccurance < larger.secondOccurance)) {
							maxCountSecond++;
						}
						higherEntry = map.higherEntry(higherEntry.getKey());
					}
					totalCount += (minCount * maxCount)
							+ ((minCountSecond - minCount) * maxCountSecond);
				}
				// System.out.print("Number " + i);
				// System.out.print("\tMin Count " + minCount);
				// System.out.print("\tMax Count " + maxCount);
				// System.out.print("\tMin Second Count " + minCountSecond);
				// System.out.println("\tMax Second count " + maxCountSecond);
			}
			System.out.println(totalCount);
		}
	}

	private static void usingTreeSets(int[] intArr) {
		Set<Triplet> solutions = new LinkedHashSet<Triplet>();
		Set<Point> points = new

		// LinkedHashSet<Point>();

		TreeSet<Point>(new Comparator<Point>() {

			@Override
			public int compare(Point arg0, Point arg1) {
				int ydiff = arg0.y - arg1.y;
				if (ydiff != 0) {
					return ydiff;
				} else {
					return arg0.x - arg1.x;
				}
			}

		});
		Set<Integer> pointsSeenSofar = new TreeSet<Integer>();
		pointsSeenSofar.add(intArr[0]);
		for (int i = 1; i < intArr.length; i++) {
			int a = intArr[i];
			// System.out.println("Iteration " + i + "\tCurrent Elem:" + a);
			// if (!pointsSeenSofar.add(a)) {
			// continue;
			// }
			for (Point point : points) {
				if (point.y < a) {
					Triplet t = new Triplet();
					t.x = point.x;
					t.y = point.y;
					t.z = a;
					solutions.add(t);
				} else {
					break;
				}
			}
			for (int j = 0; j < i; j++) {
				if (a > intArr[j]) {
					Point p = new Point();
					p.x = intArr[j];
					p.y = a;
					points.add(p);
				}
			}
			// System.out.println(pointsSeenSofar);
			// System.out.println(points);
			// System.out.println(solutions);
		}
		System.out.println(solutions.size());
	}

	private static void usingHashSets(int[] intArr) {
		Set<Triplet> solutions = new LinkedHashSet<Triplet>();
		Set<Point> points = new LinkedHashSet<Point>();
		for (int i = 1; i < intArr.length; i++) {
			int a = intArr[i];
			for (Point point : points) {
				if (point.y < a) {
					Triplet t = new Triplet();
					t.x = point.x;
					t.y = point.y;
					t.z = a;
					solutions.add(t);
				}
			}
			for (int j = 0; j < i; j++) {
				if (a > intArr[j]) {
					Point p = new Point();
					p.x = intArr[j];
					p.y = a;
					points.add(p);
				}
			}
		}
		System.out.println(solutions);
		System.out.println(solutions.size());
	}
}