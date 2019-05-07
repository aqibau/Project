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
		System.out.println("Would you like to register a new item? Y or N?");
		String answer = in.next();
		while(repeat)
		if(answer.equalsIgnoreCase("y"))
		{
			readItem(map, in);
			break;
		}
		else if(answer.equalsIgnoreCase("N"))
		{
			System.out.println("Good bye");
			break;
		}
		else
		{
			System.out.println("Invalid input. Would you like to register a new item? Y or N?");
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
			System.out.println("Are you a new user?");
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
		while(user == false && cellList.size() != 0)
		{
			for(int i = 0; i < cellList.size(); i++)
			{
				if(name.equals(cellList.get(i).getName()))
				{
					if(cellNum == cellList.get(i).getID())
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
	public static void readItem(HashMap<String, ItemClass> map, Scanner in) throws IOException
	{
		String status = "";
		boolean repeat = true;
		System.out.println("To register a new item, enter an Item ID: ");
		int itemID = in.nextInt();
		System.out.println("Next, enter the owner's name: ");
		in.nextLine();
		String owner = in.nextLine();
		System.out.println("Is this item lost or found? Type L for lost or F for found.");
		String check = in.next();
		while(repeat)
		{
			if(check.equalsIgnoreCase("L"))
			{
				status = "lost";
				break;
			}
			else if(check.equalsIgnoreCase("F"))
			{
				status = "found";
				break;
			}
			else
			{
				System.out.println("That input is invalid. Type L for lost or F for found.");
			}
		}
		int cellCoordinateX = randomX();
		int cellCoordinateY = randomY();
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
					/*test*/System.out.println(itemID);
				}
				else if(i == 1)
				{
					owner = line;
					/*test*/System.out.println(owner);
				}
				else if(i == 2)
				{
					status = line;
					/*test*/System.out.println(status);
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
		ItemClass item = new ItemClass(itemID, owner, cellCoordinateX, cellCoordinateY);
		map.put(owner, item);
		/*test*/for(String key : map.keySet())
		{
			System.out.print(key + " " + map.get(key));
		}
			
		
		
		
	}
}
