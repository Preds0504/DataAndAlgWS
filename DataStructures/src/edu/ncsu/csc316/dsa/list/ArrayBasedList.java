package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Will add an element at the specified index
     * @param index the index to add
     * @param element the element added to data
     * @throws IndexOutOfBounds if trying add beyond 1 + the capacity
     */
	@Override
	public void add(int index, E element) {
		  ensureCapacity(size + 1);

	        for (int i = size; i > index; i--) {
	            this.data[i] = this.data[i - 1];
	        }

	        this.data[index] = element;
	        this.size += 1;
	}

	/**
	 * Will get the element at the index
	 * @return the element at the index
	 */
	@Override
	public E get(int index) {
		  if (index < 0 || index >= size()) {
	            throw new IndexOutOfBoundsException();
	        }
	        return this.data[index];
	}
	/**
	 * Removes the element at an index 
	 * @param index the index to remove the element
	 * @return the element that was removed
	 */
	@Override
	public E remove(int index) {
		E element = get(index);
        for (int i = index; i < size() - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        this.data[size() - 1] = null;
        this.size -= 1;
        return element;
	}
	/**
	 * Will replace an element at a specified index
	 * @param index The index to set
	 * @param element The replacement element
	 * @return the original element
	 */
	@Override
	public E set(int index, E element) {
		if (index >= size ) {
			throw new IndexOutOfBoundsException();
		}
		E temp = data[index];
		data[index] = element;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
    public Iterator<E> iterator() {
    	return new ElementIterator();
    }
    
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
    	  int oldCapacity = data.length;
    	    if (minCapacity > oldCapacity) {
    	        int newCapacity = (oldCapacity == 0) ? 1 : (oldCapacity * 2) + 1;
    	        if (newCapacity < minCapacity) {
    	            newCapacity = minCapacity;
    	        }
    	        data = Arrays.copyOf(data, newCapacity);
    	    }
    }
    
    private class ElementIterator implements Iterator<E> {
        /** The position of the iterator */
        private int position;
        /** Check if removal is allowed */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized to the beginning of the
         * list.
         */
        public ElementIterator() {
            this.position = 0;
            this.removeOK = false;
        }

        @Override
        public boolean hasNext() {
            return this.position < size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = data[this.position];
            this.position += 1;
            this.removeOK = true;
            return element;
        }

        @Override
        public void remove() {
            if (!this.removeOK) {
                throw new IllegalStateException();
            }

            // shift elements to left
            for (int i = this.position - 1; i < size() - 1; i++) {
                data[i] = data[i + 1];
            }
            data[size() - 1] = null;
            size -= 1;
            this.position -= 1;
            this.removeOK = false;
        }
    }
}