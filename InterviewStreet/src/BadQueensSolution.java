import java.util.ArrayList;
import java.util.List;

class Node {
	List<Node> children = new ArrayList<>(3);
	int x;
	int y;

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}

}

public class BadQueensSolution {

	int n = 9;
	private boolean[][] valid = new boolean[n][n];

	public static void main(String[] args) {
		long starTime = System.currentTimeMillis();
		BadQueensSolution solution = new BadQueensSolution();
		solution.solution();
		// solution.valid[1][2] = true;
		// solution.valid[3][1] = true;
		// solution.print(solution.valid);
		System.out.println(System.currentTimeMillis()-starTime);
	}

	public void solution() {
		Node root = new Node();
		root.x = -1;
		root.y = -1;
		for (int i = 0; i < n; i++) {
			root.children.addAll(buildTree(i, 0));
		}
		// printTree(root, 0);
		init();
		processTree(root, valid);

	}

	public void processTree(Node root, boolean[][] valid) {
		if (root.children.size() == 0) {
			for (int i = 0; i < n; i++) {
				boolean column = false;
				for (int j = 0; j < n; j++) {
					column = column || valid[i][j];
				}
				if (!column) {
					System.out.println("Solution not found for the column");
//					print(valid);
				}
			}
			System.out.println("One solution found");
//			print(valid);
		} else {
			boolean wentToChild = false;
			boolean[][] localValid = new boolean[n][n];
			for (Node child : root.children) {
				if (valid[child.x][child.y]) {
					for (int i = 0; i < n; i++) {
						for (int j = 0; j < n; j++) {
							localValid[i][j] = valid[i][j];
						}
					}
					getAllHorizantal(child.x, child.y, localValid);
					getAllVertical(child.x, child.y, localValid);
					getAllDiagonal(child.x, child.y, localValid);
					processTree(child, localValid);
					wentToChild = true;
				}
			}

			if (!wentToChild) {
				System.out.println("Solution not found for the column");
//				print(valid);
			}
		}
	}

	public void printTree(Node n, int depth) {
		System.out.print("<Node> ");
		for (int i = 0; i < depth; i++) {
			System.out.print(" ");
		}
		System.out.println(n);

		for (Node child : n.children) {
			printTree(child, depth + 1);
		}
		System.out.println("</Node>");
	}

	public void process() {
	}

	public List<Node> buildTree(int x, int y) {
		if (x < n && y < n) {
			List<Node> list = new ArrayList<Node>();
			Node node = new Node();
			node.x = x;
			node.y = y;
			list.add(node);
			for (int j = 0; j < n; j++) {
				if (j == x || j == x - 1 || j == x + 1) {
					continue;
				}
				List<Node> buildTree = buildTree(j, y + 1);
				if (buildTree != null) {
					node.children.addAll(buildTree);
				}
			}

			return list;
		}

		return null;
	}

	public void print(boolean[][] valid) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (valid[i][j])
					System.out.print("\tQ");
				else
					System.out.print("\t-");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void init() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				valid[i][j] = true;
			}
		}
	}

	public void getAllHorizantal(int x, int y, boolean[][] valid) {
		for (int i = 0; i < n; i++) {
			if (i != y) {
				valid[x][i] = false;
			}
		}
	}

	public void getAllVertical(int x, int y, boolean[][] valid) {
		for (int i = 0; i < n; i++) {
			if (i != x) {
				valid[i][y] = false;
			}
		}
	}

	public void getAllDiagonal(int x, int y, boolean[][] valid) {
		// GO NORTH WEST
		int xstart = x - 1;
		int ystart = y - 1;
		while (xstart > -1 && ystart > -1) {
			valid[xstart][ystart] = false;
			xstart = xstart - 1;
			ystart = ystart - 1;
		}

		// Go North east
		xstart = x + 1;
		ystart = y - 1;
		while (xstart < n && ystart > -1) {
			valid[xstart][ystart] = false;
			xstart = xstart + 1;
			ystart = ystart - 1;
		}

		// Go South east
		xstart = x + 1;
		ystart = y + 1;
		while (xstart < n && ystart < n) {
			valid[xstart][ystart] = false;
			xstart = xstart + 1;
			ystart = ystart + 1;
		}

		// Go South west
		xstart = x - 1;
		ystart = y + 1;
		while (xstart > -1 && ystart < n) {
			valid[xstart][ystart] = false;
			xstart = xstart - 1;
			ystart = ystart + 1;
		}
	}

	public boolean isStraigtLines(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (y2 - y1) * (x3 - x2) == (x2 - x1) * (y3 - y2);
	}

}