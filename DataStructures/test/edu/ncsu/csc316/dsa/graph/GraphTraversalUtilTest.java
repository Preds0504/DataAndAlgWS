package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class GraphTraversalUtilTest {
	
	

    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
    	 Graph<String, Integer> graph = new AdjacencyListGraph<>();
         Vertex<String> vA = graph.insertVertex("A");
         Vertex<String> vB = graph.insertVertex("B");
         Vertex<String> vC = graph.insertVertex("C");
         Vertex<String> vD = graph.insertVertex("D");
         Vertex<String> vE = graph.insertVertex("E");
         graph.insertEdge(vA, vB, 1); 
         graph.insertEdge(vA, vC, 2);
         graph.insertEdge(vB, vD, 3);
         graph.insertEdge(vC, vE, 4);
         Map<Vertex<String>, Edge<Integer>> forest = GraphTraversalUtil.depthFirstSearch(graph, vA);
         assertEquals(4, forest.size());
         assertEquals((Integer) 1, forest.get(vB).getElement());
         assertEquals((Integer) 2, forest.get(vC).getElement());
         assertEquals((Integer) 3, forest.get(vD).getElement());
         assertEquals((Integer) 4, forest.get(vE).getElement());
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
    	  Graph<String, Integer> graph = new AdjacencyListGraph<>();
          Vertex<String> vA = graph.insertVertex("A");
          Vertex<String> vB = graph.insertVertex("B");
          Vertex<String> vC = graph.insertVertex("C");
          Vertex<String> vD = graph.insertVertex("D");
          Vertex<String> vE = graph.insertVertex("E");
          graph.insertEdge(vA, vB, 1); 
          graph.insertEdge(vA, vC, 2); 
          graph.insertEdge(vB, vD, 3); 
          graph.insertEdge(vC, vE, 4); 
          Map<Vertex<String>, Edge<Integer>> forest = GraphTraversalUtil.breadthFirstSearch(graph, vA);
          assertEquals(4, forest.size());
          assertEquals((Integer) 1, forest.get(vB).getElement()); 
          assertEquals((Integer) 2, forest.get(vC).getElement()); 
          assertEquals((Integer) 3, forest.get(vD).getElement()); 
          assertEquals((Integer) 4, forest.get(vE).getElement()); 
    }
    
}
