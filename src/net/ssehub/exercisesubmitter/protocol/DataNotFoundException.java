package net.ssehub.exercisesubmitter.protocol;

public class DataNotFoundException extends NetworkException {
    
    /**
     * The Type of data for that Exceptions can be occur.
     * 
     * @author Kunold
     *
     */
    public static enum DataType {
        USER_NOT_FOUND,
        COURSE_NOT_FOUND,
        GROUP_NOT_FOUND,
        ASSIGNMENT_NOT_FOUND;
    }

    /**
     * Generated.
     */
    private static final long serialVersionUID = -2906949905575068217L;
    
    /**
     * The label of a DataType.
     */
    private DataType type;
    
    /**
     * The name of an item.
     */
    private String item;

    public DataNotFoundException(String message, String item, DataType type) {
        super(message);
        this.type = type;
        this.item = item;
    }

    /**
     * Returns what kind of data is missing on the server.
     * @return The type of missing data.
     */
    public DataType getType() {
        return type;
    }

    /**
     * Returns the missing item.
     * @return The item for which no data was found.
     */
    public String getMissingItem() {
        return item;
    }
}
