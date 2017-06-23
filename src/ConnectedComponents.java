import java.util.ArrayList;

/**
 * Created by marne on 10/25/2016.
 */
class CVertex{

    public char label;
    public boolean wasVisited;

    public CVertex(char label){
        this.label = label;
        this.wasVisited = false;
    }

}

class CStack{
    int S_size = 20;
    int[] stack ;
    int top;

    public CStack(){
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


public class ConnectedComponents {

    int nVerts;
    int[][] adjMat;
    static final int MAX_VERTS = 20 ;
    CVertex[] vertices ;
    ArrayList<CVertex> connVertices = new ArrayList<CVertex>(); //for adding the connected components and returning it

    public static CStack stack = new CStack();

    public ConnectedComponents(){
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for(int i=0;i<MAX_VERTS;i++){
            for(int j=0;j<MAX_VERTS;j++){
                adjMat[i][j] = 0;
            }
        }
        vertices = new CVertex[MAX_VERTS];
    }

    public void addToGraph(char label){
        CVertex vertex = new CVertex(label);
        vertices[nVerts++] = vertex;

    }

    public void addEdge(int start,int end){
        adjMat[start][end] = 1;
    }

    public int getAdjVertex(int v){
        for(int i=0;i<nVerts;i++){
            if(adjMat[v][i] ==1 && !vertices[i].wasVisited ){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<CVertex> connectedComponents(int startId){//it should update the adjacency matrix
        connVertices.clear();

        for(int i=0;i<nVerts;i++){
            vertices[i].wasVisited = false;
        }

        vertices[startId].wasVisited = true;
        stack.push(startId);
        connVertices.add(vertices[startId]);

        while(!stack.isEmpty()){

            int v2 = getAdjVertex(stack.peek());
            if(v2 == -1) {
                stack.pop();
            }
            else{
                stack.push(v2);
                vertices[v2].wasVisited = true;
                connVertices.add(vertices[v2]);
            }
        }

        return connVertices;
    }

    public static void main(String[] args){

        ConnectedComponents cc = new ConnectedComponents();

        cc.addToGraph('A');
        cc.addToGraph('B');
        cc.addToGraph('C');
        cc.addToGraph('D');
        cc.addToGraph('E');

        cc.addEdge(1,0);
        cc.addEdge(1,4);
        cc.addEdge(3,4);
        cc.addEdge(4,2);
        cc.addEdge(0,2);

        ArrayList<CVertex> acl = new ArrayList<CVertex>();
        for(int i=0;i< cc.nVerts;i++){
            acl.clear();
           acl = cc.connectedComponents(i);
            for (CVertex ver :
                    acl) {
                System.out.print(ver.label+" ");
            }
            System.out.println();
            //System.out.println();
        }



    }




}
