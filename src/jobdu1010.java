import java.util.HashMap;
import java.util.Scanner;

public class jobdu1010 {

	public static int toInt(String s, HashMap<String, Integer> map) {
		int ret = 0;
		String[] digits = s.split(" ");
		int len = digits.length;
		for (int i = 0; i < len; ++i) {
			ret = ret * 10 + map.get(digits[i]);
		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("zero", 0);
		map.put("one", 1);
		map.put("two", 2);
		map.put("three", 3);
		map.put("four", 4);
		map.put("five", 5);
		map.put("six", 6);
		map.put("seven", 7);
		map.put("eight", 8);
		map.put("nine", 9);
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] pair = line.split("\\+");
			String as = pair[0].trim();
			String bs = pair[1].trim();
			bs = bs.replace('=', ' ');
			bs = bs.trim();
			int a = toInt(as, map);
			int b = toInt(bs, map);
			if (a == 0 && b == 0) {
				break;
			}
			System.out.println(a + b);
		}
	}
}
