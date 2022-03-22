import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class test {
    private final DirectedGraph myGraph = new DirectedGraph();
     @BeforeEach
    public void beforeEach() {
         myGraph.addEdge("a", "b", 3);
         myGraph.addEdge("a", "c", 4);
         myGraph.addEdge("b", "c", 5);
     }

     @Test
    public void addVertex() {
         //попытка добавить вершину с именем null
         assertThrows(IllegalArgumentException.class, () -> myGraph.addVertex(null));

         //попытка добавить уже существующую вершину
         myGraph.addVertex("a");
         assertEquals(new DirectedGraph(List.of(), List.of(new DirectedGraph.Edge("a", "b", 3),
                 new DirectedGraph.Edge("a", "c", 4),
                 new DirectedGraph.Edge("b", "c", 5))), myGraph);

         //добавление несуществующей вершины
         myGraph.addVertex("d");
         assertEquals(new DirectedGraph(List.of(new DirectedGraph.Vertex("d")),
                 List.of(new DirectedGraph.Edge("a", "b", 3),
                 new DirectedGraph.Edge("a", "c", 4),
                 new DirectedGraph.Edge("b", "c", 5))), myGraph);
     }

     @Test
    public void deleteVertex() {
         //попытка удалить несуществующую вершину
         assertThrows(IllegalArgumentException.class, () -> myGraph.deleteVertex("d"));
         assertThrows(IllegalArgumentException.class, () -> myGraph.deleteVertex(null));

         //удаление существующей вершины
         myGraph.deleteVertex("a");
         assertEquals(new DirectedGraph(List.of(), List.of(new DirectedGraph.Edge("b", "c", 5))), myGraph);
     }

     @Test
    public void changeVertexName() {
         //попытка поменять имя несуществующей вершины
         assertThrows(IllegalArgumentException.class, () -> myGraph.changeVertexName("d", "f"));

         //попытка поменять имя на null
         assertThrows(IllegalArgumentException.class, () -> myGraph.changeVertexName("a", null));

         //попытка поменять имя на уже занятое
         assertThrows(IllegalArgumentException.class, () -> myGraph.changeVertexName("b", "a"));

         //изменение имени существующей вершины
         myGraph.changeVertexName("b", "f");
         assertEquals(new DirectedGraph(List.of(), List.of(new DirectedGraph.Edge("a", "f", 3),
                 new DirectedGraph.Edge("a", "c", 4),
                 new DirectedGraph.Edge("f", "c", 5))), myGraph);
     }

     @Test
    public void addEdge() {
         //попытка добавить дугу с отрицательным весом
         assertThrows(IllegalArgumentException.class, () -> myGraph.addEdge("f", "s", -1000));

         //попытка добавить дугу с null в качестве имени начала/конца
         assertThrows(IllegalArgumentException.class, () -> myGraph.addEdge("a", null, 1));
         assertThrows(IllegalArgumentException.class, () -> myGraph.addEdge(null, "a", 1));

         //добавление дуги
         myGraph.addEdge("b", "d", 8);
         assertEquals(new DirectedGraph(List.of(), List.of(new DirectedGraph.Edge("a", "b", 3),
                 new DirectedGraph.Edge("a", "c", 4),
                 new DirectedGraph.Edge("b", "c", 5),
                 new DirectedGraph.Edge("b", "d", 8))), myGraph);

     }

     @Test
    public void deleteEdge() {
         //попытка удалить несуществующую вершину
         assertThrows(IllegalArgumentException.class, () -> myGraph.deleteVertex("d"));

         //удаление вершины
         myGraph.deleteVertex("a");
         assertEquals(new DirectedGraph(List.of(), List.of(new DirectedGraph.Edge("b", "c", 5))), myGraph);
     }

     @Test
    public void changeEdgeValue() {
         //попытка изменить вес на отрицательный
         assertThrows(IllegalArgumentException.class, () -> myGraph.changeEdgeValue("a", "b", -100));

         //попытка изменить вес несуществующей дуги
         assertThrows(IllegalArgumentException.class, () -> myGraph.changeEdgeValue("s", "g", 9));

         //изменение веса
         myGraph.changeEdgeValue("a", "b", 100);
         assertEquals(new DirectedGraph(List.of(), List.of(new DirectedGraph.Edge("a", "b", 100),
                 new DirectedGraph.Edge("a", "c", 4),
                 new DirectedGraph.Edge("b", "c", 5))), myGraph);
     }

     @Test
    public void getOutgoingEdges() {
         //попытка получить дуги из несуществующей вершины
         assertThrows(IllegalArgumentException.class, () -> myGraph.getOutgoingEdges("d"));

         //получение списка исходящих дуг
         assertEquals(List.of(new DirectedGraph.Edge("a", "b", 3),
                 new DirectedGraph.Edge("a", "c", 4)), myGraph.getOutgoingEdges("a"));
     }

     @Test
    public void getIngoingEdges() {
         //попытка получить дуги в несуществующую вершину
         assertThrows(IllegalArgumentException.class, () -> myGraph.getIngoingEdges("d"));

         //получение списка входящих дуг
         assertEquals(List.of(new DirectedGraph.Edge("a", "c", 4),
                 new DirectedGraph.Edge("b", "c", 5)), myGraph.getIngoingEdges("c"));
     }
}
