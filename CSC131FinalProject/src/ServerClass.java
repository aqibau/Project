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
		System.out.println("Please register your phone with the app.");
		System.out.print("Enter your phone number: ");
		long cellnum = in.nextLong();
		System.out.println("\nEnter your name: ");
		in.nextLine();
		String name = in.nextLine();
		CellPhoneClass cell = new CellPhoneClass(cellnum, name);
		cellList.add(cell);
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
				}
				else if(i == 1)
				{
					owner = line;
				}
				else if(i == 2)
				{
					status = line;
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
		
		
		
	}
}
