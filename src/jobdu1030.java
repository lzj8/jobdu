import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class jobdu1030 {
	public static class Bg {
		int l;
		int t;
		int h;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {

			int n = sc.nextInt();
			if (n < 0) {
				break;
			}
			if (n == 0) {
				System.out.println(0);
				continue;
			}
			Bg[] bgs = new Bg[n];
			int max = 0;
			for (int i = 0; i < n; ++i) {
				bgs[i] = new Bg();
				bgs[i].h = sc.nextInt();
				bgs[i].l = sc.nextInt();
				bgs[i].t = sc.nextInt();
				if (bgs[i].t > max) {
					max = bgs[i].t;
				}
			}
			++max;
			Arrays.sort(bgs, new Comparator<Bg>() {
				public int compare(Bg o1, Bg o2) {
					return o1.t - o2.t;
				}
			});
			int[][] dp = new int[n][max];
			for (int i = 0; i < max; ++i) {
				if (i >= bgs[0].l) {
					dp[0][i] = bgs[0].h;
				} else {
					dp[0][i] = 0;
				}
			}

			for (int i = 1; i < n; ++i) {
				for (int j = 0; j < max; ++j) {
					dp[i][j] = dp[i - 1][j];
					if (j > 0) {
						dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
					}
					if (j <= bgs[i].t && j >= bgs[i].l) {
						dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - bgs[i].l]
								+ bgs[i].h);
					}
				}
			}
			System.out.println(dp[n - 1][max - 1]);
		}
	}
}