public class ItemClass
{
	private int CellID;
	private String CellName;
	private int cellCoordinateX;
	private int cellCoordinateY;
	
	private String status;
	
		public ItemClass(int CellID, String CellName, int cellCoordinateX, int cellCoordinateY)
			{

			this.CellID = CellID;
			this.CellName = CellName;
			this.cellCoordinateX = cellCoordinateX;
			this.cellCoordinateY = cellCoordinateY;


			}
		public int CellID()
		{
			return CellID;
		}

		public String cellName()
		{
			return CellName;
		}
		
		public int cellCoordinateX()
		{
			return cellCoordinateX;
		}
		
		public int cellCoordinateY()
		{
			return cellCoordinateY;
		}
		
		public String toString()
		{
			String info = CellID +"\n"+CellName+" \n" +cellCoordinateX+ "\n"+cellCoordinateY+ "\n"+ status;
			return info;
		}
		
		public void UpdateStatus(String status)
		{
			this.status = status;
			
		}
}
