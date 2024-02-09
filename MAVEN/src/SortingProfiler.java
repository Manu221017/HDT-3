package MAVEN.src;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import MAVEN.test.BubbleSort;
import MAVEN.test.InsertionSort;
import MAVEN.test.MatchingSort;
import MAVEN.test.MergeSort;
import MAVEN.test.QuickSort;
import MAVEN.test.RadixSort;

public class SortingProfiler {

    public static void main(String[] args) {
        String filename = "random_numbers.txt";
        int[] sizes = new int[30];
        for (int i = 0; i < 30; i++) {
            sizes[i] = 10 + i * 100;
        }

        
        generateAndSaveRandomNumbers(filename, sizes[sizes.length - 1]);

        String[] algorithms = {"BubbleSort", "QuickSort", "InsertionSort", "MergeSort", "RadixSort", "MatchingSort"};
        for (String algorithm : algorithms) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(algorithm + "_times.txt"))) {
                for (int size : sizes) {
                    int[] numbers = readNumbersFromFile(filename, size);
                    long elapsedTime = measureSortingTime(numbers, algorithm);
                    writer.write(size + "\t" + elapsedTime);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       
        int[] numbers = readNumbersFromFile(filename, sizes[sizes.length - 1]);
        Arrays.sort(numbers);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ordered_times.txt"))) {
            for (String algorithm : algorithms) {
                long elapsedTime = measureSortingTime(numbers, algorithm);
                writer.write(algorithm + "\t" + elapsedTime);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateAndSaveRandomNumbers(String filename, int count) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                int randomNumber = random.nextInt();
                writer.write(Integer.toString(randomNumber));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readNumbersFromFile(String filename, int count) {
        int[] numbers = new int[count];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < count; i++) {
                numbers[i] = Integer.parseInt(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }

    private static long measureSortingTime(int[] numbers, String algorithm) {
        int[] copy = Arrays.copyOf(numbers, numbers.length);

        long startTime = System.nanoTime();
        switch (algorithm) {
            case "BubbleSort":
                BubbleSort.sort(copy);
                break;
            case "QuickSort":
                QuickSort.sort(copy, 0, copy.length - 1);
                break;
            case "InsertionSort":
                InsertionSort.sort(copy);
                break;
            case "MergeSort":
                MergeSort.sort(copy, 0, copy.length - 1);
                break;
            case "RadixSort":
                RadixSort.sort(copy);
                break;
            case "MatchingSort":
                MatchingSort.sort(copy);
                break;
        }
        long endTime = System.nanoTime();

        return endTime - startTime;
    }
}
