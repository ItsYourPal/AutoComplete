import dsa.Merge;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class BinarySearchDeluxe {
    // Returns the index of the first key in a that equals the search key, or -1, according to the order induced by
    // the comparator c.
    public static <T> int firstIndexOf(T[] a, T key, Comparator<T> c) {
        if (a ==null || key == null || c == null) {
            throw new NullPointerException("a, key, or c is null");
        }
        int index = -1;
        int lo = 0;
        int hi = a.length - 1;
   
    // Do binary search to find the first occurance of the key

        while (lo <= hi) {

            int mid = lo + (hi + lo) / 2;  // set inital mid index of the array

            //The below will adjust the lo index and hi index according the search/ or by compare the key with the 
            //mid element of the array

            if (c.compare(key,a[mid])< 0) {
                hi = mid - 1;
            } else if (c.compare(key,a[mid]) > 0) {
                lo = mid + 1;

            } else {
             /*   if (mid == 0){
                    return mid;
                } else if(c.compare(key,a[mid-1]) > 0){
                    index = mid;
                    return index;
                }
                else{
                    hi = mid -1;
                } */

                hi = mid - 1;
                index = mid;
            }
            return index;

        }

        //Return the index of the first occurrence of the key
        return index;

    }

    // Returns the index of the first key in a that equals the search key, or -1, according to the order induced by
    // the comparator c.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> c) {
        
        if (a ==null || key == null || c == null) {
            throw new NullPointerException("a, key, or c is null");
        }
        // set the initial values of the indices
        int lo = 0;
        int hi = a.length - 1;
        int index = -1;

        //Do a binary search to find the last occurance of the key i.e if the key is available more than once
        // return the index of the last occurance, do it by adjusting the hi and low values based on the compare 
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (c.compare(key, a[mid]) < 0) {
                hi = mid - 1;
            }else if (c.compare(key, a[mid])> 0) {
                lo = mid + 1;
            }else {
                /* if (mid == 0){
                    return mid;
                } else if(c.compare(key,a[mid+1]) > 0){
                    index = mid;
                    return index;
                }
                else {
                    lo = mid +1;
                } */
                lo = mid + 1;
                index = mid;
            }
        }

        // Return the index of the last occurence 
        return index; 
    }

    // Unit tests the library. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        String prefix = args[1];
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        Merge.sort(terms);
        Term term = new Term(prefix);
        Comparator<Term> prefixOrder = Term.prefixOrder(prefix.length());
        int i = BinarySearchDeluxe.firstIndexOf(terms, term, prefixOrder);
        int j = BinarySearchDeluxe.lastIndexOf(terms, term, prefixOrder);
        int count = i == -1 && j == -1 ? 0 : j - i + 1;
        StdOut.println("firstIndexOf(" + prefix + ") = " + i);
        StdOut.println("lastIndexOf(" + prefix + ")  = " + j);
        StdOut.println("frequency(" + prefix + ")    = " + count);
    }
}
