package ceit.aut.ac.ir;

import java.io.*;
import java.util.ArrayList;

public class GreedyAlgorithm extends Problem {
    private Node root;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private int frontiersNum;

    public GreedyAlgorithm(Graph graph) {
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
        setHeuristic(graph);
        super.setHeuristic(graph);
    }


    public void search(boolean graphSearch) {
        openList.add(root);
        frontiersNum++;
        while (true) {

            if (openList.size() == 0) {
                System.out.println("Failure");
                return;
            } else {
                sort(openList);
                Node temp = openList.remove(0);
                if (graphSearch) {
                    closedList.add(temp);
                }
                boolean frontierExist;
                boolean exploredExist;
                for (int i = 0; i < temp.connections.size(); i++) {
                    frontierExist = false;
                    exploredExist = false;
                    Node child = temp.connections.get(i).end;

                    for (int j = 0; j < openList.size(); j++) {
                        if (openList.get(j).name.equals(child.name)) {
                            openList.get(j).parentNode = temp;

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
                            child.parentNode = temp;
                            printValues(child);
                            return;
                        } else {
                            child.parentNode = temp;
                            openList.add(child);
                            frontiersNum++;
                        }


                    }
                }
            }
        }

    }


    public void sort(ArrayList<Node> frontiers) {
        int n = frontiers.size();
        for (int i = 1; i < n; ++i) {
            Node key = frontiers.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && frontiers.get(j).getHeuristic() > key.getHeuristic()) {
                frontiers.set(j + 1, frontiers.get(j));
                j = j - 1;
            }
            frontiers.set(j + 1, key);
        }
    }

    public void printValues(Node goal) {
        Node parent = goal;
        System.out.println("Frontierss: " + frontiersNum);
        System.out.println("Explored : " + closedList.size());
        while (!parent.name.equals(root.name)) {
            super.path.add(parent);
            parent = parent.parentNode;
        }
        super.path.add(root);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).name);
        }
    }
}
