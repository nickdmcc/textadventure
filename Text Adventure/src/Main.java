import java.io.FileReader;
import java.util.Scanner;

public class Main{
	public String labelText = "";
	public Monster[] monster = new Monster[Constants.NUM_OF_MONSTERS];
	int k = -1;
	
	/**
	 * Updates label component if it is end game or if there is a place you cannot move to.
	 * Else it updates the current room you are in and displays room description.
	 * @param room
	 * @param strRoom
	 */
	public void move(Room room, String strRoom, GameWindow window)
	{
		if ((room.getRoomEast().equals("None") && room.getRoomNorth().equals("None") && room.getRoomSouth().equals("None") && room.getRoomWest().equals("None")))
		{
			labelText = "Game Over";
			window.setLabel(labelText);
			return;
		}
		else if (strRoom.equals("None"))
		{
			labelText = "You can't go that way!";
			window.setLabel(labelText);
			return;
		}
		else
		{
			labelText = "";
			window.setLabel(labelText);
		}
		
		room.setCurrentRoom(strRoom);
		
		getRoomInfo(Constants.FILE, Constants.ROOM, window);
		Constants.WINDOW.displayRoom(room);
		Constants.WINDOW.setMonster(monster[k]);
		Constants.WINDOW.checkMonster(monster[k]);
	}

	/**
	 * Stores all room info into appropriate fields.  For instance, stores room in North direction, stores room in South direction.. etc.
	 * @param file
	 * @param room
	 */
	public void getRoomInfo(String file, Room room, GameWindow window)
	{
		String strRoom = "<" + room.getCurrentRoom() + ">";
		String strMonster = "";
		String tempString = "";
		int count = 0;
		int i = 0;
		String[] lookArray = new String[Constants.MAX_LOOKS]; 
		
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
		    	  
		    	  for (i = 0; i < count % Constants.MAX_LOOKS; i++)
		    	  {
		    		  lookArray[i] = s.next();
		    	  }
		    	  
		    	  s.next();
		    	  strMonster = s.next();
		    	  
		    	  if (!strMonster.equals("None"))
		    	  {
		    		  getMonsterInfo(file, room, strMonster);
		    	  }
		    	  
		    	  room.setStrLookArray(lookArray);
		    	  s.close();
		    	  
		    	  //Checks if it is end of the game.
		    	  if ((room.getRoomEast().equals("None") && room.getRoomNorth().equals("None") && room.getRoomSouth().equals("None") && room.getRoomWest().equals("None")))
		  			{
		  				labelText = "Game Over";
		  				window.setLabel(labelText);
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
	
	public void getLookInfo(String file, Room room, String strItem)
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
					Constants.WINDOW.displayLook(room.getStrLookDescription());
		    		s.close();
		    		return;
				}
				
				}
				
			s.close();
			}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	public void getMonsterInfo(String file, Room room, String strMonster)
	{
		String strMonsterRoom = "<" + room.getCurrentRoom() + "|" + strMonster + ">";
		String tempString = "";
		for (int k = 0; k < monster.length; k++)
		{
			if (monster[k] != null && monster[k].getName().equals(strMonster))
			{
				return;
			}
		}
		try{
			Scanner s = new Scanner(new FileReader(file));
			String strLine = "";
			
			while ((strLine = s.nextLine()) != null)
			{
				
				if (strLine.equals(strMonsterRoom))
				{
					k++;
					monster[k] = new Monster();
					monster[k].setMonsterInRoom(true);
		    		monster[k].setName(strMonster);
					boolean stop = false;
					int data = 0;
					s.next();
					data = s.nextInt();
					monster[k].setHealth(data);
					s.next();
					data = s.nextInt();
					monster[k].setDamage(data);
					
					while (!stop)
					{
						strLine = s.nextLine();
				    	String[] pieces = strLine.split("\\s+");
				   		for (int j = 0; j < pieces.length; j++)
				   		{
				   			if (pieces[j].equals("*"))
				   			{
			    				stop = true;
			    				break;
			    			}
			    			  
				    		else
				    		{
				    			tempString += pieces[j] + " ";
				    		}
				   		}
				    		
				   		tempString += "\n";
					}	
					
					monster[k].setAttackMessage(tempString);
		    		s.close();
		    		return;
				}
				
				}
				
			s.close();
			}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		    }
	}
	
	public static void getPlayerStats()
	{
		String tempString;
		int data;
		try
		{
			Scanner s = new Scanner(new FileReader(Constants.CHAR_FILE));
			s.next();
			tempString = s.next();
			Constants.PLAYER.setName(tempString);
			s.next();
			data = s.nextInt();
			Constants.PLAYER.setHealth(data);
			s.next();
			tempString = s.next();
			Constants.PLAYER.setWeapon(tempString);
			s.next();
			data = s.nextInt();
			Constants.PLAYER.setDamage(data);
			s.close();
		}catch (Exception e){
		      System.err.println("Error: " + e.getMessage());
		}
		
	}
	
	/**
	 * Start of program
	 * @param args
	 */
	public static void main(String[] args)
	{	
		getPlayerStats();
		
		try{
			Scanner s = new Scanner(new FileReader(Constants.FILE));
			s.next();
			Constants.ROOM.setCurrentRoom(s.next());
			Constants.GAME.getRoomInfo(Constants.FILE, Constants.ROOM, Constants.WINDOW);
			Constants.WINDOW.displayRoom(Constants.ROOM);
			s.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}
		
	}
	
}
