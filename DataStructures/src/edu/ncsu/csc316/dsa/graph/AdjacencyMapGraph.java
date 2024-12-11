package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author // Your Name Here 
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMapGraph<V, E> extends EdgeListGraph<V, E> {

    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class AMVertex extends GraphVertex {

        /**A map of outgoing edges*/
        private Map<Vertex<V>, Edge<E>> outgoing;

        /**
         * A map of incoming edges
         */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }
    }

    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        super(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
    }

    protected Vertex<V> createVertex(V vertexData) {
        return new AMVertex(vertexData, isDirected());
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
    	AMVertex temp = validate(vertex1);
        return temp.getOutgoing().get(vertex2);
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        AMVertex temp = validate(vertex);
        return temp.getIncoming().values();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
    	AMVertex temp = validate(vertex);
        return temp.getIncoming().size();
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
    	GraphEdge edge = (GraphEdge) super.insertEdge(v1, v2, edgeData);
        AMVertex vertex1 = validate(v1);
        AMVertex vertex2 = validate(v2);
        vertex1.getOutgoing().put(v2, edge);
        vertex2.getIncoming().put(v1, edge);
        return edge;
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
    	AMVertex temp = validate(vertex);
    	return temp.getOutgoing().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
    	AMVertex temp = validate(vertex);
    	return temp.getOutgoing().values();
    }

    @Override
    public void removeEdge(Edge<E> edge) {
    	GraphEdge gEdge = (GraphEdge) edge;
        AMVertex v1 = validate(gEdge.getEndpoints()[0]);
        AMVertex v2 = validate(gEdge.getEndpoints()[1]);
        super.removeEdge(edge);
        v1.getOutgoing().remove(v2);
        v2.getIncoming().remove(v1);
    }
    
    /**
     * Safely casts a Vertex to an adjacency map vertex
     * 
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     * @param v vertex to validate
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
    
}