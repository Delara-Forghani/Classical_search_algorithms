package ceit.aut.ac.ir;


import java.util.ArrayList;

public class AStarGraphSearch extends Problem {
    private Node root;
    private int pathCost;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;


    public AStarGraphSearch(Graph graph) {
        super(graph);
        root = super.initialNode;
        pathCost = super.pathCost;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();
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
                if (goalTest(temp)) {
                    printValues(temp);
                    System.out.println("Arrived in Bucharest");
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
                                    openList.get(j).cost = cost;

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


    public void sort(ArrayList<Node> frontiers) {
        int n = frontiers.size();
        for (int i = 1; i < n; ++i) {
            Node key = frontiers.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && frontiers.get(j).getHeuristic() + frontiers.get(j).cost > key.getHeuristic() + key.cost) {
                frontiers.set(j + 1, frontiers.get(j));
                j = j - 1;
            }
            frontiers.set(j + 1, key);
        }
    }

    public void printValues(Node goal) {
        for (int i = 0; i < closedList.size(); i++) {
            int f_value=closedList.get(i).cost+closedList.get(i).getHeuristic();
            System.out.println(closedList.get(i).name + " " + f_value);
        }
        System.out.println(goal.name+" "+goal.cost);
    }


}