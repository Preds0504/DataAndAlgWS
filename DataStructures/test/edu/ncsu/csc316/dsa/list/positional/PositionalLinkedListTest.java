package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class PositionalLinkedListTest {

    private PositionalList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including any expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first.getElement(), list.first().getElement());
        Position<String> zero = list.addFirst("zero");
        assertEquals(2, list.size());
        assertEquals(zero.getElement(), list.first().getElement());
        
    }
    
    /**
     * Test the output of the last() behavior, including any expected exceptions
     */
    @Test
    public void testLast() {
    	   assertEquals(0, list.size());
           assertTrue(list.isEmpty());
           
           assertNull(list.last());
           
           Position<String> first = list.addFirst("one");
           assertEquals(1, list.size());
           assertEquals(first.getElement(), list.last().getElement());
           Position<String> second = list.addLast("two");
           assertEquals(2, list.size());
           assertEquals(second.getElement(), list.last().getElement());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one",first.getElement());
        Position<String> zero = list.addFirst("zero");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals("zero",zero.getElement());
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", first.getElement());
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        assertEquals("two",second.getElement());
        
    }
    
    /**
	 * Test the output of the after(position) behavior, including any expected exceptions
	 */     
	@Test
	public void testAfter() {
        Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        assertEquals("two", list.after(first).getElement());
        assertNull(list.after(second));
	}

	/**
     * Test the output of the before(position) behavior, including any expected exceptions
     */ 
    @Test
    public void testBefore() {
    	 Position<String> first = list.addLast("one");
         Position<String> second = list.addLast("two");
         assertEquals("one", list.before(second).getElement());
         assertNull(list.before(first));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testAddBefore() {
          Position<String> first = list.addLast("one");
          Position<String> second = list.addLast("two");
          Position<String> fifth = list.addLast("fifth");
          assertEquals("fifth", fifth.getElement());
          assertEquals(3, list.size());
          Position<String> fourth = list.addBefore(fifth, "fourth");
          assertEquals("fourth", list.before(fifth).getElement());
          assertEquals(4, list.size());
          Position<String> third = list.addBefore(fourth, "third");
          assertEquals("third", list.before(fourth).getElement());
          assertEquals(5,list.size());
          
       
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	   Position<String> first = list.addLast("one");
           Position<String> second = list.addLast("two");
           Position<String> fifth = list.addLast("fifth");
           assertEquals("fifth", fifth.getElement());
           assertEquals(3, list.size());
           Position<String> fourth = list.addAfter(second, "fourth");
           assertEquals("fourth", list.after(second).getElement());
           assertEquals(4, list.size());
           Position<String> third = list.addAfter(second, "third");
           assertEquals("third", list.after(second).getElement());
           assertEquals(5,list.size());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including any expected exceptions
     */     
    @Test
    public void testSet() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("third");
        String answer = list.set(third, "three");
        assertEquals("third", answer);
    }
    
    /**
     * Test the output of the remove(position) behavior, including any expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addLast("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("third");
        String answer = list.remove(second);
        assertEquals("two", answer);
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including any expected exceptions
     */     
    @Test
    public void testIterator() {
        Iterator<String> it = list.iterator(); 
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
    
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        Iterator<String>it2 = list.iterator();
        list.addLast("two");
        list.addLast("three");
        it2.next();
        it2.remove();

    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including any expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
        
        //TODO: complete this test case
    }

}
