import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class jobdu1005 {

	public static class Applicant implements Comparable<Applicant>{
		public final int GE;
		public final int GI;
		public int rank;
		private int[] choies = null;
		int index;
		public Applicant(int ge, int gi){
			GE = ge;
			GI = gi;
		}
		public float grade(){
			return (GE+GI)/2.0f;
		}
		public void setChoices(int[] choices){
			this.choies = choices;
		}
		@Override
		public int compareTo(Applicant o) {
			float ret = grade() - o.grade();
			if (ret > 1e-8f) {
				return -1;
			} else if(ret < - 1e-8f) {
				return 1;
			} 
			return o.GE - GE;
		}
	};
	
	public static class School{
		int quota;
		public ArrayList<Applicant> acceptedApplicants;
		private Applicant maxGradeApplicant = null;
		public School(int quota) {
			this.quota = quota;
			acceptedApplicants = new ArrayList<Applicant>();
		}
		public boolean tryApplicant(Applicant app){
			if (acceptedApplicants.size() < quota) {
				acceptedApplicants.add(app);
				maxGradeApplicant = app;
				return true;
			}
			if (maxGradeApplicant != null) {
				if (maxGradeApplicant.rank == app.rank) {
					maxGradeApplicant = app;
					acceptedApplicants.add(app);
					return true;
				}
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n, m, k;
		while (scanner.hasNextInt()) {
			n = scanner.nextInt();
			m = scanner.nextInt();
			k = scanner.nextInt();
			Applicant[] apps = new Applicant[n];
			School[] schools = new School[m];
			for (int i=0; i<m; ++i) {
				schools[i] = new School(scanner.nextInt());
			}
			for (int i=0; i<n; ++i) {
				int ge = scanner.nextInt();
				int gi = scanner.nextInt();
				apps[i] = new Applicant(ge, gi);
				int[] choies = new int[k];
				for (int j=0; j<k; ++j) {
					choies[j] = scanner.nextInt();
				}
				apps[i].index = i;
				apps[i].setChoices(choies);
			}
			
			Arrays.sort(apps);
			
			apps[0].rank = 0;
			for (int i=1; i<n; ++i) {
				int ret = apps[i-1].compareTo(apps[i]);
				if (ret == 0) {
					apps[i].rank = apps[i-1].rank;
				} else {
					apps[i].rank = i;
				}
			}
			
			for (int i=0; i<n; ++i) {
				Applicant app = apps[i];
				int[] choies = app.choies;
				for (int j=0; j<k; ++j) {
					if (schools[choies[j]].tryApplicant(app)) {
						break;
					}
				}
			}
			for (int i=0; i<m; ++i) {
				Collections.sort(schools[i].acceptedApplicants, new Comparator<Applicant>() {
					public int compare(Applicant o1, Applicant o2) {
						return o1.index - o2.index;
					}
				});
			}
			
			for (int i=0; i<m; ++i) {
				ArrayList<Applicant> acceptApps = schools[i].acceptedApplicants;
				int size = acceptApps.size();
				for (int j=0; j<size-1; ++j) {
					System.out.print(acceptApps.get(j).index+" ");
				}
				if (size > 0) {
					System.out.print(acceptApps.get(size-1).index);
				}
				System.out.println();
			}
		}
	}
}
