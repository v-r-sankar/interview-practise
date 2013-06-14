import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Track1RescuePrincess {
	/* Head ends here */
	static void displayPathtoPrincess(int n, String[] g) {
		if ('p' == g[0].charAt(0)) {
			for (int i = 0; i < n / 2; i++) {
				System.out.println("UP");
			}
			for (int i = 0; i < n / 2; i++) {
				System.out.println("LEFT");
			}
		}
		if ('p' == g[0].charAt(n - 1)) {
			for (int i = 0; i < n / 2; i++) {
				System.out.println("UP");
			}
			for (int i = 0; i < n / 2; i++) {
				System.out.println("RIGHT");
			}
		}

		if ('p' == g[n - 1].charAt(0)) {
			for (int i = 0; i < n / 2; i++) {
				System.out.println("DOWN");
			}
			for (int i = 0; i < n / 2; i++) {
				System.out.println("LEFT");
			}
		}

		if ('p' == g[n - 1].charAt(n - 1)) {
			for (int i = 0; i < n / 2; i++) {
				System.out.println("DOWN");
			}
			for (int i = 0; i < n / 2; i++) {
				System.out.println("RIGHT");
			}
		}
	}

	/* Tail starts here */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		n = in.nextInt();
		String grid[] = new String[n];
		for (int i = 0; i < n; i++) {
			grid[i] = in.next();
		}

		displayPathtoPrincess(n, grid);
	}
}
