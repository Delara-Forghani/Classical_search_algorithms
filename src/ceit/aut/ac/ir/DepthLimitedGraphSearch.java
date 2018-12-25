package ceit.aut.ac.ir;

import java.util.ArrayList;

public class DepthLimitedGraphSearch extends Problem {

    private Node root;
    private int limit;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private boolean cutoff;
    private String result;


    public DepthLimitedGraphSearch(Graph graph) {
        super(graph);
        root = super.initialNode;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String search() {
        openList.add(root);
        result = recursive_dls(root, limit);
        return result;
    }


    public String recursive_dls(Node node, int limit) {
        boolean frontierExist;
        boolean exploredExist;
        if (goalTest(node)) {
            return "solution";
        } else if (limit == 0) {
            cutoff = true;
            return "cutoff";
        } else {
            openList.remove(node);
            closedList.add(node);
            cutoff = false;
            for (int i = 0; i < node.connections.size(); i++) {
                frontierExist = false;
                exploredExist = false;
                Node child = node.connections.get(i).end;
                if (openList.contains(child)) {
                    frontierExist = true;

                }
                if (closedList.contains(child)) {
                    exploredExist = true;
                }
                if (exploredExist) {
                    continue;
                } else if (!frontierExist) {
                    child.parentNode = node;
                    openList.add(child);
                    result = recursive_dls(child, limit - 1);
                } else if (frontierExist) {
                    child.parentNode = node;
                    result = recursive_dls(child, limit - 1);
                }
                if (result.equals("cutoff")) {
                    cutoff = true;
                } else if (!result.equals("failure")) {
                    return result;
                }
            }
            if (cutoff) {
                return "cutoff";
            } else {
                return "failure";
            }
        }
    }


    public String getResult() {
        return result;
    }
}
