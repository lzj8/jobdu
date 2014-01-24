import java.util.Scanner;

import com.sun.tools.hat.internal.model.Root;

public class jobdu1009 {
	public static class Node {
		int val;
		Node left;
		Node right;

		public Node(int v) {
			val = v;
		}

		public void insert(Node n) {
			if (n.val > val) {
				if (right != null) {
					right.insert(n);
				} else {
					right = n;
				}
			} else {
				if (left != null) {
					left.insert(n);
				} else {
					left = n;
				}
			}
		}
	}

	public static Node construct(String s) {
		int len = s.length();
		Node root = null;
		for (int i = 0; i < len; ++i) {
			int v = (s.charAt(i) - '0');
			if (i == 0) {
				root = new Node(v);
			} else {
				root.insert(new Node(v));
			}
		}
		return root;
	}

	public static boolean cmp(Node root1, Node root2) {
		if (root1 == null || root2 == null) {
			return root1 == null && root2 == null;
		}
		if (root1.val != root2.val)
			return false;
		if (!cmp(root1.left, root2.left)) {
			return false;
		}
		return cmp(root1.right, root2.right);
	}

	public static void main(String[] args) {
		int n;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			n = sc.nextInt();
			if (n == 0)
				break;
			String s = sc.next();
			Node root = construct(s);
			for (int i = 0; i < n; ++i) {
				String t = sc.next();
				Node rt = construct(t);
				if (cmp(root, rt)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
}
