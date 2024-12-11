package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class ArrayBasedListTest {

    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
        list.add(0, "one");
        list.addLast("two");
        assertEquals("one",list.get(0));
        assertEquals("two", list.get(1));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
        list.addLast("one");
        list.addLast("two");
        assertEquals("two", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
        list.add(0, "one");
        list.addFirst("two");
        assertEquals("two", list.get(0));
        assertEquals("one",list.get(1));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	list.addLast("one");
        list.addLast("two");
        assertEquals("one", list.first());    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
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
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
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
        list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        list.addLast("four");
        Iterator<String> it2 = list.iterator(); 
        it2.next();
        it2.remove();
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
        list.add(0, "one");
        list.addLast("two");
        list.addLast("three");
        assertEquals(3, list.size());
        list.remove(0);
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        assertEquals(2, list.size());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	  list.add(0, "one");
          list.addLast("two");
          list.addLast("three");
          assertEquals(3, list.size());
          list.removeFirst();
          assertEquals("two", list.get(0));
          assertEquals("three", list.get(1));
          assertEquals(2, list.size());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	 list.add(0, "one");
         list.addLast("two");
         list.addLast("three");
         assertEquals(3, list.size());
         list.removeLast();
         assertEquals("one", list.get(0));
         assertEquals("two", list.get(1));
         assertEquals(2, list.size());
         //Insures that the last one was deleted
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
         list.add(0, "one");
         list.set(0, "two");
         assertEquals("two", list.get(0));
         try{
             list.set(1,  "fifteen");
             fail("An IndexOutOfBoundsException should have been thrown");
         } catch (Exception e) {
             assertTrue(e instanceof IndexOutOfBoundsException);
         }
    }
    
}
