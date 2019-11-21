public class SortMain
{
	public static void main(String[] args) throws Exception
	{
		int[] sizes = {100, 200, 400, 800, 1000, 2000, 4000, 6000, 8000, 10000};
        //int[] sizes = {50, 100, 500, 1000, 5000, 10000, 50000, 100000, 500000, 1000000};
        new BenchmarkSorts(sizes);
    }
}
