import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by marne on 2/15/2017.
 */


public class BreadthFirstSearch {
    static class Vertex{
        private int vertex;
        private boolean wasVisited;
        public Vertex(int vertex){
            this.vertex = vertex;
            this.wasVisited = false;
        }
    }

    static class Graph{//both adjacency matrix and list representations
        private int vertices;
        int[][] graphMatrix ;
        List<Integer>[] graphAdj;

        public Graph(int vertices){
            this.vertices = vertices;
            graphMatrix = new int[vertices][vertices];

            graphAdj = new LinkedList[vertices];
            for(int i=0;i<vertices;i++){
                graphAdj[i] = new LinkedList<>();
            }
        }

        public int size(){
            return vertices;
        }

        public void addEdge(int start, int end){
            if(start<0 || end>=vertices){
                System.out.println("out of range. cant add");
                return;
            }

            graphMatrix[start][end] = 1;
            graphAdj[start].add(end);


            return;
        }

        public void removeEdge(int start,int end){
            if(start<0 || end>=vertices){
                System.out.println("out of range. cant remove");
                return;
            }

            graphMatrix[start][end] = 0;
            graphAdj[start].remove(end);


            return;
        }


        public List<Integer> getAdjVertices(int vertex){
            List<Integer> adjList = graphAdj[vertex];
            return adjList;
        }


    }

    public void breadthFirstSearch(Graph graph,Integer start){//giving the graph and the position of the start index

        boolean[] wasVisited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        wasVisited[start] = true;

        while (!queue.isEmpty()){
            Integer temp = queue.poll();
            System.out.print(temp+" ");
            for (Integer vertex :
                    graph.getAdjVertices(temp)) {
                if(!wasVisited[vertex]) {
                    queue.add(vertex);
                    wasVisited[vertex] = true;
                }
            }
        }

    }

    public static void main(String[] args){
        Graph graph = new Graph(6);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(1,2);
        graph.addEdge(1,3);
        graph.addEdge(2,4);
        graph.addEdge(4,5);
        graph.addEdge(5,3);
        //graph.addEdge(4,5);

        BreadthFirstSearch bfs = new BreadthFirstSearch();
        bfs.breadthFirstSearch(graph,0);

        System.out.println();
        bfs.breadthFirstSearch(graph,2);
        System.out.println();

        System.out.println("BFS in undirected"); // edges will be repeated in the adjacency List

        Graph graphUn = new Graph(6);
        graphUn.addEdge(0,1);
        graphUn.addEdge(0,2);
        graphUn.addEdge(1,2);
        graphUn.addEdge(1,3);
        graphUn.addEdge(2,4);
        graphUn.addEdge(4,5);
        graphUn.addEdge(5,3);
        graphUn.addEdge(1,0);
        graphUn.addEdge(2,0);
        graphUn.addEdge(2,1);
        graphUn.addEdge(3,1);
        graphUn.addEdge(4,2);
        graphUn.addEdge(5,4);
        graphUn.addEdge(3,5);

        bfs.breadthFirstSearch(graphUn,2);
    }
}
