package one;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {

	public static void main(String[] args) throws Exception {
		int tokens;
		int words;
		int cases;
		String[] arrWords;
		String[] arrCases;
		String[] arrPatterns;
		int[] arrCounts;
		Pattern p;
		Matcher m;

		Scanner in = new Scanner(System.in);
		tokens = in.nextInt();
		words = in.nextInt();
		cases = in.nextInt();
		in.nextLine();

		arrWords = new String[words];
		for (int i = 0; i < words; i++) {
			arrWords[i] = in.nextLine();
		}

		arrCases = new String[cases];
		arrPatterns = new String[cases];
		arrCounts = new int[cases];
		Arrays.fill(arrCounts, 99999);
		for (int ii = 0; ii < cases; ii++) {
			arrCases[ii] = in.nextLine();
			arrPatterns[ii] = createPattern(arrCases[ii]);

			p = Pattern.compile(arrPatterns[ii]);
			arrCounts[ii] = 0;
			for (int iii = 0; iii < words; iii++) {
				m = p.matcher(arrWords[iii]);
				if (m.matches()) {
					arrCounts[ii] += 1;
				}
			}

		}
		in.close();
		System.out.println(tokens + " " + words + " " + cases);
		printarr(arrCounts);
		// printarr(arrCases);
		// printarr(arrPatterns);

	}

	public static void printarr(String[] arr) {
		for (int zz = 0; zz < arr.length; zz++) {
			if (arr[zz] != null) {
				int kk = zz + 1;
				System.out.println("Case #" + kk + ": " + arr[zz]);
			}
		}
	}

	public static void printarr(int[] arr) {
		for (int zz = 0; zz < arr.length; zz++) {
			if (arr[zz] != 99999) {
				int kk = zz + 1;
				System.out.println("Case #" + kk + ": " + arr[zz]);
			}
		}
	}

	public static String createPattern(String line) {
		return line.replace("(", "[").replace(")", "]");
	}

}
