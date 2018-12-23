package ceit.aut.ac.ir;

import java.io.*;
import java.util.ArrayList;

public class GreedyAlgorithm extends Problem {
    private Node root;
    private int pathCost;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    public GreedyAlgorithm(Graph graph) {
        super(graph);
        root = super.initialNode;
        pathCost = super.pathCost;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        setHeuristic(graph);
//        for (int i = 0; i < graph.nodes.size(); i++) {
//            System.out.println(graph.nodes.get(i).name + " : " + graph.nodes.get(i).getHeuristic());
//        }

        super.setHeuristic(graph);
    }


    public void search() {
        openList.add(root);

        while (true) {

            if (openList.size() == 0) {
                System.out.println("Failure");
                return;
            } else {
                sort(openList);
                Node temp = openList.remove(0);
                System.out.println(temp.name);
                closedList.add(temp);
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

                    for (int j = 0; j < closedList.size(); j++) {
                        if (closedList.get(j).name.equals(child.name)) {
                            exploredExist = true;
                            break;
                        }
                    }
                    if (!frontierExist && !exploredExist) {
                        if (goalTest(child)) {
                            System.out.println("Arrive in Bucharest");
                            return;
                        } else {
                            child.parentNode = temp;
                            openList.add(child);
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
}
