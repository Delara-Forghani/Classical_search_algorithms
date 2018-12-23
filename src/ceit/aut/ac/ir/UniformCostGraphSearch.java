package ceit.aut.ac.ir;

import java.util.ArrayList;

public class UniformCostGraphSearch extends Problem {
    private Node root;
    private int pathCost;
    private ArrayList<Edge> frontierPath;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;

    public UniformCostGraphSearch(Graph graph) {
        super(graph);
        root = super.initialNode;
        pathCost = super.pathCost;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();

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
                if (goalTest(temp)) {
                   printValues(temp);
                   return;

                } else {
                    closedList.add(temp);
                    boolean frontierExist;
                    boolean exploredExist;
                    for (int i = 0; i < temp.connections.size(); i++) {
                        frontierExist = false;
                        exploredExist = false;
                        Node child = temp.connections.get(i).end;

                        for (int j = 0; j < openList.size(); j++) {

                            if (openList.get(j).name.equals(child.name)) {
                                int cost = temp.cost + temp.connections.get(i).cost;
                                if (openList.get(j).cost > cost) {
                                    openList.get(j).parentNode = temp;
                                    Edge currentEdge = temp.connections.get(i);
                                    openList.get(j).cost = temp.cost + stepCost(temp, currentEdge, openList.get(j));
                                }
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
                            child.parentNode = temp;
                            Edge currentEdge = temp.connections.get(i);
                            child.cost = temp.cost + stepCost(child.parentNode, currentEdge, child);
                            openList.add(child);

                        }
                    }
                }
            }

        }


    }

    public void printValues(Node goal) {
        for (int i = 0; i < closedList.size(); i++) {
            System.out.println(closedList.get(i).name + " " + closedList.get(i).cost);
        }
        System.out.println(goal.name+" "+goal.cost);
    }

    void sort(ArrayList<Node> list) {
        int n = list.size();
        for (int i = 1; i < n; ++i) {
            Node key = list.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && list.get(j).cost > key.cost) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }
            list.set(j + 1, key);
        }
    }
}
