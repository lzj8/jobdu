import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class jobdu1542 {
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

	static String nextString() throws IOException {
		in.nextToken();
		return in.sval;
	}

	public static final int DIR = 5;
	public static final int MAXSIZE = (1 << 20);
	public static final int[] rowInc = { 0, 0, 0, -1, 1 };
	public static final int[] colInc = { -1, 0, 1, 0, 0 };
	public static boolean[] visit = new boolean[MAXSIZE];

	public static class Step {
		int step;
		int state;
		int f;

		public Step(int step, int state) {
			this.step = step;
			this.state = state;
			f = numberOfZeroBit(state) + step * 5;
		}
	}

	static int numberOfZeroBit(int number) {
		int tmp = (~number) & 0xfffff;
		int cnt = 0;
		while (tmp != 0) {
			tmp &= (tmp - 1);
			++cnt;
		}
		return cnt;
	}

	public static final Queue<Step> q = new PriorityQueue<Step>(100,
			new Comparator<Step>() {
				@Override
				public int compare(Step o1, Step o2) {
					return o1.f - o2.f;
				}
			});

	public static int flip(int fromState, int row, int col) {
		int ret = fromState;
		for (int i = 0; i < 5; ++i) {
			int newRow = row + rowInc[i];
			int newCol = col + colInc[i];
			if (newRow >= 0 && newRow < 4 && newCol >= 0 && newCol < 5) {
				int binaray = (1 << (newRow * 5 + newCol));
				ret ^= binaray;
			}
		}

		return ret;
	}

	public static void main(String[] args) throws IOException {
		setReadNumberAsString();
		int t = nextInt();
		while (t-- != 0) {
			q.clear();
			int s = 0;
			int e = 0xfffff;
			for (int i = 0; i < 4; ++i) {
				String line = nextString();
				for (int j = 0; j < 5; ++j) {
					s |= ((line.charAt(j) - '0') << (i * 5 + j));
				}
			}
			for (int i = 0; i < MAXSIZE; ++i) {
				visit[i] = false;
			}
			visit[s] = true;
			q.offer(new Step(0, s));
			while (!q.isEmpty()) {
				Step currentStep = q.poll();
				int current = currentStep.state;
				if (current == e) {
					System.out.println(currentStep.step);
					break;
				}

				for (int i = 0; i < 4; ++i) {
					for (int j = 0; j < 5; ++j) {
						int newState = flip(current, i, j);
						if (!visit[newState]) {
							q.offer(new Step(currentStep.step + 1, newState));
							visit[newState] = true;
						}
					}
				}
			}
		}
	}
}
