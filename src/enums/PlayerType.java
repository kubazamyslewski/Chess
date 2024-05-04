package enums;

public enum PlayerType {
    HUMAN,
    NETWORK_USER,
    COMPUTER;
	
	private static PlayerType[] list = PlayerType.values();

    public static PlayerType getPlayerType(int i) {
        return list[i];
    }
}
