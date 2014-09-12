package model;

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

}
