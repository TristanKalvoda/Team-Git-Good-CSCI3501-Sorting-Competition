import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Group4Optimized {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {

		if (args.length < 2) {
			System.out.println(
					"Running tests since input and output file names not specified");
			SortingCompetitionComparator.runComparatorTests();
			System.exit(0);
		}

		String inputFileName = args[0];
		String outFileName = args[1];

		int[][] data = readData(inputFileName); // read data as strings

		int[][] toSort = data.clone(); // clone the data

		sort(toSort); // call the sorting method once for JVM warmup

		toSort = data.clone(); // clone again

		Thread.sleep(10); // to let other things finish before timing; adds stability of runs

		long start = System.currentTimeMillis();

		sort(toSort); // sort again

		long end = System.currentTimeMillis();

		System.out.println(end - start);

		writeOutResult(toSort, outFileName); // write out the results

	}

	private static int[][] readData(String inputFileName) throws FileNotFoundException {
		ArrayList<int[]> input = new ArrayList<>();
		Scanner in = new Scanner(new File(inputFileName));

		while (in.hasNext()) {
			String str = in.next();
			input.add(Arrays.stream(str.split(",")).mapToInt(Integer::parseInt).toArray());
		}

		in.close();

		return input.toArray(new int[0][]);
	}

	private static final int INSERTION_SORT_THRESHOLD = 5;

	private static void sort(int[][] toSort) {
		mergeSort(toSort, 0, toSort.length - 1);
	}

	// private static void mergeSort(int[][] arr, int left, int right) {
	// 	if (left < right) {
	// 		int mid = (left + right) / 2;
	// 		mergeSort(arr, left, mid);
	// 		mergeSort(arr, mid + 1, right);
	// 		merge(arr, left, mid, right);
	// 	}
	// }

	private static void mergeSort(int[][] arr, int left, int right) {
		if (arr.length <= INSERTION_SORT_THRESHOLD) {
			// Insertion Sort
			insertionSort(arr,left,right);
		} else {
			// Merge Sort
			if (left < right) {
				int mid = (left + right) / 2;
				mergeSort(arr, left, mid);
				mergeSort(arr, mid + 1, right);
				merge(arr, left, mid, right);
			}
		}
	}

	private static void merge(int[][] arr, int left, int mid, int right) {
		int n1 = mid - left + 1;
		int n2 = right - mid;

		int[][] L = new int[n1][];
		int[][] R = new int[n2][];

		for (int i = 0; i < n1; ++i)
			L[i] = arr[left + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[mid + 1 + j];

		int i = 0, j = 0;
		int k = left;
		while (i < n1 && j < n2) {
			if (new SortingCompetitionComparator().compare(L[i], R[j]) <= 0) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		if (i < n1) {
			System.arraycopy(L, i, arr, k, n1 - i);
		}

		if(j < n2) {
			System.arraycopy(R, j, arr, k, n2 - j);
		}
		// while (i < n1) {
		// 	arr[k] = L[i];
		// 	i++;
		// 	k++;
		// }

		// while (j < n2) {
		// 	arr[k] = R[j];
		// 	j++;
		// 	k++;
		// }
	}

	private static void insertionSort(int [][] arr, int left, int right){
		for (int i = left +1; i <= right; i++) {
			int[] key = arr[i];
			int j = i - 1;
			while (j >= left && new SortingCompetitionComparator().compare(arr[j], key) > 0) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	private static class SortingCompetitionComparator implements Comparator<int[]> {

		@Override
		public int compare(int[] seq1, int[] seq2) {
			for (int i = 0; i < seq1.length && i < seq2.length; ++i) {
				int diff = seq1[i] - seq2[i];
				if (diff != 0)
					return diff;
			}

			if (seq1.length == seq2.length)
				return 0;

			int seq1_evens = 0;
			for (int i = 0; i < seq1.length; ++i) {
				if (seq1[i] % 2 == 0)
					seq1_evens++;
			}

			int seq2_evens = 0;
			for (int i = 0; i < seq2.length; ++i) {
				if (seq2[i] % 2 == 0)
					seq2_evens++;
			}

			int diff = seq1_evens - seq2_evens;
			if (diff != 0)
				return diff;

			return (seq2.length - seq2_evens) - (seq1.length - seq1_evens);
		}

		public static void runComparatorTests() {
			int[] arr1 = { 1, 3, 2 };
			int[] arr2 = { 1, 2, 3 };
			System.out.println("Comparing arr1 and arr2");
			System.out.println((new SortingCompetitionComparator()).compare(arr1, arr2));
			System.out.println((new SortingCompetitionComparator()).compare(arr2, arr1));
			System.out.println((new SortingCompetitionComparator()).compare(arr1, arr1));

			int[] arr3 = { 1, 3, 2, 5, 4 };

			System.out.println("Comparing arr1 and arr3");
			System.out.println((new SortingCompetitionComparator()).compare(arr1, arr3));
			System.out.println((new SortingCompetitionComparator()).compare(arr3, arr1));

			int[] arr4 = { 1, 3, 2, 7, 6, 5, 4 };

			System.out.println("Comparing arr1 and arr4");
			System.out.println((new SortingCompetitionComparator()).compare(arr1, arr4));
			System.out.println((new SortingCompetitionComparator()).compare(arr4, arr1));

			System.out.println("Comparing arr4 and arr3");
			System.out.println((new SortingCompetitionComparator()).compare(arr4, arr3));

			int[] arr5 = { 1, 3, 2, 5, 6, 7, 4 };

			System.out.println("Comparing arr1 and arr5");
			System.out.println((new SortingCompetitionComparator()).compare(arr1, arr5));
			System.out.println((new SortingCompetitionComparator()).compare(arr5, arr1));

			System.out.println("Comparing arr5 and arr3");
			System.out.println((new SortingCompetitionComparator()).compare(arr5, arr3));

			System.out.println("Comparing arr5 and arr4");
			System.out.println((new SortingCompetitionComparator()).compare(arr5, arr4));

			int[] arr6 = { 1, 3, 2, 6, 5, 7, 4 };
			System.out.println("Comparing arr1 and arr6");
			System.out.println((new SortingCompetitionComparator()).compare(arr1, arr6));

			System.out.println("Comparing arr3 and arr6");
			System.out.println((new SortingCompetitionComparator()).compare(arr3, arr6));

			System.out.println("Comparing arr5 and arr6");
			System.out.println((new SortingCompetitionComparator()).compare(arr5, arr6));

			System.out.println("Comparing arr4 and arr6");
			System.out.println((new SortingCompetitionComparator()).compare(arr4, arr6));

		}

	}

	private static void writeOutResult(int[][] sorted, String outputFilename) throws FileNotFoundException {
		PrintWriter out = new PrintWriter(outputFilename);
		for (int[] s : sorted) {
			for (int i = 0; i < s.length; ++i) {
				out.print(s[i] + (i < s.length - 1 ? "," : ""));
			}
			out.println();
		}
		out.close();
	}

}