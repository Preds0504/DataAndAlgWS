package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
/**
 * The bubble sorter file
 * @author Tyler Hardison
 * @param <E> the generic element
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
	 * Bubble sorter constructor
	 */
	public BubbleSorter() {
		this(null);
	}

	/**
	 * Constructor for bubblesorter
	 * 
	 * @param comparator the comparator being set
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	public void sort(E[] data) {
		int len = data.length;
		boolean swap = true;
		while (swap) {
			swap = false; 
			for (int i = 1; i < len; i++) {
				if (compare(data[i], data[i - 1]) < 0) {
					E temp = data[i - 1];
					data[i - 1] = data[i];
					data[i] = temp;
					swap = true;
				}
			}
		}
	}
}
