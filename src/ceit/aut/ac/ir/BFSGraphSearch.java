package ceit.aut.ac.ir;

import java.util.ArrayList;

public class BFSGraphSearch extends Problem {
    private Node root;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private int frontiersNum;


    public BFSGraphSearch(Graph graph) {
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


    public void search() {

        boolean frontierExist;
        boolean exploredExist;
        if (goalTest(root)) {
            System.out.println("Arrived in Bucharest");
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
                Node temp = openList.remove(0);
                closedList.add(temp);

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
                    for (int j = 0; j < closedList.size(); j++) {
                        if (closedList.get(j).name.equals(child.name)) {
                            exploredExist = true;
                            break;

                        }
                    }
                    if (!frontierExist && !exploredExist) {
                        if (goalTest(child)) {
                            child.parentNode = temp;
                            Edge currentEdge = temp.connections.get(i);
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
        Node tempNode=goal;
        System.out.println("Frontiers: " + frontiersNum);
        System.out.println("Explored: " + closedList.size());
        while (tempNode != root) {
            super.path.add(tempNode);
            tempNode = tempNode.parentNode;
        }
        super.path.add(root);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).name);
        }
    }

}
