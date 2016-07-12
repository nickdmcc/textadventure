import java.util.Scanner;

public class Main{
	
	public static final String CHAR_FILE = "PlayerStats";

	public String labelText = "";
	public Monster[] monster = new Monster[Constants.NUM_OF_MONSTERS];
	int k = 0;
	
	/**
	 * Updates label component if it is end game or if there is a place you cannot move to.
	 * Else it updates the current room you are in and displays room description.
	 * @param room
	 * @param strRoom
	 */
	public void move(Room room, String strRoom, GameWindow window, Player player)
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
		
		getRoomInfo(Constants.FILE, room, window);
		window.displayRoom(room);
		window.setMonster(monster[k]);
		window.checkMonster(monster[k], player);
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
		    Scanner s = new Scanner(getClass().getResourceAsStream(file));
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
	
	public void getLookInfo(String file, Room room, String strItem, GameWindow window)
	{
		String strLookRoom = "<" + room.getCurrentRoom() + "|" + strItem + ">";
		String tempString = "";
		try{
			Scanner s = new Scanner(getClass().getResourceAsStream(file));
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
					window.displayLook(room.getStrLookDescription());
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
			Scanner s = new Scanner(getClass().getResourceAsStream(file));
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
	
	public void setClass(Player player, GameWindow window, String className)
	{
		String strClassName = "<" + className + ">";
		String tempString;
		int data;
		try
		{
			Scanner s = new Scanner(getClass().getResourceAsStream(CHAR_FILE));
			String strLine = "";
			while ((strLine = s.nextLine()) != null)
			{
				if (strLine.equals(strClassName))
				{
					player.setClassName(className);
					s.next();
					data = s.nextInt();
					player.setHealth(data);
					player.setMaxHealth(data);
					s.next();
					tempString = s.next();
					player.setWeapon(tempString);
					s.next();
					data = s.nextInt();
					player.setDamage(data);
					s.next();
					data = s.nextInt();
					player.setRunChance(data);
					player.setEnergy(Constants.START_ENERGY);
					player.setMaxEnergy(Constants.START_ENERGY);
					s.close();
					return;
				}
				
			}
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
		Main game = new Main();
		GameWindow window = new GameWindow();
		Room room = new Room();
		Player player = new Player();
		window.setPlayerName();
		window.setRoom(room);
		window.setGameWindow(window);
		window.setPlayer(player);
		window.setMain(game);
		try{
			Scanner s = new Scanner(Main.class.getResourceAsStream(Constants.FILE));
			s.next();
			room.setCurrentRoom(s.next());
			game.getRoomInfo(Constants.FILE, room, window);
			window.displayRoom(room);
			s.close();
		}catch (Exception e){//Catch exception if any
		      System.err.println("Error: " + e.getMessage());
		}
		
	}
	
}
