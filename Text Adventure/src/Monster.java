
public class Monster {
	private String name;
	private String attackMessage;
	private int health;
	private int damage;
	private boolean monsterInRoom;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttackMessage() {
		return attackMessage;
	}
	public void setAttackMessage(String attackMessage) {
		this.attackMessage = attackMessage;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public boolean isMonsterInRoom() {
		return monsterInRoom;
	}
	public void setMonsterInRoom(boolean monsterInRoom) {
		this.monsterInRoom = monsterInRoom;
	}

}
