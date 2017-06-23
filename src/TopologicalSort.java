import java.util.HashSet;
import java.util.Set;

/**
 * Created by marne on 10/25/2016.
 */

//the first vertex in topological sort is always a vertex with indegree as 0
    //the idea here is to maintain a set with all visited vertices and a stack with those vertices for which all its adjacent vertices are visited
    //modify it so that it can detect cycles
class TVertex{
    public char label;
    public boolean wasVisited;

    public TVertex(char label){
        this.label = label;
        this.wasVisited = false;
    }
}

class TStack{
    int S_size = 20;
    int[] stack ;
    int top;

    public TStack(){
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

public class TopologicalSort {

    final int MAX_VERTS = 10;
    TVertex[] vertices ;
    int adjMat[][];
    int nVerts;

    public static TStack stack = new TStack(); //for adding tsort order
    public static TStack stack2 = new TStack(); // for maintaining dfs list
    public static Set<Integer> visitedLabels = new HashSet<Integer>();

    TopologicalSort(){
        vertices = new TVertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        for(int i=0;i<MAX_VERTS;i++){
            for(int j=0;j<MAX_VERTS;j++){
                adjMat[i][j]=0;
            }
        }
        nVerts = 0;
    }

    public void addToGraph(char label){
        TVertex vertex = new TVertex(label);
        vertices[nVerts] = vertex;
        nVerts++;
    }

    public void addEdge(int start,int end){
        adjMat[start][end] = 1;
    }

    public void displayVertex(int v){
       System.out.println(vertices[v].label);
    }

    public int getAdjLables(int v){
        for(int i=0;i<nVerts;i++){
            if(adjMat[v][i] == 1 && !visitedLabels.contains(i) && !vertices[i].wasVisited ){
                return i;
            }
        }
        return -1;
    }

    public void checkVertexVisited(){
        for(int i=0; i< nVerts;i++){
            if(vertices[i].wasVisited == true)
                continue;
            topSort(i);
        }


    }

    public void topSort(int v){

        visitedLabels.add(v);
        stack2.push(v);
        vertices[v].wasVisited = true;
        System.out.println("Pushed into dfs stack "+v);



        while(!stack2.isEmpty()){

            int v2 = getAdjLables(stack2.peek());
            if(v2 == -1){
                int currentVisit = stack2.pop();
                stack.push(currentVisit);
                System.out.println("Pushed into main stack" +currentVisit);
            }

            else {
                visitedLabels.add(v2);
                stack2.push(v2);
                System.out.println("Pushed into dfs stack "+v2);
                vertices[v2].wasVisited = true;
            }
        }






    }

    public static void main(String[] args){

        TopologicalSort ts = new TopologicalSort();

        ts.addToGraph('A');
        ts.addToGraph('B');
        ts.addToGraph('C');
        ts.addToGraph('D');
        ts.addToGraph('E');
        ts.addToGraph('F');
        ts.addToGraph('G');
        ts.addToGraph('H');

        ts.addEdge(0,2);
        ts.addEdge(1,2);
        ts.addEdge(1,3);
        ts.addEdge(2,4);
        ts.addEdge(4,7);
        ts.addEdge(4,5);
        ts.addEdge(5,6);

        ts.checkVertexVisited();

        System.out.println("The vertices in topological sort order ");
        System.out.println("The order is ");
        while (!stack.isEmpty()){

            System.out.print(ts.vertices[stack.pop()].label +" ");
        }







    }





}
