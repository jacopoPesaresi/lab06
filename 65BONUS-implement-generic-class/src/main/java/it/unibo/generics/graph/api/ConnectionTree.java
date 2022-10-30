package it.unibo.generics.graph.api;

import java.util.List;

public interface ConnectionTree<N>{

    /**
     * Print the connectionTree, and it's root node
     * if a node isn't reacheble by the root, the path start with an another node
     * (if the used strategy provide to explore all the graph and not only one component)
     */
    void printTree();

    /**
     * Help the exploration algoritms to summon the connection tree
     * @param node 
     * the target node. The source node is the root
     * @param path
     * the path between the root (already set in the costructor) and the node 
     */
    void setPath(N node, List<N> path);

    /**
     * Get the path between root node and the specifed node.
     * @param node
     * the target nood. The source nood was already set
     * @return
     * the normal path, if the node is reacheble by the root
     * else, as in "printTree", if the root node cannot reach the node, it will
     * @return
     * an empty list (if the exploration explore only one component)
     * a path that start with a different node instead of root node (if the exploration explore all graph)
     */
    List<N> getPath(N node);

    /**
     * Get the graph that this tree cover
     * @return the relative graph
     */
    Graph<N> getGraphThatConnect();

}