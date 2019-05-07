public class ItemClass
{
	public static final String toString = null;
	private int CellID;
	private String cellName;
	private int cellCoordinateX;
	private int cellCoordinateY;
	
		public ItemClass(int CellID, String cellName, int cellCoordinateX, int cellCoordinateY)
			{

			this.CellID = CellID;
			this.cellName = cellName;
			this.cellCoordinateX = cellCoordinateX;
			this.cellCoordinateY = cellCoordinateY;


			}
		public int CellID()
		{
			return CellID;
		}

		public String cellName()
		{
			return cellName;
		}
		
		public int cellCoordinateX()
		{
			return cellCoordinateX;
		}
		
		public int cellCoordinateY()
		{
			return cellCoordinateY;
		}
		public static char[] toString(String cellName2) {
			// TODO Auto-generated method stub
			return null;
		}
		public static char[] toString(int itemID) {
			// TODO Auto-generated method stub
			return null;
		}
}

