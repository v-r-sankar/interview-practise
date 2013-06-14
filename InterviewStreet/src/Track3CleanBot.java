import java.util.Scanner;

public class Track3CleanBot {

	/* Head ends here */
	static void next_move(int posx, int posy, String[] g) {
		if ('d' == g[posx].charAt(posy)) {
			System.out.println("CLEAN");
			return;
		}
		int n = g.length;

		int dx = -1, dy = -1;
		int mx = posx, my = posy;
		for (int i = 0; i < n; i++) {
			if (dy == -1) {
				dx = i;
				if (posx % 2 == 0) {
					dy = g[i].indexOf('d');
				} else {
					dy = g[i].lastIndexOf('d');
				}
			} else {
				break;
			}
		}

		int xDistance = mx - dx;
		int yDistnace = my - dy;
		if (yDistnace > 0) {
			System.out.println("LEFT");
		} else if (yDistnace < 0) {
			System.out.println("RIGHT");
		}
		if (yDistnace == 0) {
			if (xDistance > 0) {
				System.out.println("UP");
			} else {
				System.out.println("DOWN");
			}
		}
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] pos = new int[2];
		String board[] = new String[5];
		for (int i = 0; i < 2; i++)
			pos[i] = in.nextInt();
		for (int i = 0; i < 5; i++)
			board[i] = in.next();
		next_move(pos[0], pos[1], board);
	}
}