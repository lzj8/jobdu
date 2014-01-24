import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class jobdu1540 {
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

	static class Song {
		int remain;
		Song next;
		boolean flag;
		int index;
	}

	static Song list = null;
	static Song playing = null;

	static void topSong(int x) {
		Song f = null;
		Song p = list;
		for (int i = 1; i < x; ++i) {
			f = p;
			p = p.next;
		}
		if (f != null) {
			f.next = p.next;
			p.next = list;
			list = p;
		}
	}

	static void deleteSong(int x) {
		Song f = null;
		Song p = list;
		if (p == null)
			return;
		for (int i = 1; i < x; ++i) {
			f = p;
			p = p.next;
		}
		if (f == null) {
			list = p;
		} else {
			f.next = p.next;
		}
	}

	static int index = 1;

	static void addSong(int value, boolean flag) {
		Song s = new Song();
		s.flag = flag;
		s.remain = value;
		s.next = null;
		s.index = index;
		++index;
		if (list == null) {
			list = s;
		} else {
			Song p = list;
			while (p.next != null) {
				p = p.next;
			}
			p.next = s;
		}
	}

	static void cut() {
		if (playing != null) {
			playing = null;
		}
	}

	static void playNext(int t) {
		if (list != null) {
			playing = list;
			list = list.next;
			if (playing.flag) {
				System.out.println(t);
			}
			--playing.remain;
			if (playing.remain == 0) {
				playing = null;
			}
		}
	}

	static int time = 0;

	static void passTime(int t) {
		int span = t - time;
		while (span > 0 && (playing != null || list != null)) {
			if (playing != null) {
				if (playing.remain >= span) {
					playing.remain -= span;
					time += span;
					if (playing.remain == 0) {
						playing = null;
					}
					break;
				}
				span -= playing.remain + 1;
				time += playing.remain + 1;
				playNext(time);
			} else {
				++time;
				--span;
				playNext(time);
			}
		}
		time = t;
	}

	//
	// static void print() {
	// Song p = list;
	// if (p == null) {
	// System.out.println("empty");
	// }
	// while (p != null) {
	// System.out.print(p.index + "," + p.len + "--->");
	// p = p.next;
	// }
	// System.out.println();
	// }

	public static void main(String[] args) throws IOException {
		setReadNumberAsString();

		while (hasNext()) {
			index = 1;
			time = 0;
			list = null;
			playing = null;
			int n = nextInt();
			while (n-- != 0) {
				int t = nextInt();
				String opt = nextString();
				passTime(t);
				// System.out.print("after pass: " + t + "   ");
				// print();
				if (opt.equals("add")) {
					int v = nextInt();
					int flag = nextInt();
					addSong(v, (flag == 1));
				} else if (opt.equals("top")) {
					int x = nextInt();
					topSong(x);
				} else if (opt.equals("delete")) {
					deleteSong(nextInt());
				} else if (opt.equals("cut")) {
					cut();
				}
				// System.out.print("after option: ");
				// print();
			}
			passTime(Integer.MAX_VALUE);
			// System.out.print("after pass finally:");
			// print();
		}
	}
}
