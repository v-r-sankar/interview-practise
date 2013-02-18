
public class LongestIncreasingSubsequence {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int intArr[] = {10, 22, 9, 33, 21, 50, 41, 60, 80 };
		
		int longest[] = new int[intArr.length];
		
		for (int i =0;i<intArr.length;i++) {
			longest[i] = 1;
		}
		
		for (int i=1;i<intArr.length;i++) {
			for (int k=i-1;k>-1;k--) {
				if (intArr[k] < intArr[i]) {
					if (longest[i] < (longest[k] + 1)) {
						longest[i] = longest[k] + 1;
					}
				}
			}
		}
		
		for (int i =0;i<intArr.length;i++) {
			System.out.println(longest[i]);
		}
	}

}
