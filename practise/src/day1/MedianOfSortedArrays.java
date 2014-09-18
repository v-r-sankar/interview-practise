package day1;

import java.util.Arrays;

import model.TreeUtils;

public class MedianOfSortedArrays {

	private static double getMedianOfTwoSortedArrays(int[] a, int aStart,
			int aEnd, int[] b, int bStart, int bEnd) {

		int aMid = (aStart + aEnd) / 2;
		int bMid = (bStart + bEnd) / 2;

		if (aStart == aEnd) {
			if ((bEnd - bStart + 1) % 2 == 0) {
				return a[aStart] > b[bMid] ? b[bMid] : a[aStart];
			} else {
				return (double) (a[aStart] + b[bMid]) / 2;
			}
		}

		if (aStart + 1 == aEnd) {
			if (bStart + 1 == bEnd) {
				int first = Math.max(a[aStart], b[bStart]);
				int second = Math.min(a[aEnd], b[bEnd]);
				return (first + second) / 2.0;
			}

			boolean aStartLess = a[aStart] < b[bMid];
			boolean aEndGreater = a[aEnd] > b[bMid];

			if ((bEnd - bStart + 1) % 2 == 0) {
				if (aStartLess && aEndGreater) {
					return (b[bMid] + Math.min(a[aEnd], b[bMid + 1])) / 2.0;
				} else if (aStartLess && !aEndGreater) {
					return (b[bMid] + Math.max(a[aEnd], b[bMid - 1])) / 2.0;
				} else if (!aStartLess && aEndGreater) {
					aStartLess = a[aStart] < b[bMid + 1];
					aEndGreater = a[aEnd] > b[bMid + 1];
					if (aStartLess && aEndGreater) {
						return (a[aStart] + b[bMid + 1]) / 2.0;
					} else if (aStartLess && !aEndGreater) {
						return (a[aStart] + a[aEnd]) / 2.0;
					} else if (!aStartLess && aEndGreater) {
						return (a[aStart] + b[bMid + 1]) / 2.0;
					} else {
						return -1;
					}
				} else {
					return -1;
				}
			} else {

				if (aStartLess && aEndGreater) {
					return b[bMid];
				} else if (aStartLess && !aEndGreater) {
					return Math.max(a[aEnd], b[bMid - 1]);
				} else if (!aStartLess && aEndGreater) {
					return Math.min(a[aStart], b[bMid + 1]);
				} else {
					return -1;
				}
			}
		}
		if (a[aMid] < b[bMid]) {
			return getMedianOfTwoSortedArrays(a, aMid, aEnd, b, bStart, bEnd
					- (aMid - aStart));
		} else {
			return getMedianOfTwoSortedArrays(a, aStart, aMid, b, bStart
					+ (aEnd - aMid), bEnd);
		}

	}

	private static int getMedianOfTwoSortedArraysNotWorking(int a[], int b[]) {
		int leftPointer = 0;
		int rightPointer = a.length - 1;
		int median = (a.length + b.length) / 2;

		while (leftPointer <= rightPointer) {
			int leftPositionSortedIndexInOtherArray = TreeUtils
					.findSortedPositionIndex(b, a[leftPointer]);
			int leftPointerPositionInSortedArray = leftPositionSortedIndexInOtherArray
					+ leftPointer;

			if (leftPointerPositionInSortedArray == median) {
				return a[leftPointer];
			}

			if (leftPointerPositionInSortedArray > median) {
				return b[leftPositionSortedIndexInOtherArray
						- (leftPointerPositionInSortedArray - median)];
			}

			int rightPositionSortedIndexInOtherArray = TreeUtils
					.findSortedPositionIndex(b, a[rightPointer]);
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

			tmp = origLeftPointer + (median - leftPointerPositionInSortedArray);
			if (tmp < rightPointer) {
				rightPointer = tmp;
			} else {
				rightPointer--;
			}
			// leftPointer++;
		}
		return -1;
	}

	public static void main(String[] args) throws java.lang.Exception {

		int a[] = TreeUtils.getRandomSortedArray(3500, 100000);
		int b[] = TreeUtils.getRandomSortedArray(4050, 100000);

//		System.out.println(Arrays.toString(a));
//		System.out.println(Arrays.toString(b));
//		a = new int[] { 287, 485, 656, 917 };
//		b = new int[] { 53, 438, 680, 724, 908, 956 };
		long startTime = System.nanoTime();
		if (a.length < b.length) {
			System.out.print(getMedianOfTwoSortedArrays(a, 0, a.length - 1, b,
					0, b.length - 1));
		} else {
			System.out.print(getMedianOfTwoSortedArrays(b, 0, b.length - 1, a,
					0, a.length - 1));
		}
		System.out.println(", " + (System.nanoTime() - startTime));

		startTime = System.nanoTime();
		int c[] = new int[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		Arrays.sort(c);
		int length = c.length;
		if (length % 2 == 1) {
			System.out.println(c[length / 2] + ", "
					+ (System.nanoTime() - startTime));
		} else {
			System.out.println((c[length / 2] + c[length / 2 - 1]) / 2.0 + ", "
					+ (System.nanoTime() - startTime));
		}
	}
}