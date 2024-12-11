package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for LinearProbingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a linear probing hash map data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class LinearProbingHashMapTest {

    /** 'Testing' Map used (no randomization) to check placement of entries in the hash table*/
    private Map<Integer, String> testMap;
    
    /** 'Production' Map (with randomization) to check correctness of ADT behaviors*/
    private Map<Integer, String> prodMap;

    /**
     * Create a new instance of a linear probing hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are testing.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        testMap = new LinearProbingHashMap<Integer, String>(7, true);
        prodMap = new LinearProbingHashMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, testMap.size());
        assertTrue(testMap.isEmpty());
        // You should create some collisions to check that entries
        // are placed in the correct buckets.
        //
        // You should also use the prodMap to check that put works
        // as expected when randomization is used internally. You can't
        // check the placement of entries within the hash table,
        // but you can still check that put gives the correct outputs when
        // randomization is used internally.
        assertNull(testMap.put(3, "string3"));
   	 	assertNull(testMap.put(4, "string4"));
   	 	assertNull(testMap.put(10, "string10"));
        Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey());
        assertEquals(4, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
        assertEquals("string3", testMap.put(3, "stringthree"));
        Iterator<Map.Entry<Integer, String>> it2 = testMap.entrySet().iterator();
        assertEquals("stringthree", it2.next().getValue());
        assertEquals(4, (int)it2.next().getKey());
        assertEquals(10, (int)it2.next().getKey());

        
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(testMap.isEmpty());
        testMap.put(3, "string3");
   	 	testMap.put(4, "string4");
   	 	testMap.put(10, "string10");
   	 	assertEquals("string3", testMap.get(3));
   	 	assertEquals("string4", testMap.get(4));
   	 	assertEquals("string10", testMap.get(10));
        // You should also use the prodMap to check that get works
        // as expected when randomization is used internally.
    }
    
    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(testMap.isEmpty());
        // You should also use the prodMap to check that remove works
        // as expected when randomization is used internally. You can't
        // check the placement of entries within the hash table,
        // but you can still check that remove gives the correct outputs when
        // randomization is used internally.  
        testMap.put(3, "string3");
   	 	testMap.put(4, "string4");
   	 	testMap.put(10, "string10");
   	 	assertEquals("string3", testMap.remove(3));
        Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
        assertEquals(4, (int)it.next().getKey());
        assertEquals(10, (int)it.next().getKey());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	testMap.put(3, "string3");
   	 	testMap.put(4, "string4");
   	 	testMap.put(10, "string10");        
        Iterator<Integer> it = testMap.iterator();
        assertEquals(3, (int)it.next());
        assertEquals(4, (int)it.next());
        assertEquals(10, (int)it.next());
       
    }
    
    /**
     * Test the output of the entrySet() behavior
     */     
    @Test
    public void testEntrySet() {
    	 assertNull(testMap.put(3, "string3"));
    	 assertNull(testMap.put(4, "string4"));
    	 assertNull(testMap.put(10, "string10"));
    	 Iterator<Map.Entry<Integer, String>> it = testMap.entrySet().iterator();
         assertEquals(3, (int)it.next().getKey());
         assertEquals(4, (int)it.next().getKey());
         assertEquals(10, (int)it.next().getKey());    
    }
    
    /**
     * Test the output of the values() behavior
     */  
    @Test
    public void testValues() {
    	assertNull(testMap.put(3, "string3"));
    	assertNull(testMap.put(4, "string4"));
    	assertNull(testMap.put(10, "string10"));
        Iterator<String> it = testMap.values().iterator();
        assertEquals("string3", it.next());
        assertEquals("string4", it.next());
        assertEquals("string10", it.next());   
        
    }
}
