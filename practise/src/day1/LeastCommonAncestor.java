package day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import model.BinaryNode;
import model.TreeUtils;

public class LeastCommonAncestor {

	public static void main(String[] args) {

		BinaryNode root = TreeUtils.buildBasicTree();
		System.out
				.println(getLeastCommonAncestor(root, new BinaryNode(null,
						null, null, "left2"), new BinaryNode(null, null, null,
						"right2")));
	}

	private static BinaryNode getLeastCommonAncestor(BinaryNode root,
			BinaryNode first, BinaryNode second) {
		List<BinaryNode> firstParentList = getParentList(root, first);
		List<BinaryNode> secondParentList = getParentList(root, second);

		int i = firstParentList.size() - 1;
		int j = secondParentList.size() - 1;

		while (i > -1 && j > -1) {
			if (firstParentList.get(i).equals(secondParentList.get(j))) {
				i--;
				j--;
			} else {
				return firstParentList.get(i + 1);
			}
		}

		if (i == -1) {
			return firstParentList.get(0);
		}

		if (j == -1) {
			return secondParentList.get(0);
		}

		return null;

	}

	private static List<BinaryNode> getParentList(BinaryNode root,
			BinaryNode element) {
		List<BinaryNode> list = new ArrayList<BinaryNode>();
		Stack<ParentNodeDecorator> stack = new Stack<ParentNodeDecorator>();
		stack.push(new ParentNodeDecorator(root, null));
		ParentNodeDecorator currItem = null;
		while (!stack.isEmpty()) {
			currItem = stack.pop();
			if (element.equals(currItem.getNode())) {
				break;
			}
			if (currItem.getNode().getLeft() != null) {
				stack.push(new ParentNodeDecorator(
						currItem.getNode().getLeft(), currItem));
			}

			if (currItem.getNode().getRight() != null) {
				stack.push(new ParentNodeDecorator(currItem.getNode()
						.getRight(), currItem));
			}
		}

		while (currItem != null) {
			list.add(currItem.getNode());
			currItem = currItem.getParent();
		}
		return list;
	}

}

class ParentNodeDecorator {

	private ParentNodeDecorator parent;
	private BinaryNode node;

	public ParentNodeDecorator(BinaryNode node, ParentNodeDecorator parent) {
		this.node = node;
		this.parent = parent;
	}

	public ParentNodeDecorator getParent() {
		return parent;
	}

	public BinaryNode getNode() {
		return node;
	}

}
