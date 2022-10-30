package it.unibo.generics.graph.strategypattern.api;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;

public interface Search<N>{

    /**
     * Help the change of the searching method
     * @param newSearchingMethod
     */
    void setStrategy(SearchStrategy<N> newSearchingMethod);

    /**
     * To invoce the searching:
     * @param whichGraph to the determined graph
     * @param start by this root
     * @return the relative cover tree
     */
    ConnectionTree<N> search(Graph<N> whichGraph, N start);
    

}