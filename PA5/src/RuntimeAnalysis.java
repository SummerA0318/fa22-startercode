/*
 * NAME: Yubing Lin
 * PID:  A16994291
 */
 
import java.util.ArrayList;

/**
 * A class to see and compare the sorting time of different sorting method
 */
public class RuntimeAnalysis {

    private static final int NUM_DATA = 100000;
    private static final int NUM_RUN = 10;
    private static final int NUM_TEST = 6;
    private static final int MIN = 0;
    private static final int MAX = 100000;
    private static final int[] QuickSortCutoffValues = {2,4,8,16,32,64,128};

    /**
     * Returns an ArrayList of random numbers
     *
     * @param size the number of random numbers wanted
     * @param min the min value for random number
     * @param max the max value for random number
     * @return an ArrayList of random numbers
     */
    public static ArrayList<Double> randomNumbers(int size, int min, int max) {

        ArrayList<Double> randNums = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            randNums.add((Math.random() * ((max - min) + 1)) + min);
        }
        return randNums;
    }

    /**
     * Returns a deep copy of the input array list.
     *
     * @param data list to copy
     * @return a deep copy of the list
     */
    private static ArrayList<Double> deepCopyArrayList(ArrayList<Double> data) {
        ArrayList<Double> result = new ArrayList<>();
        for (Double d : data) {
            result.add(d);
        }
        return result;
    }

    /**
     * Times the insertion sort.
     * @param data list of data to sort
     * @param numRun the number of trials to run
     */
    public static void timeInsertionSort(ArrayList<Double> data, int numRun) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts<Double> sorts = new Sorts<>();
        ArrayList<Double> temp = deepCopyArrayList(data);
        for (int i = 0; i < numRun; i++) {
            data = deepCopyArrayList(temp);
            startTime = System.currentTimeMillis();
            sorts.InsertionSort(data, 0, data.size() - 1);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking insertion sort: ");
        System.out.println("Number of data to sort: " + data.size());
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    /**
     * Times the merge sort.
     * @param data list of data to sort
     * @param numRun the number of trials to run
     */
    public static void timeMergeSort(ArrayList<Double> data, int numRun) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts<Double> sorts = new Sorts<>();
        ArrayList<Double> temp = deepCopyArrayList(data);
        for (int i = 0; i < numRun; i++) {
            data = deepCopyArrayList(temp);
            startTime = System.currentTimeMillis();
            sorts.MergeSort(data, 0, data.size() - 1);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking merge sort: ");
        System.out.println("Number of data to sort: " + data.size());
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    /**
     * Times the Quicksort.
     * @param data list of data to sort
     * @param numRun the number of trials to run
     */
    public static void timeQuickSort(ArrayList<Double> data, int numRun) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts<Double> sorts = new Sorts<>();
        ArrayList<Double> temp = deepCopyArrayList(data);
        for (int i = 0; i < numRun; i++) {
            data = deepCopyArrayList(temp);
            startTime = System.currentTimeMillis();
            sorts.QuickSort(data, 0, data.size() - 1);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking quick sort: ");
        System.out.println("Number of data to sort: " + data.size());
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    /**
     * Times the modified Quicksort.
     * @param data list of data to sort
     * @param numRun the number of trials to run
     * @param param the extra parameter the sorting algorithm takes (cutoff)
     */
    public static void timeModifiedQuickSort(ArrayList<Double> data, int numRun, int param) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts<Double> sorts = new Sorts<>();
        ArrayList<Double> temp = deepCopyArrayList(data);

        for (int i = 0; i < numRun; i++) {
            data = deepCopyArrayList(temp);
            startTime = System.currentTimeMillis();
            sorts.Modified_QuickSort(data, 0, data.size() - 1, param);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Modified QuickSort Cutoff Value: " + param);
        System.out.println("Benchmarking quick sort: ");
        System.out.println("Number of data to sort: " + data.size());
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    /**
     * Times the cocktail sort.
     * @param data list of data to sort
     * @param numRun the number of trials to run
     */
    public static void timeCocktailSort(ArrayList<Double> data, int numRun) {
        long startTime = 0, endTime = 0, totalTime = 0;
        Sorts<Double> sorts = new Sorts<>();
        ArrayList<Double> temp = deepCopyArrayList(data);
        for (int i = 0; i < numRun; i++) {
            data = deepCopyArrayList(temp);
            startTime = System.currentTimeMillis();
            sorts.cocktailSort(data, 0, data.size() - 1);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking cocktail sort: ");
        System.out.println("Number of data to sort: " + data.size());
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    /**
     * Times the bucket sort.
     * @param data list of data to sort
     * @param numRun the number of trials to run
     */
    public static void timeBucketSort(ArrayList<Double> data, int numRun) {
        long startTime, endTime, totalTime = 0;
        Sorts<Double> sorts = new Sorts<>();
        ArrayList<Double> temp = deepCopyArrayList(data);
        for (int i = 0; i < numRun; i++) {
            ArrayList<Double> sortedArray;
            data = deepCopyArrayList(temp);
            startTime = System.currentTimeMillis();
            sortedArray = sorts.bucketSort(data);
            endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        System.out.println("Benchmarking bucket sort: ");
        System.out.println("Number of data to sort: " + data.size());
        System.out.println("Average time taken to sort: " + totalTime / numRun + " ms");
        System.out.println();
    }

    /**
     * Main method to run the time methods. Modify it as you need.
     * @param args arguments (not used)
     */
    public static void main(String[] args) {
        int numData;

        numData = NUM_DATA;
        int cutoff = 32;
        for (int i = 0; i < NUM_TEST; i++) {
            ArrayList<Double> data = randomNumbers(numData, MIN, MAX);
            timeModifiedQuickSort(data, NUM_RUN, cutoff);
            //cutoff += cutoff;
            numData += 100000;
        }
    }
}
