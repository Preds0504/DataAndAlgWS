package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class ShortestPathUtilTest {
	/**
	 * Inner class to create weighted edges
	 * @author Tyler Hardison
	 */
	 public class Highway implements Weighted {
		 	/**The name for the edge*/
	        private String name;
	        /**The number value*/
	        private int length;
	        /**
	         * The constructor for the edge
	         * @param n the name
	         * @param l the weight
	         */
	        public Highway(String n, int l) {
	            setName(n);
	            setLength(l);
	        }
	        /**
	         * Sets the name in constructor
	         * @param name the name string
	         */
	        public void setName(String name) {
	            this.name = name;
	        }
	        /**
	         * Just gets the length for the edge
	         * @return length as a int
	         */
	        public int getLength() {
	            return length;
	        }
	        /**
	         * Setter for the length
	         * @param length as an int
	         */
	        public void setLength(int length) {
	            this.length = length;
	        }
	        /**
	         * gives the name
	         * @return the name
	         */
	        public String getName() {
	            return name;
	        }
	        @Override
	        public int getWeight() {
	            return getLength();
	        }
	    }
    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
    	 Graph<String, Highway> graph = new AdjacencyListGraph<>();
         Vertex<String> a = graph.insertVertex("A");
         Vertex<String> b = graph.insertVertex("B");
         Vertex<String> c = graph.insertVertex("C");
         Vertex<String> d = graph.insertVertex("D");
         Vertex<String> e = graph.insertVertex("E");
         graph.insertEdge(a, b, new Highway("A-B", 4));
         graph.insertEdge(a, c, new Highway("A-C", 2)); 
         graph.insertEdge(c, b, new Highway("C-B", 1));  
         graph.insertEdge(b, d, new Highway("B-D", 5));  
         graph.insertEdge(c, e, new Highway("C-E", 10)); 
         graph.insertEdge(e, d, new Highway("E-D", 3));  
         Map<Vertex<String>, Integer> shortestPathCosts = ShortestPathUtil.dijkstra(graph, a);
         assertEquals(0, (int) shortestPathCosts.get(a));  
         assertEquals(3, (int) shortestPathCosts.get(b));  
         assertEquals(2, (int) shortestPathCosts.get(c));  
         assertEquals(11, (int) shortestPathCosts.get(e)); 
         assertEquals(8, (int) shortestPathCosts.get(d)); 
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
    	Graph<String, Highway> graph = new AdjacencyListGraph<>();
        Vertex<String> a = graph.insertVertex("A");
        Vertex<String> b = graph.insertVertex("B");
        Vertex<String> c = graph.insertVertex("C");
        Vertex<String> d = graph.insertVertex("D");
        Vertex<String> e = graph.insertVertex("E");

        // Step 2: Insert edges with weights
        graph.insertEdge(a, b, new Highway("A-B", 4));
        graph.insertEdge(a, c, new Highway("A-C", 2)); 
        graph.insertEdge(c, b, new Highway("C-B", 1));  
        graph.insertEdge(b, d, new Highway("B-D", 5));  
        graph.insertEdge(c, e, new Highway("C-E", 10)); 
        graph.insertEdge(e, d, new Highway("E-D", 3));  

        // Step 3: Run Dijkstra to get the shortest path costs
        Map<Vertex<String>, Integer> shortestPathCosts = ShortestPathUtil.dijkstra(graph, a);

        // Step 4: Get the shortest path tree
        Map<Vertex<String>, Edge<Highway>> shortestPathTree = ShortestPathUtil.shortestPathTree(graph, a, shortestPathCosts);

        assertEquals(null, shortestPathTree.get(a)); // Start vertex (A) has no incoming edge
        assertEquals("C-B", shortestPathTree.get(b).getElement().getName()); // Shortest path to B: A -> C -> B
        assertEquals("A-C", shortestPathTree.get(c).getElement().getName()); // Shortest path to C: A -> C
        assertEquals("B-D", shortestPathTree.get(d).getElement().getName()); // Shortest path to D: A -> C -> B -> D
        assertEquals("E-D", shortestPathTree.get(e).getElement().getName()); // Shortest path to E: A -> C -> E -> D
    }
    
}
