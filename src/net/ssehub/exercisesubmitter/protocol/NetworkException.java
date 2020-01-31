package net.ssehub.exercisesubmitter.protocol;

/**
 * A Network Exception occurs if there is a problem with the REST server.
 * 
 * @author Kunold
 *
 */
public abstract class NetworkException extends Exception {
    
    /**
     * Generated.
     */
    private static final long serialVersionUID = 1795210885665078124L;

    public NetworkException(String message) {
        super(message);
    }

}
