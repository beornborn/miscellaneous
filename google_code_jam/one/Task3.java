package one;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("serial")
		Map<String, String> alphabet = new HashMap<String, String>() {
			{
				put("a", "2");
				put("b", "22");
				put("c", "222");
				put("d", "3");
				put("e", "33");
				put("f", "333");
				put("g", "4");
				put("h", "44");
				put("i", "444");
				put("j", "5");
				put("k", "55");
				put("l", "555");
				put("m", "6");
				put("n", "66");
				put("o", "666");
				put("p", "7");
				put("q", "77");
				put("r", "777");
				put("s", "7777");
				put("t", "8");
				put("u", "88");
				put("v", "888");
				put("w", "9");
				put("x", "99");
				put("y", "999");
				put("z", "9999");
				put(" ", "0");
				put("pause", " ");
			}
		};

		String line = "";
		String result = "";
		int amount;
		char[] arr;
		String[] arr2 = new String[100];

		Scanner in = new Scanner(System.in);
		amount = Integer.parseInt(in.nextLine());

		for (int i = 0; i < amount; i++) {

			line = in.nextLine();

			arr = line.toCharArray();
			for (int index = 0; index < arr.length; index++) {
				if (index > 0
						&& alphabet.get(arr[index - 1] + "").contains(
								alphabet.get(arr[index] + "").substring(0, 1))) {
					result += " ";
				}
				result += alphabet.get(arr[index] + "");
			}
			arr2[i] = result;
			result = "";

		}
		in.close();
		printarr(arr2);

	}

	public static void printarr(String[] arr) {
		for (int zz = 0; zz < arr.length; zz++) {
			if (arr[zz] != null) {
				int kk = zz + 1;
				System.out.println("Case #" + kk + ": " + arr[zz]);
			}
		}
	}

}
