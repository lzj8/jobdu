import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class jobdu1544 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));;

	static int nextInt() throws IOException {
		in.nextToken();
		return (int) in.nval;
	}

	static boolean hasNextInt() throws IOException {
		int ret = in.nextToken();
		in.pushBack();
		return ret != StreamTokenizer.TT_EOF;
	}

	public static final int N = 100001;
	public static final int MAXD = 20;
	static int[][] dp = new int[N][MAXD];;
	static int[] numbers = new int[N];

	public static void main(String[] args) throws IOException {
		int n;
		while (hasNextInt()) {
			n = nextInt();
			int depth = (int) Math.floor(Math.log(n) / Math.log(2));
			for (int i = 0; i < n; ++i) {
				numbers[i] = nextInt();
				dp[i][0] = numbers[i];
			}
			int pow = 1;
			for (int i = 1; i <= depth; ++i) {
				for (int j = 0; j < n; ++j) {
					if (j + pow < n) {
						dp[j][i] = Math.min(dp[j][i - 1], dp[j + pow][i - 1]);
					} else {
						dp[j][i] = Math.min(dp[j][i - 1], dp[n - pow][i - 1]);
					}
				}
				pow *= 2;
			}
			int t = nextInt();
			while (t-- != 0) {
				int l = nextInt();
				int r = nextInt();
				--l;
				--r;
				int k = (int) Math.floor(Math.log(r - l + 1) / Math.log(2));
				pow = (int) Math.pow(2, k);
				out.println(Math.min(dp[l][k], dp[r - pow + 1][k]));
			}
			out.flush();
		}
	}
}
