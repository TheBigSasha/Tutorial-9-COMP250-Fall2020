import java.util.Arrays;
import java.util.Random;
import java.util.SortedSet;

import RuntimeTester.*;

public class SortingAlgorithms<E extends Comparable> {
    private static Random rand = new Random(24601);
    private static final SortingAlgorithms<String> sortingAlgorithms = new SortingAlgorithms<>();

    public SortingAlgorithms(){

    }

    /**
     * Merges subarrays of dataset, first being from start to mid,
     * second being from mid+1 to end.
     * @param dataset       Array to be merged
     * @param startIDX      Start index
     * @param midIDX        Mid index (end of first array, start of second)
     * @param endIDX        End index
     */
    private void merge(E[] dataset, int startIDX, int midIDX, int endIDX) {
            int sizeOfFirst = midIDX - startIDX + 1;
            int sizeOfSecond = endIDX - midIDX;

            E[] left = (E[]) new Comparable[sizeOfFirst];
            E[] right = (E[]) new Comparable[sizeOfSecond];

            for(int i = 0; i < sizeOfFirst; i++){
                left[i] = dataset[startIDX + i];
            }

            for(int i = 0; i < sizeOfSecond; i++){
                right[i] = dataset[midIDX + 1 + i];
            }

            int indexAtLeft = 0; int indexAtRight = 0;
            int indexAtDataset = startIDX;

            while(indexAtLeft < sizeOfFirst && indexAtRight < sizeOfSecond){
                if(left[indexAtLeft].compareTo(right[indexAtRight]) <= 0){
                    dataset[indexAtDataset] = left[indexAtLeft];
                    indexAtLeft++;
                }else{
                    dataset[indexAtDataset] = right[indexAtRight];
                    indexAtRight++;
                }
                indexAtDataset++;
        }


            while(indexAtLeft < sizeOfFirst){
                dataset[indexAtDataset] = left[indexAtLeft];
                indexAtLeft++;
                indexAtDataset++;
            }

        while(indexAtRight < sizeOfSecond){
            dataset[indexAtDataset] = right[indexAtRight];
            indexAtRight++;
            indexAtDataset++;
        }
    }

    /**
     * The recursive helper of mergeSort, it divides and conquers.
     * @param dataset       Array to be sorted
     * @param startIDX      Start index of the operation
     * @param endIDX        End index of the operation
     */
    private  void mergeSortRecursive(E[] dataset, int startIDX, int endIDX) {
        if (startIDX <endIDX) {
            int midIDX = (startIDX + endIDX) / 2;
            mergeSortRecursive(dataset, startIDX, midIDX);
            mergeSortRecursive(dataset, midIDX + 1, endIDX);

            merge(dataset,startIDX, midIDX, endIDX);
        }
    }

    /**
     * Sorts the array, hopefully fast
     * @param dataset       Array to be sorted
     */
    public void mergeSort(E[] dataset) {
        mergeSortRecursive(dataset,0,dataset.length-1);
    }

    public void bubbleSort(E[] dataset) {
        int wall = dataset.length;
        for (int i = 0; i < wall-1; i++)
            for (int j = 0; j < wall-i-1; j++)
                if (dataset[j].compareTo(dataset[j+1]) > 0)
                {
                    E temp = dataset[j];
                    dataset[j] = dataset[j+1];
                    dataset[j+1] = temp;
                }
    }

    public void insertionSort(E[] dataset) {
        int n = dataset.length;
        for (int i = 1; i < n; ++i) {
            E key = dataset[i];
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
        String[] testSubject = new String[777777];
        fillList(testSubject);
        String[] subjectII = Arrays.copyOf(testSubject,testSubject.length);
        Arrays.sort(testSubject);
        sortingAlgorithms.mergeSort(subjectII);
        for(int i = 0; i < testSubject.length; i++){
            if(!testSubject[i].equals(subjectII[i])){ System.out.println("Error! Sort failed at index " + i);}
        }

    }

    public static void fillList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            list[i] = ((char) rand.nextInt(i + 1) + " Test " + i + " ---- " + " " + (char) rand.nextInt(400));
        }
    }

    @benchmark(name = "Our Merge Sort", category = "Drag Race")
    public static long testMergeSort(long size){
        String[] test = new String[(int) size];
        fillList(test);
        long startTime = System.nanoTime();
        sortingAlgorithms.mergeSort(test);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @benchmark(name = "Our Insertion Sort", category = "Drag Race")
    public static long testInsertionSort(long size){
        String[] test = new String[(int) size];
        fillList(test);
        long startTime = System.nanoTime();
        sortingAlgorithms.insertionSort(test);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    @benchmark(name = "Our Bubble Sort", category = "Drag Race")
    public static long ourBubbleSort(long size){
        String[] test = new String[(int) size];
        fillList(test);
        long startTime = System.nanoTime();
        sortingAlgorithms.bubbleSort(test);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }



}
