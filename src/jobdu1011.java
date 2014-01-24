import java.util.Scanner;

public class jobdu1011 {
	public static void main(String[] args) {
		int k;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			k = sc.nextInt();
			if (k == 0)
				break;
			int[] numbers = new int[k];
			for (int i = 0; i < k; ++i) {
				numbers[i] = sc.nextInt();
			}

			int maxSum = Integer.MIN_VALUE;
			int sum = 0;
			int maxStart = -1, maxEnd = -1;
			int currentStart = 0, currentEnd = 0;
			for (int i = 0; i < k; ++i) {
				sum += numbers[i];
				if (sum < 0) {
					sum = 0;
					currentStart = i + 1;
					continue;
				}
				currentEnd = i;
				if (sum > maxSum) {
					maxSum = sum;
					maxStart = currentStart;
					maxEnd = currentEnd;
				}
			}

			if (maxStart == -1) {
				System.out.println("0 " + numbers[0] + " " + numbers[k - 1]);
			} else {
				System.out.println(maxSum + " " + numbers[maxStart] + " "
						+ numbers[maxEnd]);
			}
		}
	}
}
