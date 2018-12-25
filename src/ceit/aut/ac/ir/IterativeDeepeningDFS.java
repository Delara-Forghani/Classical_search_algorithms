package ceit.aut.ac.ir;

public class IterativeDeepeningDFS extends Problem {
    Node root;
    DepthLimitedGraphSearch dls;
    String result;

    public IterativeDeepeningDFS(Graph graph) {
        super(graph);
        root = super.initialNode;
        result = search(graph);
    }

    public String search(Graph graph) {
        int count = 0;
        dls = new DepthLimitedGraphSearch(graph);
        while (true) {
            dls.setLimit(count);
            String temp = dls.search();
            if (!temp.equals("cutoff")) {
                return temp;
            } else {
                count++;
            }
        }
    }
}
