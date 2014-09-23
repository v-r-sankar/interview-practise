package day4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BreakStringIntoWords {

	private final static Set<String> dictionary = new HashSet<String>();
	private static int[][] arr;
	static {
		String dict[] = { "mobile", "samsung", "sam", "sung", "man", "mango",
				"icecream", "and", "go", "i", "like", "ice", "cream" };
		dictionary.addAll(Arrays.asList(dict));
		dictionary.add("i");
		dictionary.add("am");
		dictionary.add("test");

	}

	public static void main(String[] args) {
		String s = "ilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmangoilikeicecreamandmango";
		arr = new int[s.length()][s.length()];
		long start = System.nanoTime();
		System.out.print(canStringWordBreaken(s, 0, s.length() - 1));
		System.out.println("\t " + (System.nanoTime() - start));

		start = System.nanoTime();
		System.out.print(canStringWordBreakendp(s, 0, s.length() - 1));
		System.out.println("\t " + (System.nanoTime() - start));

	}

	private static boolean canStringWordBreakendp(String s, int start, int end) {

		if (end - start < 0) {
			return true;
		}

		if (arr[start][end] == 1) {
			return true;
		}

		if (arr[start][end] == 2) {
			return false;
		}

		String tmp = "";
		for (int i = start; i <= end; i++) {
			tmp += s.charAt(i);
			if (dictionary.contains(tmp)) {
				boolean subStrValue = canStringWordBreaken(s, i + 1, end);
				if (subStrValue) {
					arr[start][end] = 1;
					return true;
				}
			}
		}
		arr[start][end] = 2;
		return false;
	}

	private static boolean canStringWordBreaken(String s, int start, int end) {

		if (end - start < 0) {
			return true;
		}

		String tmp = "";
		for (int i = start; i <= end; i++) {
			tmp += s.charAt(i);
			if (dictionary.contains(tmp)) {
				boolean subStrValue = canStringWordBreaken(s, i + 1, end);
				if (subStrValue) {
					return true;
				}
			}
		}
		return false;
	}

}
