import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class jobdu1014 {
	public static class Person {
		String id;
		int score;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while (sc.hasNextInt()) {
			n = sc.nextInt();
			if (n == 0)
				break;
			int m = sc.nextInt();
			int g = sc.nextInt();
			int[] scores = new int[m];
			for (int i = 0; i < m; ++i) {
				scores[i] = sc.nextInt();
			}
			ArrayList<Person> acceptPersons = new ArrayList<Person>();
			for (int i = 0; i < n; ++i) {
				Person p = new Person();
				p.id = sc.next();
				int t = sc.nextInt();
				while (t-- != 0) {
					int index = sc.nextInt();
					p.score += scores[index - 1];
				}
				if (p.score >= g) {
					acceptPersons.add(p);
				}
			}

			Collections.sort(acceptPersons, new Comparator<Person>() {
				public int compare(Person o1, Person o2) {
					int ret = o2.score - o1.score;
					if (ret != 0)
						return ret;
					return o1.id.compareTo(o2.id);
				}
			});

			int size = acceptPersons.size();
			System.out.println(size);
			for (int i = 0; i < size; ++i) {
				Person p = acceptPersons.get(i);
				System.out.println(p.id + " " + p.score);
			}
		}
	}
}
