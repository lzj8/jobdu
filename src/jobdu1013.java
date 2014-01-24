import java.util.Scanner;

public class jobdu1013 {

	public static int toSeconds(String time) {
		int ret = 0;
		int tmp = (time.charAt(0)-'0') *10 + (time.charAt(1)-'0');
		ret += tmp*3600;
		tmp = (time.charAt(3)-'0') *10 + (time.charAt(4)-'0');
		ret += tmp*60;
		tmp = (time.charAt(6)-'0') *10 + (time.charAt(7)-'0');
		ret += tmp;
		return ret;
	}

	public static void main(String[] args) {
		int n, m;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			n = sc.nextInt();
			while (n-- != 0) {
				m = sc.nextInt();
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				String startId = null;
				String endId = null;
				while (m-- != 0) {
					String id = sc.next();
					String start = sc.next();
					String end = sc.next();
					int startSeconds = toSeconds(start);
					int endSeconds = toSeconds(end);
					if (startSeconds < min) {
						min = startSeconds;
						startId = id;
					}
					if (endSeconds > max) {
						max = endSeconds;
						endId = id;
					}
				}
				System.out.println(startId + " " + endId);
			}
		}
	}
}
