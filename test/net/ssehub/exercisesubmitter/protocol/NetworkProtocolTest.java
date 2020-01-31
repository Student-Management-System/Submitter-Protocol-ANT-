package net.ssehub.exercisesubmitter.protocol;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.swagger.client.model.CourseDto;

/**
 * This class declares unittests for the {@link NetworkProtocol} class.
 * 
 * @author Kunold
 *
 */
public class NetworkProtocolTest {
    /**
     * The url of the test server.
     */
    public static final String TEST_SERVER = "http://147.172.178.30:3000";

    /**
     * Test if the REST server is not found.
     */
    @Test
    public void testServerNotFound() {
        NetworkProtocol np = new NetworkProtocol("NON_EXISTING_SERVER", "a_user");
        try {
            np.getCourses();
            Assert.fail("Expected ServerNotFoundException, but did not occur.");
        } catch (ServerNotFoundException e) {
            Assert.assertEquals("NON_EXISTING_SERVER", e.getURL());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }
    }
    
    /**
     * Test if a List of courses is returned.
     */
    @Test
    public void testListOfCourses() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_user");
        try {
            List<CourseDto> courses = np.getCourses();
            Assert.assertNotNull("Course list was null, but should never be null.", courses);
            Assert.assertFalse("List of courses was empty", courses.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }
    }
    
    /**
     * Test if a List of groups is returned.
     */
    @Test
    public void testListOfGroups() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_user");
        try {
            List<CourseDto> groups = np.getGroup();
            Assert.assertNotNull("Group list was null, but should never be null.", groups);
            Assert.assertFalse("List of groups was empty", groups.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }  
    }
    
    /**
     * Test if a List of assignments is returned.
     */
    @Test
    public void testListOfAssignments() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_user");
        try {
            List<CourseDto> assignments = np.getAssignments();
            Assert.assertNotNull("Assignment list was null, but should never be null.", assignments);
            Assert.assertFalse("List of assignments was empty", assignments.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }  
    }

}
