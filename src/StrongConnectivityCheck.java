import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by marne on 10/26/2016.
 */

class StackC{
    int S_size = 20;
    int[] stack ;
    int top;

    public StackC(){
        stack = new int[S_size];
        top = -1;
    }
    public void push(int data){
        stack[++top] = data;
    }

    public int pop(){
        return stack[top--];
    }
    public int peek(){
        return stack[top];
    }
    public boolean isEmpty(){
        return (top==-1);
    }


}

public class StrongConnectivityCheck {

    private int nVerts;
    private LinkedList<Integer> adj[];
    private boolean[] visited;
    StackC stack = new StackC();

    public StrongConnectivityCheck(int v){
        nVerts = v;
        adj = new LinkedList[v];
        visited = new boolean[nVerts];

        for(int i =0;i<v;i++){
            adj[i] = new LinkedList<Integer>();
        }

    }
    public void addEdge(int start,int end){
        adj[start].add(end);
    }

    public int getAdjVertex(int v){
        for(int i=0;i<nVerts;i++){
            if(adj[v].get(i) != -1 && !visited[i] )
                return i;
        }
        return -1;
    }

    public void dfs(int v){
        visited[v] = true;
        stack.push(v);
        System.out.print(adj[v]+" ");

        while(!stack.isEmpty()){

            int v2 = getAdjVertex(stack.peek());
            if(v2 == -1)
                stack.pop();
            else{
                visited[v2] = true;
                stack.push(v2);
                System.out.print(adj[v2]+" ");

            }


        }
        System.out.println();



    }

    public StrongConnectivityCheck getTranspose(){
        StrongConnectivityCheck scc = new StrongConnectivityCheck(nVerts);
        for(int v=0;v<nVerts;v++){
            Iterator<Integer> iterator = adj[v].iterator();
            while(iterator.hasNext()){
                scc.adj[iterator.next()].add(v);
            }
        }
        return scc;
    }

    public static void main(String[] args){


        StrongConnectivityCheck graph = new StrongConnectivityCheck(5);

        graph.addEdge(1,0);
        graph.addEdge(0,2);
        graph.addEdge(2,1);
        graph.addEdge(0,3);
        graph.addEdge(3,4);

        System.out.println("These are the strongly connected components");


    }
}
