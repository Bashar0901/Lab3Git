import java.util.Scanner;
/**
 * @author Salman Amer
 * @author Bashar Bashir 
 * @version 6.0z Bulid 9000 Novmber 2
 */
public class TestThreadCheckArray {
	/** main to test
	 * @param args for main */
	public static void main(String[] args) {
		/** To check if there is sum of some numbers equal our num*/
		boolean myflag=false;
		/** To put + just from the second number*/
		boolean MySecFlag=true;
		try (Scanner input = new Scanner(System.in)) {
			Thread thread1, thread2;
			System.out.println("Enter array size");
			int num  = input.nextInt();
			int [] array = new int[num];
			System.out.println("Enter numbers for array");
			
			for (int index = 0; index < num; index++) 
				array[index] = input.nextInt();
			
			System.out.println("Enter number");
			num = input.nextInt();
			
			SharedData sd = new SharedData(array, num);
			
			thread1 = new Thread(new ThreadCheckArray(sd), "thread1");
			thread2 = new Thread(new ThreadCheckArray(sd), "thread2");
			thread1.start();
			thread2.start();
			/**@throws InterruptedException */
			try 
			{
				thread1.join();
				thread2.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			/** printing Sorry if there is no numbers */
			if (!sd.getFlag())
			{
				System.out.println("Sorry");
				return;
			}
			/**Printing the arrays */
			System.out.println("Solution for b : " + sd.getB() + ",n = " + sd.getArray().length);
			System.out.print("I:    ");
			for(int index = 0; index < sd.getArray().length ; index++)
				System.out.print(index + "    ");
			System.out.println();
			System.out.print("A:    ");
			for (int index : sd.getArray())
			{
				System.out.print(index);
				int counter = 5;
				while (true)
				{
					index = index / 10;
					counter--;
					if (index == 0)
						break;
				}
				for (int i = 0; i < counter; i++)
					System.out.print(" ");
			}

			System.out.println();
			System.out.print("C:    ");
			for (boolean index : sd.getWinArray())
			{
				if (index)
					System.out.print("1    ");
				else
					System.out.print("0    ");	
			}
			System.out.println();
			System.out.print("        ");
			for(int i=0;i<sd.getArray().length;i++) {
				if(sd.getWinArray()[i]==true) 
					myflag=true;
			}
			if(myflag==true) {
				for(int i=0; i<sd.getArray().length;i++) {
					if(sd.getWinArray()[i]==true) {
						if(MySecFlag==false)
							System.out.print("+");
						System.out.print(sd.getArray()[i]);
						MySecFlag=false;
					}
					if(i!=sd.getArray().length-1)
					System.out.print("    ");
				}
				System.out.print("=  " + num);	
			}
			
		}
	}
}