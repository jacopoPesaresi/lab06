package it.unibo.collections;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {

    private static final int START = 1000;
    private static final int END = 2000;

    private UseListsAndMaps() {
    }

    private static long startTest() {
        return System.nanoTime();
    }

    private static void printAddTestResult(final long time, final String dataStructureUsed, final int amountElementToInsert) {
        final var firstMillis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Add "
                + amountElementToInsert
                + " int in the " + dataStructureUsed + " structure using "
                + time
                + "ns ("
                + firstMillis
                + "ms)"
        );
    }

    private static void printReadTestResult(final long time, final String dataStructureUsed, final int amountElementToRead) {
        final var firstMillis = TimeUnit.NANOSECONDS.toMillis(time);
        System.out.println(// NOPMD
            "Read "
                + amountElementToRead
                + " times the middle element in the " + dataStructureUsed + " structure using "
                + time
                + "ns ("
                + firstMillis
                + "ms)"
        );
    }

   

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> first = new ArrayList<>(END-START);
        for (int i = START; i < END; i++){
            first.add(i);
        }
        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> second = new LinkedList<>(first);
        

        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int firstSize = first.size();
        final int tmp = first.get(0);
        first.set(0, first.get(firstSize - 1));
        first.set(firstSize - 1, tmp);
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        
        /*
        for (final int i : first) {
            System.out.print(i + ", ");
        }
        */
        
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        int amountElementToInsert = 100000;

        long time = startTest();
        for (int i=0; i<amountElementToInsert; i++) {
            first.add(Integer.valueOf(-i));
        }
        time = System.nanoTime() - time;

        printAddTestResult(time, "ArrayList", amountElementToInsert);

        time = startTest();
        for (int i=0; i<amountElementToInsert; i++) {
            second.add(Integer.valueOf(-i));
        }
        time = System.nanoTime() - time;
        printAddTestResult(time, "LinkedList", amountElementToInsert);

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */

        int amountReadTime = 1000;
        

        time = startTest(); 
        for (int i=0; i<amountReadTime; i++){
           first.get(first.size() / 2);
        }
        time = System.nanoTime() - time;
        printReadTestResult(time, "ArrayList", amountReadTime);

        time = startTest(); 
        for (int i=0; i<amountReadTime; i++) {
            second.get(second.size() / 2);
        }
        time = System.nanoTime() - time;
        printReadTestResult(time, "LinkedList", amountReadTime);

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> myMap = new HashMap<String,Long>();
        myMap.put("Africa", 1_110_635_000L);
        myMap.put("Americas", 972_005_000L);
        myMap.put("Antarctica", 0L);
        myMap.put("Asia", 4_295_723_000L);
        myMap.put("Europe", 742_452_000L);
        myMap.put("Oceania", 38_304_000L);
        
        /*
         * 8) Compute the population of the world
         */
        long amount = 0L;
        for (final long l : myMap.values()) {
            amount += l;
        }
        System.out.println(amount);
    }
}
