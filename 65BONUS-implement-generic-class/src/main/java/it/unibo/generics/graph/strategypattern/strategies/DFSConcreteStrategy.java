package it.unibo.generics.graph.strategypattern.strategies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.unibo.generics.graph.api.ConnectionTree;
import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.impl.ImplConnectionTree;
import it.unibo.generics.graph.strategypattern.Color;
import it.unibo.generics.graph.strategypattern.api.SearchStrategy;

public class DFSConcreteStrategy<N> implements SearchStrategy<N>{
    
     /**
     * Exploration of all graph
     * using the DFS strategy
     * 
     * For the exploration of all graph, this function use
     * an another function, the "DFSVisit".
     * 
     * @param start
     *      because we put a priority in this case in the research
     *      (we need to find the path between two nodes)
     *      So if the graph has more than two component, the component
     *      that there isn't the "start element" will be explored
     *      arbitary by the code
     * @return 
     *      the relative DFS-Tree
     * @throws IllegalArgumentException
     *      if the "start" node isn't a node of the graph
     */

    @Override
    public ConnectionTree<N> search(final Graph<N> graph, final N start) throws IllegalArgumentException{
        if (graph.nodeSet().contains(start)){
            ConnectionTree<N> DFSTree = new ImplConnectionTree<N>(graph, start);
            Map<N,Color> colors = new HashMap<>();

            for (N node : graph.nodeSet()) {
                colors.put(node,Color.WHITE);
            }

            DFSTree.setPath(start,new LinkedList<>(List.of(start)));
            DFSVisit(DFSTree, graph, colors, start);

            for (N node : graph.nodeSet()) {
                if(colors.get(node)==Color.WHITE){
                    DFSTree.setPath(node,new LinkedList<>(List.of(node)));
                    this.DFSVisit(DFSTree, graph, colors,node);
                }
            }

            return DFSTree;
            
        } else { 
            throw new IllegalArgumentException();
        }
    }


    private void DFSVisit(final ConnectionTree<N> DFSTree, final Graph<N> graph, final Map<N,Color> colors, final N start){
        colors.put(start,Color.GREY);
        LinkedList<N> thisPath; 

        for (N nearNode : graph.linkedNodes(start)) {
            if(colors.get(nearNode)==Color.WHITE){
                thisPath = new LinkedList<>(DFSTree.getPath(start));
                thisPath.addLast(nearNode);
                DFSTree.setPath(nearNode, thisPath);
                DFSVisit(DFSTree,graph, colors, nearNode);
            }
        }
        colors.put(start,Color.BLACK);

    }
}