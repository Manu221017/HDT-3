package MAVEN.test;

public class MatchingSort {
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int minIndex = i;
            int maxIndex = i;
            for (int j = i + 1; j < n - i; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                } else if (arr[j] > arr[maxIndex]) {
                    maxIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
            if (maxIndex == i)
                maxIndex = minIndex;
            temp = arr[n - i - 1];
            arr[n - i - 1] = arr[maxIndex];
            arr[maxIndex] = temp;
        }
    }
}
