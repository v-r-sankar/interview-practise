package day1;

import java.util.Arrays;

import model.TreeUtils;

public class MedianOfSortedArrays {
	private static int getMedianOfTwoSortedArrays(int a[], int b[]) {
		int leftPointer = 0;
		int rightPointer = a.length - 1;
		int median = (a.length + b.length) / 2;

		while (leftPointer <= rightPointer) {
			int leftPositionSortedIndexInOtherArray = TreeUtils.findSortedPositionIndex(
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

			int rightPositionSortedIndexInOtherArray = TreeUtils.findSortedPositionIndex(
					b, a[rightPointer]);
			int rightPointerPositionInSortedArray = rightPositionSortedIndexInOtherArray
					+ rightPointer;

			if (rightPointerPositionInSortedArray == median) {
				return a[rightPointer];
			}
			if (rightPointerPositionInSortedArray <= median) {
				return b[rightPositionSortedIndexInOtherArray - 1
						+ (median - rightPointerPositionInSortedArray)];
			}
			
			int origLeftPointer = leftPointer;

			int tmp = rightPointer
					- (rightPointerPositionInSortedArray - median);
			if (tmp > leftPointer) {
				leftPointer = tmp;
			} else {
				leftPointer++;
			}
			
			tmp = origLeftPointer + (median-leftPointerPositionInSortedArray);
			if (tmp < rightPointer) {
				rightPointer = tmp;
			} else {
				rightPointer --;
			}
			// leftPointer++;
		}
		return -1;
	}

	public static void main(String[] args) throws java.lang.Exception {

		int a[] = TreeUtils.getRandomSortedArray(100,5000);
		int b[] = TreeUtils.getRandomSortedArray(100,5000);

		// a = new int[] { 37, 41, 43, 73, 99 };
		// b = new int[] { 21, 23, 36, 39, 58 };
		long startTime = System.nanoTime();
		if (a.length < b.length) {
			System.out.print(getMedianOfTwoSortedArrays(a, b));
		} else {
			System.out.print(getMedianOfTwoSortedArrays(b, a));
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
}