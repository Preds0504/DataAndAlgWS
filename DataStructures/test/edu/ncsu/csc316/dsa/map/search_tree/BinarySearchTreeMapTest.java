package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class BinarySearchTreeMapTest {
	/**The tree that we are testing*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(6, "six");
        tree.put(9, "nine");
        tree.put(8, "eight");
        assertEquals("four", tree.get(4));
        assertEquals("three", tree.get(3));
        assertEquals("six", tree.get(6));
        assertEquals("nine", tree.get(9));
        assertEquals("eight", tree.get(8));
        assertEquals(6, tree.size());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(6, "six");
        tree.put(9, "nine");
        tree.put(8, "eight");
        assertEquals("four", tree.get(4));
        assertEquals("three", tree.get(3));
        assertEquals("six", tree.get(6));
        assertEquals("nine", tree.get(9));
        assertEquals("eight", tree.get(8));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
    	//Checks for removing the root with no children
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        //Will be testing some other cases unrelated to the root
        //The only cases with only left or right child or test no children
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(1, "one");
        tree.put(3, "three");
        tree.put(6, "six");
        tree.put(5, "five");
        tree.put(7, "seven");
        //Test removing with only sentinel children
        tree.remove(7);
        assertEquals(6, tree.size());
        assertNull(tree.get(7));
        assertNotNull(tree.get(1));
        assertNotNull(tree.get(2));
        assertNotNull(tree.get(3));
        assertNotNull(tree.get(4));
        assertNotNull(tree.get(5));
        assertNotNull(tree.get(6));
        //Only left child
        tree.remove(6);
        assertEquals(5, tree.size());
        assertNull(tree.get(6));
        assertNotNull(tree.get(1));
        assertNotNull(tree.get(2));
        assertNotNull(tree.get(3));
        assertNotNull(tree.get(4));
        assertNotNull(tree.get(5));
        //Two children
        tree.remove(2);
        assertEquals(4, tree.size());
        assertNull(tree.get(2));
        assertNotNull(tree.get(4));
        assertNotNull(tree.get(5));
        assertNotNull(tree.get(1));
        assertNotNull(tree.get(3));
    }
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testMisc() {
        tree.put(1,  "one");
        tree.put(4, "four");
        tree.put(3, "three");
        tree.put(6, "six");
        tree.put(9, "nine");
        tree.put(8, "eight");
        assertEquals("four", tree.get(4));
        assertEquals("three", tree.get(3));
        assertEquals("six", tree.get(6));
        assertEquals("nine", tree.get(9));
        assertEquals("eight", tree.get(8));
        tree.entrySet();
        tree.iterator();
        assertEquals(tree.toString(), tree.toString());
    }
}
