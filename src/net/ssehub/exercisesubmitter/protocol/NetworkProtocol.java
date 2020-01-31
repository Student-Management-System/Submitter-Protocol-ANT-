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
	 * The user that is currently logged in.
	 */
	private String user;
	
	/**
	 * The base PAth to the REST server.
	 */
	private String basePath;
	
	/**
	 * A list of the courses.
	 */
	private List<CourseDto> courses = new ArrayList<>();
	
	/**
	 * The constructor of the class.
	 * @param basePath The REST URL of the student management server.
	 * @param user The user that is logged in the exercise Submitter.
	 */
	public NetworkProtocol(String basePath, String user) {
	    apiClient = new ApiClient();
        apiClient.setBasePath(basePath);
        apiUser = new UsersApi(apiClient);
		this.user = user;
		this.basePath = basePath;
	}
	
	/**
	 * Getter for the Courses of a user.
	 * @return A List of all courses from the user.
	 */
	public List<CourseDto> getCourses() throws NetworkException {
		try {
            courses = apiUser.getCoursesOfUser("c290382b-8df7-409d-b6d8-719ed1a5c109");
		} catch (IllegalArgumentException e) {
		    throw new ServerNotFoundException(e.getMessage(), basePath);
        } catch (ApiException e) {
            e.printStackTrace();
        }
		return courses;
	}
	
	
	public static void main(String[] args) {
	    NetworkProtocol np = new NetworkProtocol("http://147.172.178.30:3000", "123456");
	    //CourseDto c = getCourses();
	    
	}

}
