package ceit.aut.ac.ir;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        EnterInput input = new EnterInput("E:\\Computer_Network\\DiffernetSearchAlgorithms\\src\\ceit\\aut\\ac\\ir\\input.txt");
        Graph graph = input.getGraph();
        Problem problem = new Problem(graph);


//        BFSSearch bfsGraphSearch = new BFSSearch(graph);
//        bfsGraphSearch.search(true);
//        BFSSearch bfsTreeSearch = new BFSSearch(graph);
//        bfsTreeSearch.search(false);

//        UniformCostSearch uniformCostGraphSearch = new UniformCostSearch(graph);
//        uniformCostGraphSearch.search(true);
//        UniformCostSearch uniformCostTreeSearch = new UniformCostSearch(graph);
//        uniformCostTreeSearch.search(false);

//        DFSSearch dfsGraphSearch = new DFSSearch(graph);
//        dfsGraphSearch.search(true);
//        DFSSearch dfsTreeSearch = new DFSSearch(graph);
//        dfsTreeSearch.search(false);

//         GreedyAlgorithm greedyAlgorithmGS = new GreedyAlgorithm(graph);
//         greedyAlgorithmGS.search(true);
//         GreedyAlgorithm greedyAlgorithmTS = new GreedyAlgorithm(graph);
//         greedyAlgorithmTS.search(false);

//         AStarSearch aStarGraphSearch = new AStarSearch(graph);
//         aStarGraphSearch.search(true);
//         AStarSearch aStarTreeSearch = new AStarSearch(graph);
//         aStarTreeSearch.search(false);


//         DepthLimitedSearch limitedGraphSearch = new DepthLimitedSearch(graph);
//         limitedGraphSearch.setLimit(3);
//         limitedGraphSearch.search(true);

//        DepthLimitedSearch limitedTreeSearch = new DepthLimitedSearch(graph);
//        limitedTreeSearch.setLimit(3);
//        limitedTreeSearch.search(false);

//         IterativeDeepeningDFS iterativeDeepGraphDFS = new IterativeDeepeningDFS(graph,true);
//         IterativeDeepeningDFS iterativeDeepTreeDFS = new IterativeDeepeningDFS(graph,false);
    }

}
