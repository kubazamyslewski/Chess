package networking;

/**
 * Headers of messges sent to message servers
 */
public enum Protocol {
    EVERYTHING_OK(0), // successful connection to a table

    ERROR_INVALID_TABLE_ID(1),

    ERROR_TABLE_IS_FULL(2),

    ERROR_INVALID_PASSWORD(3),
    NULL_COMMAND(4),
    STOP(5), // sent to server to stop running
    STOPPED(6), // sent to clients hen server is stopped
    JOIN(7), //message with this header contains tableID, username and password separated by \n character
    CHAT_MESSAGE(8); // message with this character contains one-line chat message


    private int value;
    Protocol(int value){

    }

    /**
     * @return the ConnectionInformation using its id
     */
    public static Protocol get(int id){
        return null;
    }

    public int getValue() {
        return value;
    }
}
