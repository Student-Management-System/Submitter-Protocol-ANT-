package net.ssehub.exercisesubmitter.protocol;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.swagger.client.model.AssessmentDto;
import io.swagger.client.model.AssignmentDto;
import io.swagger.client.model.CourseDto;
import io.swagger.client.model.GroupDto;

/**
 * This class declares unit tests for the {@link NetworkProtocol} class.
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
        NetworkProtocol np = new NetworkProtocol("NON_EXISTING_SERVER", "a_course");
        try {
            np.getCourses("userID");
            Assert.fail("Expected ServerNotFoundException, but did not occur.");
        } catch (ServerNotFoundException e) {
            Assert.assertEquals("NON_EXISTING_SERVER", e.getURL());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }
    }
    
    /**
     * Test if a course to a id is found.
     */
    @Test
    public void testGetCourseID() {
        // probably a bit dirty to hardcode the courseName.
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "java");
        try {
            String course = np.getCourseID();
            Assert.assertFalse("No course found", course.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }
    }
    
    /**
     * Test if a List of courses is returned.
     */
    @Test
    public void testListOfCourses() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_course");
        try {
            List<CourseDto> courses = np.getCourses("userID");
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
    public void testGetGroup() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_course");
        try {
            List<GroupDto> groups = np.getGroups("id");
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
    public void testGetfAssignments() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_course");
        try {
            List<AssignmentDto> assignments = np.getAssignments();
            Assert.assertNotNull("Assignment list was null, but should never be null.", assignments);
            Assert.assertFalse("List of assignments was empty", assignments.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned.");
        }  
    }
    
    /**
     * Test if a List of assessments is returned.
     */
    @Test
    public void testGetAssessmentsWithGoups() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_course");
        try {
            List<AssessmentDto> assessments = np.getAssessmentsWithGroups("");
            Assert.assertNotNull("Assessment list was null, but should never be null.", assessments);
            Assert.assertFalse("List of assessments was empty", assessments.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected Exception returned");
        }
    }

}
