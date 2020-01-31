package net.ssehub.exercisesubmitter.protocol;

import java.util.ArrayList;
import java.util.List;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.CourseDto;

/**
 * Manages the network protocol communication with the api for the exercise submitter.
 * 
 * @author Kunold
 *
 */
public class NetworkProtocol {
	
    private ApiClient apiClient;
    
	/**
	 * The api to communicate with.
	 */
	private UsersApi apiUser;
	
	/**
	 * The name of the course that uses the exercise submitter.
	 */
	private String courseName;
	
	/**
	 * The base Path to the REST server.
	 */
	private String basePath;
	
	/**
	 * A list of the courses.
	 */
	private List<CourseDto> courses = new ArrayList<>();
	
	/**
	 * A list of the groups.
	 */
	private List<CourseDto> groups = new ArrayList<>();
	
	/**
	 * A list of the assignments.
	 */
	private List<CourseDto> assignments = new ArrayList<>();
	
	/**
	 * The constructor of the class.
	 * @param basePath The REST URL of the student management server.
	 * @param courseName The course that is associated with the exercise submitter.
	 */
	public NetworkProtocol(String basePath, String courseName) {
	    apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiUser = new UsersApi(apiClient);
		this.courseName = courseName;
		this.basePath = basePath;
	}
	
	/**
	 * Getter for the Courses of a user.
	 * @return A List of all courses from the user (will never be <tt>null</tt>).
	 * @throws NetworkException when network problems occur.
	 */
	public List<CourseDto> getCourses() throws NetworkException {
		try {
            courses = apiUser.getCoursesOfUser("c290382b-8df7-409d-b6d8-719ed1a5c109");
		} catch (IllegalArgumentException e) {
		    throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            e.printStackTrace();
        }
		
		if (null == courses) {
		    courses = new ArrayList<>();
		}

        return courses;
	}
	
	/**
	 * Getter for the groups of a user.
	 * @return the group of a user.
	 * @throws NetworkException when network problems occur.
	 */
	public List<CourseDto> getGroup() throws NetworkException {
	    if (null == groups) {
	        groups = new ArrayList<>();
	    }
	    
	    return groups;
	}
	
	/**
	 * Getter for the assignments of a user.
	 * @return the assignments of a user.
	 * @throws NetworkException when network problems occur.
	 */
	public List<CourseDto> getAssignments() throws NetworkException {
	    if (null == assignments) {
	        assignments = new ArrayList<>();
	    }
	    
	    return assignments;
	}

}
