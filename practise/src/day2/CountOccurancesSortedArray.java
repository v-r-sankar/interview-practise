package day2;

import java.util.Arrays;

import model.TreeUtils;

public class CountOccurancesSortedArray {

	public static void main(String[] args) {
		int[] a = TreeUtils.getRandomSortedArray(100, 100);
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		int index = TreeUtils.findSortedPositionIndex(a, 5, true);
		if (index != -1) {
			int count = 1;
			int pointer = index - 1;
			while (pointer > -1) {
				if (a[pointer--] != a[index]) {
					break;
				}
				count++;
			}

			pointer = index + 1;
			while (pointer < a.length) {
				if (a[pointer++] != a[index]) {
					break;
				}
				count++;
			}
			System.out.println(count);
		} else {
			System.out.println(0);
		}
	}
}
