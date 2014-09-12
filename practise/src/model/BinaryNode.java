package model;

public class BinaryNode {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BinaryNode other = (BinaryNode) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	public BinaryNode(BinaryNode parent, BinaryNode left, BinaryNode right,
			Object data) {
		super();
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.data = data;
	}

	private BinaryNode parent;

	@Override
	public String toString() {
		return "BinaryNode [data=" + data + ", left=" + left + ", right="
				+ right + ", parent=" + parent + "]";
	}

	private BinaryNode left;
	private BinaryNode right;
	private Object data;

	public BinaryNode getLeft() {
		return left;
	}

	public void setLeft(BinaryNode left) {
		this.left = left;
	}

	public BinaryNode getRight() {
		return right;
	}

	public void setRight(BinaryNode right) {
		this.right = right;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public BinaryNode getParent() {
		return parent;
	}

	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

}
