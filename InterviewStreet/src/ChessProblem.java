import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

class ChessBoardPoint {
	int x;
	int y;

	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + "]";
	}

}

public class ChessProblem {

	public static void main(String[] args) {
		long starTime = System.currentTimeMillis();
		ChessProblem solution = new ChessProblem();
		solution.process(0,0);
	
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

	public void process(int startX, int startY) {
		Set<String> solutins = new TreeSet<String>();
		for (int i = 0; i <= n; i++) {
			boolean[][] valid = new boolean[n][n];
			init(valid);
			stateMap.put(i, valid);
		}

		ChessBoardPoint nextPoint = new ChessBoardPoint();
		nextPoint.x = startX;
		nextPoint.y = startY;
		pointStack.push(nextPoint);

		boolean firstTime = false;
		while (pointStack.size() != n) {
			int size = pointStack.size();
			if (size == 1 && firstTime) {
				break;
			}
			firstTime = true;
			
			boolean[][] valid = stateMap.get(size);
			boolean[][] fromValid = stateMap.get(size-1);
			createCopy(fromValid, valid);
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

				while (!nextPointFound && pointStack.size() > 0) {
					ChessBoardPoint current = pointStack.pop();
					valid = stateMap.get(pointStack.size());
					for (int i = current.y + 1; i < n; i++) {
						if (valid[current.x][i]) {
							nextPointFound = true;
							ChessBoardPoint point = new ChessBoardPoint();
							point.x = current.x;
							point.y = i;
							pointStack.push(point);
							break;
						}
					}
				}
			} else {
				pointStack.push(nextPoint);
			}
			
			if (pointStack.size() == n) {
				System.out.println(solutins.add(pointStack.toString()));
				boolean nextPointFound = false;
				while (!nextPointFound && pointStack.size() > 0) {
					ChessBoardPoint current = pointStack.pop();
					valid = stateMap.get(pointStack.size());
					for (int i = current.y + 1; i < n; i++) {
						if (valid[current.x][i]) {
							nextPointFound = true;
							ChessBoardPoint point = new ChessBoardPoint();
							point.x = current.x;
							point.y = i;
							pointStack.push(point);
							break;
						}
					}
				}
			}
		}
		System.out.println(solutins);
		System.out.println(solutins.size());
	}

}