import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by marne on 2/16/2017.
 */
public class KrushkalMinimalSpanningTree {//This is using DFS as it is unweighted graph - each edge weight can be counted as 1

    static class Vertex{
        public int vertexId;
        public boolean wasVisited;

        Vertex(int vertexId){
            this.vertexId = vertexId;
            this.wasVisited = false;
        }
    }

    static class Graph{
        private final int MAX_VERTS = 20;
        private Vertex[] vertics;
        private int adjMat[][];
        private int nVerts;
        private Stack<Integer> stack;

        public Graph(){
            vertics = new Vertex[MAX_VERTS];
            adjMat = new int[MAX_VERTS][MAX_VERTS];
            nVerts = 0;
            for (int i=0;i<MAX_VERTS;i++){
                for(int j=0;j<MAX_VERTS;j++){
                    adjMat[i][j] = 0;
                }
            }
            stack = new Stack<>();
        }

        public void addVertex(int vertexid){
            vertics[nVerts++] = new Vertex(vertexid);

        }

        public void addEdge(int start,int end){
            adjMat[start][end] = 1;
            adjMat[end][start] = 1;
        }

        public void displayVertex(int v){
            System.out.println(vertics[v].vertexId);
        }

        public int getAdjUnvisitedVertex(int v){
            for(int i=0;i<nVerts;i++){
                if(adjMat[v][i]==1 && vertics[i].wasVisited == false){
                    return i;
                }
            }
            return -1;
        }

        public void minimalSpanningTree(){

            vertics[0].wasVisited = true;
            stack.push(0);
            displayVertex(0);

            while (!stack.isEmpty()){
                Integer popped = stack.peek();

                int v = getAdjUnvisitedVertex(popped);
                if(v==-1)
                    stack.pop();
                else{
                    if(!vertics[v].wasVisited) {
                        vertics[v].wasVisited = true;
                        stack.push(v);
                        //displayVertex(popped);
                        displayVertex(v);
                        System.out.print(" ");
                    }
                }

            }
        }

    }

    public static void main(String[] args){

        Graph graph = new Graph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(0,1);
        graph.addEdge(0,2);

        graph.addEdge(0, 3); // AD
        graph.addEdge(0, 4); // AE
        graph.addEdge(1, 2); // BC
        graph.addEdge(1, 3); // BD
        graph.addEdge(1, 4); // BE
        graph.addEdge(2, 3); // CD
        graph.addEdge(2, 4); // CE
        graph.addEdge(3, 4);

        System.out.println("Minimum spanning tree");
        KrushkalMinimalSpanningTree minimalSpanningTree = new KrushkalMinimalSpanningTree();
        graph.minimalSpanningTree();


    }
}
