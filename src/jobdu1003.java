import java.util.Scanner;


public class jobdu1003 {
	
	public static int toInteger(String s) {
		int flag = 1;
		int index = 0;
		int ret = 0;
		if (s.charAt(0) == '-') {
			flag = -1;
			++ index;
		} else if(s.charAt(0) == '+') {
			++ index;
		}
		
		int len = s.length();
		for (; index < len; ++ index) {
			char c = s.charAt(index);
			if (c == ',' || c == ' ') continue;
			ret = ret*10 + (c-'0');
		}
		return ret * flag;
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String astr = sc.next();
			String bstr = sc.next();
			int A = toInteger(astr);
			int B = toInteger(bstr);
			System.out.println(astr+" : "+A+" "+bstr+" :"+B);
			System.out.println(A+B);
		}
	}
}
