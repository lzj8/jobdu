import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class jobdu1541 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(
			new InputStreamReader(System.in)));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));;

	static void setReadNumberAsString() {
		in.resetSyntax();
		// in.wordChars('a', 'z');
		// in.wordChars('A', 'Z');
		// in.wordChars('0', '9');

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

	static class Node {
		int count;
		int index;
		Node left;
		Node right;
		Node parent;
	}

	public static final int SIZE = 1000;
	static Node[] nodes = new Node[SIZE];

	static void getCount(Node node) {
		if (node.count == 0) {
			node.count = 1;
			if (node.left != null) {
				getCount(node.left);
				node.count += node.left.count;
			}
			if (node.right != null) {
				getCount(node.right);
				node.count += node.right.count;
			}
		}
	}

	static void rotate(Node node) {
		Node p = node.parent;
		if (p == null) {
			return;
		}
		Node pp = p.parent;
		node.parent = pp;
		p.parent = node;
		if (pp != null && pp.left == p) {
			pp.left = node;
		}
		if (pp != null && pp.right == p) {
			pp.right = node;
		}

		if (p.left == node) {
			p.left = node.right;
			if (node.right != null) {
				node.right.parent = p;
			}
			node.right = p;
		} else {
			p.right = node.left;
			if (node.left != null) {
				node.left.parent = p;
			}
			node.left = p;
		}
		p.count = 1;
		if (p.left != null) {
			p.count += p.left.count;
		}
		if (p.right != null) {
			p.count += p.right.count;
		}
		node.count = 1;
		if (node.left != null) {
			node.count += node.left.count;
		}
		if (node.right != null) {
			node.count += node.right.count;
		}
	}

	public static void main(String[] args) throws IOException {
		setReadNumberAsString();
		for (int i = 0; i < SIZE; ++i) {
			nodes[i] = new Node();
		}
		while (hasNext()) {
			int n = nextInt();
			for (int i = 0; i < n; ++i) {
				nodes[i].count = 0;
				nodes[i].left = null;
				nodes[i].right = null;
				nodes[i].parent = null;
				nodes[i].index = i;
			}
			for (int i = 0; i < n; ++i) {
				int x = nextInt();
				int y = nextInt();
				--x;
				--y;
				if (x >= 0) {
					nodes[i].left = nodes[x];
					nodes[x].parent = nodes[i];
				}
				if (y >= 0) {
					nodes[i].right = nodes[y];
					nodes[y].parent = nodes[i];
				}
			}

			for (int i = 0; i < n; ++i) {
				getCount(nodes[i]);
			}

			int t = nextInt();
			while (t-- != 0) {
				String opt = nextString();
				int nodeIndex = nextInt();
				--nodeIndex;
				if (opt.equals("size")) {
					out.println(nodes[nodeIndex].count);
				} else if (opt.equals("rotate")) {
					rotate(nodes[nodeIndex]);
				} else if (opt.equals("parent")) {
					if (nodes[nodeIndex].parent != null) {
						out.println(nodes[nodeIndex].parent.index + 1);
					} else {
						out.println(-1);
					}
				}
			}
			out.flush();
		}
	}
}
