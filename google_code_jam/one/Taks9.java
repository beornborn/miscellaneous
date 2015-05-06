package one;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Taks9 {

	public static void main(String[] args) throws Exception {
		BigDecimal sqrt5pl3 = new BigDecimal(
				"5.2360679774997896964091736687312762354406183596115257242"
						+ "708972454105209256378048994144144083787822749695081761507737"
						+ "8350425326772444707386358636012153345270886677817319187916581127"
						+ "664532263985658053576135041753378500342339241406444208643253909725"
						+ "259262722887629951740244068161177590890949849237139072972889848208"
						+ "864154268989409913169357701974867888442508975413295618317692149"
						+ "99774248015304341150359576683325124988151781394080005624"
						+ "2085524354223555610630634282023409333198293395974635227"
						+ "120134174961420263590473788550438968706113566004575713995"
						+ "659556695691756457822195250006053923123400500928676487552972"
						+ "20567662536660744858535052623306784946334222423176372770266324"
						+ "0768010444331582573350589309813622634319868647194698997018081895"
						+ "242644596203452214119223291259819632581110417049580704812040345599"
						+ "494350685555185557251238864165501026");
		int cases;
		int[] stepens;
		BigDecimal[] results;
		String[] resultsstr;
		Scanner in = new Scanner(System.in);
		cases = Integer.parseInt(in.nextLine());
		stepens = new int[cases];
		results = new BigDecimal[cases];
		resultsstr = new String[cases];
		for (int i = 0; i < cases; i++) {
			stepens[i] = Integer.parseInt(in.nextLine());
		}
		in.close();
		for (int i = 0; i < cases; i++) {
			results[i] = sqrt5pl3.pow(stepens[i]);
			resultsstr[i] = results[i] + "";
		}

		for (int i = 0; i < cases; i++) {
			String res = "";
			int index = resultsstr[i].indexOf(".");
			res = resultsstr[i].substring(0, index);
			if (res.length() == 2) {
				res = "0" + res;
			} else {
				res = res.substring(res.length() - 3);
			}
			p("Case #" + (i + 1) + ": " + res);
		}
	}

	public static void p(Object o) {
		System.out.println(o);
	}
}