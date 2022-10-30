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

    public ImplConnectionTree(Graph<N> start,N root){
        this.root = root;
        this.byThisGraph = start;

        this.coverTree = new HashMap<>();
        for (N node : start.nodeSet()) {
            coverTree.put(node, new LinkedList<>()); //TODO-vedi se modificarmi
        }
        
    }

    @Override
    public void printTree() {
        System.out.println("Exploration started by: " + root.toString());
        for (N node : coverTree.keySet()) {
            System.out.println("Path from " + root.toString() + " to " + node.toString() + " : " + coverTree.get(node));   
        }
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPath(N whichNode, List<N> path) {
        this.coverTree.put(whichNode, new LinkedList<>(path));
    }

    @Override
    public List<N> getPath(N node) {
        return this.coverTree.get(node);
        
    }

    @Override
    public Graph<N> getGraphThatConnect() {
        return this.byThisGraph;
    }

    

    

}
