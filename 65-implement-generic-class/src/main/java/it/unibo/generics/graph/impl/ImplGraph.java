package it.unibo.generics.graph.impl;

import java.util.Set;
import java.util.HashSet;

import java.util.LinkedList;

import java.util.HashMap;
import java.util.Map;

import java.util.List;

import java.util.Queue;
import java.util.Scanner;

import it.unibo.generics.graph.api.Graph;


enum Color {WHITE, GREY, BLACK};


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
        Scanner s = new Scanner(System.in);
        boolean gate = true;
        
        while(gate){
            try {
                System.out.println("Preferisci BFS o DFS? ");
                String in = s.nextLine();
                gate = false;
                
                if(in.equals("BFS")){
                    s.close();
                    return this.BFS(source).get(target);
                } else if (in.equals("DFS")) {
                    s.close();
                    return this.DFSForPath(source).get(target);
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Scrivere BFS o DFS");
                gate=true;
            }
        }
        return this.BFS(source).get(target);
    }


    

    /*
    public void printExplorationTree (N source) {
        Scanner s = new Scanner(System.in);
        boolean gate = true;
        Map <N,List<N>> myExpTre=null;

        if(this.getGraph().containsKey(source)){
            System.out.println("Nodo di partenza: " + source.toString());
            
        while(gate){
            try {
                System.out.println("Preferisci osservare l'esplorazione BFS o DFS? ");
                String in = s.nextLine();
                gate = false;
                
                if(in.equals("BFS")){
                    s.close();
                    myExpTre = this.BFS(source);
                } else if (in.equals("DFS")) {
                    s.close();
                    myExpTre = this.DFSForPath(source);
                } else throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                System.out.println("Scrivere BFS o DFS");
                gate=true;
            }
        }

        if(myExpTre!=null){
            for (N node : myExpTre.keySet()) {
                System.out.print("Nodo: " + node.toString());
                System.out.println("; predecessori: " + myExpTre.get(node).toString());
            }
        }

        }
        
    }
    */

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
            for (N nearNode : this.getGraph().get(nodeConsidered)) {
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
     */
    private Map<N,List<N>> DFSForPath(final N start) {
        Map<N,List<N>> DFSTree = new HashMap<>();
        Map<N,Color> colors = new HashMap<>();

        for (N node : this.getGraph().keySet()) {
            DFSTree.put(node, null);
            colors.put(node,Color.WHITE);
        }

        DFSTree.put(start,new LinkedList<>(List.of(start)));
        DFSVisit(DFSTree, colors, start);

        for (N node : DFSTree.keySet()) {
            if(colors.get(node)==Color.WHITE){
                DFSTree.put(node,new LinkedList<>(List.of(node)));
                this.DFSVisit(DFSTree,colors,node);
            }
        }

        return DFSTree;
    }

    private void DFSVisit(final Map<N,List<N>> DFSTree, final Map<N,Color> colors, final N start){
        colors.put(start,Color.GREY);
        LinkedList<N> thisPath; 

        for (N nearNode : this.getGraph().get(start)) {
            if(colors.get(nearNode)==Color.WHITE){
                thisPath = new LinkedList<>(DFSTree.get(start));
                thisPath.addLast(nearNode);
                DFSTree.put(nearNode,thisPath);
                DFSVisit(DFSTree, colors, nearNode);
            }
        }
        colors.put(start,Color.BLACK);

    }



    private Map<N,Set<N>> getGraph(){
        return this.myGraph;
    }


}