package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Task10 {

	public static void main(String[] args) throws Exception {
		File f = new File("large.in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("large.out"));
		String cases = input.readLine();
		//p(cases);
		int[] leftwindow;
		int[] rightwindow;
		for (int i = 0; i < Integer.parseInt(cases); i++) {
			int wires = Integer.parseInt(input.readLine());
			//p(wires);
			leftwindow = new int[wires];
			rightwindow = new int[wires];
			for (int ii = 0; ii < wires; ii++) {
				String[] coords = input.readLine().split(" ");
				leftwindow[ii] = Integer.parseInt(coords[0]);
				rightwindow[ii] = Integer.parseInt(coords[1]);
			}
			out.write("Case #" + (i + 1) + ": " + calculate(leftwindow, rightwindow)+"\n");
	
			//p("Case #" + (i + 1) + ": " + calculate(leftwindow, rightwindow));
		}
		out.close();
	}

	private static long calculate(int[] leftwindow, int[] rightwindow) {
		long count = 0;
		for (int i = 0; i < leftwindow.length; i++) {
			for (int ii = 0; ii < leftwindow.length; ii++) {
				if ((leftwindow[i] < leftwindow[ii] && rightwindow[i] > rightwindow[ii])
						|| ((leftwindow[i] > leftwindow[ii] && rightwindow[i] < rightwindow[ii]))) {
					count++;
				}
			}
		}
		return count/2;
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}