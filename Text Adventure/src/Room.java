
public class Room {
	
	private String currentRoom;
	private String roomDescription;
	private String roomNorth;
	private String roomSouth;
	private String roomEast;
	private String roomWest;
	private String strLookDescription;
	private String[] strLookArray = new String[Constants.MAX_LOOKS];
	private Monster monster = new Monster();
	private boolean monsterInRoom;
	
	public String getCurrentRoom() {
		return currentRoom;
	}
	
	public void setCurrentRoom(String currentRoom) {
		this.currentRoom = currentRoom;
	}

	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public String getRoomNorth() {
		return roomNorth;
	}

	public void setRoomNorth(String roomNorth) {
		this.roomNorth = roomNorth;
	}

	public String getRoomSouth() {
		return roomSouth;
	}

	public void setRoomSouth(String roomSouth) {
		this.roomSouth = roomSouth;
	}

	public String getRoomEast() {
		return roomEast;
	}

	public void setRoomEast(String roomEast) {
		this.roomEast = roomEast;
	}

	public String getRoomWest() {
		return roomWest;
	}

	public void setRoomWest(String roomWest) {
		this.roomWest = roomWest;
	}

	public String getStrLookDescription() {
		return strLookDescription;
	}

	public void setStrLookDescription(String strLookDescription) {
		this.strLookDescription = strLookDescription;
	}

	public String[] getStrLookArray() {
		return strLookArray;
	}

	public void setStrLookArray(String[] strLookArray) {
		this.strLookArray = strLookArray;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	public boolean isMonsterInRoom() {
		return monsterInRoom;
	}

	public void setMonsterInRoom(boolean monsterInRoom) {
		this.monsterInRoom = monsterInRoom;
	}

}
