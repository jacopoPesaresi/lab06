package it.unibo.generics.graph;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.impl.ImplGraph;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/**
 *
 */
public final class UseGraph {

    private UseGraph() {
    }

    /**
     * @param args
     *            ignored
     */
    public static void main(final String... args) {
        /*
         * Test your graph implementation(s) by calling testGraph
         */
        Graph<String> g = new ImplGraph<>();
        testGraph(g);
        test(g);
        
    }

    /**
     * My little tester, only for funny
     * @param graph
     */
    private static void test(final Graph<String> graph) {
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");
        graph.addNode("f");
        graph.addNode("g");
        graph.addEdge("a", "d");
        graph.addEdge("b", "c");
        graph.addEdge("c", "e");
        graph.addEdge("d", "f");
        graph.addEdge("e", "d");
        graph.addEdge("f", "b");
        graph.addEdge("g", "a");
        graph.addEdge("g", "e");
        

        System.out.println(graph.getPath("a","g"));
        
    }
    


    private static void testGraph(final Graph<String> graph) {
        graph.addNode("a");
        graph.addNode("b");
        graph.addNode("c");
        graph.addNode("d");
        graph.addNode("e");
        graph.addEdge("a", "b");
        graph.addEdge("b", "c");
        graph.addEdge("c", "d");
        graph.addEdge("d", "e");
        graph.addEdge("c", "a");
        graph.addEdge("e", "a");
        /*
         * Should be ["a","b","c","d","e"], in any order
         */
        assertIsAnyOf(graph.nodeSet(), Set.of(splitOnWhiteSpace("a b c d e")));
        /*
         * ["d","a"], in any order
         */
        assertIsAnyOf(graph.linkedNodes("c"), Set.of(splitOnWhiteSpace("a d")));
        /*
         * Either the path b,c,a or b,c,d,e,a
         */

        assertIsAnyOf(
            graph.getPath("b", "a"),
            Arrays.asList(splitOnWhiteSpace("b c a")),
            Arrays.asList(splitOnWhiteSpace("b c d e a"))
        );
    }



    private static void assertIsAnyOf(final Object actual, final Object... valid) {
        for (final var target: Objects.requireNonNull(valid)) {
            if (Objects.equals(target, actual)) {
                System.out.println("OK: " + actual + " matches " + target); // NOPMD
                return;
            }
        }
        throw new AssertionError("None of " + Arrays.asList(valid) + " matches " + actual);
    }

    private static String[] splitOnWhiteSpace(final String target) {
        return target.split("\\s+");
    }
}
