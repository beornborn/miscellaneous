package one;

import java.util.Scanner;

public class Task5 {

	public static void main(String[] args) throws Exception {
		int N = 99999, W = 99999, E = 99999, S = 99999;
		int maps;
		int[] arrH;
		int[] arrW;
		int[][][] arrA;
		String[][][] arrR;
		int[][][][] arrM;
		int minNeighbour;
		String currentFakeColour = "aaa";
		String[] lfake = { "aa", "bb", "cc", "dd", "ee", "ff", "gg", "hh",
				"ii", "jj", "kk", "ll", "mm", "nn", "oo", "pp", "qq", "rr",
				"ss", "tt", "uu", "vv", "ww", "xx", "yy", "zz" };
		String[] l = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z" };
		int cursor = 0;

		Scanner in = new Scanner(System.in);
		maps = Integer.parseInt(in.nextLine());
		arrA = new int[maps][][];
		arrR = new String[maps][][];
		arrM = new int[maps][][][];
		arrH = new int[maps];
		arrW = new int[maps];
		for (int i = 0; i < maps; i++) {
			arrH[i] = in.nextInt();
			arrW[i] = in.nextInt();
			in.nextLine();
			arrA[i] = new int[arrH[i]][arrW[i]];
			for (int ii = 0; ii < arrH[i]; ii++) {
				for (int iii = 0; iii < arrW[i]; iii++) {
					arrA[i][ii][iii] = in.nextInt();
				}
				in.nextLine();
			}
		}

		in.close();

		for (int i = 0; i < maps; i++) {

			arrR[i] = new String[arrH[i]][arrW[i]];
			arrM[i] = new int[arrH[i]][arrW[i]][2];

			for (int ii = 0; ii < arrH[i]; ii++) {
				for (int iii = 0; iii < arrW[i]; iii++) {
					// remember neighbours
					if (ii - 1 >= 0) {
						N = arrA[i][ii - 1][iii];
					}
					if (ii + 1 < arrH[i]) {
						S = arrA[i][ii + 1][iii];
					}
					if (iii - 1 >= 0) {
						W = arrA[i][ii][iii - 1];
					}
					if (iii + 1 < arrW[i]) {
						E = arrA[i][ii][iii + 1];
					}
					// if the neighbours are higher or the same, then it is
					// basin
					if (arrA[i][ii][iii] <= N && arrA[i][ii][iii] <= W
							&& arrA[i][ii][iii] <= E && arrA[i][ii][iii] <= S) {
						arrR[i][ii][iii] = lfake[cursor];
						cursor++;
						arrM[i][ii][iii][0] = 99999;
					} else {
						// not basin so look for neighbour with lower attitude
						minNeighbour = getMinValue(N, W, E, S);
						switch (minNeighbour) {
						case 0:
							arrM[i][ii][iii][0] = ii - 1;
							arrM[i][ii][iii][1] = iii;
							break;
						case 1:
							arrM[i][ii][iii][0] = ii;
							arrM[i][ii][iii][1] = iii - 1;
							break;
						case 2:
							arrM[i][ii][iii][0] = ii;
							arrM[i][ii][iii][1] = iii + 1;
							break;
						case 3:
							arrM[i][ii][iii][0] = ii + 1;
							arrM[i][ii][iii][1] = iii;
							break;
						}
					}
					N = E = W = S = 9999;
				}
			}
			cursor = 0;
		}

		for (int i = 0; i < maps; i++) {
			for (int ii = 0; ii < arrH[i]; ii++) {
				for (int iii = 0; iii < arrW[i]; iii++) {
					if (arrM[i][ii][iii][0] == 99999) {
						arrR = setBasins(i, arrM, arrH, arrW, ii, iii, arrR);
					}
				}
			}
		}

		for (int i = 0; i < maps; i++) {
			cursor = 0;
			for (int ii = 0; ii < arrH[i]; ii++) {
				for (int iii = 0; iii < arrW[i]; iii++) {
					if (arrR[i][ii][iii].length() == 2) {
						currentFakeColour = arrR[i][ii][iii];
						for (int n = 0; n < arrH[i]; n++) {
							for (int nn = 0; nn < arrW[i]; nn++) {
								if (arrR[i][n][nn].equals(currentFakeColour)) {
									arrR[i][n][nn] = l[cursor];
								}
							}
						}
						cursor++;
					}
				}
			}
		}

		System.out.println("");
		System.out.println("");
		printResult(arrR);

	}

	public static String[][][] setBasins(int i, int[][][][] arrM, int[] arrH,
			int[] arrW, int y, int x, String[][][] arrR) {
		for (int ii = 0; ii < arrH[i]; ii++) {
			for (int iii = 0; iii < arrW[i]; iii++) {
				if (arrM[i][ii][iii][0] == y && arrM[i][ii][iii][1] == x) {
					arrR[i][ii][iii] = arrR[i][y][x];
					arrR = setBasins(i, arrM, arrH, arrW, ii, iii, arrR);
				}
			}
		}
		return arrR;
	}

	public static void printResult(String[][][] arrR) {

		for (int i = 0; i < arrR.length; i++) {
			System.out.println("Case #" + (i + 1) + ":");
			for (int ii = 0; ii < arrR[i].length; ii++) {
				String line = "";
				for (int iii = 0; iii < arrR[i][ii].length; iii++) {
					line += arrR[i][ii][iii] + " ";
				}
				System.out.println(line.trim());
			}
		}
	}

	public static int getMinValue(int N, int W, int E, int S) {
		if (N <= W && N <= E && N <= S) {
			return 0;
		} else if (W <= N && W <= E && W <= S) {
			return 1;
		} else if (E <= W && E <= N && E <= S) {
			return 2;
		} else if (S <= W && S <= E && S <= N) {
			return 3;
		}
		return -1;
	}

}