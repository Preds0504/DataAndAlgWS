package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }

	@Override
	public void sort(E[] data) {
		//(From Textbook page 536)
		 int n = data.length;
		    if (n < 2) return;                              // array is trivially sorted
		    // divide
		    int mid = n / 2;
		    E[ ] s1 = Arrays.copyOfRange(data, 0, mid);         // copy of first half
		    E[ ] s2 = Arrays.copyOfRange(data, mid, n);         // copy of second half
		    // conquer (with recursion)
		    sort(s1);                            // sort copy of first half
		    sort(s2);                            // sort copy of second half
		    // merge results
		    merge(s1, s2, data);              // merge sorted halves back into original
		
	}
    
	 /** 
	  * Merge contents of arrays S1 and S2 into properly sized array S. (From Textbook page 536)
	  * @param s1 previously sorted sequence 1
	  * @param s2 previously sorted sequence 2
	  * @param s where the output will be copied
	  */
	 public void merge(E[] s1, E[] s2, E[] s) {
	   int i = 0, j = 0;
	   while (i + j < s.length) {
	     if (j == s2.length || (i < s1.length && compare(s1[i], s2[j]) < 0))
	        s[i + j] = s1[i++];               // copy ith element of S1 and increment i
	     else
	        s[i + j] = s2[j++];                // copy jth element of S2 and increment j
	   }
	 }
	

}