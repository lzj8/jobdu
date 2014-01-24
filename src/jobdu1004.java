import java.util.Scanner;

public class jobdu1004 {

	public static long find(long[] a1, int start1, int len1, long[] a2,
			int start2, int len2, int k) {
		if (len1 == 0) {
			return a2[start2 + k];
		}
		if (len2 == 0) {
			return a1[start1 + k];
		}
		long mid1 = a1[start1 + (len1 - 1) / 2];
		long mid2 = a2[start2 + (len2 - 1) / 2];

		int keypos = (len1 + 1) / 2 + (len2 + 1) / 2 - 1;

		if (mid1 < mid2) {
			int l1 = (len1 - 1) / 2;
			int l2 = len2 - 1 - (len2 - 1) / 2;
			if (k < keypos) {
				return find(a1, start1, len1, a2, start2, len2 - l2 - 1, k);
			} else {
				return find(a1, start1 + l1 + 1, len1 - l1 - 1, a2, start2,
						len2, k - l1 - 1);
			}
		} else {
			int l1 = len1 - 1 - (len1 - 1) / 2;
			int l2 = (len2 - 1) / 2;
			if (k < keypos) {
				return find(a1, start1, len1 - l1 - 1, a2, start2, len2, k);	
			} else {
				return find(a1, start1, len1, a2, start2 + l2 + 1, len2 - l2
						- 1, k - l2 - 1);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			int len1 = scanner.nextInt();
			long[] array1 = new long[len1];
			for (int i = 0; i < len1; ++i) {
				array1[i] = scanner.nextLong();
			}

			int len2 = scanner.nextInt();
			long[] array2 = new long[len2];
			for (int i = 0; i < len2; ++i) {
				array2[i] = scanner.nextLong();
			}
			int pos = (len1 + len2 - 1) / 2;
			System.out.println(find(array1, 0, len1, array2, 0, len2, pos));
		}
	}
}
