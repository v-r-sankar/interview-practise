package day3;

import java.util.Arrays;

public class LongestPalindromicSubstring {

	private static int[][] arr;

	public static void main(String[] args) {
		String s = "madamadmadam";
		arr = new int[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++) {
			Arrays.fill(arr[i], -1);
		}
		System.out
				.println(getLongestPalindromicSubstring(s, 0, s.length() - 1));
		;
	}

	private static int getLongestPalindromicSubstring(String s, int start,
			int end) {
		if (end - start < 0) {
			return 0;
		}

		if (end == start) {
			return 1;
		}

		if (end - start == 1) {
			if (s.charAt(start) == s.charAt(end)) {
				return 2;
			}
		}

		if (arr[start][end] != -1) {
			return arr[start][end];
		}

		int a = getLongestPalindromicSubstring(s, start + 1, end);
		int b = getLongestPalindromicSubstring(s, start, end - 1);
		int c = getLongestPalindromicSubstring(s, start + 1, end - 1);
		if (s.charAt(start) == s.charAt(end) && c == end - start - 1) {
			c = c + 2;
		}

		int max = Math.max(a, b);
		max = Math.max(max, c);

		arr[start][end] = max;

		return max;
	}
}
