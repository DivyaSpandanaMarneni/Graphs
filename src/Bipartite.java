import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.*;

/**
 * Created by marne on 2/17/2017.
 */

//To check if a given graph is bipartite
public class Bipartite {

    class Vertex{
        public int label;
        public boolean isVisited;
        public char color;
        public Vertex(int label){
            this.label = label;
            this.isVisited = false;
            color='W';
        }
    }

    class Graph{
        public final int MAX_VERTICES = 6;
        public Vertex[] vertices;
        public int[][] adjMat ;
        public int nVerts;
        public Graph(){
            vertices = new Vertex[MAX_VERTICES];
            adjMat = new int[MAX_VERTICES][MAX_VERTICES];
            for(int i=0;i<MAX_VERTICES;i++){
                for(int j=0;j<MAX_VERTICES;j++){
                    adjMat[i][j] = 0;
                }
            }

            nVerts = 0;
        }

        public void addVertex(int vertex){
            vertices[nVerts++] = new Vertex(vertex);
        }

        public void addEdge(int start,int end){
            adjMat[start][end] = 1;
            adjMat[end][start] = 1;
        }

        public int size(){
            return nVerts;
        }

        public int[] getAdjVertices(int vertexId){
            int[] adjArray = adjMat[vertexId];
            return adjArray;
        }
    }

    public boolean checkBipartite(Graph graph){//using colors blue and red
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        graph.vertices[0].color = 'r';
        Set<Integer> red = new HashSet<>();
        Set<Integer> blue = new HashSet<>();
        red.add(0);


        while (!queue.isEmpty()) {
            Integer out = queue.poll();
            char currentColor = graph.vertices[out].color;
            graph.vertices[out].isVisited = true;
            int[] adjVertices = graph.getAdjVertices(out);
            for (int i = 0; i < adjVertices.length; i++) {
                if (adjVertices[i] == 1 && !graph.vertices[i].isVisited) {
                    queue.add(i);
                    if (currentColor == 'r') {

                        graph.vertices[i].color = 'b';
                        //graph.vertices[adjVertices[i]].color = 'b';
                        blue.add(i);
                    } else {
                        graph.vertices[i].color = 'r';
                        //graph.vertices[adjVertices[i]].color = 'r';
                        red.add(i);
                    }
                }

            }
        }

            System.out.println("red set size"+red.size());
            System.out.println("blue set size"+blue.size());

            for(int i=0;i<graph.nVerts;i++){
                char color = graph.vertices[i].color;
                int[] adjVert = graph.getAdjVertices(i);
                for(int j=0;j<adjVert.length;j++) {
                  if(graph.adjMat[i][j]==1 &&  graph.vertices[j].color == color)
                      return false;
                }
            }




        //checking the bipartite

        return true;
    }

    public static void main(String[] args){
        Graph graph = (new Bipartite()).new Graph();

        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(0,1);
        graph.addEdge(0,3);
        graph.addEdge(2,1);
        graph.addEdge(2,3);
        graph.addEdge(1,3);

        Bipartite checkBipatite = new Bipartite();
        System.out.println("The graph is bipartite: "+checkBipatite.checkBipartite(graph));
    }
}
