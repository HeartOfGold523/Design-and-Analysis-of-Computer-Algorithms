public class BubbleSort implements SortInterface
{
	int count = 0;
    long timeStart = 0;
    long timeEnd = 0;
    
    //critical operations we are counting in bubble sort is:
    // 1) checking to see if 2 indices in the array must be swaped
    // 2) actually making the swap if needed
    
    //Utility function to swap values at 2 indices in array
    public void swap(int[] arr, int i, int j)
    {
    	//increment count to count swap
    	count++;
    	int temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
    }
    
    /* * *
     * Iterative version of Bubble Sort
     * * */
    public int[] iterativeSort(int[] a) throws UnsortedException
    {
    	timeStart = System.nanoTime();
    	
    	int n = a.length;
    	for (int i = 0; i < n - 1; i++)
    	{
    		//last i items are already sorted, so inner loop can avoid looking at the last i items
    		for (int j = 0; j < n - 1 - i; j++)
    		{
    			count++;
    			if (a[j] > a[j+1])
                { 
                    swap(a, j, j+1);
                }
    		}
    	}
    	
    	timeEnd = System.nanoTime();
    	checkSortedArray(a);
    	return a;
    }
    
    
    /* * *
     * Recursive version of Bubble Sort
     * * */
    private void recursiveSort(int[] a, int n)
    {
    	
    	for (int i = 0; i < n - 1; i++)
    	{
    		count++;
    		if(a[i] > a[i+1])
    		{
    			swap(a, i, i+1);
    		}
    	}
    	
    	if(n - 1 > 1)
    	{
    		recursiveSort(a, n - 1);
    	}
    }
    
    public int[] recursiveSort(int[] a) throws UnsortedException
    {
    	timeStart = System.nanoTime();
    	
    	int n = a.length;
    	recursiveSort(a, n);
    	
    	timeEnd = System.nanoTime();
    	return a;
    }
    
    
    private void checkSortedArray(int[] list) throws UnsortedException
    {
        for (int i = 0; i < list.length - 1; i++)
        {
            if (list[i] > list[i + 1])
            {
                for (int j = 0; i < list.length - 1; j++)
                {
                    System.out.println(" " + list[j]);
                }
                throw new UnsortedException("The array was not sorted correctly: \n" +
                        list[i] + " at index " + i + " and " + list[i + 1] + " at index " + (i + 1));
            }
        }
    }
    
    public int getCount()
    {
        int result = count;
        count = 0;
        return result;
    }

    public long getTime()
    {
        long time = timeEnd - timeStart;
        timeEnd = 0;
        timeStart = 0;
        return time;
    }
}
