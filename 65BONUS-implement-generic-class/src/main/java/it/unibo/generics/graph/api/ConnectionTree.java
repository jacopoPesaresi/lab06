package it.unibo.generics.graph.api;

import java.util.List;

public interface ConnectionTree<N>{

    void printTree();

    void setPath(N node, List<N> path);

    List<N> getPath(N node);

    Graph<N> getGraphThatConnect();

}