package org.example.sorting;

/**
 * Details and explanation here https://habr.com/ru/articles/181271/
 */
public class InsertionSorting {

    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {5, 2, 4, 1, 8, 9, 3};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
