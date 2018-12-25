package ceit.aut.ac.ir;

import java.util.ArrayList;

public class UniformCostGraphSearch extends Problem {
    private Node root;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private int frontiersNum;

    public UniformCostGraphSearch(Graph graph) {
        super(graph);
        root = super.initialNode;
        frontiersNum = 0;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();

    }

    public void search() {
        openList.add(root);
        frontiersNum++;
        while (true) {

            if (openList.size() == 0) {
                System.out.println("Failure");
                return;
            } else {
                sort(openList);
                Node temp = openList.remove(0);
                if (goalTest(temp)) {
                    printValues(temp);
                    System.out.println(setPathCost(super.path));
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
                            frontiersNum++;

                        }
                    }
                }
            }

        }


    }

    public void printValues(Node goal) {
        Node parent = goal;
        System.out.println("Frontiers: " + frontiersNum);
        System.out.println("Explored : " + closedList.size());
        while (parent != root) {
            super.path.add(parent);
            parent = parent.parentNode;
        }
        super.path.add(root);
        for (int i = path.size() - 1; i >= 0; i--) {
            System.out.println(path.get(i).name);
        }
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
