import dsa.Merge;

import java.util.Comparator;

import stdlib.In;
import stdlib.StdOut;

public class Term implements Comparable < Term > {
        private String query;
        private long weight = 0;

        // Constructor for Term given the associated query string, having weight 0.

        public Term(String query) {
            if (query == null)
                throw new NullPointerException("query is null");
            else {
                this.query = query;
                this.weight = 0;
            }
        }


        // Constructor for Term given the associated query string and weight.
        public Term(String query, long weight) {
            if (query == null)
                throw new NullPointerException("query is null");
            else if (weight < 0)
                throw new IllegalArgumentException("Illegal weight");
            else {
                this.query = query;
                this.weight = weight;
            }
        }

        // Returns a string representation of this term.
        public String toString() {

            String strRep = this.weight + "\t" + this.query;
            return strRep;

        }

        // Returns a comparison of this term and other by query.
        public int compareTo(Term other) {

            return this.query.compareTo(other.query);
        }


        // Returns a comparator for comparing two terms in reverse order of their weights.
        public static Comparator < Term > reverseWeightOrder() {
            return new ByReverseWeightOrder();
        }

        // Returns a comparator for comparing two terms by their prefixes of length r.
        public static Comparator < Term > prefixOrder(int r) {
            if (r < 0) //check if the r length is positive i.e valid value else throw the exception
                throw new IllegalArgumentException("Illegal r");

            return new ByPrefixOrder(r);
        }

        // Reverse-weight comparator.
        private static class ByReverseWeightOrder implements Comparator < Term > {
            // Returns a comparison of terms v and w by their weights in reverse order.

            public int compare(Term v, Term w) {
                {
                    // Compare the wiights
                    int wtDiff = (int) (w.weight - v.weight); //Casting long into int
                    return wtDiff;
                }


            }
        }

            // Prefix-order comparator.
            private static class ByPrefixOrder implements Comparator < Term > {

                private int r;

                // Constructs a PrefixOrder given the prefix length.
                ByPrefixOrder(int r) {
                    this.r = r;
                }

                // Returns a comparison of terms v and w by their prefixes of length r.
                public int compare(Term v, Term w) {

                    // set the end of the substring if the store value is longer or seach value is larger
                    int r1 = 0;
                    int r2 = 0;
                    long queryLength = 0;

                    queryLength = Math.min(v.query.length(),r);
                    r1 = (int)queryLength;

                    queryLength = Math.min(w.query.length(),r);
                    r2 = (int)queryLength;

                    return v.query.substring(0, r1).compareTo(w.query.substring(0, r2));
                }
            }
  

    // Unit tests the data type. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int n = in.readInt();
        Term[] terms = new Term[n];
        for (int i = 0; i < n; i++) {
            long weight = in.readLong();
            in.readChar();
            String query = in.readLine();
            terms[i] = new Term(query.trim(), weight);
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Merge.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Merge.sort(terms, Term.reverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
