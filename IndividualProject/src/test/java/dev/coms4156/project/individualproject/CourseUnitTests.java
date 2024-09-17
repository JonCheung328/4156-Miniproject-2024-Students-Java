package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the Course class.
 * This test class verifies the functionality of the Course class
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Josh Alman", "417 IAB", "2:40-3:55", 150);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Josh Alman; Location: 417 IAB; Time: 2:40-3:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentTest() {
    testCourse.setEnrolledStudentCount(100); 
    assertTrue(testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(300); // When Exceed capacity
    assertFalse(testCourse.enrollStudent());
  }

  @Test
  public void dropStudentTest() {
    testCourse.setEnrolledStudentCount(100); 
    assertTrue(testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(0); // When No Student
    assertFalse(testCourse.dropStudent());
  }

  @Test
  public void getCourseLocationTest() {
    assertEquals("417 IAB", testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    assertEquals("Josh Alman", testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    assertEquals("2:40-3:55", testCourse.getCourseTimeSlot());
  }

  @Test
  public void reassignInstructorTest() {
    testCourse.reassignInstructor("Jae Lee");
    assertEquals("Jae Lee", testCourse.getInstructorName());
  }

  @Test
  public void reassignLocationTest() {
    testCourse.reassignLocation("309 HAV");
    assertEquals("309 HAV", testCourse.getCourseLocation());
  }

  @Test
  public void reassignTimeTest() {
    testCourse.reassignTime("4:10-5:25");
    assertEquals("4:10-5:25", testCourse.getCourseTimeSlot());
  }

  @Test
  public void setEnrolledStudentCountTest() {
    testCourse.setEnrolledStudentCount(100);
    assertFalse(testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(300);
    assertTrue(testCourse.isCourseFull());
  }

  @Test
  public void isCourseFullTest() {
    testCourse.setEnrolledStudentCount(300); 
    assertTrue(testCourse.isCourseFull());
  }


  /** The test course instance used for testing. */
  public Course testCourse;
}

