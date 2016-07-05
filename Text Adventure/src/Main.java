import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileReader;
import java.util.Scanner;
import java.awt.event.*;

public class Main extends JFrame{

	static private String FILE = "World.txt";
	
	static Room room = new Room();
		
	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	static JLabel label1;
	static JTextArea textArea1;
	
	/**
	 * Shows the room description after typing "look" or when entering a new room
	 * @param room
	 */
	public static void displayRoom(Room room)
	{
		textArea1.setText(room.getRoomDescription());
	}

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
		    	  return;
		      }
		    }
		    //Close the input stream
		    s.close();
		    }catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
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
	
	public Main()
	{
		this.setSize(600,300);

		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dim = tk.getScreenSize();
		
		int xPos = (dim.width / 2) - (this.getWidth() / 2);
		int yPos = (dim.height / 2) - (this.getHeight() / 2);
		
		this.setLocation(xPos, yPos);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setTitle("Text Adventure");
		
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));
		
		textArea1 = new JTextArea();
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		textArea1.setEditable(false);
		textPanel.add(textArea1);
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.LINE_AXIS));
		label1 = new JLabel("");
		labelPanel.add(label1);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		button1 = new JButton("North");
		ListenForButton lForButtonN = new ListenForButton();
		
		button1.addActionListener(lForButtonN);
		buttonPanel.add(button1);
		
		button2 = new JButton("East");
		ListenForButton lForButtonE = new ListenForButton();
		
		button2.addActionListener(lForButtonE);
		buttonPanel.add(button2);
		
		button3 = new JButton("South");
		ListenForButton lForButtonS = new ListenForButton();
		
		button3.addActionListener(lForButtonS);
		buttonPanel.add(button3);
		
		button4 = new JButton("West");
		ListenForButton lForButtonW = new ListenForButton();
		
		button4.addActionListener(lForButtonW);
		buttonPanel.add(button4);
		
		Container contentPane = getContentPane();
		contentPane.add(textPanel, BorderLayout.NORTH);
		contentPane.add(labelPanel, BorderLayout.CENTER);
		contentPane.add(buttonPanel, BorderLayout.PAGE_END);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Main();
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
