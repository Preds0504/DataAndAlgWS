package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {

	
	@Override
	public void sort(E[] data) {
		int length = data.length;
		if (length == 0) {
			return;
		}
		int max = data[0].getId();
		for (int i = 1; i < length; i++) {
			max = Math.max(max, data[i].getId());
		}
		int maxDigits = (int) Math.ceil(Math.log10(max + 1));
		int temp = 1;
		for (int j = 0; j < maxDigits; j++) {
			int[] list2 = new int[10];
			for (int i = 0; i < length; i++) {
				int digit = data[i].getId() / temp % 10;
				list2[digit]++;
			}
			for (int i = 1; i < 10; i++) {
				list2[i] += list2[i - 1];
			}
			@SuppressWarnings("unchecked")
			E[] list = (E[]) new Identifiable[length];
			for (int i = length - 1; i >= 0; i--) {
				int digit = data[i].getId() / temp % 10;
				list[list2[digit] - 1] = data[i];
				list2[digit]--;
			}
			for (int i = 0; i < length; i++) {
				data[i] = list[i];
			}
			temp *= 10;
		}
	}

}
