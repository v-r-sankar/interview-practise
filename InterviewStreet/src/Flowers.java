import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Flowers {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line1 = br.readLine();
			String[] split = line1.split(" ");
			if (split.length > 1) {
				int flowerCount = Integer.parseInt(split[0]);
				int friendsCount = Integer.parseInt(split[1]);

				String line2 = br.readLine();
				String[] arr = line2.split(" ");
				int intarr[] = new int[arr.length];
				if (arr.length == flowerCount) {
					for (int i = 0; i < arr.length; i++) {
						intarr[i] = Integer.parseInt(arr[i]);
					}
				}

				Arrays.sort(intarr);

				long total = 0;
				long count = 1;
				int fcount = 0;
				for (int i = arr.length-1; i > -1; i--) {
					total += count * intarr[i];
					fcount ++;
					if (fcount % friendsCount == 0) {
						count++;
					}
				}
				System.out.println(total);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} finally {
		}
	}

}
