package it.unibo.generics.graph.strategypattern;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.strategypattern.api.Search;
import it.unibo.generics.graph.strategypattern.api.SearchStrategy;

public class ImplSearch<N> implements Search<N>{

    private SearchStrategy<N> searchingMethod;

    public ImplSearch (SearchStrategy<N> searchingMethod){
        this.searchingMethod = searchingMethod;
    }

    @Override
    public void setStrategy(SearchStrategy<N> newSearchingMethod) {
        this.searchingMethod = newSearchingMethod;
    }

    @Override
    public ConnectionTree<N> search(Graph<N> whichGraph, N start) {
        return searchingMethod.search(whichGraph, start);
    }

}