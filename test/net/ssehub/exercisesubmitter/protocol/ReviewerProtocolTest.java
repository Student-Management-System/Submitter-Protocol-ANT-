package net.ssehub.exercisesubmitter.protocol;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import io.swagger.client.model.AssessmentDto;

/**
 * This class declares unit tests for the {@link ReviewerProtocol} class.
 * 
 * @author Kunold
 *
 */
public class ReviewerProtocolTest { 
    /**
     * The test data.
     */
    public static final String TEST_SERVER = "http://147.172.178.30:3000";
    public static final String TEST_COURSE_ID = "java";
    public static final String TEST_ASSIGNMENT_ID = "993b3cd0-6207-11ea-bc55-0242ac130003";
    public static final String TEST_ASSESSMENT_ID = "680dd44a-93b0-4d1c-a947-9b50a4bbb68e";
    public static final String TEST_SEMESTER = "wise1920";
    
    /**
     * Tests if a List of Assessments for an Assignment is returned.
     */
    @Test
    public void testGetAssessments() {
        ReviewerProtocol rp = new ReviewerProtocol(TEST_SERVER, TEST_COURSE_ID);
        rp.setSemester(TEST_SEMESTER);
        try {
            List <AssessmentDto> assessments = rp.getAssessments(TEST_ASSIGNMENT_ID);
            Assert.assertNotNull("Assessment List was null, but never should be null", assessments);
            Assert.assertFalse("List of Assessments was empty", assessments.isEmpty());
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
    /**
     * Tests if a single Assessment for an Assignment is returned.
     */
    @Test
    public void testGetAssessmentForAssignment() {
        ReviewerProtocol rp = new ReviewerProtocol(TEST_SERVER, TEST_COURSE_ID);
        rp.setSemester(TEST_SEMESTER);
        try {
            AssessmentDto assessment = rp.getAssessmentForAssignment(TEST_ASSIGNMENT_ID, TEST_ASSESSMENT_ID);
            Assert.assertNotNull("No Assessment returned", assessment);
        } catch (NetworkException e) {
            Assert.fail("Unexpected NetworkException returned: " + e.getMessage());
        }
    }
    
}
