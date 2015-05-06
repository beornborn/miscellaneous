package one;

import java.util.Arrays;
import java.util.Scanner;

public class Task6 {

	public static void main(String[] args) throws Exception {

		char[] welcome = { 'w', 'e', 'l', 'c', 'o', 'm', 'e', ' ', 't', 'o',
				' ', 'c', 'o', 'd', 'e', ' ', 'j', 'a', 'm' };
		int cases;
		String[] lines;
		int[] results;
		int result = 0;
		Scanner in = new Scanner(System.in);
		cases = Integer.parseInt(in.nextLine());
		int[][][] place = new int[cases][19][500];
		int[][] realLength = new int[cases][19];
		int[][][] amountsBefore = new int[cases][19][500];
		results = new int[cases];
		lines = new String[cases];
		for (int i = 0; i < cases; i++) {
			lines[i] = in.nextLine();
		}
		for (int i = 0; i < cases; i++) {
			for (int ii = 0; ii < welcome.length; ii++) {
				Arrays.fill(place[i][ii], -1);
				int amount = 0;
				for (int iii = 0; iii < lines[i].length(); iii++) {
					if (lines[i].charAt(iii) == welcome[ii]) {
						place[i][ii][amount] = iii;
						amount++;
					}
				}
			}
		}

		realLength = getRealLength(place);

		for (int i = 0; i < cases; i++) {
			results[i] = slide(place, 0, i, result, 0, realLength);
		}

		for (int i = 0; i < cases; i++) {
			boolean change = true;
			while (change) {
				change = false;
				for (int ii = welcome.length - 1; ii > 0; ii--) {
					for (int iii = 0; iii < realLength[i][ii]; iii++) {

						for (int iiii = 0; iiii < realLength[i][ii - 1]; iiii++) {
							if (place[i][ii][iii] > place[i][ii - 1][iiii]) {
								amountsBefore[i][ii][iii]++;
							}

						}
						if (amountsBefore[i][ii][iii] == 0) {
							place[i][ii][iii] = -1;
							change = true;
						}

					}
				}
			}
		}

		for (int i = 0; i < cases; i++) {
			for (int ii = welcome.length - 1; ii > 0; ii--) {
				int buffer = 99999;
				for (int iii = 0; iii < realLength[i][ii]; iii++) {

					for (int iiii = 0; iiii < realLength[i][ii - 1]; iiii++) {
						if (place[i][ii][iii] > place[i][ii - 1][iiii]) {
							amountsBefore[i][ii][iii]++;
						}

					}
					if (amountsBefore[i][ii][iii] != 0
							&& amountsBefore[i][ii][iii] < buffer) {
						buffer = amountsBefore[i][ii][iii];
					}
					if (iii == realLength[i][ii] - 1) {
						System.out.println("before " + welcome[ii] + "("
								+ place[i][ii][iii] + "): " + buffer);
					}

				}
			}
		}

		for (int i = 0; i < cases; i++) {
			for (int ii = 0; ii < welcome.length; ii++) {
				System.out.println(welcome[ii] + ": " + realLength[i][ii]);
			}
		}
		 for (int i = 0; i < cases; i++) {
		 for (int ii = 0; ii < welcome.length; ii++) {
		 String res = "";
		 for (int iii = 0; iii < place[i][ii].length; iii++) {
		 if (place[i][ii][iii]!=-1) {
		 res+=place[i][ii][iii]+" ";
		 }
		 }
		 res="for "+welcome[ii]+": "+res;
		 System.out.println(res);
		 }
		 }
		printarr(results);
		System.out.println(results[0]);
	}

	public static int slide(int[][][] place, int letter, int casse, int result,
			int posPrev, int[][] realLength) {

		for (int i = 0; i < realLength[casse][letter]; i++) {
			if (place[casse][letter][i] == -1) {
				return result;
			}
			if (place[casse][letter][i] < posPrev) {
				continue;
			}
			if (letter != 18) {
				result = slide(place, letter + 1, casse, result,
						place[casse][letter][i], realLength);
			} else {
				result++;
			}
		}

		return result;
	}

	public static int[][] getRealLength(int[][][] place) {
		int[][] realLength = new int[place.length][19];
		for (int i = 0; i < place.length; i++) {
			for (int ii = 0; ii < place[i].length; ii++) {
				for (int iii = 0; iii < place[i][ii].length; iii++) {
					if (place[i][ii][iii] != -1) {
						realLength[i][ii]++;
					}
				}
			}
		}
		return realLength;
	}

	public static String removeCharAt(String s, int pos) {
		StringBuffer buf = new StringBuffer(s.length() - 1);
		buf.append(s.substring(0, pos)).append(s.substring(pos + 1));
		return buf.toString();

	}

	public static void printarr(int[] arr) {
		for (int zz = 0; zz < arr.length; zz++) {

			int kk = zz + 1;
			String res = "0000" + arr[zz];
			if (res.length() > 4) {
				res = res.substring(res.length() - 4, res.length());
			}
			System.out.println("Case #" + kk + ": " + res);

		}
	}

}