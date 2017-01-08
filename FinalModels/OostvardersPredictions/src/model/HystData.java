package model;

import java.util.Scanner;

import java.io.File;

import java.util.ArrayList;

public class HystData {

	public static ArrayList<Integer> read(File f) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		try {

			Scanner sc = new Scanner(f);
			while (sc.hasNextLine()) {
				array.add(Integer.valueOf(sc.nextLine()));
			}

		} catch (Exception e) {
			return null;
		}
		return array;
	}

	public static void main(String[] args) {
		File file1 = new File("Data.txt");//  file path here
		ArrayList<Integer> array1;
		array1 = read(file1);
		System.out.println(array1);
	}
}