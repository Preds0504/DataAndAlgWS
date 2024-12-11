package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class RedBlackTreeMapTest {
	/**The tree to be tested*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        // You should create test cases to check all the
        // rules for red-black trees. The textbook has examples
        // that you can use to create your test cases

        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());   
        
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(8, "eight");
        tree.put(1, "one");
        assertEquals(6, tree.size());
        assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(8, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(3, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());

    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(3, "three");
        assertEquals("three", tree.get(3));
        assertEquals("nine", tree.get(9));
        assertEquals("ten", tree.get(10));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(3, "three");
        tree.put(7, "seven");
        tree.put(8, "eight");
        tree.put(1, "one");
        tree.remove(1);
        tree.remove(3);
        assertEquals(9, (int)tree.root().getElement().getKey());
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(8, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertNull(tree.get(1));
        assertNull(tree.get(3));
        tree.remove(10);
        assertEquals(8, (int)tree.root().getElement().getKey());
        assertEquals(9, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(7, (int)tree.left(tree.root()).getElement().getKey());
        

        
    }
    /**testing misc stuff that doesn't impact anyhting*/
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
