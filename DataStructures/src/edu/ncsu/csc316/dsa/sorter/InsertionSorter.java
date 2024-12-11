package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @param <E> is the generic element
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E>  {
	
	/**
	 * Constructor without param is null
	 */
	public InsertionSorter() {
		this(null);
	}
	/**
	 * Constructor for the insertion sorter
	 * @param comparator the comparator
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	/**
	 * Will sort using the insertion-sort algorithm
	 * @param data the data to be sorted
	 */
	@Override
	public void sort(E[] data) {
		int len = data.length;
		for (int i = 1; i < len; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && compare(data[j], x) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = x;

		}
	}


}
