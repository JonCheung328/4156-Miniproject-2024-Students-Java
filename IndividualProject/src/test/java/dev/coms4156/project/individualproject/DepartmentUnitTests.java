package dev.coms4156.project.individualproject;

// Static imports should come after regular imports
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the Department class.
 * This test class verifies the functionality of the Department class
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  public static Department department;
  public static Map<String, Course> courses;

  /**
   * Sets up the department and course data for testing before each test case.
   * This method initializes a map of courses with predefined values, 
   * including course details such as instructor name, location, time, 
   * and enrollment capacity. 
   */
  @BeforeEach
  public void setupDepartmentForTesting() {
    courses = new HashMap<>();
    courses.put("1004", new Course("Adam Cannon", "417 IAB", "11:40-12:55", 400));
    courses.put("3134", new Course("Brian Borowski", "301 URIS", "4:10-5:25", 250));

    testDepartment = new Department("COMS", courses, "Adam Cannon", 10);
    emptyDepartment = new Department("COMS", courses, "Adam Cannon", 0);
  }
    

  @Test
  public void getNumberOfMajorsTest() {
    assertEquals(10, testDepartment.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    assertEquals("Adam Cannon", testDepartment.getDepartmentChair());
  }

  @Test
  public void getCourseSelectionTest() {
    assertEquals("Adam Cannon", testDepartment.getDepartmentChair()); // Need to modify
  }

  @Test
  public void addPersonToMajorTest() {
    int ogMajor = testDepartment.getNumberOfMajors();
    testDepartment.addPersonToMajor();
    assertEquals(ogMajor + 1, testDepartment.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorTest() {
    int ogMajor = testDepartment.getNumberOfMajors();
    testDepartment.dropPersonFromMajor();
    assertEquals(ogMajor - 1, testDepartment.getNumberOfMajors());
    emptyDepartment.dropPersonFromMajor(); // Should not decrement below zero
    assertEquals(0, emptyDepartment.getNumberOfMajors());
  }

  @Test
  public void addCourseTest() {
    Course newCourse = new Course("Daniel Rubenstein", "207 Math", "10:10-11:25", 300);
    testDepartment.addCourse("3827", newCourse);

    Map<String, Course> newCourses = testDepartment.getCourseSelection();
    assertTrue(newCourses.containsKey("3827"));
    assertEquals(3, newCourses.size()); 
  
  }

  @Test
  public void createCourseTest() {
    testDepartment.createCourse("3827", "Daniel Rubenstein",
        "207 Math", "10:10-11:25", 300);

    Map<String, Course> newCourses = testDepartment.getCourseSelection();
    assertTrue(newCourses.containsKey("3827"));
    assertEquals(3, newCourses.size()); 
  }


  /** The test course instance used for testing. */
  public static Department testDepartment;
  public static Department emptyDepartment;
}
