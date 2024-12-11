package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.FirstElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.LastElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.MiddleElementSelector;
import edu.ncsu.csc316.dsa.sorter.QuickSorter.RandomElementSelector;
/**
 * QuickSort test file
 * @author Tyler Hardison
 */
public class QuickSorterTest {
	/**student 1 object*/
	private Student student1 = new Student("A", "A", 1, 1, 1.2, "A");
	/**student 2 object*/
	private Student student2 = new Student("A", "B", 2, 1, 1.2, "A");
	/**student 3 object*/
	private Student student3 = new Student("A", "B", 3, 1, 1.2, "A");
	/**student 4 object*/
	private Student student4 = new Student("B", "B", 4, 1, 1.2, "A");
	/**student 5 object*/
	private Student student5 = new Student("B", "C", 5, 1, 1.2, "A");
	/**Students in acsending order to be sorted */
	private Student[] studentAscending = {student1, student2, student3, student4, student5};
	/**Students in descending order to be sorted */
	private Student[] studentDescending = {student5, student4, student3, student2, student1};
	/**Students in random order to be sorted */
	private Student[] studentRandom = {student2, student5, student3, student1, student4};
	/**Student sorter with ID and random pivot selection */
	private QuickSorter studentIDrandomSorter;
	/**Student sorter with ID and the first pivot selection*/
	private QuickSorter studentIDfirstSorter;
	/**Student sorter with ID and the last pivot selection*/
	private QuickSorter studentIDlastSorter;
	/**Student sorter with ID and the middle pivot selection*/
	private QuickSorter studentIDmiddleSorter;
	/**The comparator for the sorter*/
	StudentIDComparator sample = new StudentIDComparator();
	/**The random selector for the sorter*/
	RandomElementSelector randomPivot = new RandomElementSelector();
	/**The first selector for the sorter */
	FirstElementSelector firstPivot = new FirstElementSelector();
	/**The last selector for the sorter */
	LastElementSelector lastPivot = new LastElementSelector();
	/**The middle selector for the sorter */
	MiddleElementSelector middlePivot = new MiddleElementSelector();
	/**
	 * Sets up the students to be sorted
	 */
	@Before
	public void setUp() {
		
		studentIDrandomSorter = new QuickSorter(sample, randomPivot);
		studentIDfirstSorter = new QuickSorter(sample, firstPivot);
		studentIDlastSorter = new QuickSorter(sample, lastPivot);
		studentIDmiddleSorter = new QuickSorter(sample, middlePivot);
		
	}
	/**
	 * Test for the random pivot sorting algorithm
	 */
	@Test
	public void testSortRandom() {
		studentIDrandomSorter.sort(studentAscending);
		assertEquals(1, studentAscending[0].getId());
		assertEquals(2, studentAscending[1].getId());
		assertEquals(3, studentAscending[2].getId());
		assertEquals(4, studentAscending[3].getId()); 
		assertEquals(5, studentAscending[4].getId());

		studentIDrandomSorter.sort(studentDescending);
		assertEquals(1, studentDescending[0].getId());
		assertEquals(2, studentDescending[1].getId());
		assertEquals(3, studentDescending[2].getId());
		assertEquals(4, studentDescending[3].getId());
		assertEquals(5, studentDescending[4].getId());

		studentIDrandomSorter.sort(studentRandom);
		assertEquals(1, studentRandom[0].getId());
		assertEquals(2, studentRandom[1].getId());
		assertEquals(3, studentRandom[2].getId());
		assertEquals(4, studentRandom[3].getId());
		assertEquals(5, studentRandom[4].getId());
	}
	/**
	 * Test for the first pivot sorting algorithm
	 */
	@Test
	public void testSortfirst() {
		Student[] studentAscending2 = {student1, student2, student3, student4, student5};
		Student[] studentDescending2 = {student5, student4, student3, student2, student1};
		Student[] studentRandom2 = {student2, student5, student3, student1, student4};
		studentIDfirstSorter.sort(studentAscending2);
		assertEquals(1, studentAscending2[0].getId());
		assertEquals(2, studentAscending2[1].getId());
		assertEquals(3, studentAscending2[2].getId());
		assertEquals(4, studentAscending2[3].getId()); 
		assertEquals(5, studentAscending2[4].getId());

		studentIDfirstSorter.sort(studentDescending2);
		assertEquals(1, studentDescending2[0].getId());
		assertEquals(2, studentDescending2[1].getId());
		assertEquals(3, studentDescending2[2].getId());
		assertEquals(4, studentDescending2[3].getId());
		assertEquals(5, studentDescending2[4].getId());

		studentIDfirstSorter.sort(studentRandom2);
		assertEquals(1, studentRandom2[0].getId());
		assertEquals(2, studentRandom2[1].getId());
		assertEquals(3, studentRandom2[2].getId());
		assertEquals(4, studentRandom2[3].getId());
		assertEquals(5, studentRandom2[4].getId());
	}
	/**
	 * Test for the last pivot sorting algorithm
	 */
	@Test
	public void testSortlast() {
		Student[] studentAscending2 = {student1, student2, student3, student4, student5};
		Student[] studentDescending2 = {student5, student4, student3, student2, student1};
		Student[] studentRandom2 = {student2, student5, student3, student1, student4};
		studentIDlastSorter.sort(studentAscending2);
		assertEquals(1, studentAscending2[0].getId());
		assertEquals(2, studentAscending2[1].getId());
		assertEquals(3, studentAscending2[2].getId());
		assertEquals(4, studentAscending2[3].getId()); 
		assertEquals(5, studentAscending2[4].getId());

		studentIDlastSorter.sort(studentDescending2);
		assertEquals(1, studentDescending2[0].getId());
		assertEquals(2, studentDescending2[1].getId());
		assertEquals(3, studentDescending2[2].getId());
		assertEquals(4, studentDescending2[3].getId());
		assertEquals(5, studentDescending2[4].getId());

		studentIDlastSorter.sort(studentRandom2);
		assertEquals(1, studentRandom2[0].getId());
		assertEquals(2, studentRandom2[1].getId());
		assertEquals(3, studentRandom2[2].getId());
		assertEquals(4, studentRandom2[3].getId());
		assertEquals(5, studentRandom2[4].getId());
	}
	/**
	 * Test for the middle pivot sorting algorithm
	 */
	@Test
	public void testSortmiddle() {
		Student[] studentAscending2 = {student1, student2, student3, student4, student5};
		Student[] studentDescending2 = {student5, student4, student3, student2, student1};
		Student[] studentRandom2 = {student2, student5, student3, student1, student4};
		studentIDmiddleSorter.sort(studentAscending2);
		assertEquals(1, studentAscending2[0].getId());
		assertEquals(2, studentAscending2[1].getId());
		assertEquals(3, studentAscending2[2].getId());
		assertEquals(4, studentAscending2[3].getId()); 
		assertEquals(5, studentAscending2[4].getId());

		studentIDmiddleSorter.sort(studentDescending2);
		assertEquals(1, studentDescending2[0].getId());
		assertEquals(2, studentDescending2[1].getId());
		assertEquals(3, studentDescending2[2].getId());
		assertEquals(4, studentDescending2[3].getId());
		assertEquals(5, studentDescending2[4].getId());

		studentIDmiddleSorter.sort(studentRandom2);
		assertEquals(1, studentRandom2[0].getId());
		assertEquals(2, studentRandom2[1].getId());
		assertEquals(3, studentRandom2[2].getId());
		assertEquals(4, studentRandom2[3].getId());
		assertEquals(5, studentRandom2[4].getId());
	}
	/**
	 * Test for the other constructors
	 */
	@Test
	public void testConstructors() {
		//The names of the sorters are reused and therefore not descriptive
		//constructor with only the comparator
		studentIDrandomSorter = new QuickSorter(sample);
		//constructor with only the pivot
		studentIDfirstSorter = new QuickSorter(randomPivot);
		//constructor with neither arguments
		studentIDlastSorter = new QuickSorter();
		Student[] studentAscending2 = {student1, student2, student3, student4, student5};
		Student[] studentDescending2 = {student5, student4, student3, student2, student1};
		Student[] studentRandom2 = {student2, student5, student3, student1, student4};
		studentIDrandomSorter.sort(studentAscending2);
		assertEquals(1, studentAscending2[0].getId());
		assertEquals(2, studentAscending2[1].getId());
		assertEquals(3, studentAscending2[2].getId());
		assertEquals(4, studentAscending2[3].getId()); 
		assertEquals(5, studentAscending2[4].getId());

		studentIDfirstSorter.sort(studentDescending2);
		assertEquals(1, studentDescending2[0].getId());
		assertEquals(2, studentDescending2[1].getId());
		assertEquals(3, studentDescending2[2].getId());
		assertEquals(4, studentDescending2[3].getId());
		assertEquals(5, studentDescending2[4].getId());

		studentIDlastSorter.sort(studentRandom2);
		assertEquals(1, studentRandom2[0].getId());
		assertEquals(2, studentRandom2[1].getId());
		assertEquals(3, studentRandom2[2].getId());
		assertEquals(4, studentRandom2[3].getId());
		assertEquals(5, studentRandom2[4].getId());
		
		


	}
	/**
	 * Test for the default constructor
	 */
	@Test
	public void testDefaultConstructor() {
		Student[] studentAscending2 = {student1, student2, student3, student4, student5};
		Student[] studentDescending2 = {student5, student4, student3, student2, student1};
		Student[] studentRandom2 = {student2, student5, student3, student1, student4};
		studentIDlastSorter = new QuickSorter();
		studentIDlastSorter.sort(studentAscending2);
		assertEquals(1, studentAscending2[0].getId());
		assertEquals(2, studentAscending2[1].getId());
		assertEquals(3, studentAscending2[2].getId());
		assertEquals(4, studentAscending2[3].getId()); 
		assertEquals(5, studentAscending2[4].getId());

		studentIDlastSorter.sort(studentDescending2);
		assertEquals(1, studentDescending2[0].getId());
		assertEquals(2, studentDescending2[1].getId());
		assertEquals(3, studentDescending2[2].getId());
		assertEquals(4, studentDescending2[3].getId());
		assertEquals(5, studentDescending2[4].getId());

		studentIDlastSorter.sort(studentRandom2);
		assertEquals(1, studentRandom2[0].getId());
		assertEquals(2, studentRandom2[1].getId());
		assertEquals(3, studentRandom2[2].getId());
		assertEquals(4, studentRandom2[3].getId());
		assertEquals(5, studentRandom2[4].getId());
	}

}
