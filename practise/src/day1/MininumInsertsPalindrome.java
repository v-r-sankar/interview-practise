package day1;

import java.util.Arrays;

public class MininumInsertsPalindrome {

	public static void main(String[] args) {
		System.out.println(getMinimumInsertionsToMakePalindrome("abcdcba"));
	}

	private static int[][] arr;

	public static int getMinimumInsertionsToMakePalindrome(String s) {
		arr = new int[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			Arrays.fill(arr[i], -1);
		}

		return getMinimumInsertions(s, 0, s.length() - 1);
	}

	private static int getMinimumInsertions(String s, int start, int end) {
		if (arr[start][end] != -1) {
			return arr[start][end];
		}

		if (end - start < 1) {
			return 0;
		}

		int firstExclued = getMinimumInsertions(s, start, end - 1);
		int lastExcluded = getMinimumInsertions(s, start + 1, end);

		int min = 1 + Math.min(firstExclued, lastExcluded);
		if (s.charAt(start) == s.charAt(end)) {
			int bothIncluded = getMinimumInsertions(s, start + 1, end - 1);
			min = Math.min(bothIncluded, min);
		}
		arr[start][end] = min;
		return min;
	}
}
