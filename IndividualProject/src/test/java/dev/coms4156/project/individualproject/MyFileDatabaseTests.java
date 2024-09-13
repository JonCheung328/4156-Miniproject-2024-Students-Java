package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Unit tests for the MyFileDatabase class.
 * This test class verifies the functionality of the MyFileDatabase class
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseTests {

  public MyFileDatabase testDatabase;
  public MyFileDatabase saveContentDatabase;
  public Map<String, Department> testDeptMapping;
  public Map<String, Course> testCourses;
  public Department testDept;

  /**
   * Sets up the test environment before each test case. 
   * This method initializes two instances of MyFileDatabase for testing,
   * loading the department mapping and course selection for the 
   * "COMS" department. 
   * 
   */
  @BeforeEach
  public void setUp() {
    testDatabase = new MyFileDatabase(0, "./data.txt");
    saveContentDatabase = new MyFileDatabase(1, "./data.txt");

    testDeptMapping = testDatabase.getDepartmentMapping();
    testDept = testDatabase.getDepartmentMapping().get("COMS"); 
    testCourses = testDept.getCourseSelection();

    //HashMap<String, Department> departments = testDatabase.getDepartmentMapping();
  }

  @Test
  public void setAndGetMappingTest() {
    Map<String, Department> newMapping = new HashMap<>(); // Department map (can be anything)
    testDatabase.setMapping(newMapping);
    assertEquals(newMapping, testDatabase.getDepartmentMapping());
  }

  @Test
  public void deSerializeObjectFromFileTest() {
    Map<String, Department> newMapping = saveContentDatabase.deSerializeObjectFromFile();
    assertEquals(testDeptMapping.size(), newMapping.size());
  }

  @Test
  public void saveContentsToFileTest() {
    Map<String, Department> newMapping = new HashMap<>();
    newMapping.put("COMS", testDept); // Add the department to the map

    testDatabase.setMapping(newMapping);
    testDatabase.saveContentsToFile();

    MyFileDatabase loadedDatabase = new MyFileDatabase(0, "./data.txt");
    assertEquals(1, loadedDatabase.getDepartmentMapping().size());
  }

  @Test
  public void toStringTest() {
    StringBuilder expectedString = new StringBuilder();

    // Iterate through the department mapping to build the expected string
    for (HashMap.Entry<String, Department> entry : testDatabase.getDepartmentMapping().entrySet()) {
      String key = entry.getKey();
      Department value = entry.getValue();
      expectedString.append("For the ").append(key).append(" department: \n")
                    .append(value.toString());
    }

    // Assert that the actual output matches the expected output
    assertEquals(expectedString.toString(), testDatabase.toString());
  }
}
