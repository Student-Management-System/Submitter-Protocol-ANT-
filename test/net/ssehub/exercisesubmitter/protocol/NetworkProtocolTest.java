package net.ssehub.exercisesubmitter.protocol;


import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import io.swagger.client.model.AssessmentDto;
import io.swagger.client.model.AssignmentDto;
import io.swagger.client.model.AssignmentDto.StateEnum;
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
     * The test data.
     */
    public static final String TEST_SERVER = "http://147.172.178.30:3000";
    public static final String TEST_COURSE_ID = "java";
    public static final String TEST_USER_ID = "a019ea22-5194-4b83-8d31-0de0dc9bca53";
    public static final String TEST_SEMESTER = "wise1920";

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
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
    /**
     * Test if a course to a id is found.
     */
    @Test
    public void testGetCourseID() {
        // probably a bit dirty to hardcode the courseName.
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, TEST_COURSE_ID);
        np.setSemester(TEST_SEMESTER);
        try {
            String course = np.getCourseID();
            Assert.assertFalse("No course found", course.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
    /**
     * Test if a List of courses is returned.
     */
    @Test
    public void testListOfCourses() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, TEST_COURSE_ID);
        np.setSemester(TEST_SEMESTER);
        try {
            List<CourseDto> courses = np.getCourses(TEST_USER_ID);
            Assert.assertNotNull("Course list was null, but should never be null.", courses);
            Assert.assertFalse("List of courses was empty", courses.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
    /**
     * Test if a List of groups is returned.
     */
    @Test
    public void testGetGroup() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, TEST_COURSE_ID);
        np.setSemester(TEST_SEMESTER);
        try {
            List<GroupDto> groups = np.getGroups(TEST_USER_ID);
            Assert.assertNotNull("Group list was null, but should never be null.", groups);
            Assert.assertFalse("List of groups was empty", groups.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
    /**
     * Test if a List of assignments is returned.
     */
    @Test
    public void testGetAssignments() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, TEST_COURSE_ID);
        np.setSemester(TEST_SEMESTER);
        try {
            List<AssignmentDto> assignments = np.getAssignments();
            Assert.assertNotNull("Assignment list was null, but should never be null.", assignments);
            Assert.assertFalse("List of assignments was empty", assignments.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }  
    }
    
    /**
     * Test if a List of assessments is returned.
     */
    //TODO TK: deactivated till API query is fixed
    @Ignore
    @Test
    public void testGetAssessmentsWithGoups() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, TEST_COURSE_ID);
        np.setSemester(TEST_SEMESTER);
        try {
            List<AssessmentDto> assessments = np.getAssessmentsWithGroups(TEST_USER_ID);
            Assert.assertNotNull("Assessment list was null, but should never be null.", assessments);
            Assert.assertFalse("List of assessments was empty", assessments.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
    /**
     * Test if a Map of assignments of all specified submissions and their permissions is returned.
     */
    @Test
    public void testReadPermissions() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, TEST_COURSE_ID);
        np.setSemester(TEST_SEMESTER);
        Map <String, StateEnum> assignments = np.readPermissions();
        Assert.assertNotNull("Assignment map was null, but should never be null.", assignments);
        Assert.assertFalse("Map of assignments was empty", assignments.isEmpty());
    }

}
