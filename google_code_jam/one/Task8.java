package one;

import java.util.Arrays;
import java.util.Scanner;

public class Task8 {

	public static void main(String[] args) throws Exception {

		int cases;
		int[] flavors;
		int[] amountcustomers;

		int[][][] choice;
		int[][] results;
		Scanner in = new Scanner(System.in);
		cases = Integer.parseInt(in.nextLine());
		amountcustomers = new int[cases];
		flavors = new int[cases];
		choice = new int[cases][][];
		results = new int[cases][];
		for (int i = 0; i < cases; i++) {
			flavors[i] = Integer.parseInt(in.nextLine());
			results[i] = new int[flavors[i]];
			amountcustomers[i] = Integer.parseInt(in.nextLine());
			choice[i] = new int[amountcustomers[i]][];
			for (int ii = 0; ii < amountcustomers[i]; ii++) {
				int count = in.nextInt();
				choice[i][ii] = new int[flavors[i]];
				Arrays.fill(choice[i][ii], -1);
				for (int iii = 0; iii < count; iii++) {
					int a = in.nextInt();
					int b = in.nextInt();
					choice[i][ii][a - 1] = b;
				}
				in.nextLine();
			}
		}
		in.close();

		for (int i = 0; i < cases; i++) {
			boolean hasanswer = false;
			for (int ii = 0; ii < amountcustomers[i]; ii++) {
				int placemelted = -1;
				int countofnormals = 0;
				for (int iii = 0; iii < choice[i][ii].length; iii++) {
					if (choice[i][ii][iii] == 1) {
						placemelted = iii;
					}
					if (choice[i][ii][iii] == 0) {
						countofnormals++;
					}
				}
				if (countofnormals == 0 && placemelted != -1) {
					results[i][placemelted] = 1;
				}
			}
			boolean change = false;
			lala: while (!hasanswer) {
				change = false;
				for (int ii = 0; ii < amountcustomers[i]; ii++) {
					for (int iii = 0; iii < results[i].length; iii++) {
						if (choice[i][ii][iii] == 0 && results[i][iii] == 1) {
							boolean vsenorm = false;
							for (int iiii = 0; iiii < choice[i][ii].length; iiii++) {
								if (choice[i][ii][iiii] == results[i][iiii]) {
									vsenorm = true;
								}
							}
							if (!vsenorm) {
								for (int iiii = 0; iiii < choice[i][ii].length; iiii++) {
									
									if (choice[i][ii][iiii] == 1
											&& results[i][iiii] != 1) {
										results[i][iiii] = 1;
										change=true;
									} 
								}
								if (!change){
									hasanswer = true;
									results[i][0] = -1;
								break lala;}
							}
						}
					}
				}
				if (!change){
				hasanswer = true;}
			}
		}
		printarr(results);
	}

	public static void p(Object o) {
		System.out.println(o);
	}

	public static void printarr(int[][] arr) {
		for (int zz = 0; zz < arr.length; zz++) {
			String s = "Case #" + (zz + 1) + ":";
			if (arr[zz][0] != -1) {
				for (int i = 0; i < arr[zz].length; i++) {
					s += " " + arr[zz][i];
				}
			} else {
				s += " IMPOSSIBLE";
			}
			p(s);
		}
	}

}