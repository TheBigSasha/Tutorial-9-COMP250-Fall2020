import java.util.Arrays;
import java.util.Random;
import RuntimeTester.*;

public class SortingAlgorithms {
    private static Random rand = new Random(24601);

    /**
     * Merges subarrays of dataset, first being from start to mid,
     * second being from mid+1 to end.
     * @param dataset       Array to be merged
     * @param startIDX      Start index
     * @param midIDX        Mid index (end of first array, start of second)
     * @param endIDX        End index
     */
    private static void merge(String[] dataset, int startIDX, int midIDX, int endIDX) {

    }

    /**
     * The recursive helper of mergeSort, it divides and conquers.
     * @param dataset       Array to be sorted
     * @param startIDX      Start index of the operation
     * @param endIDX        End index of the operation
     */
    private static void mergeSortRecursive(String[] dataset, int startIDX, int endIDX) {

    }

    /**
     * Sorts the array, hopefully fast
     * @param dataset       Array to be sorted
     */
    public static void mergeSort(String[] dataset) {

    }

    public static void bubbleSort(String[] dataset) {
        int wall = dataset.length;
        for (int i = 0; i < wall-1; i++)
            for (int j = 0; j < wall-i-1; j++)
                if (dataset[j].compareTo(dataset[j+1]) > 0)
                {
                    String temp = dataset[j];
                    dataset[j] = dataset[j+1];
                    dataset[j+1] = temp;
                }
    }

    public static void insertionSort(String[] dataset) {
        int n = dataset.length;
        for (int i = 1; i < n; ++i) {
            String key = dataset[i];
            int j = i - 1;
            while (j >= 0 && dataset[j].compareTo(key) > 0) {
                dataset[j + 1] = dataset[j];
                j = j - 1;
            }
            dataset[j + 1] = key;
        }
    }

    public static void main(String args[]) {
        testSorting();
        Visualizer.launch(SortingAlgorithms.class);
    }

    private static void testSorting() {
        String[] testSubject = new String[10];
        fillList(testSubject);
        String[] subjectII = Arrays.copyOf(testSubject,testSubject.length);
        Arrays.sort(testSubject);
        insertionSort(subjectII);
        for(int i = 0; i < testSubject.length; i++){
            if(!testSubject[i].equals(subjectII[i])) System.out.println("Error! Sort failed at index " + i);
        }

    }

    public static void fillList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = ((char) rand.nextInt(400) + " Test " + i + " ---- " + " " + (char) rand.nextInt(400));
        }
    }

    @benchmark(name = "Our Merge Sort", category = "Drag Race")
    public static long testMergeSort(long size){
        String[] test = new String[(int) size];
        fillList(test);
        long startTime = System.nanoTime();
        mergeSort(test);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @benchmark(name = "Our Insertion Sort", category = "Drag Race")
    public static long testInsertionSort(long size){
        String[] test = new String[(int) size];
        fillList(test);
        long startTime = System.nanoTime();
        insertionSort(test);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @benchmark(name = "Our Bubble Sort", category = "Drag Race")
    public static long ourBubbleSort(long size){
        String[] test = new String[(int) size];
        fillList(test);
        long startTime = System.nanoTime();
        bubbleSort(test);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }



}
