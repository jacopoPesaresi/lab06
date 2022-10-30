package it.unibo.generics.graph.strategypattern.strategies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.impl.ImplConnectionTree;
import it.unibo.generics.graph.strategypattern.Color;
import it.unibo.generics.graph.strategypattern.api.SearchStrategy;


public class BFSConcreteStrategy<N> implements SearchStrategy<N>{

    @Override
    public ConnectionTree<N> search(Graph<N> graph, N start) {
        ConnectionTree<N> BFSTree = new ImplConnectionTree<N>(graph,start);
        Map<N,Color> colors = new HashMap<>();
        Queue<N> Q = new LinkedList<>();

        for (N node : graph.nodeSet()) {
            colors.put(node,Color.WHITE);
        }

        BFSTree.setPath(start, List.of(start));
        colors.put(start, Color.GREY);  
        Q.add(start);

        N nodeConsidered;
        LinkedList<N> tmp;
        while (!Q.isEmpty()) {
            nodeConsidered = Q.poll();
            for (N nearNode : graph.linkedNodes(nodeConsidered)){
                if(colors.get(nearNode) == Color.WHITE){
                    colors.put(nearNode, Color.GREY);
                    tmp = new LinkedList<>(BFSTree.getPath(nodeConsidered));
                    tmp.addLast(nearNode);
                    BFSTree.setPath(nearNode, tmp);
                    Q.add(nearNode);
                }
            }
            colors.put(nodeConsidered, Color.BLACK);
        }
        return BFSTree;
    }


        

}