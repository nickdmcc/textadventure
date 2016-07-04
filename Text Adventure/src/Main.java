import java.io.FileReader;
import java.util.Scanner;
public class Main {
	
	static private int STILL_PLAYING = 1;
	static private int QUIT = 0;
	static private String FILE = "World.txt";
	
	/**
	 * Shows the room description after typing "look" or when entering a new room
	 * @param room
	 */
	public static void displayRoom(Room room)
	{
		System.out.println(room.getRoomDescription());
		System.out.println();
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
		if (strRoom.equals("None"))
		{
			System.out.println("You can't go that way!");
			return;
		}
		
		room.setCurrentRoom(strRoom);
		
		getRoomInfo(FILE, room);
		displayRoom(room);
	}
	
	public static int getInput(Room room, String input)
	{
		if (input.equals("look"))
		{
			displayRoom(room);
		}
		else if (input.equals("quit"))
		{
			System.out.println("Game Over");
			return QUIT;
		}
		else if (input.equals("north"))
		{
			move(room, room.getRoomNorth());
		}
		else if (input.equals("east"))
		{
			move(room, room.getRoomEast());
		}
		else if (input.equals("south"))
		{
			move(room, room.getRoomSouth());
		}
		else if (input.equals("west"))
		{
			move(room, room.getRoomWest());
		}
		else
		{
			System.out.println("Commands: look north south east west quit");
		}

		return STILL_PLAYING;
	}
	
	public static void main(String args[])
	{
		Room room = new Room();
		String strInput = "";
		try{
			Scanner scan = new Scanner(new FileReader(FILE));
			Scanner reader = new Scanner(System.in);
			scan.next();
			room.setCurrentRoom(scan.next());
			getRoomInfo(FILE, room);
			displayRoom(room);
			while(true)
			{
				if ((room.getRoomEast().equals("None") && room.getRoomNorth().equals("None") && room.getRoomSouth().equals("None") && room.getRoomWest().equals("None")))
				{
					System.out.println("Game Over");
					break;
				}
				System.out.println();
				System.out.print(": ");
				strInput = reader.next();
				if (getInput(room, strInput) == QUIT)
				{
					break;
				}
			}
			scan.close();
			reader.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}
		
	}
}
