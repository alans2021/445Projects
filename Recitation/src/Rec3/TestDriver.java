package Rec3;

public class TestDriver <T>{
	
	public static <T> void main(String[]args)
	{
		int length = Integer.parseInt(args[0]);
		@SuppressWarnings("unchecked")
		Integer[] iterative = new Integer[length];
		Integer[] recursive = new Integer[length];
		for(int i = 0; i < length; i++)
		{
			Integer num = (int) (10000 * Math.random());
			iterative[i] = num;
			recursive[i] = num;
		}
		long startTime = System.nanoTime();
		Insertion.insertionSort(iterative, length);
		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
		
		startTime = System.nanoTime();
		Insertion.insertionSort2(recursive, length);
		endTime = System.nanoTime();
		System.out.println(endTime - startTime);
		
		
	}

}
