import java.util.Arrays;

public class BIT {

	private int size;
	private int tree[];

	public BIT(int size) {
		this.size = size;
		tree = new int[size + 1];
	}

	void update(int idx, int val) {
		idx += 1;
		while (idx <= size) {
			tree[idx] += val;
//			if (tree[idx] < 0) {
//				tree[idx] = 0;
//			}
			idx += (idx & -idx);
		}
	}

	int read(int idx) {
		idx += 1;
		int sum = 0;
		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}
		return sum;
	}

	public static void main(String[] args) {

		int arr[] = new int[] { 1, 0, 2, 1, 1, 3, 0, 4, 2, 5, 2, 2, 3, 1, 0, 2 };

		BIT bit = new BIT(arr.length);

		for (int i = 0; i < arr.length; i++) {
			bit.update(i, arr[i]);
		}

		System.out.println(Arrays.toString(bit.tree));
		
		bit.update(2, -2);
		System.out.println(Arrays.toString(bit.tree));
		
		for (int i = 0; i < arr.length; i++) {
			System.out.print(bit.read(i)+",");
		}

	}

	@Override
	public String toString() {
		return Arrays.toString(tree);
	}
}
