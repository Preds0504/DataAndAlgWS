package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
/**
 * The Abstract comparison sorter class
 * @param <E> the data used
 * @author Tyler Hardison
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {
	/**For the custom comparator*/
    private Comparator<E> comparator;
    /**
     * Constructor for the abstract comparison sorter
     * @param comparator the comparator chosen
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    private class NaturalOrder implements Comparator<E> {
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    /**
     * compares two elements
     * @param first the first compared
     * @param second the second compared
     * @return the result of the comparison as an int
     */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}
