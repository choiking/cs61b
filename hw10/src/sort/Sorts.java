/* Sorts.java */

package sort;

import java.io.*;
import java.util.*;

public class Sorts {

    /**
     *  Place any final static fields you would like to have here.
     **/
    static int getMax(int arr[], int n)
    {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    /**
     *  countingSort() sorts an array of int keys according to the
     *  values of _one_ of the base-16 digits of each key.  "whichDigit"
     *  indicates which digit is the sort key.  A zero means sort on the least
     *  significant (ones) digit; a one means sort on the second least
     *  significant (sixteens) digit; and so on, up to a seven, which means
     *  sort on the most significant digit.
     *  @param key is an array of ints.  Assume no key is negative.
     *  @param whichDigit is a number in 0...7 specifying which base-16 digit
     *    is the sort key.
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys sorted according to the chosen digit.
     *
     *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
     **/
    public static int[] countingSort(int[] keys, int whichDigit) {
        // Replace the following line with your solution.
        int output[] = new int[19]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count,0);

        // Store count of occurrences in count[]
        for (i = 0; i < 19; i++)
            count[ (keys[i]/whichDigit)%10 ]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = 19 - 1; i >= 0; i--)
        {
            output[count[ (keys[i]/whichDigit)%10 ] - 1] = keys[i];
            count[ (keys[i]/whichDigit)%10 ]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < 19; i++)
            keys[i] = output[i];
        return keys;
    }

    /**
     *  radixSort() sorts an array of int keys (using all 32 bits
     *  of each key to determine the ordering).
     *  @param key is an array of ints.  Assume no key is negative.
     *  @return an array of type int, having the same length as "keys"
     *    and containing the same keys in sorted order.
     *
     *    Note:  Return a _newly_ created array.  DO NOT CHANGE THE ARRAY keys.
     **/
    public static int[] radixSort(int[] keys) {
        // Replace the following line with your solution.
        // Find the maximum number to know number of digits
        int m = getMax(keys, 19);

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int whichDigit = 1; m/whichDigit > 0; whichDigit *= 10)
            countingSort(keys, whichDigit);
        return keys;
    }

    /**
     *  yell() prints an array of int keys.  Each key is printed in hexadecimal
     *  (base 16).
     *  @param key is an array of ints.
     **/
    public static void yell(int[] keys) {
        System.out.print("keys are [ ");
        for (int i = 0; i < keys.length; i++) {
            System.out.print(Integer.toString(keys[i], 16) + " ");
        }
        System.out.println("]");
    }

    /**
     *  main() creates and sorts a sample array.
     *  We recommend you add more tests of your own.
     *  Your test code will not be graded.
     **/
    public static void main(String[] args) {
        int[] keys = { Integer.parseInt("60013879", 16),
                Integer.parseInt("11111119", 16),
                Integer.parseInt("2c735010", 16),
                Integer.parseInt("2c732010", 16),
                Integer.parseInt("7fffffff", 16),
                Integer.parseInt("4001387c", 16),
                Integer.parseInt("10111119", 16),
                Integer.parseInt("529a7385", 16),
                Integer.parseInt("1e635010", 16),
                Integer.parseInt("28905879", 16),
                Integer.parseInt("00011119", 16),
                Integer.parseInt("00000000", 16),
                Integer.parseInt("7c725010", 16),
                Integer.parseInt("1e630010", 16),
                Integer.parseInt("111111e5", 16),
                Integer.parseInt("61feed0c", 16),
                Integer.parseInt("3bba7387", 16),
                Integer.parseInt("52953fdb", 16),
                Integer.parseInt("40013879", 16) };

        yell(keys);
        keys = radixSort(keys);
        yell(keys);
    }

}