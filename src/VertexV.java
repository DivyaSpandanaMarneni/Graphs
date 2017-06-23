/**
 * Created by marne on 2/15/2017.
 */
public class VertexV {

    public int vertex;
    public boolean visited;

    public VertexV(){

    }

    public VertexV(int vertex){
        this.vertex = vertex;
        this.visited = false;
    }


    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }



}


