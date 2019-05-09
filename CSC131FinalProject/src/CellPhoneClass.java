import java.io.*;
import java.util.Scanner;

public class CellPhoneClass {
	private /*int*/long cellID;
	private String cellName;
	private int cellCoordinateX;
	private int cellCoordinateY;
	
	public CellPhoneClass(/*int*/long id, String name)
	{
		cellID = id;
		cellName = name;
	}
	public static void registerItem(String itemID, String owner, String status) throws IOException	
	{
		FileWriter file = new FileWriter("itemRegister.txt");
		BufferedWriter writer = new BufferedWriter(file);
		writer.write(itemID + "\n");
		writer.write(owner + "\n");
		writer.write(status);
		writer.close();
		file.close();
	}
	public /*int*/long getID ()
	{
		return cellID;
	}
	public String getName()
	{
		return cellName;
	}
	public int getCoordinateX()
	{
		return cellCoordinateX;
	}
	public int getCoordinateY()
	{
		return cellCoordinateY;
	}
	public void updateCoordinate(int x, int y)
	{
		cellCoordinateX = x;
		cellCoordinateY = y;
	}
	public static void reportLostItem() throws IOException
	{
		FileWriter file = new FileWriter("itemLoss.txt");
		BufferedWriter writer = new BufferedWriter(file);
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the lost item ID:");
		int i = 0;
		while(input.hasNextInt() && i < 1)
		{
			if (input.hasNextInt())
			{	
				writer.write(Integer.toString(input.nextInt()));
				i++;
				//input.nextLine();
				break;
			}
			else
			{
				System.out.print("invalid ID!!!");
				break;
			}
		}
		input.close();
		writer.close();
		file.close();
	}
	public static void reportFoundItem() throws IOException
	{
		FileWriter file = new FileWriter("itemFound.txt");
		BufferedWriter writer = new BufferedWriter(file);
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the found item ID:");
		while(input.hasNext())
		{
			if (input.hasNextInt())
			{	
				writer.write(Integer.toString(input.nextInt()));
				break;
			}
			else
			{
				System.out.print("invalid ID!!!");
				break;
			}
		}
		input.close();
		writer.close();
		file.close();
	}
}
