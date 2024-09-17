package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the RouteControllerUnitTests class.
 * This test class verifies the functionality of the RouteControllerUnitTests class
 */
@SpringBootTest
@ContextConfiguration
public class RouteControllerUnitTests {

  @BeforeEach
  public void setUp() {
    testController = new RouteController();
  }

  @Test
  public void indexTest() {
    String res = "Welcome, in order to make an API call direct your browser "
        + "or Postman to an endpoint " 
        + "\n\n This can be done using the following format: \n\n "
        + "http:127.0.0.1:8080/endpoint?arg=value";
    assertEquals(res, testController.index());
  }

  @Test
  public void retrieveDepartmentTest() {
    assertEquals(HttpStatus.OK, testController.retrieveDepartment("COMS").getStatusCode());
    assertEquals(HttpStatus.NOT_FOUND, testController.retrieveDepartment("ENGR").getStatusCode());
  }

  @Test
  public void retrieveCourseTest() {
    ResponseEntity<?> resOk = testController.retrieveCourse("COMS", 4156);
    assertEquals(HttpStatus.OK, resOk.getStatusCode());  

    ResponseEntity<?> resFail1 = testController.retrieveCourse("ENGR", 123);
    assertEquals(HttpStatus.NOT_FOUND, resFail1.getStatusCode());
    assertEquals("Department Not Found", resFail1.getBody());

    ResponseEntity<?> resFail2 = testController.retrieveCourse("COMS", 123);
    assertEquals(HttpStatus.NOT_FOUND, resFail2.getStatusCode());
    assertEquals("Course Not Found", resFail2.getBody());
  }

  @Test
  public void isCourseFullTest() {
    assertEquals(HttpStatus.OK, testController.isCourseFull("COMS", 1004).getStatusCode());
    assertEquals(HttpStatus.NOT_FOUND, testController.isCourseFull("COMS", 104).getStatusCode());
  }

  @Test
  public void getMajorCtFromDeptTest() {
    assertEquals(HttpStatus.OK, testController.getMajorCtFromDept("COMS").getStatusCode());
    assertEquals(HttpStatus.NOT_FOUND, testController.getMajorCtFromDept("ENGR").getStatusCode());
  }

  @Test
  public void identifyDeptChairTest() {
    ResponseEntity<?> resOk = testController.identifyDeptChair("COMS");
    assertEquals("Luca Carloni is the department chair.", resOk.getBody());
    assertEquals(HttpStatus.OK, resOk.getStatusCode());

    ResponseEntity<?> resFail = testController.identifyDeptChair("ENGR");
    assertEquals("Department Not Found", resFail.getBody());
    assertEquals(HttpStatus.NOT_FOUND, resFail.getStatusCode());
  }

  @Test
  public void setEnrollmentCountTest() {
    ResponseEntity<?> resOk = testController.setEnrollmentCount("COMS", 4156, 25);
    assertEquals("Attributed was updated successfully.", resOk.getBody());
    assertEquals(HttpStatus.OK, resOk.getStatusCode());

    ResponseEntity<?> resFail = testController.setEnrollmentCount("COMS", 1234, 25);
    assertEquals("Course Not Found", resFail.getBody());
    assertEquals(HttpStatus.NOT_FOUND, resFail.getStatusCode());

  }

  @Test
  public void changeCourseTimeTest() {
    ResponseEntity<?> resOk = testController.changeCourseTime("COMS", 4156, "00:00-01:30");
    assertEquals(HttpStatus.OK, resOk.getStatusCode());
    assertEquals("Attributed was updated successfully.", resOk.getBody());

    ResponseEntity<?> resFail = testController.changeCourseTime("COMS", 1234, "00:00-01:30");
    assertEquals(HttpStatus.NOT_FOUND, resFail.getStatusCode());
    assertEquals("Course Not Found", resFail.getBody());
  }


  @Test
  public void changeCourseTeacherTest() {
    ResponseEntity<?> resOk = testController.changeCourseTeacher("COMS", 4156, "Adam Cannon");
    assertEquals(HttpStatus.OK, resOk.getStatusCode());
    assertEquals("Attributed was updated successfully.", resOk.getBody());
  
    ResponseEntity<?> resFail = testController.changeCourseTeacher("COMS", 1234, "Adam Cannon");
    assertEquals(HttpStatus.NOT_FOUND, resFail.getStatusCode());
    assertEquals("Course Not Found", resFail.getBody());
  }

  @Test
  public void changeCourseLocationTest() {
    ResponseEntity<?> resOk = testController.changeCourseLocation("COMS", 4156, "309 HAV");
    assertEquals(HttpStatus.OK, resOk.getStatusCode());
    assertEquals("Attributed was updated successfully.", resOk.getBody());

    ResponseEntity<?> resFail = testController.changeCourseLocation("COMS", 1234, "309 HAV");
    assertEquals(HttpStatus.NOT_FOUND, resFail.getStatusCode());
    assertEquals("Course Not Found", resFail.getBody());
  }



  /** The test RouteController instance used for testing. */
  public static RouteController testController;
}