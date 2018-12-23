package ceit.aut.ac.ir;

import java.io.*;
import java.util.ArrayList;

public class Problem {
    public Node initialNode;
    public Node goal;
    public ArrayList<Edge> path;
    public int pathCost;

    public Problem(Graph graph) {
        initialNode = graph.getNode("Arad");
        goal = graph.getNode("Bucharest");
        path = new ArrayList<>();
        pathCost = 0;
    }


//    public static Node setInitNode(Node init) {
//        initialNode = init;
//        return initialNode;
//    }

//    public Node setGoalNode(Node goal) {
//        this.goal=goal;
//        return goal;
//    }

    public ArrayList<Edge> actions(Node currentState) {
        return currentState.connections;
    }

    public Node result(Node currentState, Edge action) {
        if (currentState.connections.contains(action)) {
            path.add(action);
            return action.end;
        }
        return null;
    }

    public boolean goalTest(Node currentState) {
        if (currentState.name.equals(goal.name)) {
            return true;
        }
        return false;
    }

    public int stepCost(Node firstState, Edge action, Node secondState) {
        for (int i = 0; i < firstState.connections.size(); i++) {
            if (firstState.connections.get(i).cost == action.cost
                    && firstState.connections.get(i).end.name.equals(action.end.name) &&
                    action.end.name.equals(secondState.name)) {
                return firstState.connections.get(i).cost;

            }
        }
        return 0;
    }

    public int pathCost(ArrayList<Edge> path) {
        pathCost = 0;
        for (int i = 0; i < path.size(); i++) {
            pathCost += path.get(i).cost;
        }
        return pathCost;
    }


    public void setHeuristic(Graph graph) {
        File myFile = new File("E:\\Computer_Network\\DiffernetSearchAlgorithms\\src\\ceit\\aut\\ac\\ir\\Heuristics.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String st;
            while ((st = reader.readLine()) != null) {
                String[] stArr = st.split(" ");
                for (int i = 0; i < graph.nodes.size(); i++) {
                    if (graph.nodes.get(i).name.equals(stArr[0])) {
                        graph.nodes.get(i).setHeuristic(Integer.parseInt(stArr[1]));
                        break;
                    }
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

