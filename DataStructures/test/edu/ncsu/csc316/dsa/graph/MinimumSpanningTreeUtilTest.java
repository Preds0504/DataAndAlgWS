package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class MinimumSpanningTreeUtilTest {
	/**
     * Highway class that implements Weighted interface for test purposes
     */
    public class Highway implements Weighted {
        /** Name */
        private String name;
        /** Length */
        private int length;

        /**
         * Construct the class when name and length is given
         * 
         * @param n name
         * @param l length
         */
        public Highway(String n, int l) {
            setName(n);
            setLength(l);
        }

        /**
         * Set the name
         * 
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Retrieve the name
         * 
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Retrieve the length
         * 
         * @return the length
         */
        public int getLength() {
            return length;
        }

        /**
         * Set length
         * 
         * @param length length to set
         */
        public void setLength(int length) {
            this.length = length;
        }

        @Override
        public int getWeight() {
            return getLength();
        }
    }
    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
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
        PositionalList<Graph.Edge<Highway>> mst = MinimumSpanningTreeUtil.primJarnik(graph);
        assertEquals(4, mst.size()); 
        int totalWeight = 0;
        for (Graph.Edge<Highway> edge : mst) {
            totalWeight += edge.getElement().getWeight();
        }
        assertEquals(11, totalWeight); 
        boolean containsAC = false, containsCB = false, containsBD = false, containsED = false;
        for (Graph.Edge<Highway> edge : mst) {
            String edgeName = edge.getElement().getName();
            if ("A-C".equals(edgeName)) {
            	containsAC = true;
            }
            if ("C-B".equals(edgeName)) {
            	containsCB = true;
            }
            if ("B-D".equals(edgeName)) {
            	containsBD = true;
            }
            if ("E-D".equals(edgeName)) {
            	containsED = true;
            }
        }
        assertTrue(containsAC);
        assertTrue(containsCB);
        assertTrue(containsBD);
        assertTrue(containsED);
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
        Graph<String, Highway> graph = new AdjacencyListGraph<>();
        Vertex<String> v1 = graph.insertVertex("V1");
        Vertex<String> v2 = graph.insertVertex("V2");
        Vertex<String> v3 = graph.insertVertex("V3");
        Vertex<String> v4 = graph.insertVertex("V4");
        Vertex<String> v5 = graph.insertVertex("V5");
        graph.insertEdge(v1, v2, new Highway("V1-V2", 6));
        graph.insertEdge(v1, v3, new Highway("V1-V3", 2));
        graph.insertEdge(v2, v3, new Highway("V2-V3", 3));
        graph.insertEdge(v2, v4, new Highway("V2-V4", 5));
        graph.insertEdge(v3, v5, new Highway("V3-V5", 4));
        graph.insertEdge(v5, v4, new Highway("V5-V4", 7));
        PositionalList<Edge<Highway>> mst = MinimumSpanningTreeUtil.kruskal(graph);
        int totalWeight = 0;
        for (Edge<Highway> edge : mst) {
            totalWeight += edge.getElement().getWeight();
        }
        assertEquals(4, mst.size());
        assertEquals(14, totalWeight); 
    }
    
}
