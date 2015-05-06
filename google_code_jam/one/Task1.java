package one;

import java.util.Scanner;

public class Task1 {

	public static void main(String[] args) {
		String line = "";
		String line2 = "";
		String[] arr = new String[500];
		String[] arr2 = new String[100];
		int amount;
		int length;
		Scanner in = new Scanner(System.in);
		amount = Integer.parseInt(in.nextLine());
		for (int i = 0; i < amount; i++) {
			line = in.nextLine();
			arr = line.trim().split(" ");
			length = arr.length;
			for (int l = 0; l < length / 2; l++) {
				String j = arr[l];
				arr[l] = arr[length - 1 - l];
				arr[length - 1 - l] = j;
			}
			for (int qq = 0; qq < length; qq++) {
				line2 += " " + arr[qq];
			}
			arr2[i] = line2.trim();
			line2 = "";
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
