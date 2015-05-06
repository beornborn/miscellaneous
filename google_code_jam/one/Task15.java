package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Task15 {

	public static void main(String[] args) throws Exception {
		File f = new File("in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("out"));
		int cases = Integer.parseInt(input.readLine());
		p(cases);
		char[][] board;

		for (int i = 0; i < cases; i++) {
			String[] temp = input.readLine().split(" ");
			int n = Integer.parseInt(temp[0]);
			int k = Integer.parseInt(temp[1]);
			board = new char[n][n];
			for (int ii = 0; ii < n; ii++) {
				board[ii] = input.readLine().toCharArray();

			}
			p(n + " " + k);
//			for (int ii = 0; ii < n; ii++) {
//				String outty = "";
//				for (int iii = 0; iii < n; iii++) {
//					outty += board[ii][iii];
//				}
//				p(outty);
//			}
//			p("");
//			p("");
//			p("");
			board = transpose(board);
//			for (int ii = 0; ii < n; ii++) {
//				String outty = "";
//				for (int iii = 0; iii < n; iii++) {
//					outty += board[ii][iii];
//				}
//				p(outty);
//			}
//			p("");
//			p("");
//			p("");
			board = falldown(board);
			for (int ii = 0; ii < n; ii++) {
				String outty = "";
				for (int iii = 0; iii < n; iii++) {
					outty += board[ii][iii];
				}
				p(outty);
			}
			boolean red = getresponse(board, 'R', k);
			boolean blue = getresponse(board, 'B', k);
			String result = "";
			if (red && blue) {
				result = "Both";
			} else if (!red && !blue) {
				result = "Neither";
			} else if (red && !blue) {
				result = "Red";
			} else if (!red && blue) {
				result = "Blue";
			}

			p("Case #" + (i + 1) + ": " + result);
			out.write("Case #" + (i + 1) + ": " + result + "\n");
		}

		out.close();
	}

	private static boolean getresponse(char[][] board, char c, int k) {
		int l = board.length;
		for (int ii = 0; ii < l; ii++) {
			for (int iii = 0; iii < l; iii++) {
				char nope = c == 'R' ? 'B' : 'R';
				if (board[ii][iii] != '.' && board[ii][iii] != nope && iii<l-k+1) {
					int a = 1;
					boolean ok = true;
					while (a < k) {
						if (board[ii][iii+a]!=c){
							ok =false;
						}
						a++;
					}
					if (ok){return true;}
				}
				
				if (board[ii][iii] != '.' && board[ii][iii] != nope && ii<l-k+1) {
					int a = 1;
					boolean ok = true;
					while (a < k) {
						if (board[ii+a][iii]!=c){
							ok =false;
						}
						a++;
					}
					if (ok){return true;}
				}
				
				if (board[ii][iii] != '.' && board[ii][iii] != nope && ii<l-k+1 && iii<l-k+1) {
					int a = 1;
					boolean ok = true;
					while (a < k) {
						if (board[ii+a][iii+a]!=c){
							ok =false;
						}
						a++;
					}
					if (ok){return true;}
				}
				
				if (board[ii][iii] != '.' && board[ii][iii] != nope && ii>k-2 && iii<l-k+1) {
					int a = 1;
					boolean ok = true;
					while (a < k) {
						if (board[ii-a][iii+a]!=c){
							ok =false;
						}
						a++;
					}
					if (ok){return true;}
				}
			}
		}
		return false;
	}

	private static char[][] transpose(char[][] in) {
		int l = in.length;
		char[][] res = new char[l][l];
		for (int ii = 0; ii < l; ii++) {
			for (int iii = 0; iii < l; iii++) {
				res[iii][l - ii - 1] = in[ii][iii];
			}
		}
		return res;
	}

	private static char[][] falldown(char[][] in) {
		int l = in.length;
		char[][] res = new char[l][l];
		res = in.clone();
		for (int ii = l - 2; ii >= 0; ii--) {
			for (int iii = 0; iii < l; iii++) {
				int z = ii;
				while (z != l - 1 && res[z][iii] != '.'
						&& res[z + 1][iii] == '.') {
					res[z + 1][iii] = res[z][iii];
					res[z][iii] = '.';
					z++;
				}
			}
		}
		return res;

	}

	public static void p(Object o) {
		System.out.println(o);
	}
}