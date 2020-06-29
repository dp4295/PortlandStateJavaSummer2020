package edu.pdx.cs410J.deep;

import edu.pdx.cs410J.lang.Human;

import java.util.ArrayList;
                                                                                    
/**                                                                                 
 * This class is represents a <code>Student</code>.                                 
 */                                                                                 
public class Student extends Human {                                                

  private double gpa;
  /**                                                                               
   * Creates a new <code>Student</code>                                             
   *  @param createStudentNamed
   *        The student's name
   * @param classes
   *        The names of the classes the student is taking.  A student
   *        may take zero or more classes.
   * @param gpa
 *        The student's grade point average
   * @param gender
   */

    public Student(String createStudentNamed, ArrayList<String> classes, double gpa, String gender) {
     super(createStudentNamed);
     this.gpa = gpa;
  }

  /**
   *
   * All students say "This class is too much work"
   */
  @Override
  public String says() {                                                            
    throw new UnsupportedOperationException("Not implemented yet");
  }
                                                                                    
  /**                                                                               
   * Returns a <code>String</code> that describes this                              
   * <code>Student</code>.                                                          
   */                                                                               
  public String toString() {
   // throw new UnsupportedOperationException("Not implemented yet");
    return this.getName() + " has a GPA of " + this.gpa;

  }

  /**
   * Main program that parses the command line, creates a
   * <code>Student</code>, and prints a description of the student to
   * standard out by invoking its <code>toString</code> method.
   */
  public static void main(String[] args) {
    System.err.println("Missing command line arguments");
    System.exit(1);
  }
}