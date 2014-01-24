import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.math.BigInteger;

public class jobdu1543 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));;

	static void setReadNumberAsString() {
		in.resetSyntax();
		in.wordChars('a', 'z');
		in.wordChars('A', 'Z');
		in.wordChars('0', '9');
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

	static BigInteger nextBigInteger() throws IOException {
		in.nextToken();
		return new BigInteger(in.sval);
	}

	public static void main(String[] args) throws IOException {
		setReadNumberAsString();
		int t = nextInt();
		while (t-- != 0) {
			int opt = nextInt();
			if (opt == 1) {
				BigInteger n = nextBigInteger();
				int k = 0;
				long rev = 0;
				while (!n.equals(BigInteger.ZERO)) {
					rev = (rev << 1)
							| (n.mod(BigInteger.valueOf(2)).intValue());
					n = n.shiftRight(1);
					++k;
				}
				rev >>>= 1;
				long p = 1, q = 1;
				--k;
				while (k-- != 0) {
					if ((rev & 1) == 0) {
						q = p + q;
					} else {
						p = p + q;
					}
					rev >>>= 1;
				}
				out.println(p + " " + q);
			} else {
				long p = nextLong();
				long q = nextLong();
				BigInteger ret = BigInteger.ZERO;
				int k = 0;
				while (p != 1 || q != 1) {
					if (p > q) {
						ret = ret.setBit(k);
						p -= q;
					} else {
						q -= p;
					}
					++k;
				}
				ret = ret.setBit(k);
				out.println(ret);
			}
			out.flush();
		}
	}
}
