import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class ServerClass 
{
	public static void main(String[] args) throws IOException
	{
		//Array list of CellPhoneClass which keeps track of people who register their phone
		//the string that is entered as their name is also used as the key for the hashmap
		ArrayList<CellPhoneClass> cellList = new ArrayList<CellPhoneClass>();
		HashMap<String, ItemClass> map = new HashMap<String, ItemClass>();
		Scanner in = new Scanner(System.in);
		int answer = 0;
		boolean repeat = true;
		boolean nextStep = intro(cellList, in);
		if(nextStep == true)
		{
			System.out.println("Please register your phone with the app.");
			System.out.print("Enter your phone number: ");
			long cellNum = in.nextLong();
			System.out.print("\nEnter your name: ");
			in.nextLine();
			String name = in.nextLine();
			CellPhoneClass cell = new CellPhoneClass(cellNum, name);
			cellList.add(cell);
		}
		while(repeat)
		{
			System.out.println("What would you like to do? \n\n1. Register a new item.");
			System.out.println("2. Report an item lost.");
			System.out.println("3. Report an item found.");
			System.out.println("4. Exit");
			System.out.print("\nEnter the number of what you would like to do: ");
			if(in.hasNextInt() == true)
			{
				answer = in.nextInt();
			}
			else
			{
				answer = 5;
				//in.nextLine();
			}
			if(answer == 1)
			{
				readItem(map, in);
			}
			else if(answer == 2)
			{
				String lost = "lost";
				reportLost(lost, map);
				for(String key : map.keySet())
				{
					System.out.print(key + " " + map.get(key));
				}
				break;
			}
			else if(answer == 3)
			{
				String found = "found";
				boolean valid = false;
				int x = 0;
				int y = 0;
				
				System.out.println("Enter new coordinates for the item: ");
				while(valid == false)
				{	
					if(in.hasNextInt() == true)
					{
						x = in.nextInt();
						valid = true;
						if(in.hasNextInt() == true)
						{
							y = in.nextInt();
							valid = true;
						}
					}
					else
					{
						System.out.println("Invalid input.");
					}
				}
				reportFound(found, x, y, map);
				System.out.println("Thank you! Owner notified of new location");
				break;
			}
			else if(answer == 4)
			{
				System.out.println("Good bye");
				break;
			}
			else
			{
				System.out.println("\nInvalid input.\n");
				in.reset();
				break;
			}
		}
				
	}
	//this method gets run first and lets the user login or register if they are new
	//calls verify method if they claim to not be a new user and checks against ArrayList of cellphones
	//if they are a new user, then they can register their phone and it will be added to the cellList
	public static boolean intro(ArrayList<CellPhoneClass> cellList, Scanner in)
	{
		boolean register = false;
		boolean user = false;
		while(user == false)
		{
			System.out.println("Are you a new user? Enter Y or N.");
			String response = in.next();
			if(response.equalsIgnoreCase("Y"))
			{
				register = true;
				user = true;
			}
			else if(response.equalsIgnoreCase("N"))
			{
				System.out.print("Enter your phone number: ");
				in.nextLine();
				long cellNum = in.nextLong();
				System.out.print("\nEnter your name: ");
				in.nextLine();
				String name = in.nextLine();
				user = verify(cellList, cellNum, name);
				if(user == false)
				{
					System.out.println("User not found.");
				}
				register = false;
			}
			else
			{
				System.out.println("Invalid input.");
				register = false;
			}
		}
		return register;
	}
	//verify method checks the name and number entered by the user against the celllist
	//it returns true if they have registered before or false if they have not
	//if false then it will output an error and take them through the process again
	public static boolean verify(ArrayList<CellPhoneClass> cellList, long cellNum, String name)
	{
		boolean user = false;
		Iterator<CellPhoneClass>cellIterator = cellList.iterator();
		while(user == false)
		{
			while(cellIterator.hasNext())
			{
				if(name.equals(cellIterator.next().getName()))
				{
					if(cellNum == cellIterator.next().getID())
					{
						user = true;
						break;
					}
				}
			}
		}
		return user;
	}
	//I made these methods to generate some random x and y coordinates between -150 and 150
	public static int randomX()
	{
		Random rand = new Random();
		int x = rand.nextInt(150-(-150))-150;
		return x;
	}
	public static int randomY()
	{
		Random rand = new Random();
		int y = rand.nextInt(150-(-150))-150;
		return y;
	}
	//readItem method gets info from the user and calls registerItem from cellPhoneClass
	
	//registerItem writes the information to the file --> itemRegister.txt
	
	//then readItem reads that information and creates an instance of the ItemClass
	
	//the instance of ItemClass is then added to the hashmap where the name of the person
	//who registered the item is used as the key
	public static void readItem(HashMap<String, ItemClass> map, Scanner in) throws IOException
	{
		String status = "";
		boolean repeat = true;
		System.out.print("To register a new item, enter an Item ID: ");
		int itemID = in.nextInt();
		System.out.print("\nNext, enter the owner's name: ");
		in.nextLine();
		String owner = in.nextLine();
		int cellCoordinateX = randomX();
		int cellCoordinateY = randomY();
		status = "found";
		CellPhoneClass.registerItem(Integer.toString(itemID), owner, status);
		try
		{
			BufferedReader inputStream = new BufferedReader(new FileReader("itemRegister.txt"));
			String line = "";
			int i = 0;
			while((line = inputStream.readLine()) != null && i < 3)
			{
				if(i == 0)
				{
					itemID = Integer.parseInt(line);
					///*test*/System.out.println(itemID);
				}
				else if(i == 1)
				{
					owner = line;
					///*test*/System.out.println(owner);
				}
				else if(i == 2)
				{
					status = line;
					///*test*/System.out.println(status);
				}
				i++;
			}
			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("The file you're attempting to read from does not exist.");
		}
		catch(IOException e)
		{
			System.out.println("Error reading from the file");
		}
		ItemClass item = new ItemClass(itemID, owner, cellCoordinateX, cellCoordinateY, status);
		map.put(owner, item);
		
		PrintWriter outputStream = null;
		try
		{
			outputStream = new PrintWriter (new FileOutputStream("itemList.txt", true));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error opening file itemList.txt"); // create text file
			System.exit(0);
		}
		
		outputStream.print(map.get(owner.toString()));
		outputStream.println();
		outputStream.println();
		
		outputStream.close();
	}		
		public static void reportLost(String lost, HashMap<String, ItemClass> map) throws IOException
		{
			CellPhoneClass.reportLostItem();
			try
			{
				BufferedReader inputStream = new BufferedReader(new FileReader("itemLoss.txt"));
				String itemID = inputStream.readLine();
				for (String key: map.keySet())
				{	
					if (map.get(key).CellID() == Integer.parseInt(itemID))
					{
						map.get(key).UpdateStatus(lost);
					}
				}
				inputStream.close();
		
			}
			catch(FileNotFoundException e)
			{
				System.out.println("The file you're attempting to read from does not exist.");
			}
			catch(IOException e)
			{
				System.out.println("Error reading from the file");
			}
		}
		
		public static void reportFound(String found, int x, int y, HashMap<String, ItemClass> map) throws IOException
		{
			CellPhoneClass.reportFoundItem();
			found = "found";
			try
			{
				BufferedReader inputStream = new BufferedReader(new FileReader("itemFound.txt"));
				String itemID = inputStream.readLine();
				for (String key: map.keySet())
				{	
					if (map.get(key).CellID() == Integer.parseInt(itemID))
					{
						map.get(key).UpdateStatus(found);
					}
				}
				inputStream.close();
		
			}
			catch(FileNotFoundException e)
			{
				System.out.println("The file you're attempting to read from does not exist.");
			}
			catch(IOException e)
			{
				System.out.println("Error reading from the file");
			}
		
		}
		
		
		
		
		/*test for(String key : map.keySet())
		{
			System.out.print(key + " " + map.get(key));
		}*/
			
		
		
		
	}

