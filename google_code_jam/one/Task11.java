package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Task11 {

	public static void main(String[] args) throws Exception {
		File f = new File("small.in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("large.out"));
		String cases = input.readLine();
		p(cases);
		long can;
		long notcan;
		int tryes;
		int c;
		for (int i = 0; i < Integer.parseInt(cases); i++) {
			String[] data = input.readLine().split(" ");
			can = Integer.parseInt(data[0]);
			notcan= Integer.parseInt(data[1]);
			c= Integer.parseInt(data[2]);
			out.write("Case #" + (i + 1) + ": " + calculate(can, notcan, c)+"\n");
			p("Case #" + (i + 1) + ": " + calculate(can, notcan, c));
		}
		out.close();
	}

	private static int calculate(long can, long notcan, int c) {
		int step=0;
		while (can<notcan){
			can=c*can;
			step++;
		}
		return logar(step);
	}

	private static int logar(int step) {
		int count = 0;
		int step2=0;
		while (step2<step && step!=1){
			count++;
			step2 = (int) Math.pow(2, count);
		}
		return count;
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}