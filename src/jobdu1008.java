import java.security.interfaces.DSAKey;
import java.util.Scanner;

import sun.net.www.content.text.plain;

public class jobdu1008 {
	public static final int MAX = 1000;
	public static int[][] distance = new int[MAX][MAX];
	public static int[][] cost = new int[MAX][MAX];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			if (n == 0 && m == 0)
				break;
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					distance[i][j] = Integer.MAX_VALUE;
					cost[i][j] = Integer.MAX_VALUE;
				}
			}
			for (int i = 0; i < m; ++i) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int d = sc.nextInt();
				int p = sc.nextInt();
				--a;
				--b;
				distance[a][b] = distance[b][a] = d;
				cost[a][b] = cost[b][a] = p;
			}

			int s = sc.nextInt();
			int t = sc.nextInt();
			--s;
			--t;
			int[] d = new int[n];
			int[] c = new int[n];
			boolean[] visit = new boolean[n];
			for (int i = 0; i < n; ++i) {
				if (i != s && distance[s][i] != Integer.MAX_VALUE) {
					d[i] = distance[s][i];
					c[i] = cost[s][i];
				} else {
					d[i] = c[i] = Integer.MAX_VALUE;
				}
			}

			d[s] = c[s] = 0;
			visit[s] = true;
			for (int i = 1; i < n; ++i) {
				int minPos = -1;
				int minValue = Integer.MAX_VALUE;
				int minCost = Integer.MAX_VALUE;
				for (int j = 0; j < n; ++j) {
					if (!visit[j]) {
						if (minValue > d[j]
								|| (d[j] != Integer.MAX_VALUE
										&& minValue == d[j] && minCost > c[j])) {
							minValue = d[j];
							minPos = j;
							minCost = c[j];
						}
					}
				}

				visit[minPos] = true;
				for (int j = 0; j < n; ++j) {
					if (!visit[j] && distance[minPos][j] != Integer.MAX_VALUE) {
						if (d[j] > d[minPos] + distance[minPos][j]) {
							d[j] = d[minPos] + distance[minPos][j];
							c[j] = c[minPos] + cost[minPos][j];
						}
					}
				}
			}

			System.out.println(d[t] + " " + c[t]);
		}
	}
}
