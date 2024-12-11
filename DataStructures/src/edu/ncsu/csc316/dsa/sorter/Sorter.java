package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @param <E> the generic elements
 */
public interface Sorter<E> {
	/** 
	 * sort data based on different algrothms 
	 * @param data the data to sort
	 */
    void sort(E[] data);
}
