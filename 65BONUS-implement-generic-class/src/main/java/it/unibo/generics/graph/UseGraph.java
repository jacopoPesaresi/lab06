package it.unibo.generics.graph;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.impl.ImplGraph;
import it.unibo.generics.graph.strategypattern.ImplSearch;
import it.unibo.generics.graph.strategypattern.api.Search;
import it.unibo.generics.graph.strategypattern.strategies.BFSConcreteStrategy;
import it.unibo.generics.graph.strategypattern.strategies.DFSConcreteStrategy;

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
        testStrategy(g);
    }

    
    private static void test(final Graph<String> graph) {
        System.out.println("### using a new graph ###");
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


    private static void testStrategy(final Graph<String> graph) {
        System.out.println("### try the Strategy Pattern ###");
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
        graph.addEdge("e", "d");
        graph.addEdge("f", "b");
        graph.addEdge("g", "a");
        graph.addEdge("g", "e");


        String rootNode = "b";

        Search<String> mySearch = new ImplSearch<String>(new BFSConcreteStrategy<>());
        
        ConnectionTree<String> coverTree = mySearch.search(graph, rootNode);
        coverTree.printTree();

        System.out.println(); //separate the two results
        mySearch.setStrategy(new DFSConcreteStrategy<>());
        
        ConnectionTree<String> coverTree2 = mySearch.search(graph, rootNode);
        coverTree2.printTree();
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
