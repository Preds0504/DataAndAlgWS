package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

public class RadixSorterTest {

	private Student student1 = new Student("A","A",1,1, 1.2,"A");
	private Student student2 = new Student("A","B",2,1, 1.2,"A");
	private Student student3 = new Student("A","B",3,1, 1.2,"A");
	private Student student4 = new Student("B","B",4,1, 1.2,"A");
	private Student student5 = new Student("B","C",5,1, 1.2,"A");
	private Student[] studentAscending = {student1, student2, student3, student4, student5};
	private Student[] studentDescending = {student5, student4, student3, student2, student1};
	private Student[] studentRandom = {student2, student5, student3, student1, student4};
	
	
	
	
	
	private RadixSorter integerSorter;
	StudentIDComparator sample = new StudentIDComparator();
	@Before
	public void setUp() {
		integerSorter = new RadixSorter();
		
	}
	@Test
	public void testSortStudent() {
		integerSorter.sort(studentAscending);
		assertEquals(1, studentAscending[0].getId());
		assertEquals(2, studentAscending[1].getId());
		assertEquals(3, studentAscending[2].getId());
		assertEquals(4, studentAscending[3].getId()); 
		assertEquals(5, studentAscending[4].getId());

		integerSorter.sort(studentDescending);
		assertEquals(1, studentDescending[0].getId());
		assertEquals(2, studentDescending[1].getId());
		assertEquals(3, studentDescending[2].getId());
		assertEquals(4, studentDescending[3].getId());
		assertEquals(5, studentDescending[4].getId());

		integerSorter.sort(studentRandom);
		assertEquals(1, studentRandom[0].getId());
		assertEquals(2, studentRandom[1].getId());
		assertEquals(3, studentRandom[2].getId());
		assertEquals(4, studentRandom[3].getId());
		assertEquals(5, studentRandom[4].getId());
	}
}
