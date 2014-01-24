import java.util.HashSet;
import java.util.Scanner;

public class jobdu1012 {
	public static int[] root = null;
	public static int[] rank = null;

	public static int findRoot(int a) {
		if (root[a] != a) {
			root[a] = findRoot(root[a]);
		}
		return root[a];
	}

	public static void merge(int a, int b) {
		int x = findRoot(a);
		int y = findRoot(b);
		if (x != y) {
			if (rank[x] > rank[y]) {
				root[y] = x;
			} else {
				root[x] = y;
				if (rank[x] == rank[y]) {
					++rank[y];
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, m;
		while (sc.hasNextInt()) {
			n = sc.nextInt();
			if (n == 0)
				break;
			rank = new int[n];
			root = new int[n];
			for (int i = 0; i < n; ++i)
				root[i] = i;

			m = sc.nextInt();
			for (int i = 0; i < m; ++i) {
				int a = sc.nextInt();
				--a;
				int b = sc.nextInt();
				--b;
				merge(a, b);
			}
			HashSet<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < n; ++i) {
				set.add(findRoot(i));
			}
			System.out.println(set.size() - 1);
		}
	}
}
