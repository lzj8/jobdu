import java.util.Scanner;

public class jobdu1006 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String s = sc.next();
			int len = s.length();
			int pos0 = s.indexOf('z');
			int pos1 = s.lastIndexOf('z');
			int pos2 = s.indexOf('j');
			int pos3 = s.lastIndexOf('j');
			boolean accept = false;
			if (pos0 == pos1 && pos2 == pos3 && pos0 != -1 && pos2 != -1) {
				int a = pos0;
				int b = pos2 - pos0 - 1;
				int c = len - 1 - pos2;
				while (true) {
					if (a < 0 || b < 1 || c < 0) {
						break;
					}
					if (c == a && b == 1) {
						accept = true;
						break;
					} else {
						c = c - a;
						--b;
					}
				}
			}
			if (accept)
				System.out.println("Accepted");
			else
				System.out.println("Wrong Answer");
		}
	}
}
