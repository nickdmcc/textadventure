import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Nicholas McCarty
 *
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame{
	
	public Monster monster = new Monster();
	
	public GameWindow window;
	public Main game;
	public Player player;
	public Room room;
	
	static JPanel thePanel = new JPanel();
	static JPanel monsterPanel = new JPanel();
	static JPanel classPanel = new JPanel();
	static JPanel startPanel = new JPanel();
	
	JButton buttonNorth;
	JButton buttonEast;
	JButton buttonSouth;
	JButton buttonWest;
	JButton buttonView;
	JButton buttonStatus;
	JButton buttonHeal;
	JButton buttonItem1;
	JButton buttonItem2;
	JButton buttonItem3;
	JButton buttonOk;
	JButton buttonStart;
	JButton buttonClass1;
	JButton buttonClass2;
	JButton buttonClass3;
	JButton buttonClass4;
	JButton buttonChoose;
	JButton buttonAttack;
	JButton buttonSkill1;
	JButton buttonRun;
	ButtonGroup directionGroup = new ButtonGroup();
	ButtonGroup classGroup = new ButtonGroup();
	ButtonGroup actionGroup = new ButtonGroup();
	ButtonGroup itemGroup = new ButtonGroup();
	Box directionBox;
	Box classBox;
	Box actionBox;
	Box itemBox;
	static JLabel label1;
	static JLabel label2;
	static JLabel label3;
	static JLabel label4;
	static JLabel label5;
	static JTextArea textName;
	static JTextArea textArea1;
	static JTextArea textArea2 = new JTextArea();
	static JTextArea textArea3 = new JTextArea();
	static JTextArea textArea4 = new JTextArea();
	static JTextArea textArea5 = new JTextArea();
	
	/**
	 * Creates the game window and all of its components.
	 */
	public GameWindow()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Escape");
		this.setSize(750, 750);
		
		startPanel.setLayout(new GridBagLayout());
		label2 = new JLabel("Enter your name: ");
		addComp(startPanel, label2, 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		textName = new JTextArea(1, 15);
		addComp(startPanel, textName, 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);	
		
		buttonStart = new JButton("Start!");
		ListenForButton lForButton11 = new ListenForButton();
		buttonStart.addActionListener(lForButton11);
		addComp(startPanel, buttonStart, 2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		label3 = new JLabel();
		addComp(startPanel, label3, 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		classPanel.setLayout(new GridBagLayout());
		label4 = new JLabel("Choose your class");
		addComp(classPanel, label4, 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		classBox = Box.createHorizontalBox();
		buttonClass1 = new JButton("Guard");
		ListenForButton lForButtonGuard = new ListenForButton();
		buttonClass1.addActionListener(lForButtonGuard);
		buttonClass2 = new JButton("Assassin");
		ListenForButton lForButtonAssassin = new ListenForButton();
		buttonClass2.addActionListener(lForButtonAssassin);
		buttonClass3 = new JButton("Bandit");
		ListenForButton lForButtonBandit = new ListenForButton();
		buttonClass3.addActionListener(lForButtonBandit);
		buttonClass4 = new JButton("Fighter");
		ListenForButton lForButtonFighter = new ListenForButton();
		buttonClass4.addActionListener(lForButtonFighter);
		classGroup.add(buttonClass1);
		classGroup.add(buttonClass2);
		classGroup.add(buttonClass3);
		classGroup.add(buttonClass4);
		classBox.add(buttonClass1);
		classBox.add(buttonClass2);
		classBox.add(buttonClass3);
		classBox.add(buttonClass4);
		classBox.setBorder(BorderFactory.createTitledBorder("Classes"));
		addComp(classPanel, classBox, 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		textArea5.setEditable(false);
		textArea5.setVisible(false);
		addComp(classPanel, textArea5, 0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		buttonChoose = new JButton("Choose");
		ListenForButton lForButtonChoose = new ListenForButton();
		buttonChoose.addActionListener(lForButtonChoose); 
		buttonChoose.setVisible(false);
		addComp(classPanel, buttonChoose, 0, 3, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			
		monsterPanel.setLayout(new GridBagLayout());
		textArea4.setEditable(false);
		addComp(monsterPanel, textArea4, 0, 0, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE);
		
		label5 = new JLabel();
		label5.setVisible(false);
		addComp(monsterPanel, label5, 0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.NONE);
		
		buttonAttack = new JButton("Attack");
		ListenForButton lForButtonAttack = new ListenForButton();
		buttonAttack.addActionListener(lForButtonAttack);
		addComp(monsterPanel, buttonAttack, 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		buttonRun = new JButton("Run");
		ListenForButton lForButtonRun = new ListenForButton();
		buttonRun.addActionListener(lForButtonRun);
		addComp(monsterPanel, buttonRun, 1, 1, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		buttonSkill1 = new JButton();
		ListenForButton lForButtonSkill1 = new ListenForButton();
		buttonSkill1.addActionListener(lForButtonSkill1);
		addComp(monsterPanel, buttonSkill1, 2, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		buttonOk = new JButton("OK");
		ListenForButton lForButtonOK = new ListenForButton();
		buttonOk.addActionListener(lForButtonOK);
		addComp(monsterPanel, buttonOk, 0, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		buttonOk.setVisible(false);
		
		thePanel.setLayout(new GridBagLayout());
		
		textArea1 = new JTextArea();
		textArea1.setEditable(false);
		
		addComp(thePanel, textArea1, 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		label1 = new JLabel("Start");
		
		addComp(thePanel, label1, 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		directionBox = Box.createHorizontalBox();
		buttonNorth = new JButton("North");
		ListenForButton lForButtonN = new ListenForButton();
		buttonNorth.addActionListener(lForButtonN);
		buttonEast = new JButton("East");
		ListenForButton lForButtonE = new ListenForButton();
		buttonEast.addActionListener(lForButtonE);
		buttonSouth = new JButton("South");
		ListenForButton lForButtonS = new ListenForButton();
		buttonSouth.addActionListener(lForButtonS);
		buttonWest = new JButton("West");
		ListenForButton lForButtonW = new ListenForButton();
		buttonWest.addActionListener(lForButtonW);
		buttonView = new JButton("View");
		ListenForButton lForButtonV = new ListenForButton();
		buttonView.addActionListener(lForButtonV);
		buttonStatus = new JButton("Status");
		ListenForButton lForButtonSt = new ListenForButton();
		buttonStatus.addActionListener(lForButtonSt);
		buttonHeal = new JButton("Heal");
		ListenForButton lForButtonHeal = new ListenForButton();
		buttonHeal.addActionListener(lForButtonHeal);

		directionGroup.add(buttonNorth);
		directionGroup.add(buttonEast);
		directionGroup.add(buttonSouth);
		directionGroup.add(buttonWest);
		directionBox.add(buttonNorth);
		directionBox.add(buttonEast);
		directionBox.add(buttonSouth);
		directionBox.add(buttonWest);
		directionBox.setBorder(BorderFactory.createTitledBorder("Directions"));
		addComp(thePanel, directionBox, 0, 3, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		actionBox = Box.createHorizontalBox();
		actionGroup.add(buttonView);
		actionGroup.add(buttonStatus);
		actionGroup.add(buttonHeal);
		actionBox.add(buttonView);
		actionBox.add(buttonStatus);
		actionBox.add(buttonHeal);
		actionBox.setBorder(BorderFactory.createTitledBorder("Actions"));
		addComp(thePanel, actionBox, 0, 4, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		this.add(thePanel);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Makes it easier to add and shift components around.
	 * @param thePanel
	 * @param comp
	 * @param xPos
	 * @param yPos
	 * @param compWidth
	 * @param compHeight
	 * @param compWeightx
	 * @param compWeighty
	 * @param place
	 * @param stretch
	 */
	private static void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int compWeightx, int compWeighty, int place, int stretch)
	{
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = compWeightx;
		gridConstraints.weighty = compWeighty;
		gridConstraints.insets = new Insets(5,5,5,5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);	
	}
	
	public void removeItemButton()
	{
		if (itemGroup.getButtonCount() == 1)
		{
			itemGroup.remove(buttonItem1);
			thePanel.remove(itemBox);
		}
		else if (itemGroup.getButtonCount() == 2)
		{
			itemGroup.remove(buttonItem1);
			itemGroup.remove(buttonItem2);
			thePanel.remove(itemBox);
		}
		else if (itemGroup.getButtonCount() == 3)
		{
			itemGroup.remove(buttonItem1);
			itemGroup.remove(buttonItem2);
			itemGroup.remove(buttonItem3);
			thePanel.remove(itemBox);
		}
		else
		{
			
		}
		
		thePanel.updateUI();
		
	}
	
	public void setLabel(String label)
	{
		label1.setText(label);
		thePanel.updateUI();
	}
	
	public void addLabelComponent()
	{
		if (label1.getParent() == null)
		{
			addComp(thePanel, label1, 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		}
	}
	
	public void listItems()
	{
		String[] itemArray = new String[Constants.MAX_LOOKS];
		if (room.getStrLookArray().length != 0)
		{
			thePanel.remove(label1);
			itemArray = room.getStrLookArray();
			itemBox = Box.createHorizontalBox();
			buttonItem1 = new JButton();
			ListenForButton lForButton1 = new ListenForButton();
			buttonItem1.addActionListener(lForButton1);
			buttonItem2 = new JButton();
			ListenForButton lForButton2 = new ListenForButton();
			buttonItem2.addActionListener(lForButton2);
			buttonItem3 = new JButton();
			ListenForButton lForButton3 = new ListenForButton();
			buttonItem3.addActionListener(lForButton3);
			
			if (itemArray[0] != null)
			{
				buttonItem1.setText(itemArray[0]);
				itemGroup.add(buttonItem1);
				itemBox.add(buttonItem1);
			}
			if (itemArray[1] != null)
			{
				buttonItem2.setText(itemArray[1]);
				itemGroup.add(buttonItem2);
				itemBox.add(buttonItem2);
			}
			if (itemArray[2] != null)
			{
				buttonItem3.setText(itemArray[2]);
				itemGroup.add(buttonItem3);
				itemBox.add(buttonItem3);
			}
			
			itemBox.setBorder(BorderFactory.createTitledBorder("Items"));
			addComp(thePanel, itemBox, 0, 1, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			thePanel.updateUI();
		}
	}
	
	/**
	 * Shows the room description when entering a new room
	 * @param room
	 */
	public void displayRoom(Room room)
	{
		textArea1.setText(room.getRoomDescription());
	}
	
	
	public void displayLook(String strLookDescription)
	{
		textArea2.setText(strLookDescription);
		textArea2.setEditable(false);
		
		addComp(thePanel, textArea2, 0, 2, 1, 1, 1, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL);
		thePanel.updateUI();
	}
	
	public void removeDisplayLook()
	{
		if (textArea2.getParent() != null)
		{
			thePanel.remove(textArea2);
		}
	}
	
	public void displayStatus(Player player)
	{
		String strPlayer = "Name: " + player.getName() + "\nHealth: " + player.getHealth() + 
							"\nEnergy: " + player.getEnergy() + "\n\n" +
							"Class: " + player.getClassName() + "\nWeapon: " + player.getWeapon() + "\n" + 
							"Damage: " + player.getDamage() + "\nCritical chance: " + player.getCriticalChance() +
							"\nRun chance: " + player.getRunChance();
		textArea2.setText(strPlayer);
		textArea2.setEditable(false);
		
		addComp(thePanel, textArea2, 0, 5, 1, 1, 1, 0, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
		thePanel.updateUI();
	}
	
	public void removeDisplayStatus()
	{
		if (textArea3.getParent() != null)
		{
			thePanel.remove(textArea3);
		}
	}
	
	public void checkHeal()
	{
		if (player.getEnergy() < 2)
		{
			textArea2.setText("You need at least 2 energy to heal!");
		}
		else if (player.getHealth() == player.getMaxHealth())
		{
			textArea2.setText("You are already at max health!");
		}
		else
		{
			textArea2.setText("You recover ");
			if ((player.getMaxHealth() - player.getHealth()) < 30) 
			{
				textArea2.append(player.getMaxHealth() - player.getHealth() +" points of health!");
				player.setHealth(player.getMaxHealth());
				player.setEnergy(player.getEnergy() - 2);
			}
			else
			{
				textArea2.append("30 points of health!");
				player.setHealth(player.getHealth() + 30);
				player.setEnergy(player.getEnergy() - 2);
			}
		}
		textArea2.setEditable(false);
		addComp(thePanel, textArea2, 0, 5, 1, 1, 1, 0, GridBagConstraints.SOUTH, GridBagConstraints.NONE);
		thePanel.updateUI();
	}
	
	public void checkSkill1(Player player, Monster monster)
	{
		int attack = 0;
		int monsterAttack = 0;
		String className = player.getClassName();
		if (className.equals("Guard") && player.getEnergy() > 4)
		{
			attack = (int) Math.round(player.criticalRole(player.attackDamageRange()) * 1.3);
			monsterAttack = monster.attackDamageRange() / 2;
			if (player.isCritical())
			{
				textArea4.setText(player.getName() + " attacks with a Shield Ram and deals " + attack + " damage\n"
									+ "Critical damage!\n");
			}
			else
			{	
				textArea4.setText(player.getName() + " attacks with a Shield Ram and deals " + attack + " damage\n");
			}
			monster.setHealth(monster.getHealth() - attack);
			if (monster.getHealth() > 0)
			{
				player.setHealth(player.getHealth() - monsterAttack);
				textArea4.append(monster.getAttackMessage() + "\n" 
								+ "You take " + monsterAttack + " points of collateral damage.");
			}
			if (player.getHealth() <= 0)
			{
				textArea4.append("\n\nYou are dead.");
				buttonAttack.setVisible(false);
				buttonSkill1.setVisible(false);
				buttonRun.setVisible(false);
			}
			else if (player.getEnergy() > 4)
			{
				player.setEnergy(player.getEnergy() - 5);
			}
			else
			{
				player.setEnergy(0);
			}
			label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());
		}
		else if (className.equals("Assassin") && player.getEnergy() > 4)
		{
			attack = player.criticalRole(player.attackDamageRange());
			monsterAttack = monster.attackDamageRange();
			if (player.isCritical())
			{
				textArea4.setText(player.getName() + " uses Perfect Strike! You land a critical dealing " + attack + " \n"
									+ "Critical damage!\n");
			}
			else
			{	
				textArea4.setText(player.getName() + " uses Perfect Strike! You land a critical dealing " + (attack * 2) + " \n"
						+ "Critical damage!\n");			}
			monster.setHealth(monster.getHealth() - attack);
			if (monster.getHealth() > 0)
			{
				player.setHealth(player.getHealth() - monsterAttack);
				textArea4.append(monster.getAttackMessage() + "\n" 
								+ "You take " + monsterAttack + " points of damage.");
			}
			
			if (player.getHealth() <= 0)
			{
				textArea4.append("\n\nYou are dead.");
				buttonAttack.setVisible(false);
				buttonSkill1.setVisible(false);
				buttonRun.setVisible(false);
			}
			else if (player.getEnergy() > 4)
			{
				player.setEnergy(player.getEnergy() - 5);
			}
			else
			{
				player.setEnergy(0);
			}
			label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());
		}
		else if (className.equals("Bandit") && player.getEnergy() > 4)
		{
			attack = player.criticalRole(player.attackDamageRange());
			monsterAttack = monster.attackDamageRange();
			if (player.isCritical())
			{
				textArea4.setText(player.getName() + " performs Shadow Step. You deal " + attack + " damage\n"
									+ "Critical damage!\n");
			}
			else
			{	
				textArea4.setText(player.getName() + " peforms Shadow Step. You deal " + attack + " damage\n");
			}
			monster.setHealth(monster.getHealth() - attack);
			textArea4.append( "You avoid the enemy's attack!");
			if (player.getHealth() <= 0)
			{
				textArea4.append("\n\nYou are dead.");
				buttonAttack.setVisible(false);
				buttonSkill1.setVisible(false);
				buttonRun.setVisible(false);
			}
			else if (player.getEnergy() > 4)
			{
				player.setEnergy(player.getEnergy() - 5);
			}
			else
			{
				player.setEnergy(0);
			}
			label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());
		}
		else if (className.equals("Fighter") && player.getEnergy() > 4)
		{
			attack = (int) Math.round(player.criticalRole(player.attackDamageRange()) * .7);
			attack += (int) Math.round(player.criticalRole(player.attackDamageRange()) * .7);
			attack += (int) Math.round(player.criticalRole(player.attackDamageRange()) * .7);
			monsterAttack = monster.attackDamageRange();
			if (player.isCritical())
			{
				textArea4.setText(player.getName() + " uses Triple Slash and deals " + attack + " damage\n"
									+ "Critical damage!\n");
			}
			else
			{	
				textArea4.setText(player.getName() + " uses Triple Slash and deals " + attack + " damage\n");
			}
			monster.setHealth(monster.getHealth() - attack);
			if (monster.getHealth() > 0)
			{
				player.setHealth(player.getHealth() - monsterAttack);
				textArea4.append(monster.getAttackMessage() + "\n" 
								+ "You take " + monsterAttack + " points of damage.");
			}
			if (player.getHealth() <= 0)
			{
				textArea4.append("\n\nYou are dead.");
				buttonAttack.setVisible(false);
				buttonSkill1.setVisible(false);
				buttonRun.setVisible(false);
			}
			else if (player.getEnergy() > 4)
			{
				player.setEnergy(player.getEnergy() - 5);
			}
			else
			{
				player.setEnergy(0);
			}
			label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());	
		}
		else
		{
			textArea4.setText("You don't have enough energy!");
		}
		if (monster.getHealth() <= 0)
		{
			textArea4.append("\nThe " + monster.getName() + " has been killed.\n");
			monster.setMonsterInRoom(false);
			buttonOk.setVisible(true);
			buttonAttack.setVisible(false);
			buttonSkill1.setVisible(false);
			buttonRun.setVisible(false);
		}
	}
	
	public void checkMonster(Monster monster, Player player)
	{
		if (monster != null)
		{
			if (monster.isMonsterInRoom())
			{
				thePanel.setVisible(false);
				monsterPanel.setVisible(true);
				label5.setVisible(true);
				label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());
				if (player.getClassName().equals("Guard"))
				{
					buttonSkill1.setText("Shield Ram");

				}
				else if (player.getClassName().equals("Assassin"))
				{
					buttonSkill1.setText("Perfect Strike");

				}
				else if (player.getClassName().equals("Bandit"))
				{
					buttonSkill1.setText("Shadow Step");

				}
				else if (player.getClassName().equals("Fighter"))
				{
					buttonSkill1.setText("Triple Slash");

				}
				else
				{
					buttonSkill1.setText("Error");
				}
				buttonAttack.setVisible(true);
				buttonSkill1.setVisible(true);
				buttonRun.setVisible(true);
				displayMonsterInRoom(monster, player);
			}
			
			else
			{
				this.remove(monsterPanel);
				thePanel.setVisible(true);
			}
		}
		
	}
	
	public void attackMonster(Monster monster, Player player)
	{	
		int attack = 0;
		int monsterAttack = 0;
		
		attack = player.criticalRole(player.attackDamageRange());
		monsterAttack = monster.attackDamageRange();
		if (player.isCritical())
		{
			textArea4.setText(player.getName() + " attacks with a " + player.getWeapon() +  " and deals " + attack + " \n"
								+ "Critical damage!\n");
		}
		else
		{	
			textArea4.setText(player.getName() + " attacks with a " + player.getWeapon() +  " and deals " + attack + " \n");
		}
		monster.setHealth(monster.getHealth() - attack);
		if (monster.getHealth() > 0)
		{
			player.setHealth(player.getHealth() - monsterAttack);
			textArea4.append(monster.getAttackMessage() + "\n" 
							+ "You take " + monsterAttack + " points of damage.");
		}
		if (player.getEnergy() < player.getMaxEnergy())
		{
			player.setEnergy(player.getEnergy() + 1);
		}
		label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());
		if (player.getHealth() <= 0)
		{
			textArea4.append("\n\nYou are dead.");
			buttonAttack.setVisible(false);
			buttonSkill1.setVisible(false);
			buttonRun.setVisible(false);
		}
		if (monster.getHealth() <= 0)
		{
			textArea4.append("\nThe " + monster.getName() + " has been killed.\n");
			monster.setMonsterInRoom(false);
			buttonOk.setVisible(true);
			buttonAttack.setVisible(false);
			buttonSkill1.setVisible(false);
			buttonRun.setVisible(false);
		}
	}
	
	public void runAway(Monster monster, Player player)
	{
		Random random = new Random();
		int chance = random.nextInt(100);
		if (chance <= player.getRunChance())
		{
			textArea4.setText("You escape from the " + monster.getName() + "!");
			monster.setMonsterInRoom(false);
			buttonAttack.setVisible(false);
			buttonSkill1.setVisible(false);
			buttonRun.setVisible(false);
			buttonOk.setVisible(true);
		}
		else
		{
			player.setHealth(player.getHealth() - monster.getDamage());
			textArea4.setText("You failed to run away!\n" + monster.getAttackMessage() + "\n" 
					+ "You take " + monster.getDamage() + " points of damage.");
			label5.setText("   Health: " + player.getHealth() + "    " + "Energy: " + player.getEnergy());
			if (player.getHealth() < 0)
			{
				textArea4.append("\n\nYou are dead.");
				buttonAttack.setVisible(false);
				buttonSkill1.setVisible(false);
				buttonRun.setVisible(false);
			}
		}
	}
	
	public void displayMonsterInRoom(Monster monster, Player player)
	{
		this.add(monsterPanel);
		textArea4.append("Oh no! A " + monster.getName() + " has appeared!\n\n");	
	}
	
	public void setPlayerName()
	{
		thePanel.setVisible(false);
		this.add(startPanel);
	}
	
	public void chooseClass()
	{
		startPanel.setVisible(false);
		this.add(classPanel);
	}
	
	public void confirmClass(String className, Player player)
	{
		textArea5.setVisible(true);
		textArea5.setText("Are you sure you want to be a " + className +"?\n"
						+ "Health: " + player.getHealth() +"\n"
						+ "Weapon: " + player.getWeapon() +"\n"
						+ "Damage: " + player.getDamage() + "\n"
						+ "Critical chance: " + player.getCriticalChance() + "\n"
						+ "Run chance: " + player.getRunChance());
		buttonChoose.setVisible(true);
		player.setClassName(className);
	}
	
	public void startGame()
	{
		classPanel.setVisible(false);
		thePanel.setVisible(true);
	}
	
	public void setMonster(Monster monster)
	{
		this.monster = monster;
	}
	
	public void setGameWindow(GameWindow window)
	{
		this.window = window;
	}
	
	public void setMain(Main game)
	{
		this.game = game;
	}
	
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	
	public void setRoom(Room room)
	{
		this.room = room;
	}
	
	/**
	 * Private class for when a button is clicked.
	 * @author Nicholas McCarty
	 *
	 */
	private class ListenForButton implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == buttonNorth)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				game.move(room, room.getRoomNorth(), window, player);
			}
			else if (e.getSource() == buttonEast)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				game.move(room, room.getRoomEast(), window, player);

			}
			else if (e.getSource() == buttonSouth)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				game.move(room, room.getRoomSouth(), window, player);

			}
			else if (e.getSource() == buttonWest)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				game.move(room, room.getRoomWest(), window, player);

			}
			else if (e.getSource() == buttonView)
			{
				removeDisplayLook();
				removeItemButton();
				listItems();
			}
			else if (e.getSource() == buttonItem1)
			{
				removeDisplayLook();
				game.getLookInfo(Constants.FILE, room, buttonItem1.getText(), window);
			}
			else if (e.getSource() == buttonItem2)
			{
				removeDisplayLook();
				game.getLookInfo(Constants.FILE, room, buttonItem2.getText(), window);
			}
			else if (e.getSource() == buttonItem3)
			{
				removeDisplayLook();
				game.getLookInfo(Constants.FILE, room, buttonItem3.getText(), window);
			}
			else if (e.getSource() == buttonStatus)
			{
				window.displayStatus(player);
			}
			else if (e.getSource() == buttonOk)
			{
				buttonOk.setVisible(false);
				textArea4.setText("");
				checkMonster(monster, player);
			}
			else if (e.getSource() == buttonStart)
			{
				if (textName.getText().equals(""))
				{
					label3.setText("You did not enter a name.");
				}
				else if (textName.getText().length() > 15)
				{
					label3.setText("Name must be 15 characters or less");
				}
				else
				{
					for(int i = 0; i < textName.getText().length(); i++){
				            if(Character.isWhitespace(textName.getText().charAt(i))){
				                label3.setText("Your name should not have a space in it");
				                return;
				            }
				        }
					player.setName(textName.getText());
					chooseClass();
				}
			}
			else if (e.getSource() == buttonClass1)
			{
				game.setClass(player, window, buttonClass1.getText());
				confirmClass(buttonClass1.getText(), player);
			}
			else if (e.getSource() == buttonClass2)
			{
				game.setClass(player, window, buttonClass2.getText());
				confirmClass(buttonClass2.getText(), player);
			}
			else if (e.getSource() == buttonClass3)
			{
				game.setClass(player, window, buttonClass3.getText());
				confirmClass(buttonClass3.getText(), player);
			}
			else if (e.getSource() == buttonClass4)
			{
				game.setClass(player, window, buttonClass4.getText());
				confirmClass(buttonClass4.getText(), player);
			}
			else if (e.getSource() == buttonChoose)
			{
				startGame();
			}
			else if (e.getSource() == buttonAttack)
			{
				attackMonster(monster, player);
			}
			else if (e.getSource() == buttonRun)
			{
				runAway(monster, player);
			}
			else if (e.getSource() == buttonHeal)
			{
				checkHeal();
			}
			else if (e.getSource() == buttonSkill1)
			{
				checkSkill1(player, monster);
			}
			else
			{
				
			}
		}
	}

}
