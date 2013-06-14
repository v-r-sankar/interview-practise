import java.util.Scanner;

public class Track2RescuePrincess {

	static void nextMove(int n, int x, int y, String[] g) {
		int px = -1, py = -1;
		int mx = -1, my = -1;
		for (int i = 0; i < n; i++) {
			if (px == -1) {
				py = i;
				px = g[i].indexOf('p');
			}
			if (mx == -1) {
				my = i;
				mx = g[i].indexOf('m');
			}
		}

		int xDistance = mx - px;
		int yDistnace = my - py;
		if (xDistance > 0) {
			System.out.println("LEFT");
		} else if (xDistance < 0) {
			System.out.println("RIGHT");
		}
		if (xDistance == 0) {
			if (yDistnace > 0) {
				System.out.println("UP");
			} else {
				System.out.println("DOWN");
			}
		}
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n, x, y;
		n = in.nextInt();
		x = in.nextInt();
		y = in.nextInt();
		in.useDelimiter("\n");
		String board[] = new String[n];

		for (int i = 0; i < n; i++) {
			board[i] = in.next().trim();
		}

		nextMove(n, x, y, board);

	}
}
