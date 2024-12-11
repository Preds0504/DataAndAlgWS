package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityID.
 * 
 * @author Dr. King
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** first name of the student*/
	private String first;
	/** last name of the student*/
	private String last;
	/**the id of the student*/
	private int id;
	/**The credit Hours the student has*/
	private int creditHours;
	/**the student gpa*/
	private double gpa;
	/**the Unity Id of the student*/
	private String unityID;
	/**
	 * Constructor for the student
	 * @param first the first name
	 * @param last the last name
	 * @param id the id for a student 
	 * @param creditHours the credit hours for the student
	 * @param gpa the gpa for the student
	 * @param unityID the UnityID
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityID) {
		setFirst(first);
		setLast(last);
		setId(id);
		setCreditHours(creditHours);
		setGpa(gpa);
		setUnityID(unityID);
		
	}

	/**
	 * The getter for first
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Setter for the first name
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * getter for the last night
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * setter for the last name
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * getter for the id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter for the id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter for the credithours
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}

	/**
	 * setter for the credit hours
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * the getter for the gpa
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}

	/**
	 * the setter for the gpa
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	/**
	 * the getter for the unityid
	 * @return the unityID
	 */
	public String getUnityID() {
		return unityID;
	}

	/**
	 * setter for the unityID
	 * @param unityID the unityID to set
	 */
	public void setUnityID(String unityID) {
		this.unityID = unityID;
	}
	/**
	 * check if equal or not for students
	 * @param obj the object to be compared
	 * @return if the students are equal or not
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			return this.first.equals(((Student) obj).getFirst()) && this.last.equals(((Student) obj).getLast()) && this.id == ((Student) obj).getId();
		}
		return false;
	}
	/**
	 * Compares one student to another to find orderings
	 * @param other the other student to compare
	 * @return if one is greater/less than another
	 */
	public int compareTo(Student other) {
		if (this.equals(other)) {
			return 0;
		}
		if (this.last.compareTo(other.getLast()) >= 1) {
			return 1;
		} else if (this.last.compareTo(other.getLast()) <= -1) {
			return -1;
		}
		if (this.first.compareTo(other.getFirst()) >= 1) {
			return 1;
		} else if (this.first.compareTo(other.getFirst()) <= -1) {
			return -1;
		}
		if (this.id > other.id) {
			return 1;
		} else if (this.id < other.id) {
			return -1;
		}
		return 0;
	}
	/**
	 * Generates the hashCode for the student object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityID == null) ? 0 : unityID.hashCode());
		return result;
	}
}
