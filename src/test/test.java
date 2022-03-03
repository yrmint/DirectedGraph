import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    public void equalsVertexes() {
        DirectedGraph.Vertex a = new DirectedGraph.Vertex("A");
        DirectedGraph.Vertex a1 = new DirectedGraph.Vertex("A");
        DirectedGraph.Vertex a2 = new DirectedGraph.Vertex("a");
        DirectedGraph.Vertex b = new DirectedGraph.Vertex("B");

        assertTrue(a.equals(a1));
        assertFalse(a.equals(b));
        assertFalse(a.equals(a2));
    }

    @Test
    public void equalsEdges() {
        DirectedGraph.Edge ab = new DirectedGraph.Edge(new DirectedGraph.Vertex("A"), new DirectedGraph.Vertex("B"), 5);
        DirectedGraph.Edge ab1 = new DirectedGraph.Edge(new DirectedGraph.Vertex("A"), new DirectedGraph.Vertex("B"), 5);
        DirectedGraph.Edge ac = new DirectedGraph.Edge(new DirectedGraph.Vertex("A"), new DirectedGraph.Vertex("C"), 6);

        assertTrue(ab.equals(ab1));
        assertFalse(ab.equals(ac));
    }

    @Test
    public void equalsGraphs() {
        DirectedGraph graph1 = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 1),
                new DirectedGraph.Edge("C", "B", 2),
                new DirectedGraph.Edge("A", "C", 3)));

        DirectedGraph graph2 = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 1),
                new DirectedGraph.Edge("C", "B", 2),
                new DirectedGraph.Edge("A", "C", 3)));

        DirectedGraph graph3 = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 1),
                new DirectedGraph.Edge("C", "B", 2),
                new DirectedGraph.Edge("A", "F", 3)));

        DirectedGraph graph4 = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 1),
                new DirectedGraph.Edge("C", "B", 2),
                new DirectedGraph.Edge("A", "C", 1000)));

        assertTrue(graph1.equals(graph2));
        assertTrue(graph1.equals(graph1));
        assertFalse(graph1.equals(graph3));
        assertFalse(graph1.equals(graph4));
    }

    @Test
    public void addVertex() {
        DirectedGraph myGraph = new DirectedGraph();
        myGraph.addVertex("A");
        myGraph.addVertex("A");
        myGraph.addVertex("C");
        myGraph.addVertex("B");

        assertEquals(new DirectedGraph(Set.of(new DirectedGraph.Vertex("A"),
                new DirectedGraph.Vertex("C"),
                new DirectedGraph.Vertex("B")), Set.of()), myGraph);
    }

    @Test
    public void deleteVertex() {
        DirectedGraph myGraph = new DirectedGraph(Set.of(new DirectedGraph.Vertex("A"),
                new DirectedGraph.Vertex("B"),
                new DirectedGraph.Vertex("C")),
                Set.of(new DirectedGraph.Edge("A", "B", 5),
                        new DirectedGraph.Edge("C", "A", 3)));
        myGraph.deleteVertex("A");

        assertEquals(new DirectedGraph(Set.of(new DirectedGraph.Vertex("B"),
                new DirectedGraph.Vertex("C")), Set.of()), myGraph);
    }

    @Test
    public void changeVertexName() {
        DirectedGraph myGraph = new DirectedGraph(Set.of(new DirectedGraph.Vertex("A"),
                new DirectedGraph.Vertex("B"),
                new DirectedGraph.Vertex("C")),
                Set.of(new DirectedGraph.Edge("A", "B", 5),
                        new DirectedGraph.Edge("C", "A", 3)));
        myGraph.changeVertexName("A", "F");

        DirectedGraph myGraph2 = myGraph;
        myGraph2.changeVertexName("P", "F");

        assertEquals(new DirectedGraph(Set.of(new DirectedGraph.Vertex("F"),
                new DirectedGraph.Vertex("B"),
                new DirectedGraph.Vertex("C")),
                Set.of(new DirectedGraph.Edge("F", "B", 5),
                        new DirectedGraph.Edge("C", "F", 3))), myGraph);
        assertEquals(myGraph, myGraph2);
    }

    @Test
    public void addEdge() {
        DirectedGraph myGraph = new DirectedGraph();
        myGraph.addEdge("A", "B", 1);
        myGraph.addEdge("A", "C", 2);
        myGraph.addEdge("B", "C", 3);
        myGraph.addEdge("B", "C", 1000);

        assertEquals(new DirectedGraph(Set.of(), Set.of(
                new DirectedGraph.Edge("A", "B", 1),
                new DirectedGraph.Edge("A", "C", 2),
                new DirectedGraph.Edge("B", "C", 3)
        )), myGraph);
    }

    @Test
    public void deleteEdge() {
        DirectedGraph myGraph = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 5),
                new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2)));
        myGraph.deleteEdge("A", "B");

        assertEquals(new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2))), myGraph);
    }

    @Test
    public void changeEdgeValue() {
        DirectedGraph myGraph = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 5),
                new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2)));
        myGraph.changeEdgeValue("A", "B", 100);

        DirectedGraph myGraph2 = myGraph;
        myGraph2.changeEdgeValue("P", "H", 42);

        assertEquals(new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 100),
                new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2))), myGraph);
        assertEquals(myGraph, myGraph2);
    }

    @Test
    public void getOutgoingEdges() {
        DirectedGraph myGraph = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 5),
                new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2)));

        assertEquals(Set.of(new DirectedGraph.Edge("A", "B", 5),
                new DirectedGraph.Edge("A", "C", 2)), myGraph.getOutgoingEdges("A"));

        assertEquals(Set.of(), myGraph.getOutgoingEdges("C"));
    }

    @Test
    public void getIngoingEdges() {
        DirectedGraph myGraph = new DirectedGraph(Set.of(), Set.of(new DirectedGraph.Edge("A", "B", 5),
                new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2)));

        assertEquals(Set.of(new DirectedGraph.Edge("B", "C", 1),
                new DirectedGraph.Edge("A", "C", 2)), myGraph.getIngoingEdges("C"));

        assertEquals(Set.of(), myGraph.getIngoingEdges("A"));
    }
}
