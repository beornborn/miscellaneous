package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task12 {

	public static void main(String[] args) throws Exception {
		File f = new File("in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("out"));
		String cases = input.readLine();
		int[][] fields;
		int[][] squaresizes;
		int[] result;
		int[][] temp;
		for (int i = 0; i < Integer.parseInt(cases); i++) {
			String[] data = input.readLine().split(" ");
			// p(data[0]+" "+data[1]);
			int rows = Integer.parseInt(data[0]);
			int colls = Integer.parseInt(data[1]);
			// p(rows + " " + colls);
			fields = new int[rows][colls];
			squaresizes = new int[rows][colls];
			temp = new int[rows][colls];
			result = new int[Math.min(rows, colls) + 1];
			for (int ii = 0; ii < rows; ii++) {
				String number = getnumber(input);
				String lll = "";
				for (int iii = 0; iii < number.length(); iii++) {
					fields[ii][iii] = Integer.parseInt(number.charAt(iii) + "");
					lll += fields[ii][iii] + "";
				}

				//p(lll);
			}
//			 for (int sss=0;sss<=0xF;sss++){
//			 p(sss+"       "+Integer.toBinaryString(sss));
//			 }

			for (int row = 0; row < fields.length; row++) {
				for (int col = 0; col < fields[row].length; col++) {
					squaresizes[row][col] = getSquareSize(fields, row, col, 1);
				}
			}
int ccc = 0;
			while (isAnyNotUsed(fields)) {
				// p(getDateTime());
				int maxrow = 0;
				int maxcol = 0;
				for (int row = 0; row < squaresizes.length; row++) {
					for (int col = 0; col < squaresizes[row].length; col++) {
						if (squaresizes[row][col] > squaresizes[maxrow][maxcol]) {
							maxrow = row;
							maxcol = col;
						}
					}
				}
				if (squaresizes[maxrow][maxcol] != 1) {
					temp[maxrow][maxcol] = squaresizes[maxrow][maxcol];
				} else {
					result[1] = findwithone(squaresizes);
					break;
				}
				int square = squaresizes[maxrow][maxcol];
				result[squaresizes[maxrow][maxcol]]++;
				for (int row = maxrow; row < maxrow + square; row++) {
					for (int col = maxcol; col < maxcol + square; col++) {
						fields[row][col] = -1;
						squaresizes[row][col] = -1;
					}
				}
				
				squaresizes = dopereshet(squaresizes, fields);
//				for (int a = 0; a < squaresizes.length; a++) {
//					String qqq = "";
//					String ggg = "";
//					for (int aa = 0; aa < squaresizes[0].length; aa++) {
//						ggg += temp[a][aa] + " ";
//						if (squaresizes[a][aa] == -1) {
//							qqq += "0 ";
//						} else {
//							qqq += squaresizes[a][aa] + " ";
//						}
//					}
//
//					 //p(ggg);
//				}
			}

			out.write("Case #" + (i + 1) + ": " + calculate(result) + "\n");
			p("Case #" + (i + 1) + ": " + calculate(result));
			for (int z = result.length - 1; z >= 0; z--) {
				if (result[z] > 0) {
					p(z + " " + result[z]);
					out.write(z + " " + result[z] + "\n");
				}
			}
		}

		out.close();
	}
	private static void pf(int[][] fields ) {
		for (int aa=0;aa<fields.length;aa++){
			String qq="";
			for (int aaa=0;aaa<fields.length;aaa++){
				qq+=fields[aa][aaa]+" ";
			}
			p(qq+"");
		}
		p("");
	}
	
	private static void pss(int[][] ss ) {
		for (int aa=0;aa<ss.length;aa++){
			String qq="";
			for (int aaa=0;aaa<ss.length;aaa++){
				qq+=ss[aa][aaa]+" ";
			}
			p(qq+"");
		}
		p("");
	}
	private static int findwithone(int[][] squaresizes) {
		int count = 0;
		for (int i = 0; i < squaresizes.length; i++) {
			for (int ii = 0; ii < squaresizes[0].length; ii++) {
				if (squaresizes[i][ii] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	private static int[][] dopereshet(int[][] squaresizes, int[][] fields) {
		for (int i = 0; i < fields.length; i++) {
			for (int ii = 0; ii < fields[0].length; ii++) {
				if (fields[i][ii] != -1 && squaresizes[i][ii] != 1) {
					boolean notAllCheck = true;
					while (notAllCheck) {
						int temp = squaresizes[i][ii];
						for (int a = ii; a < ii + squaresizes[i][ii]; a++) {
							
							if (fields[i + squaresizes[i][ii] - 1][a] == -1) {
								squaresizes[i][ii]--;
								if (squaresizes[i][ii] == 1) {
									notAllCheck = false;
								}
								break;
							}
						}

						for (int a = i; a < i + squaresizes[i][ii]; a++) {
							if (fields[a][ii + squaresizes[i][ii] - 1] == -1) {
								squaresizes[i][ii]--;
								if (squaresizes[i][ii] == 1) {
									notAllCheck = false;
								}
								break;
							}
						}
						if (temp==squaresizes[i][ii]){
							notAllCheck = false;
						}
					}
				}
			}
		}
		return squaresizes;
	}

	private static int getSquareSize(int[][] fields, int row, int col, int level) {
		if (fields[row][col] == -1) {
			return -1;
		}
		if (row + level >= fields.length || col + level >= fields[0].length) {
			return level;
		}

		int previous = fields[row + level - 1][col];
		for (int i = col; i <= col + level; i++) {
			// p(fields[row][i]+" "+previous);
			if (chered(fields[row + level][i], previous)) {
				previous = fields[row + level][i];
			} else {
				return level;
			}
		}
		previous = fields[row][col + level - 1];
		for (int i = row; i <= row + level; i++) {
			if (chered(fields[i][col + level], previous)) {
				previous = fields[i][col + level];
			} else {
				return level;
			}
		}

		return getSquareSize(fields, row, col, level + 1);
	}

	private static boolean chered(int a, int b) {
		if ((a == 1 && b == 0) || (a == 0 && b == 1)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean isAnyNotUsed(int[][] fields) {
		for (int i = 0; i < fields.length; i++) {
			for (int ii = 0; ii < fields[i].length; ii++) {
				if (fields[i][ii] != -1) {
					return true;
				}
			}
		}
		return false;
	}

	private static int calculate(int[] result) {
		int count = 0;
		for (int i = 0; i < result.length; i++) {
			if (result[i] > 0) {
				count++;
			}
		}
		return count;
	}

	private static String getnumber(BufferedReader input) throws IOException {
		String result = "";
		String temp = input.readLine();
		String tempstr = "";
		for (int i = 0; i < temp.length(); i++) {
			tempstr = Long.toBinaryString(Long.parseLong(temp.charAt(i) + "",
					16));
			while (tempstr.length() != 4) {
				tempstr = "0" + tempstr;
			}
			result += tempstr;
		}
		return result;
	}

	private static String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void p(Object o) {
		System.out.println(o);

	}
}