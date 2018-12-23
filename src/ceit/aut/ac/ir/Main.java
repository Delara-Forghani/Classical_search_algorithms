package ceit.aut.ac.ir;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        EnterInput input = new EnterInput("E:\\Computer_Network\\DiffernetSearchAlgorithms\\src\\ceit\\aut\\ac\\ir\\input.txt");
        Graph graph = input.getGraph();
        Problem problem = new Problem(graph);
//        for (int i = 0; i < graph.nodes.size(); i++) {
//            System.out.println(graph.nodes.get(i).name + " : "+ graph.nodes.get(i));
//        }

//        for (int i = 0; i < graph.nodes.size(); i++) {
//            graph.nodes.get(i).printConnections();
//            System.out.println();
//        }

//        BFSGraphSearch bfsGraphSearch = new BFSGraphSearch(graph);
//        bfsGraphSearch.search();

//        UniformCostGraphSearch uniformCostGraphSearch=new UniformCostGraphSearch(graph);
//        uniformCostGraphSearch.search();


        //  DFSGraphSearch dfsGraphSearch = new DFSGraphSearch(graph);
        //  dfsGraphSearch.search();


        // GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(graph);
        // greedyAlgorithm.search();

        AStarGraphSearch aStarGraphSearch=new AStarGraphSearch(graph);
        aStarGraphSearch.search();
    }

}
