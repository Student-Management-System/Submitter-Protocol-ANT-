package net.ssehub.exercisesubmitter.protocol;


import org.junit.Assert;
import org.junit.Test;

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
     * Test .
     */
    @Test
    public void test() {
        NetworkProtocol np = new NetworkProtocol(TEST_SERVER, "a_user");
        
    }

}
