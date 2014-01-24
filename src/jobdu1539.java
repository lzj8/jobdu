import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class jobdu1539 {
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

	static class Edge {
		int v;
		int to;
	}

	static class Node {
		boolean canVisit;
		ArrayList<Edge> adjs = new ArrayList<Edge>();
	}

	static final int N = 1005;
	static final int M = 100005 * 2;
	static Node[] nodes = new Node[N];
	static Edge[] edges = new Edge[M];
	static boolean[] visit = new boolean[N];
	static int[] minDist = new int[N];
	static int[] minDistRev = new int[N];

	static void bfs(int i, int k) {
		nodes[i].canVisit = true;
		visit[i] = true;
		Queue<Integer> q = new ArrayDeque<Integer>();
		int placeHolder = -1;
		int step = 0;
		q.offer(i);
		q.offer(placeHolder);
		while (!q.isEmpty()) {
			int current = q.poll();
			if (current == placeHolder) {
				++step;
				if (step >= k) {
					break;
				}
				q.offer(placeHolder);
				continue;
			}

			for (Edge edge : nodes[current].adjs) {
				if (!visit[edge.to]) {
					visit[edge.to] = true;
					nodes[edge.to].canVisit = true;
					q.offer(edge.to);
				}
			}
		}
	}

	static void findMinDist(int source, int total, int[] result) {
		for (int i = 0; i < total; ++i) {
			result[i] = Integer.MAX_VALUE;
			visit[i] = false;
		}
		result[source] = 0;
		for (int i = 0; i < total; ++i) {
			int min = Integer.MAX_VALUE;
			int minIndex = -1;
			for (int j = 0; j < total; j++) {
				if (!visit[j]) {
					if (min > result[j]) {
						min = result[j];
						minIndex = j;
					}
				}
			}
			if (min == Integer.MAX_VALUE)
				break;
			visit[minIndex] = true;
			for (Edge e : nodes[minIndex].adjs) {
				if (!visit[e.to] && min + e.v < result[e.to]) {
					result[e.to] = min + e.v;
				}
			}
		}
	}

	static void solve(int n, int k) {
		for (int i = 0; i < n; ++i) {
			visit[i] = false;
		}
		bfs(0, k);
		findMinDist(0, n, minDist);
		findMinDist(n - 1, n, minDistRev);
		int minPath = minDist[n - 1];
		int cnt = 0;
		for (int i = 0; i < n; ++i) {
			if (nodes[i].canVisit && minDist[i] != Integer.MAX_VALUE
					&& minDistRev[i] != Integer.MAX_VALUE
					&& minDist[i] + minDistRev[i] == minPath) {
				++cnt;
			}
		}
		System.out.println(cnt);
	}

	public static void main(String[] args) throws IOException {
		setReadNumberAsString();
		for (int i = 0; i < N; ++i) {
			nodes[i] = new Node();
		}
		for (int i = 0; i < M; ++i) {
			edges[i] = new Edge();
		}
		while (hasNext()) {
			int n = nextInt();
			int m = nextInt();
			int k = nextInt();
			for (int i = 0; i < n; ++i) {
				nodes[i].adjs.clear();
				nodes[i].canVisit = false;
			}

			for (int i = 0; i < m; ++i) {
				int from = nextInt();
				int to = nextInt();
				int v = nextInt();
				--from;
				--to;

				edges[2 * i].to = to;
				edges[2 * i].v = v;
				nodes[from].adjs.add(edges[2 * i]);

				edges[2 * i + 1].to = from;
				edges[2 * i + 1].v = v;
				nodes[to].adjs.add(edges[2 * i + 1]);
			}
			solve(n, k);
		}
	}
}
