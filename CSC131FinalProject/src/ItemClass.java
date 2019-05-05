public class ItemClass
{
	private int CellID;
	private String CellName;
	private int cellCoordinateX;
	private int cellCoordinateY;
	
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

		public String CellName()
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
}

