package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.AbstractMap.MapEntry;
import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class UnorderedLinkedMapTest {
	/**The map*/
    private Map<Integer, String> map;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        assertEquals(5, map.size());
        assertEquals(map.get(3), "string3");
        map.put(3, "stringThree");
        assertEquals(map.get(3), "stringThree");
        map.put(1, "stringOne");
        assertEquals(map.get(1), "stringOne");


    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
        
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        assertNull(map.remove(20), null);
        assertEquals(map.remove(4), "string4");
        map.remove(4);
        assertEquals("UnorderedLinkedMap[1, 2, 5, 3]", map.toString());
        assertNull(map.get(4));
        map.remove(1);
        assertEquals("UnorderedLinkedMap[2, 5, 3]", map.toString());
        assertNull(map.get(1));
        map.remove(2);
        assertEquals("UnorderedLinkedMap[5, 3]", map.toString());
        assertNull(map.get(2));
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	//value iterator
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        Iterator<Entry<Integer, String>> iterator = map.entrySet().iterator();

        assertTrue(iterator.hasNext());
        Entry<Integer, String> entry = iterator.next();
        assertEquals(Integer.valueOf(1), entry.getKey());
        assertEquals("string1", entry.getValue());

        assertTrue(iterator.hasNext());
        entry = iterator.next();
        assertEquals(Integer.valueOf(4), entry.getKey());
        assertEquals("string4", entry.getValue());
        try {
            iterator.remove();
            fail();
        } catch(Exception e) {
        	 assertEquals("The remove operation is not supported yet.", e.getMessage());
        }

        assertTrue(iterator.hasNext());
        entry = iterator.next();
        assertEquals(Integer.valueOf(2), entry.getKey());
        assertEquals("string2", entry.getValue());
        
        assertTrue(iterator.hasNext());
        entry = iterator.next();
        assertEquals(Integer.valueOf(5), entry.getKey());
        assertEquals("string5", entry.getValue());

        assertTrue(iterator.hasNext());
        entry = iterator.next();
        assertEquals(Integer.valueOf(3), entry.getKey());
        assertEquals("string3", entry.getValue());
        
        assertFalse(iterator.hasNext());
        
        
        //key iterator
        Iterator<Integer> keyIterator = map.iterator();

        assertTrue(keyIterator.hasNext());
        assertEquals(Integer.valueOf(1), keyIterator.next());  // "cherry" should be first due to move-to-front

        assertTrue(keyIterator.hasNext());
        assertEquals(Integer.valueOf(4), keyIterator.next());  // "banana" should be second
        
        try {
        	keyIterator.remove();
            fail();
        } catch(Exception e) {
        	 assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        Iterable<Entry<Integer, String>> entrySet = map.entrySet();
        Iterator<Entry<Integer, String>> iterator = entrySet.iterator();

        assertTrue(iterator.hasNext());
        Entry<Integer, String> entry = iterator.next();
        assertEquals(Integer.valueOf(1), entry.getKey());
        assertEquals("string1", entry.getValue());

        //Adding in testing for compareTo
        MapEntry<Integer, String> entryComp = new MapEntry<>(1, "1");
        assertEquals(entryComp.compareTo(entryComp), 0);
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */     
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterable<String> values = map.values();
        Iterator<String> iterator = values.iterator();
        
        assertTrue(iterator.hasNext());
        assertEquals("string1", iterator.next());  // Since "cherry" was added last

        assertTrue(iterator.hasNext());
        assertEquals("string4", iterator.next());  // Then "banana"

        assertTrue(iterator.hasNext());
        assertEquals("string2", iterator.next());  // Finally, "apple"

        
    }
}