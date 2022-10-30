package it.unibo.generics.graph.strategypattern.api;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;

public interface Search<N>{

    void setStrategy(SearchStrategy<N> newSearchingMethod);

    ConnectionTree<N> search(Graph<N> whichGraph, N start);
    

}