package ceit.aut.ac.ir;

public class Edge {
    public Node start;
    public Node end;
    public int cost;

    public Edge(Node start, Node end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Node getEnd() {
        return end;
    }

}
