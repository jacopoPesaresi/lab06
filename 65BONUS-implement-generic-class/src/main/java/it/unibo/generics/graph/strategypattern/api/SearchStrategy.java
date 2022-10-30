package it.unibo.generics.graph.strategypattern.api;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;

public interface SearchStrategy<N>{

    ConnectionTree<N> search(Graph<N> graph, N start);
}