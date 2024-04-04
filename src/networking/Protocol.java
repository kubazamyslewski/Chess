package networking;

/**
 * enum for sending protocols
 */
public enum Protocol {
    EVERYTHING_OK(0), // successful connection

    ERROR_INVALID_TABLE_ID(1),

    ERROR_TABLE_IS_FULL(2),

    ERROR_INVALID_PASSWORD(3),
    MOVE(4),
    STOP(5), // For server to send if it wants to stop running
    STOPPED(6), // For clients to return when server sends STOP
    NULL_COMMAND(7);


    private int value;
    Protocol(int value){

    }

    /**
     *
     * @return the ConnectionInformation using its id
     */
    public static Protocol get(int id){
        return null;
    }

    public int getValue() {
        return value;
    }
}
