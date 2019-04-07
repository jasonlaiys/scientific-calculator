package driver;

import util.*;

public class Driver_DyamicArrayTest {

	public static void main(String[] args) {
		String[] s = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"};
		
		DynamicArray<String> da = new DynamicArray<String>(s);
		
		System.out.println("----- INITIALIZING -----");
		for (int i = 0; i < da.getSize(); i++) {
			System.out.print(da.get(i));
		}
		System.out.println();
		
		da.add("m");
		System.out.println("----- ADD M -----");
		for (int i = 0; i < da.getSize(); i++) {
			System.out.print(da.get(i));
		}
		System.out.println();
		
		da.removePushBack("i");
		System.out.println("----- REMOVE PUSH BACK I -----");
		for (int i = 0; i < da.getSize(); i++) {
			System.out.print(da.get(i));
		}
		System.out.println();
		
		da.merge("x", 4, 7);
		System.out.println("----- MERGE X 4 7 -----");
		for (int i = 0; i < da.getSize(); i++) {
			System.out.print(da.get(i));
		}
		System.out.println();
		
		String arr[] = {"y", "z"};
		da.addPushBack(arr, 2);
		System.out.println("----- ADD PUSH BACK Y 2 -----");
		for (int i = 0; i < da.getSize(); i++) {
			System.out.print(da.get(i));
		}
		System.out.println();
	}

}
