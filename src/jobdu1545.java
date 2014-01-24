import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class jobdu1545 {
	public static class Edge {
		int from;
		int to;
		int v;

		public Edge(int f, int t, int v) {
			this.from = f;
			this.to = t;
			this.v = v;
		}
	}

	public static class Node {
		ArrayList<Edge> adjs = new ArrayList<Edge>();
		int minMax = Integer.MAX_VALUE;
	}

	public static class Step {
		int node;
		int m;

		public Step(int node, int m) {
			this.node = node;
			this.m = m;
		}
	}

	static int n = 0;
	static final int M = 10005;
	static Node[] nodes = null;
	static Edge[] edges = null;
	static Queue<Step> q = new PriorityQueue<Step>(10, new Comparator<Step>() {
		public int compare(Step o1, Step o2) {
			return o1.m - o2.m;
		}
	});

	static int findMaxMinConnect() {
		q.clear();
		int lastMax = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		q.offer(new Step(0, Integer.MIN_VALUE));
		while (!q.isEmpty()) {
			Step s = q.poll();
			int c = s.node;
			lastMax = s.m;
			Node node = nodes[c];
			if (c == n - 1) {
				min = Math.min(min, lastMax);
				continue;
			}
			ArrayList<Edge> adjs = node.adjs;
			for (Edge e : adjs) {
				int m = Math.max(lastMax, e.v);
				if (m < nodes[e.to].minMax) {
					nodes[e.to].minMax = m;
					q.offer(new Step(e.to, m));
				}
			}
		}
		return min;
	}

	public static void main(String[] args) {
		int m;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			n = sc.nextInt();
			m = sc.nextInt();
			nodes = new Node[n];
			for (int i = 0; i < n; ++i) {
				nodes[i] = new Node();
				nodes[i].minMax = Integer.MAX_VALUE;
			}
			edges = new Edge[m * 2];
			for (int i = 0; i < m; ++i) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				int v = sc.nextInt();
				--from;
				--to;
				edges[2 * i] = new Edge(from, to, v);
				nodes[from].adjs.add(edges[2 * i]);

				edges[2 * i + 1] = new Edge(to, from, v);
				nodes[to].adjs.add(edges[2 * i + 1]);
			}
			int ret = findMaxMinConnect();
			if (ret == Integer.MAX_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(ret);
			}
		}
	}
}
