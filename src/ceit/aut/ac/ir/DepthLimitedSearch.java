package ceit.aut.ac.ir;

import java.util.ArrayList;

public class DepthLimitedSearch extends Problem {

    private Node root;
    private int limit;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private boolean cutoff;
    private String result;
    private int frontiersNum;


    public DepthLimitedSearch(Graph graph) {
        super(graph);
        for (int i = 0; i < graph.nodes.size(); i++) {
            for (int j = 0; j < graph.nodes.get(i).connections.size(); j++) {
                graph.nodes.get(i).connections.get(j).setCost(1);
            }
        }
        root = super.initialNode;
        frontiersNum = 0;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String search(boolean graphSearch) {
        openList.add(root);
        frontiersNum++;
        result = recursive_dls(root, limit, graphSearch);
        System.out.println(result);
        return result;
    }


    public String recursive_dls(Node node, int limit, boolean graphSearch) {
        boolean frontierExist;
        boolean exploredExist;
        if (goalTest(node)) {
            printValues(node);
            System.out.println(setPathCost(super.path));
            return "solution";
        } else if (limit == 0) {
            cutoff = true;
            return "cutoff";
        } else {
            openList.remove(node);
            if (graphSearch) {
                closedList.add(node);
            }
            cutoff = false;
            for (int i = 0; i < node.connections.size(); i++) {
                frontierExist = false;
                exploredExist = false;
                Node child = node.connections.get(i).end;
                if (openList.contains(child)) {
                    frontierExist = true;

                }
                if (!graphSearch) {
                    exploredExist = false;
                } else {
                    if (closedList.contains(child)) {
                        exploredExist = true;
                    }
                }
                if (exploredExist) {
                    continue;
                } else if (!frontierExist) {
                    child.parentNode = node;
                    Edge currentEdge = node.connections.get(i);
                    child.cost = node.cost + stepCost(node, currentEdge, child);
                    openList.add(child);
                    frontiersNum++;
                    result = recursive_dls(child, limit - 1, graphSearch);
                } else if (frontierExist) {
                    child.parentNode = node;
                    Edge currentEdge = node.connections.get(i);
                    child.cost = node.cost + stepCost(node, currentEdge, child);
                    frontiersNum++;
                    result = recursive_dls(child, limit - 1, graphSearch);
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


    public void printValues(Node goal) {
        Node parent = goal;
        System.out.println("Frontierss : " + frontiersNum);
        System.out.println("Explored: " + closedList.size());
        while (!parent.name.equals(root.name)) {
            super.path.add(parent);
            parent = parent.parentNode;
        }
        super.path.add(root);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).name);
        }
    }

    public String getResult() {
        return result;
    }
}
