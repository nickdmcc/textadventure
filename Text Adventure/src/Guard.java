
public class Guard extends PlayerClass{
	private String attackMessage;
	private String monsterAttackMessage;
	
	public Guard(String name, String weapon, String className, int health, int maxHealth, int damage, int critical, int run)
	{
		setName(name);
		setWeapon(weapon);
		setClassName(className);
		setHealth(health);
		setMaxHealth(maxHealth);
		setDamage(damage);
		setCriticalChance(critical);
		setRunChance(run);
		setMaxEnergy(Constants.START_ENERGY);
		setEnergy(Constants.START_ENERGY);
	}

	public String getAttackMessage() {
		return attackMessage;
	}

	public void setAttackMessage(String attackMessage) {
		this.attackMessage = attackMessage;
	}
	
	public String getMonsterAttackMessage() {
		return monsterAttackMessage;
	}

	public void setMonsterAttackMessage(String monsterAttackMessage) {
		this.monsterAttackMessage = monsterAttackMessage;
	}
	
	public String getStatus()
	{
		String strGuard = "Name: " + getName() + "\nHealth: " + getHealth() + 
				"\nEnergy: " + getEnergy() + "\n\n" +
				"Class: " + getClassName() + "\nWeapon: " + getWeapon() + "\n" + 
				"Damage: " + getDamage() + "\nCritical chance: " + getCriticalChance() +
				"\nRun chance: " + getRunChance();
		
		return strGuard;
	}
	
	public String heal()
	{
		String healMessage;
		if (getEnergy() < 2)
		{
			healMessage = ("You need at least 2 energy to heal!");
		}
		else if (getHealth() == getMaxHealth())
		{
			healMessage = ("You are already at max health!");
		}
		else
		{
			healMessage = ("You recover ");
			if ((getMaxHealth() - getHealth()) < 30) 
			{
				healMessage += (getMaxHealth() - getHealth() +" points of health!");
				setHealth(getMaxHealth());
				setEnergy(getEnergy() - 2);
			}
			else
			{
				healMessage += ("30 points of health!");
				setHealth(getHealth() + 30);
				setEnergy(getEnergy() - 2);
			}
		}
		
		return healMessage;
	}
	
	public void skill1(Monster monster)
	{
		int attack;
		int monsterAttack;
		
		if (getEnergy() >= 4)
		{
			attack = (int) Math.round(criticalRole(attackDamageRange()) * 1.3);
			monsterAttack = monster.attackDamageRange() / 2;
			
			if (isCritical())
			{
				setAttackMessage((getName() + " attacks with a Shield Ram and deals " + attack + " damage\n"
									+ "Critical damage!\n"));
			}
			else
			{	
				setAttackMessage((getName() + " attacks with a Shield Ram and deals " + attack + " damage\n"));
			}
			monster.setHealth(monster.getHealth() - attack);
			if (monster.getHealth() > 0)
			{
				setHealth(getHealth() - monsterAttack);
				setMonsterAttackMessage("You take " + monsterAttack + " points of collateral damage.");
			}
			
			if (getEnergy() >= 4)
			{
				setEnergy(getEnergy() - 4);
			}
			else
			{
				setEnergy(0);
			}
			
		}
		
		else
		{
			setAttackMessage("You do not have enough energy!");
			setMonsterAttackMessage("");
		}
	}
}
