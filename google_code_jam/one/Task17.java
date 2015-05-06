package one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Task17 {
	int D;
	int I;
	int M;
	int N;
	int[] values;
	int diff;
	int cases;

	public void solve() throws IOException {
		File f = new File("in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("out"));
		int cases = Integer.parseInt(input.readLine());
		p(cases);
		for (int i = 0; i < cases; i++) {
			String[] data = (input.readLine()).split(" ");
			D = Integer.parseInt(data[0]);
			I = Integer.parseInt(data[1]);
			M = Integer.parseInt(data[2]);
			N = Integer.parseInt(data[3]);
			values = new int[N];
			data = (input.readLine()).split(" ");
			for (int k = 0; k < values.length; k++) {
				values[k] = Integer.parseInt(data[k]);
			}
			p(D + " " + I + " " + M + " " + N);
			String u = "";
			for (int k = 0; k < values.length; k++) {
				u += values[k] + " ";
			}
			p(u);

			int megaresult = 0;
			int lim = 0;
			while (this.estimate(this.values) > 0) {
				int[][] ddata = new int[1][];
//				ddata[0] = this.getBestI();
//				ddata[1] = getBestD();
				ddata[0] = getBestC();
				int[] costs = new int[1];
//				costs[0] = I;
//				costs[1] = D;
				
				//p("ytyt " + estimate(ddata[1]));
				costs[0] = diff;
				p("ytyt " + estimate(ddata[0]));
				this.values = getBest(ddata, costs).clone();

				String hh = "";
				for (int jj = 0; jj < this.values.length; jj++) {
					hh += this.values[jj] + " ";
				}
				p(hh);

				lim++;
				for (int z = 0; z < ddata.length; z++) {
					if (ravno(this.values, ddata[z])) {
						megaresult += costs[z];
						// p(costs[z]);
					}

				}

			}
			p("Case #" + (i + 1) + ": " + megaresult);
			// out.write("Case #" + (i + 1) + ": " + result + "\n");
		}
		out.close();
	}

	private static boolean ravno(int[] one, int[] two) {
		if (one.length != two.length) {
			return false;
		}
		for (int i = 0; i < one.length; i++) {
			if (one[i] != two[i]) {
				return false;
			}
		}
		return true;
	}

	private int[] getBest(int[][] ddata, int[] costs) {
		int resnum = 0;
		int[] estims = new int[ddata.length];
		for (int i = 0; i < ddata.length; i++) {
			estims[i] = this.estimate(ddata[i]);
		}
		double[] effs = new double[costs.length];
		for (int y = 0; y < effs.length; y++) {
			if (costs[y] == 0) {
				effs[y] = 999999;
			} else {
				effs[y] = (double) (Math.abs(estimate(this.values) - estims[y]))
						/ (double) costs[y];
				//p(y + " effs " + effs[y]);
			}
		}
		double max = effs[0];

		for (int i = 1; i < estims.length; i++) {
			if (effs[i] > max) {
				max = effs[i];
				resnum = i;
			}
		}
		return ddata[resnum];
	}

	private int[] getBestC() {
		int[][] ddata = new int[values.length * 256][];
		int[] costs = new int[ddata.length];
		int[] temp = values.clone();
		int count = 0;
			for (int ii = 0; ii < values.length; ii++) {
				for (int iii = 0; iii < 256; iii++) {
					temp[ii] = iii;
					costs[count] = Math.abs(temp[ii] - values[ii]);
					ddata[count] = temp.clone();
					temp[ii] = values[ii];
					count++;
				}
			}
		
		int[] result = getBest(ddata, costs);
		diff = 0;
		for (int ii = 0; ii < result.length; ii++) {
			if (result[ii]!=values[ii]){
				diff = Math.abs(result[ii] - values[ii]);
				p(diff);
				break;
			}
		}
		return result;
	}

	private int[] getBestD() {
		int[][] ddata = new int[values.length][];
		int[] costs = new int[ddata.length];
		int[] temp;
		for (int i = 0; i < values.length; i++) {
			costs[i] = D;
			temp = new int[values.length - 1];
			int cotemp = 0;
			for (int ii = 0; ii < values.length; ii++) {
				if (ii != i) {
					temp[cotemp] = values[ii];
					cotemp++;
				}
			}
			ddata[i] = temp;

		}
		return getBest(ddata, costs);
	}

	private int[] getBestI() {
		// p("aaaaaaaaa");
		String hh = "";
		for (int jj = 0; jj < values.length; jj++) {
			hh += values[jj] + " ";
		}
		// p("temp "+hh);
		int[][] ddata = new int[values.length - 1][];
		int[] costs = new int[ddata.length];
		int[] temp;
		for (int i = 0; i < values.length - 1; i++) {
			costs[i] = I;
			temp = new int[values.length + 1];
			int cotemp = 0;
			for (int ii = 0; ii < values.length; ii++) {

				temp[cotemp] = values[ii];
				cotemp++;
				if (ii == i) {
					int slag = (values[cotemp] - values[cotemp - 1] > 0) ? M
							: -M;
					temp[cotemp] = temp[cotemp - 1] + slag;
					cotemp++;
				}
			}
			ddata[i] = temp;
			hh = "";
			for (int jj = 0; jj < temp.length; jj++) {
				hh += temp[jj] + " ";
			}
			// p("temp "+hh);
			// p("estimate "+estimate(temp));
		}
		return getBest(ddata, costs);
	}

	public int estimate(int[] vals) {
		int result = 0;
		for (int i = 0; i < vals.length - 1; i++) {
			int slag = Math.abs(vals[i] - vals[i + 1]) - M;
			result += slag < 0 ? 0 : slag;
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		Task17 t = new Task17();
		t.solve();

	}

	public static void p(Object o) {
		System.out.println(o);
	}
}