import java.util.Scanner;


public class jobdu1002 {

	public static void main(String[] args){
		int p, t, g1, g2, g3, gj;
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()) {
			p = sc.nextInt();
			t = sc.nextInt();
			g1 = sc.nextInt();
			g2 = sc.nextInt();
			g3 = sc.nextInt();
			gj = sc.nextInt();

			float ret = 0f;
			if(Math.abs(g1-g2) <= t) {
				ret = (g1+g2)/2.0f;
			} else {
				if (Math.abs(g3-g1) <= t && Math.abs(g3-g2) <= t) {
					ret = Math.max(g1,  g2);
					ret = Math.max(ret, g3);
				} else if (Math.abs(g3-g1) <= t) {
					ret = (g1 + g3)/2.0f;
				} else if(Math.abs(g3-g2) <= t){
					ret = (g3+g2)/2.0f;
				} else {
					ret = gj;
				}
			}
			System.out.printf("%.1f\n", ret);
		}
	}
}
