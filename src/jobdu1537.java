import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class jobdu1537 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));;

	static void setReadNumberAsString() {
		in.resetSyntax();
		in.wordChars(0, 0xff);
		in.whitespaceChars(' ', ' ');
		in.whitespaceChars('\n', '\n');
		in.whitespaceChars('\r', '\r');
		in.eolIsSignificant(false);
	}

	static int nextInt() throws IOException {
		in.nextToken();
		return Integer.parseInt(in.sval);
	}

	static boolean hasNext() throws IOException {
		int ret = in.nextToken();
		in.pushBack();
		return ret != StreamTokenizer.TT_EOF;
	}

	static long nextLong() throws IOException {
		in.nextToken();
		return Long.parseLong(in.sval);
	}

	static String nextString() throws IOException {
		in.nextToken();
		return in.sval;
	}

	static int[][] f;
	static int[] g;
	static int[] num;

	public static void main(String[] args) throws IOException {
		setReadNumberAsString();
		int n, k;
		while (hasNext()) {
			n = nextInt();
			k = nextInt();
			num = new int[n];
			for (int i = 0; i < n; ++i) {
				num[i] = nextInt();
			}
			f = new int[n][k + 1];
			g = new int[k + 1];
			for (int i = 0; i < k + 1; ++i) {
				g[i] = Integer.MIN_VALUE;
			}
			for (int i = 1; i < n; ++i) {
				for (int j = 1; j < k + 1; ++j) {
					g[j] = Math.max(g[j], f[i - 1][j - 1] - num[i - 1]);
					f[i][j] = Math.max(f[i - 1][j], g[j] + num[i]);
				}
			}

			System.out.println(f[n - 1][k]);
		}
	}
}
