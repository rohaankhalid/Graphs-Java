package graphlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;

import org.junit.jupiter.api.Test;

public class ComponentsTest
{

    @Test
    public void testCrazySimple()
    {
        Graph g = new Graph();
        Node a = g.getOrCreateNode("A");
        Node b = g.getOrCreateNode("B");
        a.addUnweightedUndirectedEdge(b);

        assertEquals(1, g.getNumComponents());
    }

    @Test
    public void testCrazySimple2()
    {
        Graph g = new Graph();
        Node a = g.getOrCreateNode("A");
        Node b = g.getOrCreateNode("B");

        assertEquals(2, g.getNumComponents());
    }

    @Test
    public void testOneBigComponent()
    {
        Graph g = new Graph();
        Node a = g.getOrCreateNode("A");
        Node b = g.getOrCreateNode("B");
        Node c = g.getOrCreateNode("C");
        Node d = g.getOrCreateNode("D");
        Node e = g.getOrCreateNode("E");
        a.addUnweightedUndirectedEdge(b);
        b.addUnweightedUndirectedEdge(c);
        c.addUnweightedUndirectedEdge(d);
        d.addUnweightedUndirectedEdge(a);
        d.addUnweightedUndirectedEdge(e);

        assertEquals(1, g.getNumComponents());
    }

    @Test
    public void testTwoComponents()
    {
        Graph g = new Graph();
        Node a = g.getOrCreateNode("A");
        Node b = g.getOrCreateNode("B");
        Node c = g.getOrCreateNode("C");
        Node d = g.getOrCreateNode("D");
        Node e = g.getOrCreateNode("E");
        a.addUnweightedUndirectedEdge(b);
        b.addUnweightedUndirectedEdge(c);
        c.addUnweightedUndirectedEdge(d);
        d.addUnweightedUndirectedEdge(a);
        d.addUnweightedUndirectedEdge(e);
        g.getOrCreateNode("F");

        assertEquals(2, g.getNumComponents());
    }


    @Test
    public void testTwoComponentsFromFile() throws Exception
    {
        Graph g = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/component1.txt"));

        assertEquals(2, g.getNumComponents());
    }

    // Part 1: How many componenets?
    @Test
    public void testMultipleComponents() {
        Graph g = new Graph();
        Node a = g.getOrCreateNode("A");
        Node b = g.getOrCreateNode("B");
        Node c = g.getOrCreateNode("C");
        Node d = g.getOrCreateNode("D");
        Node e = g.getOrCreateNode("E");
        Node f = g.getOrCreateNode("F");
    
        a.addUnweightedUndirectedEdge(b);
        b.addUnweightedUndirectedEdge(c);
        d.addUnweightedUndirectedEdge(e);

        // expected three components: A-B-C, D-E, F
        assertEquals(3, g.getNumComponents());
    }

    @Test
    public void testMultipleComponentsFromFile() throws Exception {
        Graph g = Graph.readUndirectedUnweightedGraph(new FileInputStream("datafiles/part1.txt"));
        assertEquals(2, g.getNumComponents());
    }

    // Edge cases for Part 1
    @Test
    public void testEmptyGraph() {
        Graph g = new Graph();
        assertEquals(0, g.getNumComponents());
    }

    @Test
    public void testSingleNode() {
        Graph g = new Graph();
        g.getOrCreateNode("A");
        assertEquals(1, g.getNumComponents());
    }

    @Test
    public void testDisconnectedNodes() {
        Graph g = new Graph();
        g.getOrCreateNode("A");
        g.getOrCreateNode("B");
        g.getOrCreateNode("C");
        assertEquals(3, g.getNumComponents());
    }

    @Test
    public void testCyclicGraph() {
        Graph g = new Graph();
        Node a = g.getOrCreateNode("A");
        Node b = g.getOrCreateNode("B");
        Node c = g.getOrCreateNode("C");
        Node d = g.getOrCreateNode("D");
        a.addUnweightedUndirectedEdge(b);
        b.addUnweightedUndirectedEdge(c);
        c.addUnweightedUndirectedEdge(d);
        d.addUnweightedUndirectedEdge(a);
        assertEquals(1, g.getNumComponents());
    }

}
