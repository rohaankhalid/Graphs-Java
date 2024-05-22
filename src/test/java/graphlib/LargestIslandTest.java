package graphlib;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import org.junit.jupiter.api.Test;

public class LargestIslandTest {

    @Test
    public void testReadMatrix() throws Exception {
        int[][] matrix = Graph.readMatrix(new FileInputStream("datafiles/largestIsland1.txt"));
        int[][] expectedMatrix = {
            {1, 1, 0, 0},
            {0, 0, 1, 0},
            {1, 0, 0, 0}
        };
        assertEquals(expectedMatrix.length, matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            assertEquals(expectedMatrix[i].length, matrix[i].length);
            for (int j = 0; j < matrix[i].length; j++) {
                assertEquals(expectedMatrix[i][j], matrix[i][j]);
            }
        }
    }

    @Test
    public void testLargestIsland() throws Exception {
        Graph g = Graph.readIslandFile(new FileInputStream("datafiles/largestIsland1.txt"));
        assertEquals(3, g.getLargestIslandSize());
    }

    @Test
    public void testAllWater() throws Exception {
        Graph g = Graph.readIslandFile(new FileInputStream("datafiles/allWater.txt"));
        assertEquals(0, g.getLargestIslandSize());
    }

    @Test
    public void testSingleIsland() throws Exception {
        Graph g = Graph.readIslandFile(new FileInputStream("datafiles/singleIsland.txt"));
        assertEquals(9, g.getLargestIslandSize());
    }

    @Test
    public void testDisconnectedIslands() throws Exception {
        Graph g = Graph.readIslandFile(new FileInputStream("datafiles/disconnectedIslands.txt"));
        assertEquals(2, g.getLargestIslandSize());
    }

    @Test
    public void testComplexGraph() throws Exception {
        Graph g = Graph.readIslandFile(new FileInputStream("datafiles/complexGraph.txt"));
        assertEquals(7, g.getLargestIslandSize());
    }
}
