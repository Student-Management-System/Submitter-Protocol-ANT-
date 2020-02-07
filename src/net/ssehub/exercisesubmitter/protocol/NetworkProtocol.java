package net.ssehub.exercisesubmitter.protocol;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.CoursesApi;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.AssignmentDto;
import io.swagger.client.model.CourseDto;
import io.swagger.client.model.GroupDto;
import net.ssehub.exercisesubmitter.protocol.DataNotFoundException.DataType;

/**
 * Manages the network protocol communication with the API for the exercise submitter.
 * 
 * @author Kunold
 *
 */
public class NetworkProtocol {
	
    /**
     * The ApiClient enables to set a BasePath for the other API`s.
     */
    private ApiClient apiClient;
    
	/**
	 * The API of the user informations.
	 */
	private UsersApi apiUser;
	
	/**
	 * The API to get the course informations.
	 */
	private CoursesApi apiCourse;
	
	/**
	 * The name of the course that uses the exercise submitter.
	 * Will be read from the config File.
	 */
	private String courseName;
	
	/**
	 * The base Path to the REST server.
	 */
	private String basePath;
	
	/**
	 * The ID of a course.
	 */
	private String courseID;
	
	/**
	 * A list of the courses.
	 */
	private List<CourseDto> courses = new ArrayList<>();
	
	/**
	 * A group.
	 */
	private GroupDto groups;
	
	/**
	 * A list of the assignments.
	 */
	private List<AssignmentDto> assignments = new ArrayList<>();
	
	/**
	 * The constructor of the class.
	 * @param basePath The REST URL of the student management server.
	 * @param courseName The course that is associated with the exercise submitter.
	 */
	public NetworkProtocol(String basePath, String courseName) {
	    apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiUser = new UsersApi(apiClient);
        apiCourse = new CoursesApi(apiClient);
		this.courseName = courseName;
		this.basePath = basePath;
	}
	
	/**
	 * Getter for the ID of the course.
	 * @return the id of the course.
	 * @throws NetworkException when network problems occur.
	 */
	public String getCourseID() throws NetworkException{
	    CourseDto c;
	    try {
            c = apiCourse.getCourseByNameAndSemester(courseName, SemesterUtils.getSemester());
            courseID = c.getId();
        } catch (IllegalArgumentException e) {
            throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            throw new DataNotFoundException("Course not found", courseName, DataType.COURSE_NOT_FOUND);
        }
	    	    
	    return courseID;
	}
	
	/**
	 * Getter for the Courses of a user.
	 * @return A List of all courses from the user (will never be <tt>null</tt>).
	 * @throws NetworkException when network problems occur.
	 */
	public List<CourseDto> getCourses(String userID) throws NetworkException {
		try {
		    // "c290382b-8df7-409d-b6d8-719ed1a5c109"
		    // test user no longer exists
            courses = apiUser.getCoursesOfUser(userID);
		} catch (IllegalArgumentException e) {
		    throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            throw new DataNotFoundException("User not found", userID, DataType.USER_NOT_FOUND);
        }
		
		if (null == courses) {
		    courses = new ArrayList<>();
		}
		
        return courses;
	}
	
	/**
	 * Getter for the groups of a user.
	 * @param userID The id of the user whose groups are requested.
	 * @return the group of a user.
	 * @throws NetworkException when network problems occur.
	 */
	public GroupDto getGroup(String userID) throws NetworkException {
	    try {
            groups = apiUser.getGroupOfUserForCourse(userID, getCourseID());
        } catch (IllegalArgumentException e) {
            throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            throw new DataNotFoundException("Group not found", userID, DataType.GROUP_NOT_FOUND);
        }
	    
	    return groups;
	}
	
	/**
	 * Getter for all assignments of a course.
	 * @param courseID The id of the course where the assignments are requested for.
	 * @return the assignments of a course.
	 * @throws NetworkException when network problems occur.
	 */
	public List<AssignmentDto> getAssignments(String courseID) throws NetworkException {
	    try {
            assignments = apiCourse.getAssignmentsOfCourse(courseID);
        } catch (IllegalArgumentException e) {
            throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            throw new DataNotFoundException("Assignment not found", courseID, DataType.ASSIGNMENT_NOT_FOUND);
        }
	    
	    if (null == assignments) {
	        assignments = new ArrayList<>();
	    }
	    
	    return assignments;
	}
	
	// temporary main method to test stuff.
	public static void main(String[] args) {
	    // remember database is emptied after every commit.
	    NetworkProtocol nw = new NetworkProtocol("http://147.172.178.30:3000", "java");
	    try {
            System.out.println(nw.getCourses(""));
        } catch (NetworkException e) {
            e.printStackTrace();
        }
	}

}
