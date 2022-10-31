/*
 * NAME: Yubing Lin
 * PID:  A16994291
 */
import java.util.ArrayList;
import java.util.Collections;

/**
 * Sorts class.
 * @param <T> Generic type
 * @author Yubing Lin
 * @since  Oct 29, 2022
 */
public class Sorts<T extends Comparable<? super T>> {

    private static final int MIDDLE_IDX = 2;
    public static final int HALF_FACTOR = 2;

    /**
     * This method performs insertion sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void InsertionSort(ArrayList<T> list, int start, int end) {
        for (int i=start+1; i<=end; i++) {
            // Iterate over the whole subsection which should be sorted
            int index = i;
            while (index > start && list.get(index).compareTo(list.get(index-1)) < 0) {
                // Swap two elements if the later one is smaller than the previous one
                T smaller = list.get(index);
                list.set(index, list.get(index-1));
                list.set(index-1, smaller);
                index--;
            }
        }
    }

    /**
     * This method performs merge sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void MergeSort(ArrayList<T> list, int start, int end) {

        if (start < end)
        {
            int mid = start + (end - start) / MIDDLE_IDX;
            // Sort the front and last half then marge then together
            MergeSort(list, start, mid);
            MergeSort(list , mid + 1, end);

            merge(list, start, mid, end);
        }
    }

    /**
     * merge helper function for MergeSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param m the middle index we want to merge
     * @param r right-most index we want to merge
     */
    private void merge(ArrayList<T> arr, int l, int m, int r) {

        int mergedSize = r - l + 1;

        ArrayList<T> mergedNums = new ArrayList<>();
        int left = l, right = m + 1;
        // While the left and right part are not finished,
        // add the smaller value to margedNums
        while (left <= m && right <= r) {
            if (arr.get(left).compareTo(arr.get(right)) < 0) {
                mergedNums.add(arr.get(left));
                left++;
            }
            else {
                mergedNums.add(arr.get(right));
                right++;
            }
        }
        // If the left or right part has remaining elements, add them to the merged list
        while (left <= m) {
            mergedNums.add(arr.get(left));
            left++;
        }
        while (right <= r) {
            mergedNums.add(arr.get(right));
            right++;
        }
        // Replace the original list with sorted elements
        for (int i = 0; i < mergedSize; i++) {
            arr.set(l + i, mergedNums.get(i));
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void QuickSort(ArrayList<T> list, int start, int end) {
        if (end > start) {
            int lowEnd = this.partition(list, start, end);
            // Sort the left part and right part divided by the pivot
            this.QuickSort(list, start, lowEnd);
            this.QuickSort(list, lowEnd+1, end);
        }
    }

    /**
     * partition helper function for QuickSort
     *
     * @param arr The arraylist we want to sort
     * @param l left-most index we want to merge
     * @param h right-most index we want to merge
     * @return the last index in the segment
     */
    private int partition(ArrayList<T> arr, int l, int h) {
        // Choose the middle index as the pivot
        int middleIndex = l+(h-l)/HALF_FACTOR;
        T pivot = arr.get(middleIndex);
        boolean finish = false;
        // Continue looping while left numbers are not all smaller than pivot
        while (!finish) {
            while (arr.get(l).compareTo(pivot) < 0) {
                // If elements from left are not larger than pivot, continue
                l++;
            }
            while (arr.get(h).compareTo(pivot) > 0) {
                // If elements from right are not smaller than pivot, continue
                h--;
            }
            if (l >= h) {
                // If l and h have overlapped, the list is sorted and stop
                finish = true;
            } else {
                // If not overlap, the list need to be sorted
                // Exchange the element at l and h and continue
                T low = arr.get(l);
                arr.set(l, arr.get(h));
                arr.set(h, low);
                l++;
                h--;
            }
        }
        return h;
    }

    /**
     * This method performs a modified QuickSort that switches to insertion sort
     * after a certain cutoff
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param cutoff the minimum length of an subsection of the arraylist
     *               such that we switch to Insertion Sort
     */
    public void Modified_QuickSort(ArrayList<T> list, int start, int end, int cutoff) {
        if (end - start <= cutoff) {
            // If the remaining unsorted length is smaller than cutoff, use insertion instead
            this.InsertionSort(list, start, end);
        } else {
            int lowEnd = this.partition(list, start, end);
            this.Modified_QuickSort(list, start, lowEnd, cutoff);
            this.Modified_QuickSort(list, lowEnd, end, cutoff);
        }
    }

    /**
     * Sort the whole list once from the required direction
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     * @param front the direction of sort from front or last
     * @return the number of swaps made during sorting
     */
    private int Swap(ArrayList<T> list, int start, int end, boolean front) {
        int swapTime = 0;
        if (front) {
            int index = start;
            // loop from front to end if front is true
            while (index < end) {
                // swap two adjacent elements if left one is larger than right one
                if (list.get(index).compareTo(list.get(index+1))>0) {
                    T large = list.get(index);
                    list.set(index, list.get(index+1));
                    list.set(index+1, large);
                    swapTime++;
                }
                index++;
            }
        } else {
            int index = end;
            // loop from end to front if front is false
            while (index > start) {
                // swap two adjacent elements if left one is larger than right one
                if (list.get(index).compareTo(list.get(index-1))<0) {
                    T small = list.get(index);
                    list.set(index, list.get(index-1));
                    list.set(index-1, small);
                    swapTime++;
                }
                index--;
            }
        }
        return swapTime;
    }

    /**
     * This method performs cocktail sort on the input list
     *
     * @param list The arraylist we want to sort
     * @param start The inital index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public void cocktailSort(ArrayList<T> list, int start, int end){
        boolean front = true;
        int firstSwap = this.Swap(list, start, end, front);
        // Continue sorting if their are swaps made during last sort
        while (firstSwap != 0) {
            front = !front;
            firstSwap = this.Swap(list, start, end, front);
        }
    }

    /**
     * this helper finds the appropriate number of buckets you should allocate
     * based on the range of the values in the input list
     * @param list the input list to bucket sort
     * @return number of buckets
     */
    private int assignNumBuckets(ArrayList<T> list) {
        T max = Collections.max(list);
        T min = Collections.min(list);
        return (int) Math.sqrt((Double) max - (Double) min) + 1;
    }

    /**
     * this helper finds the appropriate bucket index that a data should be
     * placed in
     * @param data a particular data from the input list if you are using
     *             loop iteration
     * @param numBuckets number of buckets
     * @param listMin the smallest element of the input list
     * @return the index of the bucket for which the particular data should
     * be placed in
     */
    private int assignBucketIndex(T data, int numBuckets, T listMin) {
        return (int) (((Double) data - (Double) listMin) / numBuckets);
    }

    /**
     * This method performs bucket sort on the input list
     *
     * @param list The arraylist we want to sort
     * @return the sorted version of input list
     */
    public ArrayList<T> bucketSort(ArrayList<T> list) {
        int numBucket = this.assignNumBuckets(list);
        T min = Collections.min(list);
        ArrayList<ArrayList<T>> bucket = new ArrayList<>(numBucket);
        ArrayList<T> sorted = new ArrayList<T>();
        // Initialize the bucket based on the number of nested list it has
        for (int i=0; i<numBucket; i++) {
            bucket.add(new ArrayList<T>());
        }
        // Assign all the values to corresponding buckets
        for (int j=0; j<list.size(); j++) {
            bucket.get(this.assignBucketIndex(list.get(j), numBucket, min)).add(list.get(j));
        }
        // Sort each bucket if it's not empty
        for (ArrayList<T> b: bucket) {
            if (b.size() != 0) {
                this.InsertionSort(b, 0, b.size() - 1);
            }
        }
        // Merge all buckets back to a list
        for (ArrayList<T> b: bucket) {
            sorted.addAll(b);
        }
        return sorted;
    }

}
