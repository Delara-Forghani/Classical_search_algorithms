package ceit.aut.ac.ir;

import java.util.ArrayList;

public class DFSGraphSearch extends Problem {
    private Node root;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;


    public DFSGraphSearch(Graph graph) {
        super(graph);
        root = super.initialNode;
        openList = new ArrayList<>();
        closedList = new ArrayList<>();

    }

    public void search() {
        boolean frontierExist;
        boolean exploredExist;
        if (goalTest(root)) {
            System.out.println("Reached to Bucharest");
            return;
        } else {
            openList.add(root);
        }

        while (true) {
            if (openList.size() == 0) {
                System.out.println("failure");
                return;
            } else {
                Node temp = openList.remove(openList.size() - 1);
                System.out.println(temp.name);

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
                            System.out.println("Arrived in Bucharest");
                            return;
                        } else {
                            openList.add(child);
                        }
                    }
                }
            }
        }


    }


}
