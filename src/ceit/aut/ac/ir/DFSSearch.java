package ceit.aut.ac.ir;

import java.util.ArrayList;

public class DFSSearch extends Problem {
    private Node root;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private int frontiersNum;

    public DFSSearch(Graph graph) {
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

    public void search(boolean graphSearch) {
        boolean frontierExist;
        boolean exploredExist;
        if (goalTest(root)) {
            System.out.println("Here is Bucharest");
            return;
        } else {
            openList.add(root);
            frontiersNum++;
        }

        while (true) {
            if (openList.size() == 0) {
                System.out.println("failure");
                return;
            } else {
                Node temp = openList.remove(openList.size() - 1);
                if (graphSearch) {
                    closedList.add(temp);
                }
                for (int i = 0; i < temp.connections.size(); i++) {
                    frontierExist = false;
                    exploredExist = false;
                    Node child = temp.connections.get(i).getEnd();
                    for (int j = 0; j < openList.size(); j++) {
                        if (openList.get(j).name.equals(child.name)) {
                            frontierExist = true;
                            break;
                        }
                    }
                    if (!graphSearch) {
                        exploredExist = false;
                    } else {
                        for (int j = 0; j < closedList.size(); j++) {
                            if (closedList.get(j).name.equals(child.name)) {
                                exploredExist = true;
                                break;
                            }
                        }
                    }

                    if (!frontierExist && !exploredExist) {
                        if (goalTest(child)) {
                            Edge currentEdge = temp.connections.get(i);
                            child.parentNode = temp;
                            child.cost = temp.cost + stepCost(child.parentNode, currentEdge, child);
                            printValues(child);
                            System.out.println(setPathCost(super.path));

                            return;
                        } else {
                            child.parentNode = temp;
                            Edge currentEdge = temp.connections.get(i);
                            child.cost = temp.cost + stepCost(child.parentNode, currentEdge, child);
                            openList.add(child);
                            frontiersNum++;
                        }
                    }
                }
            }
        }


    }

    public void printValues(Node goal) {
        Node parent = goal;
        System.out.println("Frontiers : " + frontiersNum);
        System.out.println("Explored: " + closedList.size());
        while (!parent.name.equals(root.name)) {
            super.path.add(parent);
            parent = parent.parentNode;
        }
        super.path.add(root);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).name);
        }
        System.out.println("Allocated memory size in the end: " + (openList.size() + closedList.size()));
    }

}
