package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class LinkedBinaryTreeTest {
	/**tree for testing*/
    private LinkedBinaryTree<String> tree;
    /**Position one*/
    private Position<String> one;
    /**Position two*/
    private Position<String> two;
    /**Position three*/
    private Position<String> three;
    /**Position four*/
    private Position<String> four;
    /**Positon five*/
    private Position<String> five;
    /**Position six*/
    private Position<String> six;
    /**Position seven*/
    private Position<String> seven;
    /**Postion eight*/
    private Position<String> eight;
    /**Position nine*/
    private Position<String> nine;
    /**Position ten*/
    private Position<String> ten;

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        tree.set(eight, "8");
        assertEquals("8", eight.getElement());
        tree.set(four, "4");
        assertEquals("4", four.getElement());
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
        assertTrue(tree.isEmpty());
        createTree();
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();
        assertEquals(2, tree.numChildren(one));
        assertEquals(2, tree.numChildren(two));
        assertEquals(1, tree.numChildren(three));
        assertEquals(2, tree.numChildren(four));
        assertEquals(0, tree.numChildren(five));
        assertEquals(0, tree.numChildren(six));
        assertEquals(0, tree.numChildren(seven));
        assertEquals(0, tree.numChildren(eight));
        assertEquals(0, tree.numChildren(nine));
        assertEquals(2, tree.numChildren(ten));

    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
        createTree();
        assertNull(tree.parent(one));
        assertEquals(one.toString(), tree.parent(two).toString());
        assertEquals(one.toString(), tree.parent(three).toString());
        assertEquals(two.toString(), tree.parent(six).toString());
        assertEquals(two.toString(), tree.parent(ten).toString());
        assertEquals(three.toString(), tree.parent(four).toString());
        assertEquals(four.toString(), tree.parent(eight).toString());
        assertEquals(four.toString(), tree.parent(nine).toString());
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        assertNull(tree.sibling(one));
        assertEquals(two.toString(), tree.sibling(three).toString());
        assertEquals(three.toString(), tree.sibling(two).toString());
        assertEquals(six.toString(), tree.sibling(ten).toString());
        assertEquals(ten.toString(), tree.sibling(six).toString());
        assertEquals(seven.toString(), tree.sibling(five).toString());
        assertEquals(five.toString(), tree.sibling(seven).toString());
        assertEquals(eight.toString(), tree.sibling(nine).toString());
        assertEquals(nine.toString(), tree.sibling(eight).toString());
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(one));
        assertTrue(tree.isInternal(two));
        assertTrue(tree.isInternal(three));
        assertTrue(tree.isInternal(ten));
        assertTrue(tree.isInternal(four));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void isLeaf() {
        createTree();
        assertTrue(tree.isLeaf(seven));
        assertTrue(tree.isLeaf(five));
        assertTrue(tree.isLeaf(eight));
        assertTrue(tree.isLeaf(nine));
        assertTrue(tree.isLeaf(six));
        assertFalse(tree.isLeaf(one));

    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void isRoot() {
        createTree();
        assertTrue(tree.isRoot(one));
        assertFalse(tree.isRoot(two));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
    	createTree();
        Iterable<Position<String>> traversal = tree.preOrder();
        Iterator<Position<String>> it = traversal.iterator();
        String[] expected = { "one", "two", "six", "ten", "seven", "five", "three", "four", "eight", "nine" };
        int i = 0;
        while (it.hasNext()) {
            Position<String> position = it.next();
            assertEquals(expected[i], position.getElement());
            i++;
        }
        assertEquals(expected.length, i);
        
        
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
    	createTree();
        Iterable<Position<String>> traversal = tree.postOrder();
        Iterator<Position<String>> it = traversal.iterator();
        String[] expected = { "six", "seven", "five", "ten", "two", "eight", "nine", "four", "three", "one" };
        int i = 0;
        while (it.hasNext()) {
            Position<String> position = it.next();
            assertEquals(expected[i], position.getElement()); 
            i++;
        }
        assertEquals(expected.length, i);
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
        createTree();
        Iterable<Position<String>> traversal = tree.inOrder();
        Iterator<Position<String>> it = traversal.iterator();
        String[] expected = { "six", "two", "seven", "ten", "five", "one", "eight", "four", "nine", "three" };
        int i = 0;
        while (it.hasNext()) {
            Position<String> position = it.next();
            assertEquals(expected[i], position.getElement()); 
            i++;
        }
        assertEquals(expected.length, i);
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
    	tree = new LinkedBinaryTree<String>();
    	assertTrue(tree.isEmpty());
    	//Many cases to test here
    }
    /**
     * Test for the level order traversal
     */
    @Test
    public void testLevelOrder() {
        createTree();
        Iterable<Position<String>> traversal = tree.levelOrder();
        Iterator<Position<String>> it = traversal.iterator();
        String[] expected = { "one", "two", "three", "six", "ten", "four", "seven", "five", "eight", "nine" };
        int i = 0;
        while (it.hasNext()) {
            Position<String> position = it.next();
            assertEquals(expected[i], position.getElement()); 
            i++;
        }
        assertEquals(expected.length, i);
        try {
        	it.remove();
        	fail();
        } catch (Exception e) {
        	assertEquals("The remove operation is not supported yet.", e.getMessage());
        }
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	createTree();
    	Position<String> eleven;
    	eleven = tree.addLeft(nine, "eleven");
    	assertEquals(eleven.getElement(), tree.left(nine).getElement());
    	try {
    		tree.addLeft(nine, "wow");
    		fail();
    	} catch (Exception e) {
    		assertEquals("Node already has a left child.", e.getMessage());
    	}
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	createTree();
    	Position<String> eleven = tree.addRight(nine, "eleven");
    	assertEquals(eleven.toString(), tree.right(nine).toString());
    	try {
    		tree.addRight(nine, "wow");
    		fail();
    	} catch (Exception e) {
    		assertEquals("Node already has a right child.", e.getMessage());
    	}
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
        createTree();
        tree.remove(six);
        assertEquals(9, tree.size());
        assertEquals(1, tree.numChildren(two));
        assertNull(tree.left(two));
        tree.remove(two);
        assertEquals(8, tree.size());
        assertEquals(2, tree.numChildren(one));
        assertEquals(ten.toString(), tree.left(one).toString());
        try {
        	tree.remove(four);
        	fail();
        } catch (Exception e) {
        	 assertEquals("The node has two children", e.getMessage());
        }
    }
    /**
     * Test the addroot
     */      
    @Test
    public void testAddRoot() {
    	createTree();
    	try {
    		tree.addRoot("yes");
    		fail();
    	} catch (Exception e) {
    		 assertEquals("The tree already has a root.", e.getMessage());
    	}
    }   
    /**
     * Test the addroot
     */      
    @Test
    public void testSetRoot() {
    	createTree();
    	tree.setRoot(eight);
    	assertTrue(tree.isRoot(eight));
    }   
    /**
     * Test the toString
     */      
    @Test
    public void testToString() {
    	createTree();
    	assertEquals("LinkedBinaryTree[\none\n two\n  six\n  ten\n   seven\n   five\n three\n  four\n   eight\n   nine\n]", tree.toString());
    }   
}