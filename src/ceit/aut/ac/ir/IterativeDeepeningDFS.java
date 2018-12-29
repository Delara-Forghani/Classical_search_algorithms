package ceit.aut.ac.ir;

public class IterativeDeepeningDFS extends Problem {
    Node root;
    DepthLimitedSearch dls;
    String result;

    public IterativeDeepeningDFS(Graph graph, boolean graphSearch) {
        super(graph);
        root = super.initialNode;
        result = search(graph, graphSearch);
    }

    public String search(Graph graph, boolean graphSearch) {
        int count = 0;
        while (true) {
            dls = new DepthLimitedSearch(graph);
            dls.setLimit(count);
            String temp = dls.search(graphSearch);
            if (!temp.equals("cutoff")) {
                return temp;
            } else {
                count++;
            }
        }
    }
}
