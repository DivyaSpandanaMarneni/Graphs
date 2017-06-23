import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by marne on 10/28/2016.
 */



class QueueBFS{
    public final int max_size = 20;
    int[] queue;
    int front;
    int rear;

    QueueBFS(){
        queue = new int[max_size];
        front = 0;
        rear = -1;
    }

    public void insert(int data){
        if(rear == max_size -1)
            rear = -1;
        queue[++rear] = data;
    }
    public int remove(){
        int temp = queue[front++];
        if(front == max_size)
            front = 0;
        return temp;
    }

    public boolean isEmpty(){
        if(rear+1 == front || front+max_size-1 == rear)
            return true;
        else
            return false;
    }


}

public class BFSWithAdjList {

    private int nVerts;
    private final int MAX_VERTS = 10;
    private LinkedList<Integer> adj[];
    private boolean[] wasVisited;
    QueueBFS queue = new QueueBFS();

    public BFSWithAdjList(int v){
        this.nVerts = v;
        adj = new LinkedList[v];
        wasVisited = new boolean[nVerts];

        for(int i=0;i<nVerts;i++){
            adj[i] = new LinkedList();

        }
    }



    public void addEdge(int start,int end){
        adj[start].add(end);
    }
    public void displayVertex(int v){
        System.out.print(v+" ");
    }

    public void bfs(){

        queue.insert(0);
        displayVertex(0);

        while(!queue.isEmpty()){
            int v = queue.remove();
            Iterator<Integer> iterator = adj[v].iterator();
            while(iterator.hasNext()){
                int currAdj = iterator.next();
                if(!wasVisited[currAdj]) {
                    wasVisited[currAdj] = true;
                    displayVertex(currAdj);
                    queue.insert(currAdj);

                }

            }
        }


    }
    public static void main(String[] args){
        BFSWithAdjList bfsGraph = new BFSWithAdjList(5);
        bfsGraph.addEdge(0,1);
        bfsGraph.addEdge(1,2);
        bfsGraph.addEdge(0,3);
        bfsGraph.addEdge(3,4);

        System.out.println("Printing the bfs traversal order");

        bfsGraph.bfs();


    }

}
