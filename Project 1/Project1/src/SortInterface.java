public interface SortInterface
{
	int[] iterativeSort(int[] array) throws UnsortedException;
    int[] recursiveSort(int[] array) throws UnsortedException;

    int getCount();
    long getTime();
}
