import java.io.*;

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
	
	public void updateCoordinate(int x, int y)
	{
		cellCoordinateX = x;
		cellCoordinateY = y;
	}
}
