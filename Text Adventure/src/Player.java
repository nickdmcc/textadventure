import java.util.Random;


public class Player {
	private String name;
	private String className;
	private String weapon;
	private boolean critical;
	private int health;
	private int maxHealth;
	private int damage;
	private int criticalChance;
	private int runChance;
	private int energy;
	private int maxEnergy;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getRunChance() {
		return runChance;
	}
	public void setRunChance(int runChance) {
		this.runChance = runChance;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getMaxEnergy() {
		return maxEnergy;
	}
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	public void setCriticalChance(int criticalChance)
	{
		this.criticalChance = criticalChance;
	}
	public int getCriticalChance()
	{
		return criticalChance;
	}
	public boolean isCritical() {
		return critical;
	}
	public void setCritical(boolean critical) {
		this.critical = critical;
	}
	
	public int attackDamageRange()
	{
		Random random = new Random();
		int range = random.nextInt(4) + (damage - 2);
		return range;
	}
	
	public int criticalRole(int damage)
	{
		Random random = new Random();
		int range = random.nextInt(100);
		if (range <= criticalChance)
		{
			damage = damage * 2;
			setCritical(true);
		}
		else
		{
			setCritical(false);
		}
		return damage;
	}

}
