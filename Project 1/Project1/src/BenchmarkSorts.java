import java.util.Random;
import java.util.stream.IntStream;

public class BenchmarkSorts
{
	private final int NUMBER_OF_RUNS = 50;
	
    private int[] array;
    private int[] sortedIterativeArray;
    private int[] sortedRecursiveArray;
    private int iterativeCount = 0;
    private int recursiveCount = 0;
    private int iterativeIndex = 0;
    private int recursiveIndex = 0;
    private long iterativeTime, recursiveTime;
    private int [] iterativeCountLog = new int[NUMBER_OF_RUNS];
    private int [] recursiveCountLog = new int[NUMBER_OF_RUNS];
    private long [] iterativeTimeLog = new long[NUMBER_OF_RUNS];
    private long []recursiveTimeLog = new long[NUMBER_OF_RUNS];

    private BubbleSort bubbleSort = new BubbleSort();

    public BenchmarkSorts(int[] sizes)
    {
        // Creates benchmarks based on the input array size
        IntStream.range(0, sizes.length).forEach(i -> new BenchmarkSorts(sizes[i]));
    }

    private BenchmarkSorts(int n) {

        // Outer loop 50 times (NUMBER_OF_RUNS)
        IntStream.range(0, NUMBER_OF_RUNS).forEach(i -> {
            array = new int[n];
            // Inner loop based on the array size (n)
            IntStream.range(0, n).forEach(j -> {
                Random random = new Random();
                array[j] = random.nextInt(1000);
            });

            // Runs the sort and produces output if an UnsortedException is found
            try {
                runSorts();
            } catch (UnsortedException e) {
                System.out.println(e.getMessage());
            }
        });
        displayReport(n);
    }


    private void runSorts() throws UnsortedException {

        // Runs iterative sort
        sortedIterativeArray = bubbleSort.iterativeSort(array);
        int returnCount = bubbleSort.getCount();
        long returnTime = bubbleSort.getTime();
        iterativeCount = iterativeCount + returnCount;
        iterativeTime = iterativeTime + returnTime;
        iterativeCountLog[iterativeIndex] = returnCount;
        iterativeTimeLog[iterativeIndex] = returnTime;
        iterativeIndex++;

        // Runs recursive sort
        sortedRecursiveArray = bubbleSort.recursiveSort(array);
        returnCount = bubbleSort.getCount();
        returnTime = bubbleSort.getTime();
        recursiveCount = recursiveCount + returnCount;
        recursiveTime = recursiveTime + returnTime;
        recursiveCountLog[recursiveIndex] = recursiveCount;
        recursiveTimeLog[recursiveIndex] = recursiveTime;
        recursiveIndex++;
    }

    private void displayReport(int arraySize) {

        // Sets local variables
        double iterativeAverageCount = 0;
        double iterativeAverageTime = 0;
        double recursiveAverageCount = 0;
        double recursiveAverageTime = 0;
        double iterativeVarianceCount = 0;
        double iterativeVarianceTime = 0;
        double recursiveVarianceCount = 0;
        double recursiveVarianceTime = 0;
        double iterativeSDCount = 0;
        double iterativeSDTime = 0;
        double recursiveSDCount = 0;
        double recursiveSDTime = 0;

        // Calculates averages
        for (int i = 0; i < NUMBER_OF_RUNS; i++) {
            iterativeAverageCount += iterativeCountLog[i];
            iterativeAverageTime += iterativeTimeLog[i];
            recursiveAverageCount += recursiveCountLog[i];
            recursiveAverageTime += recursiveTimeLog[i];
        }

        iterativeAverageCount = iterativeAverageCount / arraySize;
        iterativeAverageTime = iterativeAverageTime / arraySize;
        recursiveAverageCount = recursiveAverageCount / arraySize;
        recursiveAverageTime = recursiveAverageTime / arraySize;

        // Calculates standard deviations
        for (int i = 0; i < NUMBER_OF_RUNS; i++) {
            iterativeVarianceCount += Math.pow(iterativeAverageCount - iterativeCountLog[i], 2);
            iterativeVarianceTime += Math.pow(iterativeAverageTime - iterativeTimeLog[i], 2);
            recursiveVarianceCount += Math.pow(recursiveAverageCount - recursiveCountLog[i], 2);
            recursiveVarianceTime += Math.pow(recursiveAverageTime - recursiveTimeLog[i], 2);
        }

        iterativeVarianceCount = iterativeVarianceCount / arraySize;
        iterativeVarianceTime = iterativeVarianceTime / arraySize;
        recursiveVarianceCount = recursiveVarianceCount / arraySize;
        recursiveVarianceTime = recursiveVarianceTime / arraySize;

        iterativeSDCount = Math.sqrt(iterativeVarianceCount);
        iterativeSDTime = Math.sqrt(iterativeVarianceTime);
        recursiveSDCount = Math.sqrt(recursiveVarianceCount);
        recursiveSDTime = Math.sqrt(recursiveVarianceTime);


        // Produces output
        System.out.println("Data Set Size (n): " + arraySize +
                    "\n\tIterative Selection Sort Results: \t\t\t\t\tRecursive Selection Sort Results:" +
                    "\n\tAverage Critical Operation Count: " + Math.round(iterativeAverageCount) +
                        "\t\t\tAverage Critical Operation Count: " + Math.round(recursiveAverageCount) +
                    "\n\tStandard Deviation of Count: " + Math.round(iterativeSDCount) +
                        "\t\t\t\t\tStandard Deviation of Count: " + Math.round(recursiveSDCount) +
                    "\n\tAverage Execution Time: " + Math.round(iterativeAverageTime) +
                        "\t\t\t\t\t\tAverage Execution Time: " + Math.round(recursiveAverageTime) +
                    "\n\tStandard Deviation of Time: " + Math.round(iterativeSDTime) +
                        "\t\t\t\t\t\tStandard Deviation of Time: " + Math.round(recursiveSDTime));
    }
}
