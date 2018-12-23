package ceit.aut.ac.ir;

import java.util.ArrayList;

public class Graph {
    public ArrayList<Edge> edges;
    public ArrayList<Node> nodes;

    public Graph() {
        edges = new ArrayList<>();
        nodes = new ArrayList<>();
    }

    public void addEdge(Edge e) {
        edges.add(e);
    }


    public void addNode(Node n) {
        nodes.add(n);
    }

    public Node getNode(String name) {
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).name.equals(name)){
                return nodes.get(i);
            }
        }
        return null;
    }


}
