package graphlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ReachabilityTest {
    @Test
    public void testreachabilitySimple() throws Exception {
        Graph graph = Graph.readDirectedUnweightedGraph(new FileInputStream("datafiles/reachabilitySimple.txt"));
        Map<String, Set<String>> reachabilityMap = graph.computeReachability();

        assertEquals(Set.of("A", "B", "C"), reachabilityMap.get("A"));
        assertEquals(Set.of("B", "C"), reachabilityMap.get("B"));
        assertEquals(Set.of("C"), reachabilityMap.get("C"));
    }

    @Test
    public void testReachabilityCycle() throws Exception {
        Graph graph = Graph.readDirectedUnweightedGraph(new FileInputStream("datafiles/reachabilityCycle.txt"));
        Map<String, Set<String>> reachabilityMap = graph.computeReachability();

        assertEquals(Set.of("A", "B", "C"), reachabilityMap.get("A"));
        assertEquals(Set.of("A", "B", "C"), reachabilityMap.get("B"));
        assertEquals(Set.of("A", "B", "C"), reachabilityMap.get("C"));
    }

    @Test
    public void testReachabilityComplex() throws Exception {
        Graph graph = Graph.readDirectedUnweightedGraph(new FileInputStream("datafiles/reachabilityComplex.txt"));
        Map<String, Set<String>> reachabilityMap = graph.computeReachability();

        assertEquals(Set.of("A", "B", "C", "D", "E"), reachabilityMap.get("A"));
        assertEquals(Set.of("B", "D", "E"), reachabilityMap.get("B"));
        assertEquals(Set.of("C", "D", "E"), reachabilityMap.get("C"));
        assertEquals(Set.of("D", "E"), reachabilityMap.get("D"));
        assertEquals(Set.of("E"), reachabilityMap.get("E"));
    }

}
