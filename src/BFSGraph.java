import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by marne on 10/23/2016.
 */
class VertexBFS
{
    public char label; // label (e.g. ‘A’)
    public boolean wasVisited;
    // -------------------------------------------------------------
    public VertexBFS(char lab) // constructor
    {
        label = lab;
        wasVisited = false;
    }
// -------------------------------------------------------------
} // end class Vertex

class QueueOwn{
    private final int size = 20;
    private int[] qArray;
    private int front;
    private int rear;

    public QueueOwn(){
        qArray = new int[size];
        front=0;
        rear=-1;
    }

    public void insert(int data){ // we insert at rear and delete at front
        if(rear == size-1)
            rear = -1;
        qArray[++rear] = data;

    }

    public int remove(){
       int temp = qArray[front++];
        if(front == size)
            front =0;
        return temp;
    }

    public boolean isEmpty(){
        return (rear+1 == front ||  front + size-1 == rear );

    }
}

//class Vertex same as in Graph program



public class BFSGraph {

    private final int MAX_VERTS = 5;
    private VertexBFS vertexList[] ;
    private int adjMat[][];
    private int nVerts;
    private QueueOwn queue;

    public BFSGraph(){
        vertexList = new VertexBFS[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        for(int i=0;i<MAX_VERTS;i++){
            for(int j=0; j<MAX_VERTS;j++)
                adjMat[i][j] = 0;
        }
        nVerts = 0;
        queue = new QueueOwn();
    }// end of constructor

    public void addVertex(char lab){
        VertexBFS v = new VertexBFS(lab);
        vertexList[nVerts++] = v;
        //nVerts ++;
    }

    public void addEdge(int start, int end){
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v){
        System.out.println(vertexList[v].label);
    }

    public int getAdjUnvisitedVertex(int v){
        for(int i=0;i<nVerts;i++){
            if(adjMat[v][i] == 1 && !vertexList[i].wasVisited )
                return i;
        }
        return -1;
    }//get the adjacent vertices list

    public void bfs(){


        vertexList[0].wasVisited = true;
        displayVertex(0);
        queue.insert(0);

        int v2;

        while(!queue.isEmpty()){

            int v1 = queue.remove(); // v1 should hold 0 initially
            while((v2 = getAdjUnvisitedVertex(v1)) != -1){
                vertexList[v2].wasVisited = true;
                displayVertex(v2);
                queue.insert(v2);
            }//end while



        }//end when queue is empty

        for(int i=0;i<nVerts;i++){
            vertexList[i].wasVisited = false;
        }//end dfs

    }

    public static void main(String[] args){

        BFSGraph graph = new BFSGraph();

        //Graph graph = new Graph();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the vertex lable");

        graph.addVertex('A');
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');

        //System.out.println("Enter the edge information");
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(0,3);
        graph.addEdge(3,4);

        System.out.println("Displaying the vertex list");
        for(int i=0;i< graph.nVerts;i++){
            graph.displayVertex(i);
        }

        System.out.println("Vists :");
        graph.bfs();


    }

}
