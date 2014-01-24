import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class jobdu1007 {

	public static class Country {
		int index;
		int gold;
		int medal;
		double goldrate;
		double medalrate;
		int[] rank = new int[4];
	}

	public static void main(String[] args) {
		int n, m;
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNextInt()) {
			n = scanner.nextInt();
			m = scanner.nextInt();
			Country[] inputs = new Country[n];
			for (int i = 0; i < n; ++i) {
				int gold = scanner.nextInt();
				int medal = scanner.nextInt();
				int popu = scanner.nextInt();
				inputs[i] = new Country();
				inputs[i].index = i;
				inputs[i].gold = gold;
				inputs[i].medal = medal;
				inputs[i].goldrate = gold / (double) popu;
				inputs[i].medalrate = medal / (double) popu;
			}

			Country[] countries = new Country[m];
			Country[] ret = new Country[m];
			for (int i = 0; i < m; ++i) {
				int index = scanner.nextInt();
				ret[i] = inputs[index];
				countries[i] = ret[i];
			}
			

			Arrays.sort(countries, new Comparator<Country>() {
				public int compare(Country o1, Country o2) {
					return o2.gold - o1.gold;
				}
			});
			countries[0].rank[0] = 0;
			for (int i = 1; i < m; ++i) {
				if (countries[i].gold == countries[i - 1].gold) {
					countries[i].rank[0] = countries[i - 1].rank[0];
				} else {
					countries[i].rank[0] = i;
				}
			}

			Arrays.sort(countries, new Comparator<Country>() {
				public int compare(Country o1, Country o2) {
					return o2.medal - o1.medal;
				}
			});
			countries[0].rank[1] = 0;
			for (int i = 1; i < m; ++i) {
				if (countries[i].medal == countries[i - 1].medal) {
					countries[i].rank[1] = countries[i - 1].rank[1];
				} else {
					countries[i].rank[1] = i;
				}
			}

			Arrays.sort(countries, new Comparator<Country>() {
				public int compare(Country o1, Country o2) {
					double ret = o1.goldrate - o2.goldrate;
					if (ret > 1e-12f) {
						return -1;
					} else if (ret < -1e-8) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			countries[0].rank[2] = 0;
			for (int i = 1; i < m; ++i) {
				double d = Math.abs(countries[i].goldrate
						- countries[i - 1].goldrate);
				if (d < 1e-12f) {
					countries[i].rank[2] = countries[i - 1].rank[2];
				} else {
					countries[i].rank[2] = i;
				}
			}

			Arrays.sort(countries, new Comparator<Country>() {
				public int compare(Country o1, Country o2) {
					double ret = o1.medalrate - o2.medalrate;
					if (ret > 1e-8f) {
						return -1;
					} else if (ret < -1e-8) {
						return 1;
					} else {
						return 0;
					}
				}
			});
			countries[0].rank[3] = 0;
			for (int i = 1; i < m; ++i) {
				double d = Math.abs(countries[i].medalrate
						- countries[i - 1].medalrate);
				if (d < 1e-12f) {
					countries[i].rank[3] = countries[i - 1].rank[3];
				} else {
					countries[i].rank[3] = i;
				}
			}

			for (int i = 0; i < m; ++i) {
				int minpos = 0;
				int minrank = m + 1;
				Country c = ret[i];
				for (int j = 0; j < 4; ++j) {
					if (minrank > c.rank[j]) {
						minrank = c.rank[j];
						minpos = j;
					}
				}
				System.out.println((minrank + 1) + ":" + (minpos + 1));
			}
			System.out.println();
		}
	}
}
