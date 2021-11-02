/**
 * @author Salman Amer
 * @author Bashar Bashir 
 * @version 6.0z Bulid 9000 Novmber 2
 */
public class SharedData 
{
	/**Array saves the number that we choose them*/
	private int [] array;
	/** Boolean array to know the index*/
	private boolean [] winArray;
	/** To know if there is some number that sum of them equal b*/
	private boolean flag;
	/** result */
	private final int b;
	
	/** Method the build our class
	 * @param array: our numbers 
	 * @param b: result*/
	public SharedData(int[] array, int b) {
		
		this.array = array;
		this.b = b;
	}
	/**@return winArray*/
	public boolean[] getWinArray() 
	{
		return winArray;
	}
	/**Set winarray
	 * @param winArray that own the indexs*/
	public void setWinArray(boolean [] winArray) 
	{
		this.winArray = winArray;
	}
	/**@return array*/
	public int[] getArray() 
	{
		return array;
	}
	/**@return b*/
	public int getB() 
	{
		return b;
	}
	/**@return flag*/
	public boolean getFlag() 
	{
		return flag;
	}
	/**set Flag
	 * @param flag: Gettig flag to set*/
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
