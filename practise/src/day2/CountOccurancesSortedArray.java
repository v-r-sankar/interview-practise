package day2;

import java.util.Arrays;

import model.TreeUtils;

public class CountOccurancesSortedArray {

	public static void main(String[] args) {
		int[] a = TreeUtils.getRandomSortedArray(100000, 10);
		Arrays.sort(a);
		System.out.println(Arrays.toString(a));
		int num = 5;
		long starTime = System.nanoTime();
		method1(a, num);
		System.out.println(System.nanoTime()-starTime);
		
		starTime = System.nanoTime();
		method2(a, num);
		System.out.println(System.nanoTime()-starTime);
	}

	private static void method2(int[] a, int num) {
		int firstIndex = TreeUtils.findSortedPositionFirstIndex(a, num);
		if (firstIndex != -1) {
			int lastIndex = TreeUtils.findSortedPositionLastIndex(a, num);
			System.out.println(lastIndex - firstIndex + 1);
		} else {
			System.out.println(0);
		}
	}

	private static void method1(int[] a, int num) {
		int index = TreeUtils.findSortedPositionIndex(a, num, true);
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
