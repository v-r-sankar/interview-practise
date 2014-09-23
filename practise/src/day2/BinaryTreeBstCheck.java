package day2;

import java.util.Comparator;

import model.BinaryNode;
import model.TreeUtils;

@SuppressWarnings("rawtypes")
public class BinaryTreeBstCheck {

	public static void main(String[] args) {
		BinaryNode root = TreeUtils.buildBinaryTreeabce();
		BinaryNode left = TreeUtils.buildBinaryTreeabce();
		BinaryNode right = TreeUtils.buildBinaryTreeabce();
		root.setLeft(left);
		root.setRight(right);
		left.setParent(root);
		right.setParent(root);
		root.print();
		System.out.println(isBinaryTreeBST(root, new Comparator<BinaryNode>() {

			@Override
			public int compare(BinaryNode o1, BinaryNode o2) {
				return o1.getData().toString()
						.compareTo(o2.getData().toString());
			}
		}));

	}

	private static boolean isBinaryTreeBST(BinaryNode root,
			Comparator<BinaryNode> comparator) {
		return isBinaryTreeBSTInternal(root, new MinMaxDecoratingComparator(
				comparator), MinMaxDecoratingComparator.MIN_VALUE,
				MinMaxDecoratingComparator.MAX_VALUE);
	}

	private static boolean isBinaryTreeBSTInternal(BinaryNode root,
			Comparator comparator, Object min, Object max) {
		if (comparator.compare(root, min) <= 0) {
			return false;
		}
		if (comparator.compare(root, max) >= 0) {
			return false;
		}
		if (root.getLeft() != null) {
			if (!isBinaryTreeBSTInternal(root.getLeft(), comparator, min, root)) {
				return false;
			}
		}
		if (root.getRight() != null) {
			if (!isBinaryTreeBSTInternal(root.getRight(), comparator, root, max)) {
				return false;
			}
		}
		return true;
	}

}

@SuppressWarnings("rawtypes")
class MinMaxDecoratingComparator implements Comparator {
	private Comparator comparator;

	public static final Object MAX_VALUE = new Object();
	public static final Object MIN_VALUE = new Object();

	public MinMaxDecoratingComparator(Comparator comparator) {
		this.comparator = comparator;
	}

	@Override
	public int compare(Object o1, Object o2) {

		if (o1 == MIN_VALUE || o2 == MAX_VALUE) {
			return -1;
		}
		if (o1 == MAX_VALUE || o2 == MIN_VALUE) {
			return 1;
		}

		return comparator.compare(o1, o2);
	}

}
