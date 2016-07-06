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
	
	static Room room = new Room();
		
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	ButtonGroup directionGroup;
	static JLabel label1;
	static JTextArea textArea1;
	
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
		    	  
		    	  room.setRoomDescription(tempString);
		    	  s.next();
		    	  room.setRoomNorth(s.next());
		    	  s.next();
		    	  room.setRoomEast(s.next());
		    	  s.next();
		    	  room.setRoomSouth(s.next());
		    	  s.next();
		    	  room.setRoomWest(s.next());
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
		
		JPanel thePanel = new JPanel();
		
		thePanel.setLayout(new GridBagLayout());
		
		textArea1 = new JTextArea(20,40);
		textArea1.setEditable(false);
		
		addComp(thePanel, textArea1, 0, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		label1 = new JLabel("Start");
		
		addComp(thePanel, label1, 0, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);
		
		Box directionBox = Box.createHorizontalBox();
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
		
		directionGroup = new ButtonGroup();
		directionGroup.add(button1);
		directionGroup.add(button2);
		directionGroup.add(button3);
		directionGroup.add(button4);
		directionBox.add(button1);
		directionBox.add(button2);
		directionBox.add(button3);
		directionBox.add(button4);
		directionBox.setBorder(BorderFactory.createTitledBorder("Directions"));
		addComp(thePanel, directionBox, 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.NONE);

		
		this.add(thePanel);
		this.pack();
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
	 * @param place
	 * @param stretch
	 */
	private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch)
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
				move(room, room.getRoomNorth());
			}
			else if (e.getSource() == button2)
			{
				move(room, room.getRoomEast());

			}
			else if (e.getSource() == button3)
			{
				move(room, room.getRoomSouth());

			}
			else if (e.getSource() == button4)
			{
				move(room, room.getRoomWest());

			}
			else
			{
				
			}
		}
	}
}
