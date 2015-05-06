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

public class Task13 {
	String[][] cur;
	public static void main(String[] args) throws Exception {
		Task13 t = new Task13();
		File f = new File("in");
		BufferedReader input = new BufferedReader(new FileReader(f));
		BufferedWriter out = new BufferedWriter(new FileWriter("out"));
		String cases = input.readLine();
		for (int i = 0; i < Integer.parseInt(cases); i++) {
			String[] data = input.readLine().split(" ");
			t.cur = new String[Integer.parseInt(data[0])
					+ Integer.parseInt(data[1])][];
			for (int a = 0; a < Integer.parseInt(data[0]); a++) {
				t.cur[a] = input.readLine().substring(1).split("/");
			}
			String[][] test = new String[Integer.parseInt(data[1])][];
			for (int a = 0; a < Integer.parseInt(data[1]); a++) {
				test[a] = input.readLine().substring(1).split("/");
			}

			// for (int b=0;b<cur.length;b++){
			// String tt = "";
			// if (cur[b]==null){break;}
			// for (int bb=0;bb<cur[b].length;bb++){
			// tt+=cur[b][bb]+"/";
			// }
			// p(tt);
			// }
			// p("");
			//
			// for (int b=0;b<test.length;b++){
			// String tt = "";
			// for (int bb=0;bb<test[b].length;bb++){
			// tt+=test[b][bb]+"/";
			// }
			// p(tt);
			// }
			// p("");
			// out.write("Case #" + (i + 1) + ": " + calculate(result) + "\n");
			
			int res=0;
			for (int a = 0; a < Integer.parseInt(data[1]); a++) {
				res+= t.getcount(t.cur, test[a], 0);
			}
			p("Case #" + (i + 1) + ": " + res);
		}

		out.close();
	}

	private int getcount(String[][] cur, String[] test, int num) {
		String[][] stor = new String[cur.length][];
		int res=0;
		int count = 0;
		if (num==test.length || test[num] == null) {
			return 0;
		}
		for (int i = 0; i < cur.length; i++) {
			if (cur[i] != null && cur[i].length>num) {
				if (cur[i][num].equals(test[num])) {
					stor[count] = cur[i];
					count++;
				}
			} 
		}

		if (count > 0) {
			res = getcount(stor, test, num + 1);
		} else {
			this.cur[this.getind()]=test;
			return test.length - num;
		}
		return res;
	}
	
	private int getind() {
		for (int i=0;i<this.cur.length;i++){
			if (this.cur[i]==null){
				return i;
			}
		}
		return -1;
	}

	private static String[] revert(String[] strings) {
		String[] temp = new String[strings.length];
		for (int i = 0; i < strings.length; i++) {
			temp[strings.length - i - 1] = strings[i];
		}
		return temp;
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