package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;

public class ImplConnectionTree<N> implements ConnectionTree<N>{

    private Map<N, List<N>> coverTree;
    private N root;
    private Graph<N> byThisGraph;

    public ImplConnectionTree(final Graph<N> start, final N root){
        this.root = root;
        this.byThisGraph = start;

        this.coverTree = new HashMap<>();
        for (N node : start.nodeSet()) {
            coverTree.put(node, new LinkedList<>()); 
        }
    }

    @Override
    public void printTree() {
        System.out.println("Exploration started by: " + root.toString());
        for (N node : coverTree.keySet()) {
            System.out.println("Path from " + root.toString() + " to " + node.toString() + " : " + coverTree.get(node));   
        }
    }

    @Override
    public void setPath(final N whichNode, final List<N> path) {
        this.coverTree.put(whichNode, new LinkedList<>(path));
    }

    @Override
    public List<N> getPath(final N node) {
        return new LinkedList<>(this.coverTree.get(node));
        
    }

    @Override
    public Graph<N> getGraphThatConnect() {
        return this.byThisGraph;
    }

}
