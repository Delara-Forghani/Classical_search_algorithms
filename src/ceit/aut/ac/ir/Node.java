package ceit.aut.ac.ir;

import java.util.ArrayList;

public class Node {
    public Node parentNode;
    public String name;
    public ArrayList<Edge> connections;
    public int cost;
    private int heuristic;

    public Node(String name) {
        parentNode = null;
        this.name = name;
        connections = new ArrayList<>();
        heuristic = 0;
        this.cost = 0;
    }

    public void addAction(Node fatherName, Node childName, int cost) {
        connections.add(new Edge(fatherName, childName, cost));

    }


    public void setHeuristic(int h) {
        this.heuristic = h;
    }

    public int getHeuristic() {
        return heuristic;
    }
}
