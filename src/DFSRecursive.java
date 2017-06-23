import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by marne on 10/23/2016.
 */


public class DFSRecursive {

    private final int MAX_VERTS = 5;
    private Vertex vertexList[]; // array to hold vertex objects
    private int adjMat[][]; //matrix to hold edge information
    private int nVerts; // numnrt of vertices added to the matrix
    StackX stack = new StackX();

    public DFSRecursive(){
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int i=0;i<MAX_VERTS;i++){
            for(int j=0;j<MAX_VERTS;j++){
                adjMat[i][j] = 0;
            }
        }//adjMat initialized
    }//end of Constructor

    public void addVertex(char lab){

        Vertex v = new Vertex(lab);
        vertexList[nVerts++] = v;

    }// adding a vertex to the array of vertices

    public void addEdge(int start, int end) {       //source and destination of the edge as parameters

        adjMat[start][end]=1;
        adjMat[end][start]= 1;

    }//reverse order too since it is an undirected graph

    public void displayVertices(int v){
        System.out.println(vertexList[v].lablel);

    }

    public void recursiveDFS(int v){

         // Mark all nodes as unvisited.
        //recursiveDFS( 0 );

        if(vertexList[v].wasVisited == true)
            return;
        vertexList[v].wasVisited = true;
        for(int i=0;i<nVerts;i++){
            if(adjMat[v][i] == 1){
                displayVertices(i);
                recursiveDFS(i);
            }
        }
    }


    public static void main(String[] args){
        DFSRecursive graph = new DFSRecursive();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the vertex lable");

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        graph.addEdge(3,4);

        System.out.println("Displaying the vertex list");
        for(int i=0;i< graph.nVerts;i++){
            graph.displayVertices(i);
        }

        for (int i = 0; i < graph.nVerts; i++)
            graph.vertexList[i].wasVisited = false;
        System.out.println("Vists :");
        graph.recursiveDFS(0);

    }


}
