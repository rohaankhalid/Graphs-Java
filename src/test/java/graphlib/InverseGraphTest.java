package graphlib;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

public class InverseGraphTest {

    @Test
    public void testInvertGraph() throws Exception {
        Graph originalGraph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/originalGraph.txt"));
        Graph invertedGraph = originalGraph.invertGraph();

        // Check that edges in the original graph are not in the inverted graph and vice versa
        for (Node node : originalGraph.getAllNodes()) {
            for (Node neighbor : node.getNeighbors()) {
                assertFalse(invertedGraph.getOrCreateNode(node.getName()).hasEdge(invertedGraph.getOrCreateNode(neighbor.getName())));
            }
        }

        for (Node node : invertedGraph.getAllNodes()) {
            for (Node neighbor : node.getNeighbors()) {
                assertFalse(originalGraph.getOrCreateNode(node.getName()).hasEdge(originalGraph.getOrCreateNode(neighbor.getName())));
            }
        }
    }

    @Test
    public void testEmptyGraph() {
        Graph originalGraph = new Graph();
        Graph invertedGraph = originalGraph.invertGraph();
        assertTrue(invertedGraph.getAllNodes().isEmpty());
    }

    @Test
    public void testSingleEdgeGraph() throws Exception {
        Graph originalGraph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/singleEdgeGraph.txt"));
        Graph invertedGraph = originalGraph.invertGraph();

        Node nodeA = invertedGraph.getOrCreateNode("A");
        Node nodeB = invertedGraph.getOrCreateNode("B");
        
        // In the original graph, there is an edge between A and B
        // In the inverted graph, there should not be an edge between A and B
        assertFalse(nodeA.hasEdge(nodeB));
    }

    @Test
    public void testCompleteGraph() throws Exception {
        Graph originalGraph = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/completeGraph.txt"));
        Graph invertedGraph = originalGraph.invertGraph();

        // In the original complete graph, every node has an edge to every other node
        // In the inverted graph, there should be no edges
        for (Node node : invertedGraph.getAllNodes()) {
            assertTrue(node.getNeighbors().isEmpty());
        }
    }
    
}
