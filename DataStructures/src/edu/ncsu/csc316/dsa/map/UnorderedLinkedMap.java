package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	/**The list for the map*/
    private PositionalList<Entry<K, V>> list;
    /**
     * The constructor for the PositionalLinkedList
     */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    
    private Position<Entry<K, V>> lookUp(K key)
    {
    	for (Position<Entry<K, V>> position : list.positions() ) {
    		if (key.equals(position.getElement().getKey())) {
    			moveToFront(position);
    			return position;
    		}
    	}
		return null;
    }

    /**
	 * helper method to move the used entries to the front
	 * @param position the position of the element to move
	 */
	private void moveToFront(Position<Entry<K, V>> position) {
		list.addFirst(list.remove(position));
	}
	
	@Override
    public V get(K key) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null) {
        	return null;
        }
		return p.getElement().getValue();
    }
    @Override
    public V put(K key, V value) {
        Position<Entry<K, V>> p = lookUp(key);
        if (p == null ) {
        	//This is the case where the entry doesn't already exist
        	list.addFirst(new MapEntry<>(key, value));
        	return null;
        } else {
        	//This is the case where the entry already exists
        	V valueBefore = p.getElement().getValue();
        	list.remove(p);
			list.addFirst(new MapEntry<>(key, value));
        	return valueBefore;
        }
		
    }
    
    @Override
    public V remove(K key) {
       Position<Entry<K, V>> p = lookUp(key);
       if (p == null) {
    	   return null;
       } else {
    	   V valueBefore = p.getElement().getValue();
    	   list.remove(p);
    	   return valueBefore;
       }
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection collection = new EntryCollection();
        for(Entry<K, V> entry : list) {
            collection.add(entry);
        }
        return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    
   
    
}

