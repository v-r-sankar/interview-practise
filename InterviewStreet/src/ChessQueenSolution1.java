import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;


public class ChessQueenSolution1 {

	public static void main(String[] args) {
		long starTime = System.currentTimeMillis();
		ChessQueenSolution1 solution = new ChessQueenSolution1();
		solution.process();
		// solution.valid[1][2] = true;
		// solution.valid[3][1] = true;
		// solution.print(solution.valid);
		System.out.println(System.currentTimeMillis() - starTime);
	}
	private int n = 8;
	private Stack<ChessBoardPoint> pointStack = new Stack<ChessBoardPoint>();

	private Map<Integer, boolean[][]> stateMap = new LinkedHashMap<Integer, boolean[][]>();

	private void createCopy(boolean[][] valid, boolean[][] toValid) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				toValid[i][j] = valid[i][j];
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

	public ChessBoardPoint getNextPoint(ChessBoardPoint current,
			boolean[][] valid) {
		if (current != null && current.x < n - 1) {

			// Try to get it from the next column. This needs to be added to the
			// stack
			for (int i = 0; i < n; i++) {
				if (valid[current.x + 1][i]) {
					ChessBoardPoint point = new ChessBoardPoint();
					point.x = current.x + 1;
					point.y = i;
					return point;
				}
			}

			// // If not, return from the same column. The current one needs to
			// be
			// // removed and new one added
			// for (int i = current.x+1; i < n; i++) {
			// if (valid[i][current.y]) {
			// ChessBoardPoint point = new ChessBoardPoint();
			// point.x = i;
			// point.y = current.y;
			// return point;
			// }
			// }
		}

		return null;
	}

	public void init(boolean[][] valid) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				valid[i][j] = true;
			}
		}
	}

	public boolean isStraigtLines(int x1, int y1, int x2, int y2, int x3, int y3) {
		return (y2 - y1) * (x3 - x2) == (x2 - x1) * (y3 - y2);
	}

	public void print() {
		for (Map.Entry<Integer, boolean[][]> entry : stateMap.entrySet()) {
			System.out.println(entry.getKey());
			print(entry.getValue());
		}
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

	public void process() {
		for (int i = 0; i <= n; i++) {
			boolean[][] valid = new boolean[n][n];
			init(valid);
			stateMap.put(i, valid);
		}

		ChessBoardPoint nextPoint = new ChessBoardPoint();
		pointStack.push(nextPoint);

		while (pointStack.size() != n) {
			boolean[][] valid = stateMap.get(pointStack.size());
			ChessBoardPoint currPoint = pointStack.peek();
			int x = currPoint.x;
			int y = currPoint.y;
			getAllDiagonal(x, y, valid);
			getAllHorizantal(x, y, valid);
			getAllVertical(x, y, valid);

//			System.out.println(currPoint);
//			print();
			nextPoint = getNextPoint(currPoint, valid);
			if (nextPoint == null) {
				boolean nextPointFound = false;

				while (!nextPointFound) {
					ChessBoardPoint current = pointStack.pop();
					valid = stateMap.get(pointStack.size());
					for (int i = current.y + 1; i < n; i++) {
						if (valid[current.x][i]) {
							nextPointFound = true;
							ChessBoardPoint point = new ChessBoardPoint();
							point.x = current.x;
							point.y = i;
							pointStack.push(point);
							boolean[][] toValid = stateMap.get(pointStack
									.size());
							createCopy(valid, toValid);
							x = point.x;
							y = point.y;
							getAllDiagonal(x, y, toValid);
							getAllHorizantal(x, y, toValid);
							getAllVertical(x, y, toValid);
							break;
						}
					}
				}
			} else {
				pointStack.push(nextPoint);
				boolean[][] toValid = stateMap.get(pointStack.size());
				createCopy(valid, toValid);
				x = nextPoint.x;
				y = nextPoint.y;
				getAllDiagonal(x, y, toValid);
				getAllHorizantal(x, y, toValid);
				getAllVertical(x, y, toValid);
			}
		}
		System.out.println(pointStack);
	}

}