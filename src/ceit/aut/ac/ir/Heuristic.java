package ceit.aut.ac.ir;

public class Heuristic {
    private Node source;
    private int heuristic;

    public Heuristic(Node s, int h) {
        source = s;
        heuristic = h;
    }

    public Node getNode() {
        return source;
    }

    public int getHeuristic() {
        return heuristic;
    }

}
