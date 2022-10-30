package it.unibo.generics.graph.strategypattern.api;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;

public interface SearchStrategy<N>{
    
    /**
     * method that all the concrete strategy need to follow
     * @param graph which graph need to explore
     * @param start which node is the root
     * @return the summoned cover tree
     */
    ConnectionTree<N> search(Graph<N> graph, N start);
}