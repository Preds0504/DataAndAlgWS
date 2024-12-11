package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>>  extends AbstractComparisonSorter<E> {

	
    /**
	 * Constructor without param is null
	 */
	public SelectionSorter() {
		this(null);
	}
    /**
     * The selectionsort constructor
     * @param comparator the comparator
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * The Selection sorting algorithm
     * @param data the data to be sorted
     */
    public void sort(E[] data) {
    	int len = data.length;
		for (int i = 0; i < len - 1; i++) {
			int min = i;
			for (int j = i + 1; j < len; j++) {
				if (compare(data[j], data[min]) < 0) {
					min = j; 
				}
			}
			if (min != i) {
				E temp = data[i];
				data[i] = data[min];
				data[min] = temp;
			}
		}
    }
}
