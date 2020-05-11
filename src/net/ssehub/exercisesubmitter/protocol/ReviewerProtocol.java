package net.ssehub.exercisesubmitter.protocol;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiException;
import io.swagger.client.api.AssessmentsApi;
import io.swagger.client.model.AssessmentDto;
import net.ssehub.exercisesubmitter.protocol.DataNotFoundException.DataType;

/**
 * Network protocol designed for the &quot;Exercise Reviewer&quot;.
 * @author El-Sharkawy
 * @author Kunold
 *
 */
public class ReviewerProtocol extends NetworkProtocol {
    
    /**
     * The API to get the assessment informations.
     */
    private AssessmentsApi apiAssessments;
    
    /**
     * List of assessments.
     */
    private List<AssessmentDto> assessments = new ArrayList<>();
    
    public ReviewerProtocol(String basePath, String courseName) {
        super(basePath, courseName);
        apiAssessments = new AssessmentsApi(apiClient);
    }
    
    /**
     * Getter for the Assessments of an Assignment.
     * @param assignmentId the id of the Specified assignment.
     * @return List of Assessments.
     * @throws NetworkException when network problems occur.
     */
    public List<AssessmentDto> getAssessments(String assignmentId) throws NetworkException {
        try {
            assessments = apiAssessments.getAllAssessmentsForAssignment(super.getCourseID(), assignmentId);
        } catch (IllegalArgumentException e) {
            throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            throw new DataNotFoundException("Assessments not found", courseName, DataType.ASSESSMENTS_NOT_FOUND);
        }
        
        return assessments;
    }
    
}
