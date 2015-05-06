package one;

import java.util.Scanner;

import org.apache.commons.lang.ArrayUtils;

public class Task2 {

	public static void main(String[] args) throws Exception {
		String line = "";
		String line2 = "";
		int credit = 0;
		int amount;
		int ampos;
		boolean key = true;
		boolean sameSuccess = false;
		int index = 0;
		int[] arr;
		int index2 = 0;
		String[] arr2 = new String[50];

		Scanner in = new Scanner(System.in);
		amount = Integer.parseInt(in.nextLine());
		for (int i = 0; i < amount; i++) {
			credit = Integer.parseInt(in.nextLine());
			ampos = Integer.parseInt(in.nextLine());
			line = in.nextLine();

			arr = convertStringArraytoIntArray(line.trim().split(" "));
			while (key) {

				if (ArrayUtils.contains(arr, credit - arr[index])) {
					sameSuccess = true;
					index2 = ArrayUtils.indexOf(arr, credit - arr[index]);
					if (arr[index] == credit / 2) {
						sameSuccess = false;
						arr[index] = arr[index] + 1;
						if (ArrayUtils.contains(arr, credit / 2)) {
							index2 = ArrayUtils.indexOf(arr, credit / 2);
							sameSuccess = true;
						}
						arr[index] = arr[index] - 1;
					}
					line2 = (index + 1) + " " + (index2 + 1);
					System.out.println("case " + i + " "
							+ (arr[index] + arr[index2]));
					if (sameSuccess)
						key = false;
				}
				index++;
				if (index > ampos)
					key = false;
			}
			arr2[i] = line2.trim();
			line2 = "";
			index = 0;
			key = true;

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

	public static int[] convertStringArraytoIntArray(String[] sarray)
			throws Exception {
		if (sarray != null) {
			int intarray[] = new int[sarray.length];
			for (int i = 0; i < sarray.length; i++) {
				intarray[i] = Integer.parseInt(sarray[i]);
			}
			return intarray;
		}
		return null;
	}

}
