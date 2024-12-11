package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for AVLTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an AVL tree data structure 
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class AVLTreeMapTest {
	/**Tree to be tested*/
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of an AVL tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new AVLTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(7, "seven");
        tree.put(3, "three");
        tree.put(10, "ten");
        tree.put(11, "eleven");
        tree.put(12, "twelve");
        assertEquals(5, tree.size());
        assertEquals(7, (int)tree.root().getElement().getKey());
        assertEquals(3, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(11, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(10, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(12, (int)tree.right(tree.right(tree.root())).getElement().getKey());

        
      //Add test cases for when restructuring occurs
        
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
        
        // You should create test cases to check all the
        // trinode restructuring scenarios. The textbook has visual examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey()); 
        tree.put(9, "nine");
        tree.put(10, "ten");
        tree.put(3, "three");
        tree.remove(9);
        assertEquals(10, (int)tree.root().getElement().getKey());
        
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