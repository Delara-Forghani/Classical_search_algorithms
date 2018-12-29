package ceit.aut.ac.ir;

import java.io.*;

public class EnterInput {
    Graph graph;

    public EnterInput(String address) {
        File myFile = new File(address);
        try {
            graph = new Graph();
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String st;
            boolean firstExist, secondExist;
            int firstIndex, secondIndex;
            while ((st = reader.readLine()) != null) {
                firstExist = false;
                secondExist = false;
                firstIndex = -1;
                secondIndex = -1;
                String[] stArr = st.split(" ");
                Node start = new Node(stArr[0]);
                Node end = new Node(stArr[1]);
                for (int i = 0; i < graph.nodes.size(); i++) {

                    if (graph.nodes.get(i).name.equals(start.name)) {
                        firstExist = true;
                        firstIndex = i;
                    } else if (graph.nodes.get(i).name.equals(end.name)) {
                        secondExist = true;
                        secondIndex = i;
                    }
                }
                if (secondExist == true && firstExist == false) {
                    graph.nodes.get(secondIndex).addAction(graph.nodes.get(secondIndex), start, Integer.parseInt(stArr[2]));
                    start.addAction(start, graph.nodes.get(secondIndex), Integer.parseInt(stArr[2]));
                    graph.addNode(start);
                    graph.addEdge(new Edge(start,graph.nodes.get(secondIndex), Integer.parseInt(stArr[2])));
                } else if (firstExist == true && secondExist == false) {
                    graph.nodes.get(firstIndex).addAction(graph.nodes.get(firstIndex), end, Integer.parseInt(stArr[2]));
                    end.addAction(end, graph.nodes.get(firstIndex), Integer.parseInt(stArr[2]));
                    graph.addNode(end);
                    graph.addEdge(new Edge(graph.nodes.get(firstIndex),end, Integer.parseInt(stArr[2])));
                } else if (firstExist == true && secondExist == true) {
                    graph.nodes.get(firstIndex).addAction(graph.nodes.get(firstIndex), graph.nodes.get(secondIndex), Integer.parseInt(stArr[2]));
                    graph.nodes.get(secondIndex).addAction(graph.nodes.get(secondIndex), graph.nodes.get(firstIndex), Integer.parseInt(stArr[2]));
                    graph.addEdge(new Edge(graph.nodes.get(firstIndex),graph.nodes.get(secondIndex), Integer.parseInt(stArr[2])));
                } else if (firstExist == false && secondExist == false) {
                    start.addAction(start, end, Integer.parseInt(stArr[2]));
                    end.addAction(end, start, Integer.parseInt(stArr[2]));
                    graph.addNode(start);
                    graph.addNode(end);
                    graph.addEdge(new Edge(start,end, Integer.parseInt(stArr[2])));
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Graph getGraph() {
        return graph;
    }
}

