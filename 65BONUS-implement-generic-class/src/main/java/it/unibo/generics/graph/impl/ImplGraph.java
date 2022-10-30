package it.unibo.generics.graph.impl;

import java.util.Set;
import java.util.HashSet;

import java.util.LinkedList;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import java.util.Queue;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.strategypattern.Color;



public class ImplGraph<N> implements Graph<N>{

    private Map<N,Set<N>> myGraph;

    public ImplGraph() {
        this.myGraph = new HashMap<N,Set<N>>();
    }

    @Override
    public void addNode(final N node) {
        if(node!=null && !myGraph.containsKey(node)){
            myGraph.put(node,new HashSet<N>());
        }
    }

    @Override
    public void addEdge(final N source,final N target) {
        if(source!=null && target!=null && myGraph.containsKey(source)){
            myGraph.get(source).add(target);
        }
    }

    @Override
    public Set<N> nodeSet() {
        return this.myGraph.keySet();
    }

    @Override
    public Set<N> linkedNodes(final N node) {
        return this.myGraph.get(node);
    }

    @Override
    public List<N> getPath(final N source, final N target) {
        return this.BFS(source).get(target);
    }


    /**
     * Exploration of a single component about a graph
     * using the BFS strategy
     * @param start
     *      so the node that stats the search of other component's node
     * @return 
     *      the relative BFS-Tree
     */
    private Map<N,List<N>> BFS(final N start) {
        Map<N,List<N>> BFSTree = new HashMap<>();
        Map<N,Color> colors = new HashMap<>();
        Queue<N> Q = new LinkedList<>();

        for (N node : this.myGraph.keySet()) {
            BFSTree.put(node,new LinkedList<>());
            colors.put(node,Color.WHITE);
        }

        BFSTree.put(start,new LinkedList<>(List.of(start)));
        colors.put(start, Color.GREY);  
        Q.add(start);

        N nodeConsidered;
        LinkedList<N> tmp;
        while (!Q.isEmpty()) {
            nodeConsidered = Q.poll();
            for (N nearNode : this.linkedNodes(nodeConsidered)) {
                if(colors.get(nearNode) == Color.WHITE){
                    colors.put(nearNode, Color.GREY);
                    tmp = new LinkedList<>(BFSTree.get(nodeConsidered));
                    tmp.addLast(nearNode);
                    BFSTree.put(nearNode, tmp);
                    Q.add(nearNode);
                }
            }
            colors.put(nodeConsidered, Color.BLACK);
        }
        return BFSTree;
    }


}