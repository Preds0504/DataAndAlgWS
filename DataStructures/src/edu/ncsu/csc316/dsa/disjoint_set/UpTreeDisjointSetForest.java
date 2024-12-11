package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The UpTreeDisjointSetForest is implemented as a forest of linked up-trees.
 * Using balanced union, {@link DisjointSetForest#union} has worst-case runtime
 * of O(1). Using path-compression find, {@link DisjointSetForest#find} has
 * worst-case O(logn), but over time has worst-case runtime O(log*(n))
 * [log-star].
 * 
 * @author Dr. King
 * @author // Your Name Here
 *
 * @param <E> the type of elements stored in the disjoint set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {

    // We need a secondary map to quickly locate an entry within the forest of
    // up-trees
    // NOTE: The textbook implementation does not include this
    // functionality; instead, the textbook implementation leaves
    // the responsibility of tracking positions to the client in a
    // separate map structure
	/**The map for the disjoint set*/
    private Map<E, UpTreeNode<E>> map;

    /**
     * Constructs a new DisjointSetForest
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    /**
     * An UpTreeNode maintains an element, a reference to the node's parent, and (if
     * it's the root of an up-tree) the number of nodes stored within that up-tree.
     * 
     * @author Dr. King
     *
     * @param <E> The position
     */
private static class UpTreeNode<E> implements Position<E> {
        /**The element of the node*/
        private E element;
        /**The parent of the node*/
        private UpTreeNode<E> parent;
        /**The number of nodes related*/
        private int count;
        
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }
        
        public void setElement(E element) {
            this.element = element;
        }
        
        @Override
        public E getElement() {
            return element;
        }
        
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }
        
        public UpTreeNode<E> getParent() {
            return parent;
        }
        
        public void setCount(int count) {
            this.count = count;
        }
        
        public int getCount() {
            return count;
        }
    }

    @Override
    public Position<E> makeSet(E value) {
        if (map.get(value) != null) {
            throw new IllegalArgumentException("Element already exists");
        }
        UpTreeNode<E> node = new UpTreeNode<>(value);
        map.put(value, node); // Add to map for quick access
        return node;
    }

    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        // track of the location of each position in the forest.
        // Our implementation includes a Map to handle this for the
        // client, so we should allow the client to find the set
        // that contains a node by specifying the element
    	  UpTreeNode<E> node = map.get(value);
    	    if (node == null) {
    	        throw new IllegalArgumentException("Element not found");
    	    }
    	    return findHelper(node);
    }

    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {
        // Implement path-compression find
    	 if (current != current.getParent()) {
    		 current.setParent(findHelper(current.getParent()));
    	 }
    	 return current.getParent();
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(s);
        UpTreeNode<E> b = validate(t);
        if (a.getCount() > b.getCount()) {
        	a.setCount(a.getCount() + b.getCount());
        	b.setParent(a);
        } else {
        	b.setCount(a.getCount() + b.getCount());
        	a.setParent(b);
        }
    }

    private UpTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>) p;
    }
    
}
