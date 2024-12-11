package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley & Sons, 2014
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator(); 
    }
    

	@Override
	public Position<E> addAfter(Position<E> p, E element) {
		//From Textbook page 278
		return addBetween(element, validate(p).getNext(), validate(p));
	}

	@Override
	public Position<E> addBefore(Position<E> p, E element) {
		//From Textbook page 278
		return addBetween(element, validate(p), validate(p).getPrevious());
	}

	@Override
	public Position<E> addFirst(E element) {
		//From Textbook page 278
		return addBetween(element, front.next, front);
	}

	@Override
	public Position<E> addLast(E element) {
		// From textbooke page 278
		return addBetween(element, tail, tail.previous);
	}

	@Override
	public Position<E> after(Position<E> p) {
		//From the textbook page 277
		PositionalNode<E> temp = validate(p);
		if (temp.getNext().getElement() == null) {
			return null;
		}
		return temp.getNext();
	}

	@Override
	public Position<E> before(Position<E> p) {
		// From the textbook page 277
		PositionalNode<E> temp = validate(p);
		if (temp.getPrevious().getElement() == null) {
			return null;
		}
		return temp.getPrevious();
	}

	@Override
	public Position<E> first() {
		//From the textbook page 277 
		if (isEmpty()) {
			return null;
		}
		return front.getNext();
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Position<E> last() {
		//From the textbook page 277 
		if (isEmpty()) {
			return null;
		}
		return tail.getPrevious();
	}

	@Override
    public Iterable<Position<E>> positions() {
		//From textbook page 286
		return new PositionIterable();
    }

	@Override
	public E remove(Position<E> p) {
		//From the textbook page 279
		PositionalNode<E> node = validate(p);
		PositionalNode<E> predecessor = node.getPrevious();
		PositionalNode<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrevious(predecessor);
		size--;
		E answer = node.getElement( );
		return answer;
		
	}

	@Override
	public E set(Position<E> p, E element) {
//		From textbook page 279
		PositionalNode<E> temp = validate(p);
		E answer = temp.getElement();
		temp.setElement(element);
		return answer;
	}

	@Override
	public int size() {
		return size;
	}
	  /**
     * Safely casts a Position, p, to be a PositionalNode.
     * 
     * @param p the position to cast to a PositionalNode
     * @return a reference to the PositionalNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  PositionalNode
     */
    private PositionalNode<E> validate(Position<E> p) {
        if (p instanceof PositionalNode) {
            return (PositionalNode<E>) p;
        }
        throw new IllegalArgumentException("Position is not a valid positional list node.");
    }
    
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
    	//From the textbook page 278
    	PositionalNode<E> temp = new PositionalNode<E>(element, next, prev); 
    	prev.setNext(temp);
    	next.setPrevious(temp);
    	size++;
        return temp;
    }
    
	 private static class PositionalNode<E> implements Position<E> {
		 	/**element for the node*/
	        private E element;
	        /** the next node in the list*/
	        private PositionalNode<E> next;
	        /** the previous in the list*/
	        private PositionalNode<E> previous;

	        public PositionalNode(E value) {
	            this(value, null);
	        }

	        public PositionalNode(E value, PositionalNode<E> next) {
	            this(value, next, null);
	        }

	        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
	            setElement(value);
	            setNext(next);
	            setPrevious(prev);
	        }

	        public void setPrevious(PositionalNode<E> prev) {
	            previous = prev;
	        }

	        public PositionalNode<E> getPrevious() {
	            return previous;
	        }
	        
	        public void setNext(PositionalNode<E> next) {
	            this.next = next;
	        }

	        public PositionalNode<E> getNext() {
	            return next;
	        }

	        @Override
	        public E getElement() {
	            return element;
	        }
	        
	        public void setElement(E element) {
	            this.element = element;
	        }
	        
	    }
	 private class PositionIterator implements Iterator<Position<E>> {
		 	/**the current node*/
	        private Position<E> current;
	        /** previous node in the list*/
	        private Position<E> previous;
	        /**If its okay to remove*/
	        private boolean removeOK;

	        public PositionIterator() {
	        	//From Textbook page 286
	            current = first();
	            previous = null;
	        }

	        @Override
	        public boolean hasNext() {
	        	//From Textbook page 286
				return current != null;
	        }

	        @Override
	        public Position<E> next() {
	        	//From Textbook page 286 
	        	if (current == null) {
	        		throw new NoSuchElementException();
	        	}
	        	Position<E> recent = current;
	        	current = after(current);
	        	removeOK = true;
				return recent;
	        }

	        @Override
	        public void remove() {
	            // From the textbook page 286
	        	if (removeOK) {
	        		PositionalLinkedList.this.remove(current);         
		        	current = null; 
	        	} else {
	        		throw new IllegalStateException();
	        	}
	        	                
	        }
	    }
	 private class ElementIterator implements Iterator<E> {
		 	/**iterator*/
	        private Iterator<Position<E>> it;

	        public ElementIterator() {
	            it = new PositionIterator();
	        }

	        @Override
	        public boolean hasNext() {
	            return it.hasNext();
	        }

	        @Override
	        public E next() {
	            return it.next().getElement();
	        }

	        @Override
	        public void remove() {
	            it.remove();
	        }
	    }
	 private class PositionIterable implements Iterable<Position<E>> {
	        
	        @Override
	        public Iterator<Position<E>> iterator() {
	            return new PositionIterator();
	        }
	    }
}
