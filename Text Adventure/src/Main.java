import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Main extends JFrame{

	static private String FILE = "World.txt";
	static private int MAX_LOOKS = 3;

	
	static Room room = new Room();
	
	static JPanel thePanel = new JPanel();
		
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;
	JButton button6;
	JButton button7;
	JButton button8;
	ButtonGroup directionGroup = new ButtonGroup();
	ButtonGroup itemGroup = new ButtonGroup();
	Box directionBox;
	Box itemBox;
	static JLabel label1;
	static JTextArea textArea1;
	static JTextArea textArea2 = new JTextArea();
	
	/**
	 * Shows the room description when entering a new room
	 * @param room
	 */
	public static void displayRoom(Room room)
	{
		textArea1.setText(room.getRoomDescription());
	}

	/**
	 * Stores all room info into appropriate fields.  For instance, stores room in North direction, stores room in South direction.. etc.
	 * @param file
	 * @param room
	 */
	public static void getRoomInfo(String file, Room room)
	{
		String strRoom = "<" + room.getCurrentRoom() + ">";
		String tempString = "";
		int count = 0;
		int i = 0;
		String[] lookArray = new String[MAX_LOOKS]; 
		
		try{
		    Scanner s = new Scanner(new FileReader(file));
		    String strLine;
		    
		    //Read File Line By Line
		    while ((strLine = s.nextLine()) != null)   {
		      if (strLine.equals(strRoom))
		      {
		    	  boolean stop = false;
		    	  
		    	  //Stores string for description of room
		    	  while (!stop)
		    	  {
		    		  strLine = s.nextLine();
		    		  String[] pieces = strLine.split("\\s+");
		    		  for (i = 0; i < pieces.length; i++)
		    		  {
		    			  if (pieces[i].equals("*"))
		    			  {
		    				  stop = true;
		    				  break;
		    			  }
		    			  
		    			  else
		    			  {
		    				  tempString += pieces[i] + " ";
		    			  }
		    		  }
		    		  tempString += "\n";
		    	  }
		    	  
		    	  room.setRoomDescription(tempString);
		    	  s.next();
		    	  room.setRoomNorth(s.next());
		    	  s.next();
		    	  room.setRoomEast(s.next());
		    	  s.next();
		    	  room.setRoomSouth(s.next());
		    	  s.next();
		    	  room.setRoomWest(s.next());
		    	  s.next();
		    	  count = s.nextInt();
		    	  
		    	  for (i = 0; i < count % MAX_LOOKS; i++)
		    	  {
		    		  lookArray[i] = s.next();
		    	  }
		    	  
		    	  room.setStrLookArray(lookArray);
		    	  s.close();
		    	  
		    	  //Checks if it is end of the game.
		    	  if ((room.getRoomEast().equals("None") && room.getRoomNorth().equals("None") && room.getRoomSouth().equals("None") && room.getRoomWest().equals("None")))
		  			{
		  				label1.setText("Game Over");
		  			}
		    	  
		    	  return;
		      }
		      
		    }
		    
		    //Close the input stream
		    s.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	public static void displayLook(String strLookDescription)
	{
		textArea2.setText(strLookDescription);
		textArea2.setEditable(false);
		
		addComp(thePanel, textArea2, 0, 2, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		thePanel.updateUI();
	}
	
	public void removeDisplayLook()
	{
		if (textArea2.getParent() != null)
		{
			thePanel.remove(textArea2);
		}
	}
	
	public static void getLookInfo(String file, Room room, String strItem)
	{
		String strLookRoom = "<" + room.getCurrentRoom() + "|" + strItem + ">";
		String tempString = "";
		try{
			Scanner s = new Scanner(new FileReader(file));
			String strLine = "";
			
			while ((strLine = s.nextLine()) != null)
			{
				if (strLine.equals(strLookRoom))
				{
					boolean stop = false;
					
					while (!stop)
					{
						strLine = s.nextLine();
				    	String[] pieces = strLine.split("\\s+");
				   		for (int i = 0; i < pieces.length; i++)
				   		{
				   			if (pieces[i].equals("*"))
				   			{
			    				stop = true;
			    				break;
			    			}
			    			  
				    		else
				    		{
				    			tempString += pieces[i] + " ";
				    		}
				   		}
				    		
				   		tempString += "\n";
					}	
					room.setStrLookDescription(tempString);
					displayLook(room.getStrLookDescription());
		    		s.close();
		    		return;
				}
				
				}
				
			s.close();
			}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	/**
	 * Updates label component if it is end game or if there is a place you cannot move to.
	 * Else it updates the current room you are in and displays room description.
	 * @param room
	 * @param strRoom
	 */
	public static void move(Room room, String strRoom)
	{
		if ((room.getRoomEast().equals("None") && room.getRoomNorth().equals("None") && room.getRoomSouth().equals("None") && room.getRoomWest().equals("None")))
		{
			label1.setText("Game Over");
			return;
		}
		else if (strRoom.equals("None"))
		{
			label1.setText("You can't go that way!");
			return;
		}
		else
		{
			label1.setText("");
		}
		
		room.setCurrentRoom(strRoom);
		
		getRoomInfo(FILE, room);
		displayRoom(room);
	}
	
	/**
	 * Creates the game window and all of its components.
	 */
	public void createFrame()
	{
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Escape");
				
		thePanel.setLayout(new GridBagLayout());
		
		textArea1 = new JTextArea(20,40);
		textArea1.setEditable(false);
		
		addComp(thePanel, textArea1, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		label1 = new JLabel("Start");
		
		addComp(thePanel, label1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		directionBox = Box.createHorizontalBox();
		button1 = new JButton("North");
		ListenForButton lForButtonN = new ListenForButton();
		button1.addActionListener(lForButtonN);
		button2 = new JButton("East");
		ListenForButton lForButtonE = new ListenForButton();
		button2.addActionListener(lForButtonE);
		button3 = new JButton("South");
		ListenForButton lForButtonS = new ListenForButton();
		button3.addActionListener(lForButtonS);
		button4 = new JButton("West");
		ListenForButton lForButtonW = new ListenForButton();
		button4.addActionListener(lForButtonW);
		button5 = new JButton("View");
		ListenForButton lForButtonV = new ListenForButton();
		button5.addActionListener(lForButtonV);

		directionGroup.add(button1);
		directionGroup.add(button2);
		directionGroup.add(button3);
		directionGroup.add(button4);
		directionGroup.add(button5);
		directionBox.add(button1);
		directionBox.add(button2);
		directionBox.add(button3);
		directionBox.add(button4);
		directionBox.add(button5);
		directionBox.setBorder(BorderFactory.createTitledBorder("Directions"));
		addComp(thePanel, directionBox, 0, 3, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		
		this.add(thePanel);
		this.pack();
		this.setVisible(true);
	}
	
	public void listItems()
	{
		String[] itemArray = new String[MAX_LOOKS];
		if (room.getStrLookArray().length != 0)
		{
			thePanel.remove(label1);
			itemArray = room.getStrLookArray();
			itemBox = Box.createHorizontalBox();
			button6 = new JButton();
			ListenForButton lForButton1 = new ListenForButton();
			button6.addActionListener(lForButton1);
			button7 = new JButton();
			ListenForButton lForButton2 = new ListenForButton();
			button7.addActionListener(lForButton2);
			button8 = new JButton();
			ListenForButton lForButton3 = new ListenForButton();
			button8.addActionListener(lForButton3);
			
			if (itemArray[0] != null)
			{
				button6.setText(itemArray[0]);
				itemGroup.add(button6);
				itemBox.add(button6);
			}
			if (itemArray[1] != null)
			{
				button7.setText(itemArray[1]);
				itemGroup.add(button7);
				itemBox.add(button7);
			}
			if (itemArray[2] != null)
			{
				button8.setText(itemArray[2]);
				itemGroup.add(button8);
				itemBox.add(button8);
			}
			
			itemBox.setBorder(BorderFactory.createTitledBorder("Items"));
			addComp(thePanel, itemBox, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
			thePanel.updateUI();
		}
	}
	
	public void addLabelComponent()
	{
		if (label1.getParent() == null)
		{
			addComp(thePanel, label1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		}
	}
	
	public void removeItemButton()
	{
		if (itemGroup.getButtonCount() == 1)
		{
			itemGroup.remove(button6);
			thePanel.remove(itemBox);
		}
		else if (itemGroup.getButtonCount() == 2)
		{
			itemGroup.remove(button6);
			itemGroup.remove(button7);
			thePanel.remove(itemBox);
		}
		else if (itemGroup.getButtonCount() == 3)
		{
			itemGroup.remove(button6);
			itemGroup.remove(button7);
			itemGroup.remove(button8);
			thePanel.remove(itemBox);
		}
		else
		{
			
		}
		
		thePanel.updateUI();
		
	}
	
	/**
	 * Makes it easier to add and shift components around.
	 * @param thePanel
	 * @param comp
	 * @param xPos
	 * @param yPos
	 * @param compWidth
	 * @param compHeight
	 * @param place
	 * @param stretch
	 */
	private static void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch)
	{
		GridBagConstraints gridConstraints = new GridBagConstraints();
		gridConstraints.gridx = xPos;
		gridConstraints.gridy = yPos;
		gridConstraints.gridwidth = compWidth;
		gridConstraints.gridheight = compHeight;
		gridConstraints.weightx = 100;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5,5,5,5);
		gridConstraints.anchor = place;
		gridConstraints.fill = stretch;

		thePanel.add(comp, gridConstraints);	
	}
	
	/**
	 * Start of program
	 * @param args
	 */
	public static void main(String[] args)
	{
		Main game = new Main();
		game.createFrame();
		
		try{
			Scanner scan = new Scanner(new FileReader(FILE));
			scan.next();
			room.setCurrentRoom(scan.next());
			getRoomInfo(FILE, room);
			displayRoom(room);
			scan.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	/**
	 * Private class for when a button is clicked.
	 * @author Soysauce
	 *
	 */
	private class ListenForButton implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == button1)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				move(room, room.getRoomNorth());
			}
			else if (e.getSource() == button2)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				move(room, room.getRoomEast());

			}
			else if (e.getSource() == button3)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				move(room, room.getRoomSouth());

			}
			else if (e.getSource() == button4)
			{
				removeDisplayLook();
				removeItemButton();
				addLabelComponent();
				move(room, room.getRoomWest());

			}
			else if (e.getSource() == button5)
			{
				removeDisplayLook();
				removeItemButton();
				listItems();
			}
			else if (e.getSource() == button6)
			{
				removeDisplayLook();
				getLookInfo(FILE, room, button6.getText());
			}
			else if (e.getSource() == button7)
			{
				removeDisplayLook();
				getLookInfo(FILE, room, button7.getText());
			}
			else if (e.getSource() == button8)
			{
				removeDisplayLook();
				getLookInfo(FILE, room, button8.getText());
			}
			else
			{
				
			}
		}
	}
}
