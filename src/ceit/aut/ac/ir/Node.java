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
        //childName.parentNode = fatherName;



    }


    public void printConnections() {
        System.out.print(name + " :   ");
        for (int i = 0; i < connections.size(); i++) {
            System.out.print(connections.get(i).end.name + "*****");
        }
    }

    public void setHeuristic(int h) {
        this.heuristic = h;
    }

    public int getHeuristic() {
        return heuristic;
    }
}
