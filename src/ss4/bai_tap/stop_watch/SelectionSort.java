package ss4.bai_tap.stop_watch;

import java.util.Random;

public class SelectionSort {
    public static void main(String[] args) {
        int size = 100000;
        int[] array = new int[size];
        Random rand = new Random();


        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(size);
        }

        StopWatch stopwatch = new StopWatch();
        stopwatch.start();

        selectionSort(array);

        stopwatch.stop();
        System.out.println("Time taken: " + stopwatch.getElapsedTime() + " milliseconds");
    }
    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
