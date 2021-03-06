/**
 * @author Salman Amer
 * @author Bashar Bashir 
 * @version 6.0z Bulid 9000 Novmber 2
 */
public class ThreadCheckArray implements Runnable 
{
	/** To check if there is sum of some numbers in the array that equal b */
	private boolean flag;
	/** boolean array to decide which number we choose*/
	private boolean [] winArray;
	/** SharedData class*/
	SharedData sd;
	/** Array numbers that we choose*/
	int[] array;
	/**The number we need to reach*/
	int b;
	/** A method that build our class
	 * @param sd: is a SharedData class that we put in the method to build our class */
	public ThreadCheckArray(SharedData sd) 
	{
		this.sd = sd;	
		synchronized (sd) 
		{
			array = sd.getArray();
			b = sd.getB();
		}		
		winArray = new boolean[array.length];
	}
	/** In the first we check if n==1
	 * 	The method finds the numbers we want to sum
	 * @param n: array lenght
	 * @param b: the result we want to reach*/
	void rec(int n, int b)
	{
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		if (n == 1)
		{
			if(b == 0 || b == array[n-1])
			{
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}			
			}
			if (b == array[n-1])
				winArray[n-1] = true;
			return;
		}
		
		rec(n-1, b - array[n-1]);
		if (flag)
			winArray[n-1] = true;
		synchronized (sd) 
		{
			if (sd.getFlag())
				return;
		}	
		rec(n-1, b);
	}
	/**Method that run the thread*/
	public void run() {
		if (array.length != 1)
			if (Thread.currentThread().getName().equals("thread1"))
				rec(array.length-1, b - array[array.length - 1]);
			else 
				rec(array.length-1, b);
		if (array.length == 1)
			if (b == array[0] && !flag)
			{
				winArray[0] = true;
				flag = true;
				synchronized (sd) 
				{
					sd.setFlag(true);
				}
			}
		if (flag)
		{
			if (Thread.currentThread().getName().equals("thread1"))
				winArray[array.length - 1] = true;
			synchronized (sd) 
			{
				sd.setWinArray(winArray);
			}	
		}
	}
}
