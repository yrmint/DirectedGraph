import java.util.HashSet;

public class DirectedGraph {
    public static class Vertex {
        String name;

        Vertex (String name) {
            this.name = name;
        }
    }


    public static class Edge {
        Vertex begin;
        Vertex end;
        int value;

        Edge (Vertex begin, Vertex end, int value) {
            this.begin = begin;
            this.end = end;
            this.value = value;
        }
    }

    private HashSet <Vertex> verticies = new HashSet<Vertex>();
    private HashSet <Edge> edges = new HashSet<Edge>();

    public Vertex getVertex(String name) {
        for (Vertex v : verticies) {
            if (name == v.name) return v;
        }
        return null;
    }

    public Edge getEdge(String begin, String end) {
        for (Edge e : edges) {
            if (e.begin.name == begin & e.end.name == end) return e;
        }
        return null;
    }

    public void addVertex(String name) {
        if (this.getVertex(name) == null) {
            verticies.add((new Vertex(name)));
            System.out.println("Вершина " + name + " создана");
        }
        else System.out.println("Вершина с именем " + name + " уже существует");
    }

    public void deleteVertex(String name) {
        if (this.getVertex(name) != null) {
            verticies.remove(this.getVertex(name));
            System.out.println("Вершина " + name + " удалена");
        }
        else System.out.println("Вершины с именем " + name + " не существует");
    }

    public void changeVertexName(String oldName, String newName) {
        if (this.getVertex(oldName) != null) {
            this.getVertex(oldName).name = newName;
            System.out.println("Вершина " + oldName + " переименована в " + newName);
        }
        else System.out.println("Вершины с именем " + oldName + " не существует");
    }

    public void addEdge(String begin, String end, int value) {
        if (this.getEdge(begin, end) == null) {
            if (this.getVertex(begin) == null) this.addVertex(begin);
            if (this.getVertex(end) == null) this.addVertex(end);
            edges.add(new Edge(getVertex(begin), getVertex(end), value));
            System.out.println("Ребро из " + begin + " в " + end + " весом " + value + " создано");
        }
        else System.out.println("Ребро из " + begin + " в " + end + " уже существует");
    }

    public void getVerticies() {
        for (Vertex v : verticies) System.out.print(v.name + " ");
        System.out.println();
    }

    public void getEdges() {
        for (Edge e : edges) System.out.println("Из " + e.begin.name + " в " + e.end.name + ", вес " + e.value);
    }

    public static void main (String[] args) {
        DirectedGraph myGraph = new DirectedGraph();
        myGraph.addVertex("A");
        myGraph.addVertex("A");
        myGraph.addVertex("B");
        myGraph.getVerticies();
        myGraph.addEdge("L", "B", 2);
        myGraph.addEdge("L", "B", 2);
        myGraph.addEdge("B", "L", 2);
        myGraph.getEdges();
        System.out.println();
        myGraph.changeVertexName("L", "I");
        myGraph.getEdges();
    }
}
