package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {

	/**
	 * Counting Sorting Algorithm
	 */
	@Override
	public void sort(E[] data) {
		int length = data.length;
		int min = data[0].getId();
		int max = data[0].getId();
		for (int i = 1; i < length; i++) {
			int id = data[i].getId();
			if (id < min) {
				min = id;
			} else if (id > max) {
				max = id;
			}
		}
		int k = max - min + 1;
		int[] list = new int[k];
		for (int i = 0; i < length; i++) {
			list[data[i].getId() - min]++;
		}
		for (int i = 1; i < k; i++) {
			list[i] += list[i - 1];
		}
		@SuppressWarnings("unchecked")
		E[] list2 = (E[]) new Identifiable[length];

		for (int i = length - 1; i >= 0; i--) {
			int id = data[i].getId();
			list2[list[id - min] - 1] = data[i];
			list[id - min]--;
		}

		for (int i = 0; i < length; i++) {
			data[i] = list2[i];
		}
	}
	
}
