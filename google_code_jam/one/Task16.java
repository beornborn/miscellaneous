package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Task16 {
	long[][] data;
	BigDecimal[] counts;
	BigDecimal[] factorials = new BigDecimal[501];
	long[][] sochetanias = new long[501][501];
	static long MOD = 100003;

	public static void main(String[] args) throws Exception {
		Task16 t = new Task16();

		t.fact(new BigDecimal(500));

//		for (BigDecimal k : t.factorials) {
//
//			p(k);
//		}

		
		t.sochetanias[0][0] = 1;
        for (int i = 1; i <= 500; i++) {
        	t.sochetanias[i][0] = 1;
            for (int j = 1; j <= 500; j++) {
            	t.sochetanias[i][j] = (t.sochetanias[i - 1][j - 1] + t.sochetanias[i - 1][j]) % MOD;
            }
        }
		

		File f = new File("in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("out"));
		int cases = Integer.parseInt(input.readLine());
		p(cases);
		int n = 500;
		t.data = new long[n + 1][];
		t.getdata(n);
		t.counts = new BigDecimal[501];
		for (int y = 0; y < t.counts.length; y++) {
			t.counts[y] = new BigDecimal(0);
		}
		p("----------------");
		for (int i = 2; i < n + 1; i++) {
			for (int ii = 1; ii < i; ii++) {
				t.counts[i] = t.counts[i].add(new BigDecimal(t.data[i][ii]));
			}
		}

		for (int i = 0; i < cases; i++) {
			int num = Integer.parseInt(input.readLine());
			//p(t.counts[num]);
			BigDecimal result = t.counts[num].remainder(new BigDecimal(t.MOD));
			p("Case #" + (i + 1) + ": " + result);
			out.write("Case #" + (i + 1) + ": " + result + "\n");
		}
		out.close();
	}

	private void getdata(int n) {

		this.data[n] = new long[n];
		for (int l = 0; l < this.data[n].length; l++) {
			this.data[n][l] = 0;
		}
		if (n > 2) {
			getdata(n - 1);
			// p(n);
			for (int son = 2; son <= n - 2; son++) {
				int dlinasonstart = (n - son - 1) < (son - 1 - 1) ? (son - 1 - 1)
						- (n - son - 1) + 1
						: 1;
				for (int dlinason = dlinasonstart; dlinason <= son - 1; dlinason++) {
					int eldlyrazmesh = n - son - 1;
					int kolvodirok = son - dlinason - 1;
					
					this.data[n][son] = this.data[n][son]
							+((this.data[son][dlinason]
									*this.sochetanias[eldlyrazmesh][kolvodirok])
							% MOD);
				}
			}
		}

		this.data[n][1] = 1;
		this.data[n][n - 1] = 1;
	}

	public BigDecimal fact(BigDecimal num) {

		this.factorials[num.intValue()] = (num.equals(new BigDecimal(0))) ? new BigDecimal(
				1) : num.multiply(fact(num.add(new BigDecimal(-1))));
		//p(num);
		return this.factorials[num.intValue()];
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}