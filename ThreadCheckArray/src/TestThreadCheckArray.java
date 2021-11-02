import java.util.Scanner;

public class TestThreadCheckArray {
	public static void main(String[] args) {
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
			try 
			{
				thread1.join();
				thread2.join();
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			if (!sd.getFlag())
			{
				System.out.println("Sorry");
				return;
			}
			int []arr=new int[sd.getArray().length];
			int k=0;
			for (int index : sd.getArray())	{
				arr[k++]=index;
			}
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
			int index1=0;
			
			boolean flag=false;
			for (boolean index : sd.getWinArray())
			{
				if (index)
					{if(flag)System.out.print("+");else {flag=true;}
						System.out.print(arr[index1]);
						int counter = 5;
						while (true)
						{
							arr[index1] = arr[index1] / 10;
							counter--;
							if (arr[index1] == 0)
								break;
						}
						for (int i = 0; i < counter; i++)
							System.out.print(" ");
					}
				else
					System.out.print("    ");	
				index1++;
			}
			System.out.print("=  "+sd.getB());
		}
	}

}
