import java.util.*;

public class DirectedGraph {
    //вершина
    public static class Vertex {
        private String name;

        public Vertex (String name) {
            if (name == null) throw new IllegalArgumentException();
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

        public Edge (String begin, String end, int value) {
            if (value <= 0 || begin == null || end == null) throw new IllegalArgumentException();
            this.begin = new Vertex(begin);
            this.end = new Vertex(end);
            this.value = value;

        }

        @Override
        public int hashCode() {
            return begin.hashCode() + end.hashCode() + value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Edge)) return false;
            Edge edge = (Edge) obj;
            return value == edge.value && begin.equals(edge.begin) && end.equals(edge.end);
        }

    }

    public DirectedGraph () {}

    public DirectedGraph (List<Vertex> vertexes, List<Edge> edges) {
        for (Vertex vertex: vertexes) this.addVertex(vertex.name);
        for (Edge edge: edges) this.addEdge(edge.begin.name, edge.end.name, edge.value);
    }

    private final List<Vertex> vertexes = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    @Override
    public int hashCode() {
        return vertexes.hashCode() + edges.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DirectedGraph)) return false;
        DirectedGraph graph = (DirectedGraph) obj;
        return edges.containsAll(graph.edges) && graph.edges.containsAll(edges) && vertexes.containsAll(graph.vertexes)
                && graph.vertexes.containsAll(vertexes);
    }

    //получение вершины по имени
    private Vertex getVertex(String name) {
        if (!vertexes.contains(new Vertex(name))) throw new IllegalArgumentException();
        for (Vertex v : vertexes) {
            if (name.equals(v.name)) return v;
        }
        return null;
    }

    //получение дуги по именам вершин
    private Edge getEdge(String begin, String end) {
        if (!vertexes.contains(new Vertex(begin)) || !vertexes.contains(new Vertex(end))) throw new IllegalArgumentException();
        for (Edge e : edges) {
            if (e.begin.name.equals(begin) & e.end.name.equals(end)) return e;
        }
        return null;
    }

    //добавление вершины
    public void addVertex(String name) {
        if (name == null) throw new IllegalArgumentException();
        Vertex v = new Vertex(name);
        if (!vertexes.contains(v)) vertexes.add(v);
    }

    //удаление вершины
    public void deleteVertex(String name) {
        edges.removeAll(getIngoingEdges(name));
        edges.removeAll(getOutgoingEdges(name));
        vertexes.remove(this.getVertex(name));
    }

    //изменение имени вершины
    public void changeVertexName(String oldName, String newName) {
        if (vertexes.contains(new Vertex(newName))) throw new IllegalArgumentException();
        for (Edge edge: this.getOutgoingEdges(oldName)) edge.begin.name = newName;
        for (Edge edge: this.getIngoingEdges(oldName)) edge.end.name = newName;
        this.getVertex(oldName).name = newName;
    }

    //добавление дуги
    public void addEdge(String begin, String end, int value) {
        Edge edge = new Edge(begin, end, value);
        if (!edges.contains(edge)){
            this.addVertex(begin);
            this.addVertex(end);
            edges.add(edge);
        }
    }

    //удаление дуги
    public void deleteEdge(String begin, String end) {
        edges.remove(getEdge(begin, end));
    }

    //изменение веса дуги
    public void changeEdgeValue (String begin, String end, int newValue) {
        if (newValue <= 0) throw new IllegalArgumentException();
        getEdge(begin, end).value = newValue;
    }

    //получение списка исходящих из вершины дуг по имени вершины
    public List<Edge> getOutgoingEdges (String name) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge: edges) {
            if (edge.begin.equals(getVertex(name))) {
                result.add(edge);
            }
        }
        return result;
    }

    //получение списка входящих в вершину дуг по имени вершины
    public List<Edge> getIngoingEdges (String name) {
        List<Edge> result = new ArrayList<>();
        for (Edge edge: edges) {
            if (edge.end.equals(getVertex(name))) {
                result.add(edge);
            }
        }
        return result;
    }
}


