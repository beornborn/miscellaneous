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

public class Task14 {
	long[] loc;
	int[] speeds;

	public static void main(String[] args) throws Exception {
		Task14 t = new Task14();
		File f = new File("in2");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("out"));
		int cases = Integer.parseInt(input.readLine());
		p(cases);
		for (int i = 0; i < cases; i++) {
			String[] data = input.readLine().split(" ");
			int N = Integer.parseInt(data[0]);
			int K = Integer.parseInt(data[1]);
			int B = Integer.parseInt(data[2]);
			int T = Integer.parseInt(data[3]);
			t.loc = new long[N];
			t.speeds = new int[N];
			long[] locnew = new long[N];
			String[] temp = input.readLine().split(" ");
			String[] temp2 = input.readLine().split(" ");
			for (int a = 0; a < t.loc.length; a++) {
				t.loc[a] = Integer.parseInt(temp[a]);
				t.speeds[a] = Integer.parseInt(temp2[a]);
			}

			//p(N + " " + K + " " + B + " " + T);
			String sss1 = "";
			String sss2 = "";
//			for (int a = 0; a < t.loc.length; a++) {
//				sss1 += t.loc[a] + " ";
//				sss2 += t.speeds[a] + " ";
//			}
//			p(sss1);
//			p(sss2);
			// String ooo = "";
			for (int b = 0; b < N; b++) {

				locnew[b] = t.loc[b] + T * t.speeds[b];
				// ooo += locnew[b] + " ";
			}
			int count = 0;
			for (int b = 0; b < N; b++) {
				if (locnew[b] >= B) {
					// p(b);
					count++;
				}
			}
			String res = "";
			if (count < K)
				res = "IMPOSSIBLE";
			if (K == 0)
				res = "0";
			int count2 = 0;
			int swaps = 0;

			for (int a = (N - 1); a >= 0; a--) {
				if (locnew[a] >= B) {
					// p("candidat "+a);
					int coll = getswaps(locnew, t.loc.clone(),
							t.speeds.clone(), a, N, B, T);
					if (coll == -1) {
						//p("inpossible because one such");
						res = "IMPOSSIBLE";
						break;
					}
					swaps += coll;
					// p("amount of swaps "+swaps);
					count2++;
					if (count2 >= K) {
						res = swaps + "";
						break;
					}
				}
			}
			// p(ooo);
			// p(res);
			// p("");
			p("Case #" + (i + 1) + ": " + res);
			out.write("Case #" + (i + 1) + ": " + res + "\n");
		}

		out.close();
	}

	private static int getswaps(long[] locnew, long[] loc, int[] speeds,
			int num, int N, int B, int T) {
		int count = 1;
		for (int i = N - 1; i >= num; i--) {
			if (locnew[i] < B) {
				count++;
			}
		}
		long[] bolshe = new long[count];
		int[] bolshespee = new int[count];
		bolshe[0] = loc[num];
		bolshespee[0] = speeds[num];
		// p("in calculate nabor "+0+" "+bolshe[0]);
		count = 1;
		for (int i = num; i < N; i++) {
			if (locnew[i] < B) {
				bolshe[count] = loc[i];
				bolshespee[count] = speeds[i];
				// p("in calculate nabor "+count+" "+bolshe[count]);
				count++;

			}
		}
		num = 0;
		for (int a = 0; a < T; a++) {
			for (int aa = bolshe.length - 1; aa >= 0; aa--) {
				long temp = bolshe[aa];
				bolshe[aa] += bolshespee[aa];
				// p("aaaasd "+aa+" "+bolshe[aa]);
				while (aa != bolshe.length - 1 && bolshe[aa] > bolshe[aa + 1]) {

					bolshe[aa] = bolshe[aa + 1];

					// p(aa+" "+bolshe[aa]+" "+bolshe[aa + 1]);
					while (aa == num && aa != bolshe.length - 1
							&& bolshe[aa] >= bolshe[aa + 1]) {
						// p("have changed place "+aa+" "+bolshe[aa]);
						// p("mmm");
						aa++;
						num++;
						int speed = bolshespee[num];
						bolshespee[num] = bolshespee[num - 1];
						bolshespee[num - 1] = speed;
					}
					if (aa == num) {
						bolshe[aa] = temp + bolshespee[aa];
					}
				}

			}
			// String jj="";
			// for (int gg=0;gg<bolshe.length;gg++){
			// String aff = gg==num ? "!" : "";
			// jj+=aff+bolshe[gg]+" ";
			// }
			// p(jj);
			// p("");
		}
		// p(num);
		// p(bolshe.length-1);

		if (num == bolshe.length - 1) {
			return bolshe.length - 1;
		} else
			return -1;

	}

	public static void p(Object o) {
		System.out.println(o);
	}
}