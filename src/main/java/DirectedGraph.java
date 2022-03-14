import java.util.*;

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
        Vertex v = new Vertex(name);
        if (!vertexes.contains(v)) vertexes.add((new Vertex(name)));
    }

    //удаление вершины
    public void deleteVertex(String name) {
        edges.removeAll(getIngoingEdges(name));
        edges.removeAll(getOutgoingEdges(name));
        vertexes.remove(this.getVertex(name));
    }

    //изменение имени вершины
    public void changeVertexName(String oldName, String newName) {
        for (Edge edge: this.getOutgoingEdges(oldName)) edge.begin.name = newName;
        for (Edge edge: this.getIngoingEdges(oldName)) edge.end.name = newName;
        this.getVertex(oldName).name = newName;
    }

    //добавление дуги
    public void addEdge(String begin, String end, int value) {
        if (this.getEdge(begin, end) == null){
            this.addVertex(begin);
            this.addVertex(end);
            edges.add(new Edge(begin, end, value));
        }
    }

    //удаление дуги
    public void deleteEdge(String begin, String end) {
        edges.remove(getEdge(begin, end));
    }

    //изменение веса дуги
    public void changeEdgeValue (String begin, String end, int newValue) {
        if (newValue > 0) getEdge(begin, end).value = newValue;
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

    @Override
    public int hashCode() {
        return vertexes.hashCode() + edges.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DirectedGraph)) return false;
        DirectedGraph graph = (DirectedGraph) obj;
        //return edges.equals(graph.edges) && vertexes.equals(graph.vertexes);
        return edges.containsAll(graph.edges) && graph.edges.containsAll(edges) && vertexes.containsAll(graph.vertexes)
                && graph.vertexes.containsAll(vertexes);
    }

//    public void getVertexes() {
//        for (Vertex v : vertexes) System.out.print(v.name + " ");
//        System.out.println();
//    }
//
//    public void getEdges() {
//        for (Edge e : edges) System.out.println(e.begin.name + e.end.name + e.value);
//    }
}

