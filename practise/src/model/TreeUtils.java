package model;

import java.util.Arrays;

public class TreeUtils {

	public static BinaryNode buildBasicTree() {

		BinaryNode right = buildBasicTree2();
		BinaryNode left = buildBasicTree1();
		BinaryNode root = new BinaryNode(null, left, right, "root");

		return root;
	}

	public static BinaryNode buildBasicTree1() {

		BinaryNode right = new BinaryNode(null, null, null, "right1");
		BinaryNode left = new BinaryNode(null, null, null, "left1");
		BinaryNode root = new BinaryNode(null, left, right, "root1");

		return root;
	}

	public static BinaryNode buildBasicTree2() {

		BinaryNode right = new BinaryNode(null, null, null, "right2");
		BinaryNode left = new BinaryNode(null, null, null, "left2");
		BinaryNode root = new BinaryNode(null, left, right, "root2");

		return root;
	}

	public static BinaryNode buildBinaryTreexyz() {

		BinaryNode right = new BinaryNode(null, null, null, "z");
		BinaryNode left = new BinaryNode(null, null, null, "x");
		BinaryNode root = new BinaryNode(null, left, right, "y");
		left.setParent(root);
		right.setParent(root);
		return root;
	}

	public static BinaryNode buildBinaryTreeabc() {

		BinaryNode right = new BinaryNode(null, null, null, "c");
		BinaryNode left = new BinaryNode(null, null, null, "a");
		BinaryNode root = new BinaryNode(null, left, right, "b");
		left.setParent(root);
		right.setParent(root);

		return root;
	}

	public static BinaryNode buildBinaryTreeabcd() {

		BinaryNode right = new BinaryNode(null, null, null, "c");
		BinaryNode left = new BinaryNode(null, new BinaryNode(null, null, null,
				"d"), null, "a");
		BinaryNode root = new BinaryNode(null, left, right, "b");

		return root;
	}

	public static BinaryNode buildBinaryTreeabce() {

		BinaryNode abcTree = buildBinaryTreeabc();
		BinaryNode xyzTree = buildBinaryTreexyz();
		BinaryNode root = new BinaryNode(null, abcTree,
				xyzTree, "e");
		abcTree.setParent(root);
		xyzTree.setParent(root);
		return root;
	}

	public static int findSortedPositionIndex(int[] a, int num) {
		return findSortedPositionIndex(a, num, false);
	}

	public static int findSortedPositionIndex(int[] a, int num,
			boolean shouldExist) {
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

		if (shouldExist) {
			return -1;
		}
		return start;
	}

	public static int findSortedPositionFirstIndex(int[] a, int num) {
		int start = 0;
		int end = a.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (num == a[mid]) {
				if (mid == 0) {
					return 0;
				}
				if (a[mid - 1] != num) {
					return mid;
				}
				end = mid - 1;
			} else if (num > a[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public static int findSortedPositionLastIndex(int[] a, int num) {
		int start = 0;
		int end = a.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (num == a[mid]) {
				if (mid == a.length - 1) {
					return mid;
				}

				if (a[mid + 1] != num) {
					return mid;
				}

				start = mid + 1;
			} else if (num > a[mid]) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public static int[] getRandomSortedArray(int length, int numRange) {
		int alen = (int) Math.round(Math.random() * length) + 2;

		int a[] = new int[alen];
		for (int i = 0; i < alen; i++) {
			a[i] = (int) Math.round(Math.random() * numRange);
		}
		Arrays.sort(a);
		// System.out.println(Arrays.toString(a));
		return a;
	}

}
