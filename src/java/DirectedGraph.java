import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class DirectedGraph {
    //вершина
    public static class Vertex {
        private String name;

        public Vertex (String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Vertex)) return false;
            return Objects.equals(this.name, ((Vertex) obj).name);
        }
    }

    //дуга
    public static class Edge {
        private final Vertex begin;
        private final Vertex end;
        private int value;

        public Edge (Vertex begin, Vertex end, int value) {
            this.begin = begin;
            this.end = end;
            if (value <= 0) throw new IllegalArgumentException();
            this.value = value;

        }

        public Edge (String begin, String end, int value) {
            this.begin = new Vertex(begin);
            this.end = new Vertex(end);
            if (value <= 0) throw new IllegalArgumentException();
            this.value = value;

        }

        @Override
        public int hashCode() {
            return begin.hashCode() + end.hashCode() + value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Edge)) return false;
            Edge arc = (Edge) obj;
            return value == arc.value && begin.equals(arc.begin) && end.equals(arc.end);
        }

    }

    public DirectedGraph () {}

    public DirectedGraph (Set<Vertex> vertexes, Set<Edge> edges) {
        this.vertexes.addAll(vertexes);
        for (Edge edge: edges) this.addEdge(edge.begin.name, edge.end.name, edge.value);
    }

    private final HashSet <Vertex> vertexes = new HashSet<Vertex>();
    private final HashSet <Edge> edges = new HashSet<Edge>();

    //получение вершины по имени
    private Vertex getVertex(String name) {
        for (Vertex v : vertexes) {
            if (name.equals(v.name)) return v;
        }
        return null;
    }

    //получение дуги по именам вершин
    private Edge getEdge(String begin, String end) {
        for (Edge e : edges) {
            if (e.begin.name.equals(begin) & e.end.name.equals(end)) return e;
        }
        return null;
    }

    //добавление вершины
    public void addVertex(String name) {
        vertexes.add((new Vertex(name)));
    }

    //удаление вершины
    public void deleteVertex(String name) {
        try {
            edges.removeAll(getIngoingEdges(name));
            edges.removeAll(getOutgoingEdges(name));
            vertexes.remove(this.getVertex(name));
        }
        catch (NullPointerException e) {}
    }

    //изменение имени вершины
    public void changeVertexName(String oldName, String newName) {
        try {
            this.getVertex(oldName).name = newName;
        }
        catch (NullPointerException e) {}
    }

    //добавление дуги
    public void addEdge(String begin, String end, int value) {
        if (!begin.equals(end) && this.getEdge(begin, end) == null){
            this.addVertex(begin);
            this.addVertex(end);
            edges.add(new Edge(getVertex(begin), getVertex(end), value));
        }
    }

    //удаление дуги
    public void deleteEdge(String begin, String end) {
        edges.remove(getEdge(begin, end));
    }

    //изменение веса дуги
    public void changeEdgeValue (String begin, String end, int newValue) {
        try {
            if (newValue > 0) getEdge(begin, end).value = newValue;
        }
        catch (NullPointerException e) {}
    }

    //получение списка исходящих из вершины дуг по имени вершины
    public HashSet<Edge> getOutgoingEdges (String name) {
        HashSet<Edge> result = new HashSet<>();
        for (Edge edge: edges) {
            if (edge.begin.equals(getVertex(name))) {
                result.add(edge);
            }
        }
        return result;
    }

    //получение списка входящих в вершину дуг по имени вершины
    public HashSet<Edge> getIngoingEdges (String name) {
        HashSet<Edge> result = new HashSet<>();
        for (Edge edge: edges) {
            if (edge.end.equals(getVertex(name))) {
                result.add(edge);
            }
        }
        return result;
    }

    @Override
    public int hashCode() {
        return vertexes.hashCode() + edges.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DirectedGraph)) return false;
        DirectedGraph graph = (DirectedGraph) obj;
        return edges.equals(graph.edges) && vertexes.equals(graph.vertexes);
    }
}
