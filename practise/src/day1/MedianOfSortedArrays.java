package day1;

import java.util.Arrays;

public class MedianOfSortedArrays {
	private static int findSortedPositionIndex(int[] a, int num) {
		int start = 0;
		int end = a.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (num == a[mid]) {
				return mid;
			} else if (num > a[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return start;
	}

	private static int getMedianOfTwoSortedArrays(int a[], int b[]) {
		int leftPointer = 0;
		int rightPointer = a.length - 1;
		int median = (a.length + b.length) / 2;

		while (leftPointer <= rightPointer) {
			int leftPositionSortedIndexInOtherArray = findSortedPositionIndex(
					b, a[leftPointer]);
			int leftPointerPositionInSortedArray = leftPositionSortedIndexInOtherArray
					+ leftPointer;

			if (leftPointerPositionInSortedArray == median) {
				return a[leftPointer];
			}

			if (leftPointerPositionInSortedArray > median) {
				return b[leftPositionSortedIndexInOtherArray
						- (leftPointerPositionInSortedArray - median)];
			}

			int rightPositionSortedIndexInOtherArray = findSortedPositionIndex(
					b, a[rightPointer]);
			int rightPointerPositionInSortedArray = rightPositionSortedIndexInOtherArray
					+ rightPointer;

			if (rightPointerPositionInSortedArray == median) {
				return a[rightPointer];
			}
			if (rightPointerPositionInSortedArray <= median) {
				return b[rightPositionSortedIndexInOtherArray
						+ (median - rightPointerPositionInSortedArray)];
			}

			int tmp = rightPointer
					- (rightPointerPositionInSortedArray - median);
			if (tmp > leftPointer) {
				leftPointer = tmp;
			} else {
				leftPointer++;
			}
			// leftPointer++;
		}
		return -1;
	}

	public static void main(String[] args) throws java.lang.Exception {

		int a[] = getRandomSortedArray();
		int b[] = getRandomSortedArray();

		// a = new int[] { 37, 41, 43, 73, 99 };
		// b = new int[] { 21, 23, 36, 39, 58 };
		long startTime = System.nanoTime();
		if (a.length < b.length) {
			System.out.print(getMedianOfTwoSortedArrays(a, b));
		} else {
			System.out.print(getMedianOfTwoSortedArrays(a, b));
		}
		System.out.println(", " + (System.nanoTime() - startTime));

		startTime = System.nanoTime();
		int c[] = new int[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		Arrays.sort(c);
		System.out.println(c[c.length / 2] + ", "
				+ (System.nanoTime() - startTime));
	}

	private static int[] getRandomSortedArray() {
		int alen = (int) Math.round(Math.random() * 50000) + 2;

		int a[] = new int[alen];
		for (int i = 0; i < alen; i++) {
			a[i] = (int) Math.round(Math.random() * 10000);
		}
		Arrays.sort(a);
		// System.out.println(Arrays.toString(a));
		return a;
	}
}