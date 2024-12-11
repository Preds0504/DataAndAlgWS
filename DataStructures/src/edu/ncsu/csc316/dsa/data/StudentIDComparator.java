package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator to compare students based on id number
 * @author Dr. King
 *
 */
public class StudentIDComparator implements Comparator<Student> {

	/**
	 * Compares students based on id number in ascending order
	 * @param one the first student
	 * @param two the second student
	 */
	@Override
	public int compare(Student one, Student two) {
		if (one.getId() < two.getId()) {
			return -1;
		} else if (one.getId() > two.getId()) {
			return 1;
		}
		return 0;
	}

}