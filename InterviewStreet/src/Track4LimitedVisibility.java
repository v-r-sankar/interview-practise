import java.util.Scanner;

public class Track4LimitedVisibility {

	// private static Map<Integer, List<Integer>> ds = new
	// LinkedHashMap<Integer, List<Integer>>();

	static void next_move(int posx, int posy, String[] g) {
		int n = g.length;

		// for (int i = 0; i < n; i++) {
		// int k = g[i].indexOf('d');
		// List<Integer> list = ds.get(i);
		// if (list == null) {
		// list = new ArrayList<>();
		// ds.put(i, list);
		// }
		// if (list.contains(k)) {
		// list.add(k);
		// }
		// }
		if ('d' == g[posx].charAt(posy)) {
			System.out.println("CLEAN");
			return;
		}
		if (posx < n) {
			if (posx % 2 == 0) {
				if (posy < n - 1) {
					System.out.println("RIGHT");
				} else {
					System.out.println("DOWN");
				}
				return;
			}
			if (posx % 2 == 1) {
				if (posy > 0) {
					System.out.println("LEFT");
				} else {
					System.out.println("DOWN");
				}
				return;
			}
		}

	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int[] pos = new int[2];
		int n = 5;
		String board[] = new String[n];
		for (int i = 0; i < 2; i++)
			pos[i] = in.nextInt();
		for (int i = 0; i < n; i++)
			board[i] = in.next();
		reveal(board);
		next_move(pos[0], pos[1], board);
	}

	private static void reveal(String[] board) {

	}
}