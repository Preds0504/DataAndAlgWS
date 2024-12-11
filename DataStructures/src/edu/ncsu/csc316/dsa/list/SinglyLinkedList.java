package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;

	/**
	 * Constructs an empty singly-linked list
	 */     
	public SinglyLinkedList() {
	    front = new LinkedListNode<E>(null);
	    tail = null;
	    size = 0;
	}

	@Override
	public void add(int index, E element) {
		//Need a throws here
		checkIndexForAdd(index);
		LinkedListNode<E> temp = new LinkedListNode<E>(element);
		if (index == 0) {
			if ( size == 0) {
				front.next = temp;
				tail = front.next;
			} else {
				temp.next = front.next;
				front.next = temp;
			}
			size++;
		} else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++ ) {
				current = current.next;
			}
			if (index == size) {
				current.next = temp;
			} else {
				temp.next = current.next;
				current.next = temp.next;
			}
			size++;
		}
	}

	@Override
	public E get(int index) {
		//Need to implement and test the exeption for get()
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.element;
	}

	@Override
	public E remove(int index) {
		LinkedListNode<E> temp;
		checkIndex(index);
		if (size == 1) {
			temp = front.next;
			front.next = null;
//			return temp.element;
		} else if (index == size - 1) {
			temp = tail;
			tail = null;
		} else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index - 1; i++ ) {
				current = current.next;
			}
			temp = current.next;
			current.next = current.next.next;
		}
		size--;
		return temp.element;
	}

	@Override
	public E set(int index, E element) {
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}
		LinkedListNode<E> current = front.next;
		for (int i = 0; i < index; i++ ) {
			current = current.next;
		}
		E temp = current.element;
		current.element = element;
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
     * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.element;
    }
    
    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */    
    @Override
    public void addLast(E element) {
    	LinkedListNode<E> temp = new LinkedListNode<E>(element);
    	if (size == 0) {
    		front.next = temp;
    		tail = temp;
    		size++;
    	} else {
    		tail.next = temp;
            tail = temp;
            size++;
    	}
        
    }
    
    private static class LinkedListNode<E> {
        
	    /** the element for the node**/
        private E element;
        /**The next Node in front of the node */
        private LinkedListNode<E> next;
        
        /**
         * The constructor for the LInked List Node
         * @param element the element for the list
         */
        public LinkedListNode(E element) { 
        	this.element = element;  
        	this.next = null;
        }
        
    }
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        
        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            current = front;
        }

        @Override
        public boolean hasNext() {
			return current.next != null;
        }

        @Override
        public E next() {
        	if (!this.hasNext() ) {
        		throw new NoSuchElementException();
        	}
        	E temp = current.next.element;
        	current = current.next;
			return temp;
        }
         
        @Override    
        public void remove() {
    	    // DO NOT CHANGE THIS METHOD
            throw new UnsupportedOperationException(
                "This SinglyLinkedList implementation does not currently support "
                + "removal of elements when using the iterator.");
        }
    }
    
}
