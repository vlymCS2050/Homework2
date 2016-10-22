/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 *
 *  Vicky Lym, 2016
 *
*/
package shell;

import java.io.*;
import java.util.*;

/**
 *  The {@code Shell} class provides static methods for sorting an
 *  array using Shellsort with Knuth's increment sequence (1, 4, 13, 40, ...).
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/21elementary">Section 2.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Shell {

    // This class should not be instantiated.
    private Shell() { }

        public static int compares;
        public static int compares1;
        public static int compares2;
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        
        // System.out.println("N elements in a" + n);

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < n/3) h = 3*h + 1; 

        // System.out.println("h is " + h);
        
        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {                
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    compares ++;
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h); 
            h /= 3;
        }
        assert isSorted(a);
    }



   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            compares1 ++;
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    // is the array h-sorted?
    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++) {
            compares2 ++;
            if (less(a[i], a[i-h])) return false;
        }
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them; 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Double[] a100 = new Double[100];
        Double[] a1000 = new Double[1000];
        Double[] a10000 = new Double[10000];
        String[] a = new String[10];
        String line;
        int ind = 0;
        int arrayN = 0;
        float diff;

        Scanner in = new Scanner(System.in);
        // while(StdIn.hasNextLine()) {
        // while (!( line = StdIn.readLine() ).equals( "" )) {
        // while (!(line = in.next(()
        // line = in.next();
        //  while ((!(line.equals("?"))) || (ind < 10)) {       
        //    a[ind] = line;
        //    ind++;
        //    line = in.next();
        // }
        // show(a);
        compares = 0;
        for (int i = 0; i < 100; i++)
            a100[i] = StdRandom.uniform();
        arrayN = a100.length;
        Shell.sort(a100);
        diff = (float)compares / 100;
        System.out.println("Compares = " + compares);
        System.out.printf("Percentage of Compares for 100 Elements is %.2f \n\n", diff);       
        
        compares = 0;
        for (int i = 0; i < 1000; i++)
            a1000[i] = StdRandom.uniform();
        arrayN = a1000.length;
        Shell.sort(a1000);
        diff = (float)compares / 1000;
        System.out.println("Compares = " + compares);
        System.out.printf("Percentage of Compares for 1000 Elements is %.2f \n\n", diff);
        
        compares = 0;
        for (int i = 0; i < 10000; i++)
            a10000[i] = StdRandom.uniform();
        arrayN = a10000.length;
        Shell.sort(a10000);
        diff = (float)compares / 10000;
        System.out.println("Compares = " + compares);
        System.out.printf("Percentage of Compares for 10000 Elements is %.2f \n", diff);
    }

}
