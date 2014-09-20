package day3;

import model.BinaryNode;
import model.TreeUtils;

public class InoderTraversalWithoutRecursion {

	public static void main(String[] args) {
		BinaryNode root = TreeUtils.buildBinaryTreeabce();
		
		BinaryNode node = root;
		
		while (node.getRight() != null) {
			node = node.getRight();
		}
		while (node != null) {
			System.out.println(node.getData());
			node = getInorderPredecessor(node);
		}

	}

	public static BinaryNode getInorderPredecessor(BinaryNode node) {
		if (node.getLeft() != null) {
			BinaryNode tmp = node.getLeft();
			while (tmp.getRight() != null) {
				tmp = tmp.getRight();
			}
			return tmp;
		} else {
			BinaryNode parent = node.getParent() ;

			while (parent != null) {
				if (parent.getRight() == node) {
					return parent;
				}
				node = parent;
				parent = parent.getParent();
			}
			return null;
		}

	}
	
	public static BinaryNode getInorderSuccessor(BinaryNode node) {
		if (node.getRight() != null) {
			BinaryNode tmp = node.getRight();
			while (tmp != null) {
				tmp = tmp.getLeft();
			}
			return tmp;
		} else {
			BinaryNode parent = node.getParent();

			while (parent != null) {
				if (parent.getRight() == node) {
					return parent;
				}
				node = parent;
			}
		}

		return null;
	}

}
